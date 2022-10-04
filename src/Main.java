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
        UserType[] arr2 = {
                new Int(15),
                new Int(5), new Int(16),
                new Int(3), new Int(12), null, new Int(20),
                null,null,new Int(10), new Int(13), null,null, new Int(18), new Int(23),
                null,null,null,null,new Int(6),null,null,null,null,null,null,null
        };

        UserType[] arr3 = {
                new Int(12),
                null, new Int(16),
                null, null, null, new Int(20),
                null,null,null,null, null,null, null, new Int(23),
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,new Int(26)
        };

        UserType[] arr4 = {
                new Int(42),
                new Int(30), null,
                new Int(21), null, null, null,
                new Int(15),null,null,null, null,null, null, null,
                new Int(2),null,null,null,null,null,null,null,null,null,null,null,null,null,null,null
        };
    /*    for (int i = 0; i < arr.length; i++) {
            arr[i] = factory.getBuilderByName("Int");
        }*/
        /*for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].readValue().toString() + " ");
        }*/

        BinaryTreeAsArray tree = new BinaryTreeAsArray(arr2);

        //tree.insertByIndex(4, new Int(40));
        tree.show();
        //tree.balance();
        tree.deleteByIndex(4);
        tree.show();

    }


}
