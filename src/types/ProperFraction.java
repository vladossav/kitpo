package types;

import java.util.Comparator;

public class ProperFraction implements UserType {
    private int intPart;
    private int denominator;
    private int numerator;


    @Override
    public String typeName() {
        return "ProperFraction";
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public Object readValue() {
        return null;
    }

    @Override
    public Object parseValue(String ss) {
        return null;
    }

    @Override
    public Comparator getTypeComparator() {
        return this;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
