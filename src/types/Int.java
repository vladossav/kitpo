package types;

public class Int implements UserType {
    private int value;

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
