import types.UserType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Objects;

public class Serialization {
    public  static void saveToFile(BinaryTreeAsArray tree, String filename, String type) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(type);
            tree.forEach(writer::print);
        }
    }

    public static BinaryTreeAsArray loadFromFile(String filename) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String type = br.readLine();
            if (!UserFactory.getTypeNameList().contains(type)) {
                throw new Exception("Wrong file structure");
            }

            String line = br.readLine();
            String[] items = line.split(" ");
            UserType[] arr = new UserType[items.length];
            System.out.println(arr.length);

            for (int i = 0; i < arr.length; i++)
                if (!Objects.equals(items[i], "null"))
                    arr[i] = (UserType) UserFactory.getBuilderByName(type).parseValue(items[i]);


            return new BinaryTreeAsArray(arr);
        }
    }
}