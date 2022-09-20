import types.UserType;


public class BinaryTreeAsArray {
    private UserType[] arr;
    int size;

    BinaryTreeAsArray() {
        size = 10;
        arr = new UserType[size];
        int f = arr.length;

    }

    void add(UserType element) {

    }

    void show() {
        for (UserType element : arr) {
            System.out.println(element.readValue());
        }
    }


}
