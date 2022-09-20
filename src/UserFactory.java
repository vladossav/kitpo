import types.Int;
import types.ProperFraction;
import types.UserType;

import java.util.ArrayList;
import java.util.Arrays;


public class UserFactory {
    public ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Int","ProperFraction"));
        return list;
    }

    public UserType getBuilderByName(String name){
        switch(name) {
            case "Int":
                return new Int();
            case "ProperFraction":
                return new ProperFraction();
            default:
                return null;
        }

    }
}
