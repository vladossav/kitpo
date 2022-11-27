package scala_module

import java_module.Gui
import java_module.types.{IntType, PropFract, UserType}

import java.util

object Main {
  def main(args: Array[String]): Unit = {
    val tree: BinaryTreeAsArray = new BinaryTreeAsArray()
    new Gui(tree)
    testInt()
    testProperFraction()
  }

  def testInt(): Unit = {
    System.out.print("\tTEST INT")
    val tree = new BinaryTreeAsArray
    System.out.print("\n\tinit empty tree")
    tree.show()

    val max = (Math.random * 15).toInt + 4
    for (i <- 0 until max) {
      val value = (Math.random * 100).toInt - 50
      tree.insertByIndex(0, new IntType(value))
    }
    System.out.print("\n\n\tempty tree random filling")
    tree.show()

    val list = new util.ArrayList[UserType]
    val index = (Math.random * tree.sizer(0, list) - 1).toInt
    list.sort(list.get(0).getTypeComparator)
    System.out.println("\n\n\tget by index " + index)
    System.out.print("ordered: ")
    for (i <- 0 until list.size) {
      System.out.print(list.get(i).toString + " ")
    }
    System.out.print("\nresult: " + tree.getByIndex(index, 0))
    System.out.print("\n\tBalancing")
    tree.balance()
    tree.show()

    val value = (Math.random * 100).toInt - 50
    System.out.print("\n\n\tinsert " + value)
    tree.insertByIndex(0, new IntType(value))
    tree.show()

    val index1 = (Math.random * tree.getSize - 1).toInt
    System.out.print("\n\n\tdeleteByIndex " + index1)
    tree.deleteByIndex(index1)
    tree.show()

    System.out.println("\n\tEND OF TEST INT")
  }

  def testProperFraction(): Unit = {
    System.out.println("------------------------")
    System.out.println("\tTEST PROPER FRACTION")
    val tree = new BinaryTreeAsArray
    System.out.print("\n\tinit empty tree")
    tree.show()

    val max = (Math.random * 12).toInt + 4
    for (i <- 0 until max) {
      val intPart = (Math.random * 100).toInt - 50
      val num = (Math.random * 100).toInt
      val denom = (Math.random * 500).toInt + num
      tree.insertByIndex(0, new PropFract(intPart, num, denom))
    }
    System.out.print("\n\tempty tree random filling")
    tree.show()

    val list = new util.ArrayList[UserType]
    val index = (Math.random * tree.sizer(0, list) - 1).toInt
    list.sort(list.get(0).getTypeComparator)
    System.out.println("\n\n\tget by index " + index)
    System.out.print("ordered: ")
    for (i <- 0 until list.size) {
      System.out.print(list.get(i).toString + " ")
    }
    System.out.println("\nresult: " + tree.getByIndex(index, 0))
    System.out.print("\n\tBalancing")
    tree.balance()
    tree.show()

    val intPart = (Math.random * 100).toInt - 50
    val num = (Math.random * 100).toInt
    val denom = (Math.random * 500).toInt + num
    System.out.print("\n\n\tinsert " + intPart + "'" + num + "/" + denom)
    tree.insertByIndex(0, new PropFract(intPart, num, denom))
    tree.show()

    val index1 = (Math.random * tree.getSize - 1).toInt
    System.out.print("\n\n\tdeleteByIndex " + index1)
    tree.deleteByIndex(index1)
    tree.show()

    System.out.println("\n\tEND OF TEST PROPER FRACTION")
  }
}
