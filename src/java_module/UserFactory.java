package java_module;

import java_module.types.IntType;
import java_module.types.PropFract;
import java_module.types.UserType;

import java.util.ArrayList;
import java.util.Arrays;


public class UserFactory {
    public static ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Int","ProperFraction"));
        return list;
    }

    public static UserType getBuilderByName(String name){
        switch(name) {
            case "Int":
                return new IntType();
            case "ProperFraction":
                return new PropFract();
            default:
                return null;
        }

    }
}
