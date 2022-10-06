import types.Int;
import types.PropFract;
import types.UserType;

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
                return new Int();
            case "ProperFraction":
                return new PropFract();
            default:
                return null;
        }

    }
}
