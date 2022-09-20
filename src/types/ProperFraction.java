package types;

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
        return null;
    }
}
