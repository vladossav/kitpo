package types;

import java.util.ArrayList;
import java.util.Comparator;

public class Int implements UserType {
    private int value;

    public Int(int val) {
        value = val;
    }
    public Int() {}

    @Override
    public String typeName() {
        return "Int";
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object clone() {
        return this;
    }

    @Override
    public Object readValue() {
        return value;
    }

    @Override
    public Object parseValue(String ss) {
        value = Integer.parseInt(ss);
        return this;
    }

    @Override
    public Comparator getTypeComparator() {
        return this;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (((Int) o1).value == ((Int) o2).value) return 0;
        if (((Int) o1).value > ((Int) o2).value) return 1;
        else return -1;
    }
}
