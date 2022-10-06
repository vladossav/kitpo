import types.UserType;
import java.util.Arrays;


public class BinaryTreeAsArray {
    private UserType[] arr;
    private int size; //размер массива
    private int level;

    BinaryTreeAsArray() {
        init();
    }

    void init() {
        size = 1;
        level = 0;
        arr = new UserType[size];
    }

    BinaryTreeAsArray(UserType[] array) {
        level = calculateLevelFromSize(array.length);
        size = calculateSizeFromLevel(level);
        arr = Arrays.copyOf(array, size);
    }


    void insertByIndex(int n, UserType element) {
        if (element == null) return;

        if (n > size) {
            level++;
            size += calculateSizeOfLevel(level);
            arr = Arrays.copyOf(arr, size);
        }

        if (arr[n] == null) {
            arr[n] = element;
            return;
        }

        if (element.getTypeComparator().compare(element, arr[n]) > 0)
            insertByIndex(2*n + 2, element); //право
        else
            insertByIndex(2*n + 1, element); //влево
    }

    UserType getByIndex(int n) {
        return arr[n];
    }

    //балансировка
    void balance(UserType[] array, int a, int b) {
        if (a>b) return;
        int m = (a + b) / 2;
        insertByIndex(0, array[m]);
        balance(array, a, m-1);
        balance(array, m+1, b);
    }

    void set(UserType[] array, int n, int ln) {
        if (n>=size || arr[n]==null) return;
        set(array,2*n + 1,ln);
        array[ln++]=arr[n];
        set(array,2*n + 2,ln);
    }


    int sizer(int n) {//подсчет потомков
        if (n>=size || arr[n]==null) return 0;
        return 1 + sizer(2*n)+sizer(2*n+1);
    }

    void balance() {
        int  size1 = sizer(1);
        UserType[] tmp = Arrays.copyOf(arr, arr.length);
        init();
        balance(tmp, 0, size1);
    }

    //удаление
    void deleteByIndex(int index) {
        if (arr[index] == null) return;
        System.out.print("\n\ndeleted "+arr[index].readValue().toString() + " at "+index);
        delete(0,arr[index]);
    }

    int min(int rootIndex) {
        int ind = rootIndex;
        while (hasLeftChildren(ind)) {
            ind = 2*ind+1;
        }
        return ind;
    }

    int searchNextAfterRemoving(int ind) {
        if (hasRightChildren(ind)) {
            return min(2*ind+2);
        }
        int parInd = getParentIndex(ind);
        while (hasLeftChildren(parInd) && (arr[ind] == arr[2*parInd+2])) {
            arr[ind] = arr[parInd];
            parInd = getParentIndex(parInd);
        }
        return parInd;
    }


    int getParentIndex(int index) {
        if (index%2 == 0) return (index-2)/2;
         else return (index-1)/2;
    }


    UserType delete(int root, UserType element) {
        if (2*root+2>=size) return null;
        if (arr[root] == null) return arr[root];


        if (element.getTypeComparator().compare(element, arr[root]) < 0) { //если элемент < зн. узла = к левому потомку
            arr[2*root + 1] = delete(2*root + 1, element);
            return arr[root];
        }

        if (element.getTypeComparator().compare(element, arr[root]) > 0) {//если элемент > зн. узла = к правому потомку
            arr[2 * root + 2] = delete(2 * root + 2, element);
            return arr[root];
        }
        // если element найден

        if (arr[2*root+1] == null) {
            int temp = 2*root+2;
            arr[root] = null;
            return arr[temp];
        }
        if (arr[2*root+2] == null) {
            int temp = 2*root+1;
            arr[root] = null;
            return arr[temp];
        }

        arr[2*root+2] = deleteHelper(2*root+2,root); //если есть оба потомка
        return arr[root];
    }

    UserType deleteHelper(int root, int root0) {
        //if (2*root+2>=size) return null;
        if (2*root+2<size) {
            if (arr[2*root+1] != null) {
                arr[2*root+1] = deleteHelper(2*root+1, root0);
                return arr[root];
            }
        }

        arr[root0] = arr[root];
        arr[root] = null;
        //int temp = 2*root + 2;
       // arr[root] = null;
        if (2*root+2<size)  return arr[2*root + 2];
        else return null;
    }

    boolean hasRightChildren(int index) {
        if (2*index+2>=size) return false;
        return arr[2 * index + 2] != null;
    }

    boolean hasLeftChildren(int index) {
        if (2*index+2>=size) return false;
        return arr[2 * index + 1] != null;
    }

    void show() {
        System.out.println();
        for (int i = 0, cnt = 0, lvl = 0; i < size; i++) {
            if (i == cnt) {
                cnt += Math.pow(2, lvl);
                lvl++;
                System.out.println();
            }

            if (arr[i] == null) {
                System.out.print("N ");
                continue;
            }
            System.out.print(arr[i].readValue() + " ");
        }
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

    private boolean hasParent(int pos) {
        if (pos%2 == 0 && arr[pos/2] != null) return true;
        if (pos%2 != 0 && arr[(pos-1)/2] != null) return true;
        return false;
    }

    public void forEach(DoWith action) {
        for (int i=0; i<arr.length;i++) {
            String str;
            if (arr[i] == null) str = "null ";
            else str = arr[i].readValue().toString() + " ";
            action.doWith(str);
        }
    }

}
