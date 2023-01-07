package java_module;

import java_module.types.UserType;

import java.util.ArrayList;


public class BinaryTreeAsArray implements BinaryTree {
    private ArrayList<UserType> arr;
    private int size;
    private int level;

    BinaryTreeAsArray() {
        init();
    }

    public void init() {
        size = 1;
        level = 0;
        arr = new ArrayList<>();
        for (int i = 0; i< size; i++) {
            arr.add(null);
        }
    }

    BinaryTreeAsArray(UserType[] array) {
        level = calculateLevelFromSize(array.length);
        size = calculateSizeFromLevel(level);
        for (int i=size/2; i < size; i++)
            arr.add(null);
    }

    int sizerLSS(int n) {
        if (n>=size || arr.get(n)==null) return 0;
        return 1 + sizerLSS(2*n+1)+sizerLSS(2*n+2);
    }

    public UserType getByIndex(int m, int n) {
        if (m < 0 || m >= size || m >= sizerLSS(n)) return null;
        int ll = sizerLSS(2 * n + 1);
        if (m < ll) return getByIndex(m, 2 * n + 1);
        m -= ll;
        if (m-- == 0) return arr.get(n);
        return getByIndex(m, 2 * n + 2);
    }

    public void insertByIndex(int n, UserType element) {
        if (element == null) return;

        if (n >= size) {
            level++;
            int oldSize = size;
            size += calculateSizeOfLevel(level);
            for (int i = oldSize+1; i<= size; i++)
                arr.add(null);
        }

        if (arr.get(n) == null) {
            arr.set(n, element);
            return;
        }

        if (element.getTypeComparator().compare(element, arr.get(n)) > 0)
            insertByIndex(2*n + 2, element); //право
        else
            insertByIndex(2*n + 1, element); //влево
    }

    int getSize() {return size;}

    //балансировка
    void balance(ArrayList<UserType> array, int a, int b) {
        if (a>b) return;
        int m = (a + b) / 2;
        insertByIndex(0, array.get(m));
        balance(array, a, m-1);
        balance(array, m+1, b);
    }

    int sizer(int n, ArrayList<UserType> arrayList) {//подсчет потомков
        if (n>=size || arr.get(n)==null) return 0;
        arrayList.add(arr.get(n));
        return 1 + sizer(2*n+1,arrayList)+sizer(2*n+2,arrayList);
    }

    int getSumPathLength(int n, int i) {
        if (n>=size || arr.get(n) == null) {
            return 0;
        }
        return i + getSumPathLength(2*n+1, i+1) + getSumPathLength(2*n+2, i+1);
    }

    int heigh(int n)
    {
        if (n>= size || arr.get(n) == null) {
            return 0;
        }
        else if (heigh(2*n+1) > heigh(2*n+2))
            return heigh(2*n+1) + 1;
        else
            return heigh(2*n+2) + 1;
    }

    public void balance() {
        ArrayList<UserType> list = new ArrayList<>();
        int  size1 = sizer(0,list);
        list.sort(list.get(0).getTypeComparator());
        init();
        balance(list, 0, size1-1);
    }

    //удаление
    public void deleteByIndex(int index) {
        if (arr.get(index) == null) return;
        delete(0,arr.get(index));
    }

    UserType delete(int root, UserType element) {
        if (2*root+2>=size) return null;
        if (arr.get(root) == null) return arr.get(root);


        if (element.getTypeComparator().compare(element, arr.get(root)) < 0) { //если элемент < зн. узла = к левому потомку
            arr.set(2*root + 1,delete(2*root + 1, element));
            return arr.get(root);
        }

        if (element.getTypeComparator().compare(element, arr.get(root)) > 0) {//если элемент > зн. узла = к правому потомку
            arr.set(2*root + 2,delete(2*root + 2, element));
            return arr.get(root);
        }

        if (arr.get(2*root+1) == null) {
            int temp = 2*root+2;
            arr.set(root, null);
            return arr.get(temp);
        }
        if (arr.get(2*root+2) == null) {
            int temp = 2*root+1;
            arr.set(root, null);
            return arr.get(temp);
        }

        arr.set(2*root+2, deleteHelper(2*root+2,root));
        return arr.get(root);
    }

    UserType deleteHelper(int root, int root0) {
        if (2*root+2<size) {
            if (arr.get(2*root+1) != null) {
                arr.set(2*root+1,deleteHelper(2*root+1, root0));
                return arr.get(root);
            }
        }

        arr.set(root0, arr.get(root));
        arr.set(root, null);
        if (2*root+2<size)  return arr.get(2*root + 2);
        else return null;
    }

    public void show() {
        for (int i = 0, cnt = 0, lvl = 0; i < size; i++) {
            if (i == cnt) {
                cnt += Math.pow(2, lvl);
                lvl++;
                System.out.println();
            }

            if (arr.get(i) == null) {
                System.out.print("N ");
                continue;
            }
            System.out.print(arr.get(i).toString() + " ");
        }
    }

    public String toString() {
        String str ="";
        for (int i = 0, cnt = 0, lvl = 0; i < size; i++) {
            if (i == cnt) {
                cnt += Math.pow(2, lvl);
                lvl++;
                str+="\n";
            }

            if (arr.get(i) == null) {
                str+="N ";
                continue;
            }
            str += (arr.get(i).toString() + " ");
        }
        return str;
    }

    private int calculateLevelFromSize(int sz) {
        int sum = 0;
        int level = -1;
        while (sum < sz) {
            level++;
            sum += Math.pow(2, level);
        }
        return level;
    }

    private int calculateSizeFromLevel(int level) {
        int sz = 0;
        for (int i = 0; i <= level; i++) {
            sz += Math.pow(2, i);
        }
        return sz;
    }

    private int calculateSizeOfLevel(int level) {
        return (int) Math.pow(2, level);
    }

    public void forEach(DoWith action) {
        for (int i=0; i<arr.size();i++) {
            String str;
            if (arr.get(i) == null) str = "null ";
            else str = arr.get(i).toString() + " ";
            action.doWith(str);
        }
    }

}
