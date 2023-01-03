package java_module;

import java_module.types.IntType;
import java_module.types.PropFract;
import java_module.types.UserType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTreeAsArray tree = new BinaryTreeAsArray();
        new Gui(tree);
        testInt();
        testProperFraction();
    }

    static void testInt() throws Exception {
        System.out.print("\tTEST INT");

        BinaryTreeAsArray tree = new BinaryTreeAsArray();
        System.out.print("\n\tinit empty tree");
        tree.show();

        int max =(int) (Math.random() * 15) + 4;
        for (int i = 0; i < max; i++) {
            int value = (int) (Math.random() * 100) -50;
            tree.insertByIndex(0, new IntType(value));
        }

        System.out.print("\n\n\tempty tree random filling");
        tree.show();

        ArrayList<UserType> list = new ArrayList<>();
        int index = (int) (Math.random() * tree.sizer(0,list)-1);
        list.sort(list.get(0).getTypeComparator());
        System.out.println("\n\n\tget by index " + index);
        System.out.print("ordered: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).toString() + " ");
        }
        System.out.print("\nresult: " + tree.getByIndex(index,0));

        System.out.print("\n\tBalancing");
        tree.balance();
        tree.show();

        int value = (int) (Math.random() * 100) - 50;
        System.out.print("\n\n\tinsert "+ value);
        tree.insertByIndex(0,new IntType(value));
        tree.show();

        int index1 = (int) (Math.random() * tree.getSize()-1);
        System.out.print("\n\n\tdeleteByIndex "+index1);
        tree.deleteByIndex(index1);
        tree.show();

        System.out.print("\n\n\tjava.Serialization: saving");
        Serialization.saveToFile(tree,"test01_int.txt", UserFactory.getTypeNameList().get(0));
        System.out.print("\tjava.Serialization: loading");
        BinaryTreeAsArray newTree = Serialization.loadFile("test01_int.txt");

        System.out.print("\n\tcheck foreach tree after serialization\n");
        newTree.forEach(System.out::print);

        System.out.println("\n\tEND OF TEST INT");
    }

    static void testProperFraction() throws Exception {
        System.out.println("------------------------");
        System.out.println("\tTEST PROPER FRACTION");

        BinaryTreeAsArray tree = new BinaryTreeAsArray();
        System.out.print("\n\tinit empty tree");
        tree.show();

        int max =(int) (Math.random() * 12) + 4;
        for (int i = 0; i < max; i++) {
            int intPart = (int) (Math.random() * 100) - 50;
            int num = (int) (Math.random() * 100);
            int denom = (int) (Math.random() * 500) + num;
            tree.insertByIndex(0, new PropFract(intPart,num,denom));
        }

        System.out.print("\n\tempty tree random filling");
        tree.show();

        ArrayList<UserType> list = new ArrayList<>();
        int index = (int) (Math.random() * tree.sizer(0,list)-1);
        list.sort(list.get(0).getTypeComparator());
        System.out.println("\n\n\tget by index " + index);
        System.out.print("ordered: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).toString() + " ");
        }
        System.out.println("\nresult: " + tree.getByIndex(index,0));

        System.out.print("\n\tBalancing");
        tree.balance();
        tree.show();

        int intPart = (int) (Math.random() * 100) - 50;
        int num = (int) (Math.random() * 100);
        int denom = (int) (Math.random() * 500) + num;
        System.out.print("\n\n\tinsert "+ intPart+"'"+num+"/"+denom);
        tree.insertByIndex(0, new PropFract(intPart,num,denom));
        tree.show();

        int index1 = (int) (Math.random() * tree.getSize()-1);
        System.out.print("\n\n\tdeleteByIndex "+index1);
        tree.deleteByIndex(index1);
        tree.show();

        System.out.print("\n\n\tjava.Serialization: saving");
        Serialization.saveToFile(tree,"test01_part.txt", UserFactory.getTypeNameList().get(1));
        System.out.print("\tjava.Serialization: loading");
        BinaryTreeAsArray newTree = Serialization.loadFile("test01_part.txt");

        System.out.print("\n\tcheck foreach tree after serialization\n");
        newTree.forEach(System.out::print);
        System.out.println("\n\tEND OF TEST PROPER FRACTION");
    }


    UserType[] arr2 = {
            new IntType(15),
            new IntType(5), new IntType(16),
            new IntType(3), new IntType(12), null, new IntType(20),
            null,null,new IntType(10), new IntType(13), null,null, new IntType(18), new IntType(23),
            null,null,null,null,new IntType(6),null,null,null,null,null,null,null,null,null,null,null
    };

    UserType[] arr3 = {
            new IntType(12),
            null, new IntType(16),
            null, null, null, new IntType(20),
            null,null,null,null, null,null, new IntType(18), new IntType(23),
            null,null,null,null,null,null,null,null,null,null,null,null, new IntType(17),null,null,new IntType(26)
    };

    UserType[] arr4 = {
            new IntType(42),
            new IntType(30), null,
            new IntType(21), null, null, null,
            new IntType(15),null,null,null, null,null, null, null,
            new IntType(2),new IntType(20),null,null,null,null,null,null,null,null,null,null,null,null,null,null
    };

    UserType[] arr5 = {
            new IntType(12),
            null, new IntType(16),
            null, null, null, new IntType(20),
            null,null,null,null, null,null, null, new IntType(23),
            null,null,null,null,null,null,null,null,null,null,null,null, null,null,null,new IntType(26)
    };

    UserType[] arr6 = {
            new PropFract(12,5,12),
            null, new PropFract(16,5,12),
            null, null, null, new PropFract(20,9,12),
            null,null,null,null, null,null, null, new PropFract(23,2,3),
            null,null,null,null,null,null,null,null,null,null,null,null, null,null,null,new PropFract(26,12,23)
    };

}