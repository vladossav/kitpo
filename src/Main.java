import types.Int;
import types.PropFract;
import types.UserType;

public class Main {
    public static void main(String[] args) throws Exception {
        testInt();
        testProperFraction();
    }

    static void testInt() throws Exception {
        System.out.println("\tTEST INT");
        UserType[] arr1 = {
                new Int(15),
                new Int(10), new Int(26),
                null, new Int(12), new Int(22), new Int(28)
        };

        BinaryTreeAsArray tree = new BinaryTreeAsArray(arr1);

        tree.forEach(System.out::print);
        System.out.print("\n\n\tinit tree");
        tree.show();

        int value1 = 20;
        System.out.print("\n\n\tinsert "+ value1);
        tree.insertByIndex(0,new Int(value1));
        tree.show();

        int value2 = -23;
        System.out.print("\n\n\tinsert "+ value2);
        tree.insertByIndex(0,new Int(value2));
        tree.show();

        int value3 = 18;
        System.out.print("\n\n\tinsert "+ value3);
        tree.insertByIndex(0,new Int(value3));
        tree.show();

        int index1 = 4;
        System.out.print("\n\n\tdeleteByIndex "+index1);
        tree.deleteByIndex(index1);
        tree.show();

        int index2 = 0;
        System.out.print("\n\n\tdeleteByIndex "+index2);
        tree.deleteByIndex(index2);
        tree.show();

        int index3 = 2;
        System.out.print("\n\n\tdeleteByIndex "+index3);
        tree.deleteByIndex(index3);
        tree.show();

        System.out.print("\n\n\tSerialization: saving");
        Serialization.saveToFile(tree,"test01_int.txt", UserFactory.getTypeNameList().get(0));
        System.out.print("\tSerialization: loading");
        BinaryTreeAsArray newTree = Serialization.loadFile("test01_int.txt");

        newTree.forEach(System.out::print);

        System.out.println("\n\tEND OF TEST INT");
    }

    static void testProperFraction() throws Exception {
        System.out.println("------------------------");
        System.out.println("\tTEST PROPER FRACTION");
        UserType[] arr1 = {
                new PropFract(15,10,14),
                new PropFract(10,5,7), new PropFract(26,12,23),
                null, new PropFract(12,4,32), new PropFract(22,1,2), new PropFract(35,1,23)
        };

        BinaryTreeAsArray tree = new BinaryTreeAsArray(arr1);

        tree.forEach(System.out::print);
        System.out.print("\n\n\tinit tree");
        tree.show();



        int[] value2 = {-2,2,3};
        System.out.print("\n\n\tinsert "+ value2[0]+"'"+value2[1]+"/"+value2[2]);
        tree.insertByIndex(0,new PropFract(value2[0],value2[1],value2[2]));
        tree.show();

        int[] value1 = {0,3,4};
        System.out.print("\n\n\tinsert "+ value1[0]+"'"+value1[1]+"/"+value1[2]);
        tree.insertByIndex(0,new PropFract(value1[0],value1[1],value1[2]));
        tree.show();

        int[] value3 = {33,1,3};
        System.out.print("\n\n\tinsert "+ value3[0]+"'"+value3[1]+"/"+value3[2]);
        tree.insertByIndex(0,new PropFract(value3[0],value3[1],value3[2]));
        tree.show();

        int index1 = 4;
        System.out.print("\n\n\tdeleteByIndex "+index1);
        tree.deleteByIndex(index1);
        tree.show();

        int index2 = 0;
        System.out.print("\n\n\tdeleteByIndex "+index2);
        tree.deleteByIndex(index2);
        tree.show();

        int index3 = 2;
        System.out.print("\n\n\tdeleteByIndex "+index3);
        tree.deleteByIndex(index3);
        tree.show();

        System.out.print("\n\n\tSerialization: saving");
        Serialization.saveToFile(tree,"test01_part.txt", UserFactory.getTypeNameList().get(1));
        System.out.print("\tSerialization: loading");
        BinaryTreeAsArray newTree = Serialization.loadFile("test01_part.txt");

        newTree.forEach(System.out::print);

        System.out.println("\n\tEND OF TEST PROPER FRACTION");
    }


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

    UserType[] arr6 = {
            new PropFract(12,5,12),
            null, new PropFract(16,5,12),
            null, null, null, new PropFract(20,9,12),
            null,null,null,null, null,null, null, new PropFract(23,2,3),
            null,null,null,null,null,null,null,null,null,null,null,null, null,null,null,new PropFract(26,12,23)
    };

}
