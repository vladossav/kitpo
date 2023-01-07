package java_module;

import java_module.types.IntType;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BinaryTreeArrayTest {
    private BinaryTreeAsArray actualBts;

    private int getExpectedSumPathLength(int level) {
        int sum = 0;
        for (int curLevel = 0, N = 1; curLevel < level; curLevel++, N *= 2)
            sum += curLevel * N;
        return sum;
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        actualBts = new BinaryTreeAsArray();
    }

    //исходный набор содержит одинаковые значения
    @org.junit.jupiter.api.Test
    public void test1(){
        System.out.println("TEST 1. Input with same values");
        int cntLevel = 3;
        int cntNode = (int) (Math.pow(2, cntLevel) - 1);
        for (int value = 0; value < cntNode; value++)
            actualBts.insertByIndex(0, new IntType(3));
        actualBts.balance();
        actualBts.show();
        int actual = actualBts.getSumPathLength(0, 0);
        int expected = 0;
        for (int i = 0; i < cntNode; i++)
            expected += i;

        System.out.println("\nmaxLevel = " + actualBts.heigh(0) + "; count = " + actualBts.sizerLSS(0));
        System.out.println("Actual sum of path lengths = " + actual);
        System.out.println("Expected sum of path lengths = " + expected);
        assertEquals(expected, actual);
    }

    //исходный набор неупорядочен
    @org.junit.jupiter.api.Test
    public void test2(){
        System.out.println("TEST 2. Random input");
        int cntLevel = 4;
        int cntNode = (int) (Math.pow(2, cntLevel) - 1);
        for (int value = 0; value < cntNode; value++) {
            actualBts.insertByIndex(0, new IntType((int) (Math.random() * 1000) -500));
        }
        actualBts.show();
        actualBts.balance();
        actualBts.show();

        int actual = actualBts.getSumPathLength(0,0);
        int expected = getExpectedSumPathLength(cntLevel);
        System.out.println("\nmaxLevel = " + actualBts.heigh(0) + "; count = " + actualBts.sizerLSS(0));
        System.out.println("Actual sum of path lengths = " + actual);
        System.out.println("Expected sum of path lengths = " + expected);
        System.out.println("----------------------");
        assertEquals(expected, actual);
    }

    //Исходный набор упорядочен в прямом порядке
    @org.junit.jupiter.api.Test
    public void test3() {
        System.out.println("TEST 3. Input values in direct order");
        int cntLevel = 3;
        int cntNode = (int) (Math.pow(2, cntLevel) - 1);
        for (int value = 0; value < cntNode; value++)
            actualBts.insertByIndex(0, new IntType(value));
        actualBts.balance();
        actualBts.show();

        int actual = actualBts.getSumPathLength(0, 0);
        int expected = getExpectedSumPathLength(cntLevel);
        System.out.println("\nActual sum of path lengths = " + actual);
        System.out.println("Expected sum of path lengths = " + expected);
        System.out.println("maxLevel: actual = " + actualBts.heigh(0) + " expected = " + cntLevel + "; \ncount: actual = " + actualBts.sizerLSS(0) + " expected = " + cntNode);
        assertEquals(expected, actual);
    }

    // исходный набор упорядочен в обратном порядке
    @org.junit.jupiter.api.Test
    public void test4(){
        System.out.println("TEST 4. Input values in reverse order");
        int cntLevel = 4;
        int cntNode = (int) (Math.pow(2, cntLevel) - 1);
        for (int value = 0; value < cntNode; value++)
            actualBts.insertByIndex(0, new IntType(cntNode - value));
        actualBts.balance();
        actualBts.show();
        int actual = actualBts.getSumPathLength(0, 0);
        int expected = getExpectedSumPathLength(cntLevel);
        System.out.println("\nActual sum of path lengths = " + actual);
        System.out.println("Expected sum of path lengths = " + expected);
        System.out.println("maxLevel: actual = " + actualBts.heigh(0) + " expected = " + cntLevel + "; \ncount: actual = " + actualBts.sizerLSS(0) + " expected = " + cntNode);
        assertEquals(expected, actual);
    }

    //дерево уже сбалансировано
    @org.junit.jupiter.api.Test
    public void test5(){
        System.out.println("TEST 5. Tree is already balanced");
        int cntLevel = 6;
        int cntNode = (int) (Math.pow(2, cntLevel) - 1);
        for (int value = 0; value < cntNode; value++) {
            actualBts.insertByIndex(0, new IntType(value));
            actualBts.balance();
        }
        actualBts.balance();
        actualBts.show();
        int actual = actualBts.getSumPathLength(0,0);
        int expected = getExpectedSumPathLength(cntLevel);
        System.out.println("\nmaxLevel = " + actualBts.heigh(0) + "; count = " + actualBts.sizerLSS(0));
        System.out.println("Actual sum of path lengths = " + actual);
        System.out.println("Expected sum of path lengths = " + expected);
        System.out.println("----------------------");
        assertEquals(expected, actual);
    }

    // элемент из середины интервала встречается несколько раз в наборе
    @org.junit.jupiter.api.Test
    public void test6(){
        System.out.println("TEST 6");
        actualBts.insertByIndex(0, new IntType(11));
        actualBts.insertByIndex(0, new IntType(9));
        actualBts.insertByIndex(0, new IntType(10));
        actualBts.insertByIndex(0, new IntType(6));
        actualBts.insertByIndex(0, new IntType(7));
        actualBts.insertByIndex(0, new IntType(13));
        actualBts.insertByIndex(0, new IntType(9));
        actualBts.balance();
        actualBts.show();
        int cntLevel = 3;
        int actual = actualBts.getSumPathLength(0,0);
        int expected = getExpectedSumPathLength(cntLevel);

        System.out.println("Actual sum of path lengths = " + actual);
        System.out.println("Expected sum of path lengths = " + expected);
        System.out.println("maxLevel = " + actualBts.heigh(0) + "; count = " + actualBts.sizerLSS(0));
        System.out.println("----------------------");
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    public void testBalanced() {
        System.out.println("BALANCED TEST");

        for (int countOfElem = 100000; countOfElem <= 1000000; countOfElem += 50000) {
            for (int i = 0; i < countOfElem; i++){
                actualBts.insertByIndex(0, new IntType((int) (Math.random() * 2000000000) -1000000000));
            }
            actualBts.balance();
            long startTime = System.nanoTime();
            actualBts.deleteByIndex(actualBts.sizerLSS(0) - 1);
            long endTime = System.nanoTime();
            double timeElapsed = (endTime - startTime) * 1.0 / 1_000_000;
            System.out.println("N = " + countOfElem + ". Time = " + timeElapsed + " ms.");
        }
        System.out.println("----------------------");
    }
}
