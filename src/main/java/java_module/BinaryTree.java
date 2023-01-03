package java_module;

import java_module.types.UserType;

public interface BinaryTree {
    void init();
    UserType getByIndex(int m, int n);
    void insertByIndex(int n, UserType element);
    void balance();
    void deleteByIndex(int index);
    void show();
    String toString();
    public void forEach(DoWith action);

}
