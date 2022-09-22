import types.UserType;
import java.util.Arrays;

public class BinaryTreeAsArray {
    private UserType[] arr;
    int size;
    int level;

    BinaryTreeAsArray() {
        size = 1;
        level = 0;
        arr = new UserType[size];
    }

    BinaryTreeAsArray(UserType[] array) {
        level = calculateLevelFromSize(array.length);
        size = calculateSizeFromLevel(level);
        arr = Arrays.copyOf(array, size);
    }

    int sizer(int n) {
        if (n>=size || arr[n]==null) return 0;
        return 1+sizer(2*n)+sizer(2*n+1);
    }

    void insertByIndex(int n, UserType element) {
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


    int calculateLevelFromSize(int sz) {
        int sum = 0;
        int level = -1;
        while (sum < sz) {
            level++;
            sum += Math.pow(2, level);
        }
        return level;
    }

    int calculateSizeFromLevel(int level) {
        int sz = 0;
        for (int i = 0; i <= level; i++) {
            sz += Math.pow(2, i);
        }
        return sz;
    }

    int calculateSizeOfLevel(int level) {
        return (int) Math.pow(2, level);
    }

    boolean hasParent(int pos) {
        if (pos%2 == 0 && arr[pos/2] != null) return true;
        if (pos%2 != 0 && arr[(pos-1)/2] != null) return true;
        return false;
    }


}
