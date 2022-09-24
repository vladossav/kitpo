import types.Int;
import types.UserType;

public class Main {
    public static void main(String[] args) {
        UserFactory factory = new UserFactory();
        UserType[] arr = {
                new Int(15),
                new Int(10), new Int(26),
                null, new Int(12), new Int(22), new Int(28)
        };
    /*    for (int i = 0; i < arr.length; i++) {
            arr[i] = factory.getBuilderByName("Int");
        }*/
        /*for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].readValue().toString() + " ");
        }*/
        BinaryTreeAsArray tree2 = new BinaryTreeAsArray();
        tree2.insertByIndex(0, new Int(-2));
        tree2.insertByIndex(0, new Int(123));
        tree2.show();

        BinaryTreeAsArray tree = new BinaryTreeAsArray(arr);
        tree.insertByIndex(0, new Int(-2));
        tree.insertByIndex(4, new Int(40));
        tree.show();
        tree.balance();
        tree.show();

    }


}
