package kotlin_module

import java_module.Gui
import java_module.Serialization
import java_module.UserFactory
import java_module.types.IntType
import java_module.types.PropFract
import java_module.types.UserType

fun main() {
    val tree = BinaryTreeAsArray()
    Gui(tree)
    testInt()
    testProperFraction()
}

@Throws(Exception::class)
fun testInt() {
    print("\tTEST INT")
    val tree = BinaryTreeAsArray()
    print("\n\tinit empty tree")
    tree.show()

    val max = (Math.random() * 15).toInt() + 4
    for (i in 0 until max) {
        val value = (Math.random() * 100).toInt() - 50
        tree.insertByIndex(0, IntType(value))
    }
    print("\n\n\tempty tree random filling")
    tree.show()

    val list = ArrayList<UserType?>()
    val index = (Math.random() * tree.sizer(0, list) - 1).toInt()
    list.sortWith(list[0]!!.typeComparator)
    println("\n\n\tget by index $index")
    print("ordered: ")
    for (i in list.indices) {
        print(list[i].toString() + " ")
    }
    print(" result: ${tree.getByIndex(index, 0)}")

    print("\n\tBalancing")
    tree.balance()
    tree.show()

    val value = (Math.random() * 100).toInt() - 50
    print("\n\n\tinsert $value")
    tree.insertByIndex(0, IntType(value))
    tree.show()

    val index1 = (Math.random() * tree.size - 1).toInt()
    print("\n\n\tdeleteByIndex $index1")
    tree.deleteByIndex(index1)
    tree.show()
    print("\n\n\tjava.Serialization: saving")
    Serialization.saveToFile(tree, "test01_int.txt", UserFactory.getTypeNameList()[0])
    print("\tjava.Serialization: loading")
    val newTree = Serialization.loadFile("test01_int.txt")
    print("\n\tcheck foreach tree after serialization\n")
    newTree.forEach { obj: Any? -> print(obj) }
    println("\n\tEND OF TEST INT")
}

@Throws(java.lang.Exception::class)
fun testProperFraction() {
    println("------------------------")
    println("\tTEST PROPER FRACTION")

    val tree = BinaryTreeAsArray()
    print("\n\tinit empty tree")
    tree.show()

    val max = (Math.random() * 12).toInt() + 4
    for (i in 0 until max) {
        val intPart = (Math.random() * 100).toInt() - 50
        val num = (Math.random() * 100).toInt()
        val denom = (Math.random() * 500).toInt() + num
        tree.insertByIndex(0, PropFract(intPart, num, denom))
    }
    print("\n\tempty tree random filling")
    tree.show()

    val list = java.util.ArrayList<UserType?>()
    val index = (Math.random() * tree.sizer(0, list) - 1).toInt()
    list.sortWith(list[0]!!.typeComparator)
    println("\n\n\tget by index $index")
    print("ordered: ")
    for (i in list.indices) {
        print(list[i].toString() + " ")
    }
    println("result: ${tree.getByIndex(index, 0)}")
    print("\n\tBalancing")
    tree.balance()
    tree.show()

    val intPart = (Math.random() * 100).toInt() - 50
    val num = (Math.random() * 100).toInt()
    val denom = (Math.random() * 500).toInt() + num
    print("\n\n\tinsert $intPart'$num/$denom")
    tree.insertByIndex(0, PropFract(intPart, num, denom))
    tree.show()

    val index1 = (Math.random() * tree.size - 1).toInt()
    print("\n\n\tdeleteByIndex $index1")
    tree.deleteByIndex(index1)
    tree.show()

    print("\n\n\tjava.Serialization: saving")
    Serialization.saveToFile(tree, "test01_part.txt", UserFactory.getTypeNameList()[1])
    print("\tjava.Serialization: loading")
    val newTree = Serialization.loadFile("test01_part.txt")
    print("\n\tcheck foreach tree after serialization\n")
    newTree.forEach { obj: Any? -> print(obj) }
    println("\n\tEND OF TEST PROPER FRACTION")
}


