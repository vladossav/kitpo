import types.Int;
import types.UserType;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        UserType[] arr1 = {
                new Int(15),
                new Int(10), new Int(26),
                null, new Int(12), new Int(22), new Int(28)
        };
        UserType[] arr2 = {
                new Int(15),
                new Int(5), new Int(16),
                new Int(3), new Int(12), null, new Int(20),
                null,null,new Int(10), new Int(13), null,null, new Int(18), new Int(23),
                null,null,null,null,new Int(6),null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,new Int(7),
                null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null
        };

        UserType[] arr3 = {
                new Int(12),
                null, new Int(16),
                null, null, null, new Int(20),
                null,null,null,null, null,null, new Int(18), new Int(23),
                null,null,null,null,null,null,null,null,null,null,null,null, new Int(17),null,null,new Int(26)
        };

        UserType[] arr4 = {
                new Int(42),
                new Int(30), null,
                new Int(21), null, null, null,
                new Int(15),null,null,null, null,null, null, null,
                new Int(2),new Int(20),null,null,null,null,null,null,null,null,null,null,null,null,null,null
        };

        UserType[] arr5 = {
                new Int(12),
                null, new Int(16),
                null, null, null, new Int(20),
                null,null,null,null, null,null, null, new Int(23),
                null,null,null,null,null,null,null,null,null,null,null,null, null,null,null,new Int(26)
        };
    /*    for (int i = 0; i < arr.length; i++) {
            arr[i] = factory.getBuilderByName("Int");
        }*/
        /*for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].readValue().toString() + " ");
        }*/

        BinaryTreeAsArray tree = new BinaryTreeAsArray(arr2);

        Serialization.saveToFile(tree,"tree_arr2.txt", UserFactory.getTypeNameList().get(0));

        BinaryTreeAsArray treeLoaded = Serialization.loadFromFile("tree_arr2.txt");
        treeLoaded.show();
       /* //tree.insertByIndex(0, new Int(1));
        tree.forEach(System.out::print);
        tree.show();
        //tree.balance();
        System.out.println(tree.searchNextAfterRemoving(1));


        tree.deleteByIndex(1);
        tree.show();*/

    }


}
