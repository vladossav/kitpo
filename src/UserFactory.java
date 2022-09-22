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
                int val = (int) (Math.random() * (200)) - 100;
                return new Int(val);
            case "ProperFraction":
                return new ProperFraction();
            default:
                return null;
        }

    }
}
