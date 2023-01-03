package scala_module

import java_module.{BinaryTree, DoWith}
import java_module.types.UserType

import java.util

class BinaryTreeAsArray extends BinaryTree {
  private var arr: Array[UserType] = new Array[UserType](0)
  private var size = 0 //размер массива
  private var level = 0

  def init(): Unit = {
    size = 1
    level = 0
    arr = new Array[UserType](size)
  }

  def this(array: Array[UserType]) {
    this()
    level = calculateLevelFromSize(array.length)
    size = calculateSizeFromLevel(level)
    arr = Array.copyOf(array, size)
  }

  def sizerLSS(n: Int): Int = {
    if (n >= size || arr(n) == null) return 0
    1 + sizerLSS(2 * n + 1) + sizerLSS(2 * n + 2)
  }

  def getByIndex(m: Int, n: Int): UserType = {
    if (m < 0 || m >= size || m >= sizerLSS(n)) return null
    val ll = sizerLSS(2 * n + 1)
    if (m < ll) return getByIndex(m, 2 * n + 1)
    val mid = m - ll
    val mid2 = mid-1
    if (mid2 == 0) return arr(n)
    getByIndex(mid, 2 * n + 2)
  }

  def insertByIndex(n: Int, element: UserType): Unit = {
    if (element == null) return
    if (n >= size) {
      level += 1
      size += calculateSizeOfLevel(level)
      arr = Array.copyOf(arr, size)
    }
    if (arr(n) == null) {
      arr(n) = element
      return
    }

    if (element.getTypeComparator.compare(element, arr(n)) > 0)
      insertByIndex(2 * n + 2, element) //право
    else insertByIndex(2 * n + 1, element) //влево
  }

  def getSize = size

  //балансировка
  def balance(array: util.ArrayList[UserType], a: Int, b: Int): Unit = {
    if (a > b) return
    val m = (a + b) / 2
    insertByIndex(0, array.get(m))
    balance(array, a, m - 1)
    balance(array, m + 1, b)
  }

  def sizer(n: Int, arrayList: util.ArrayList[UserType]): Int = { //подсчет потомков
    if (n >= size || arr(n) == null) return 0
    arrayList.add(arr(n))
    1 + sizer(2 * n + 1, arrayList) + sizer(2 * n + 2, arrayList)
  }

  def balance(): Unit = {
    val list = new util.ArrayList[UserType]
    val size1 = sizer(0, list)
    list.sort(list.get(0).getTypeComparator())
    init()
    balance(list, 0, size1 - 1)
  }

  //удаление
  def deleteByIndex(index: Int): Unit = {
    if (arr(index) == null) return
    delete(0, arr(index))
  }

  def delete(root: Int, element: UserType): UserType = {
    if (2 * root + 2 >= size) return null
    if (arr(root) == null) return arr(root)
    if (element.getTypeComparator.compare(element, arr(root)) < 0) { //если элемент < зн. узла = к левому потомку
      arr(2 * root + 1) = delete(2 * root + 1, element)
      return arr(root)
    }
    if (element.getTypeComparator().compare(element, arr(root)) > 0) { //если элемент > зн. узла = к правому потомку
      arr(2 * root + 2) = delete(2 * root + 2, element)
      return arr(root)
    }
    // если element найден
    if (arr(2 * root + 1) == null) {
      val temp = 2 * root + 2
      arr(root) = null
      return arr(temp)
    }
    if (arr(2 * root + 2) == null) {
      val temp = 2 * root + 1
      arr(root) = null
      return arr(temp)
    }
    arr(2 * root + 2) = deleteHelper(2 * root + 2, root) //если есть оба потомка
    arr(root)
  }

  def deleteHelper(root: Int, root0: Int): UserType = {
    if (2 * root + 2 < size) if (arr(2 * root + 1) != null) {
      arr(2 * root + 1) = deleteHelper(2 * root + 1, root0)
      return arr(root)
    }
    arr(root0) = arr(root)
    arr(root) = null
    if (2 * root + 2 < size) arr(2 * root + 2)
    else null
  }

  def show(): Unit = {
    var i = 0
    var cnt = 0
    var lvl = 0
    while (i < size) {
      if (i == cnt) {
        cnt += Math.pow(2, lvl).asInstanceOf[Int]
        lvl += 1
        System.out.println()
      }
      if (arr(i) == null) {
        System.out.print("N ")
      } else {
        System.out.print(arr(i).toString + " ")
      }
      i += 1
    }
  }

  override def toString = {
    var str = ""
    var i = 0
    var cnt = 0
    var lvl = 0
    while (i < size) {
      if (i == cnt) {
        cnt += Math.pow(2, lvl).asInstanceOf[Int]
        lvl += 1
        str += "\n"
      }
      if (arr(i) == null) {
        str += "N "
      }
      else {
        str += (arr(i).toString + " ")
        i += 1
      }
    }
    str
  }

  private def calculateLevelFromSize(sz: Int) = {
    var sum: Int = 0
    var level = -1
    while (sum < sz) {
      level += 1
      sum += Math.pow(2, level).asInstanceOf[Int]
    }
    level
  }

  private def calculateSizeFromLevel(level: Int) = {
    var sz = 0
    for (i <- 0 to level) {
      sz += Math.pow(2, i).asInstanceOf[Int]
    }
    sz
  }

  private def calculateSizeOfLevel(level: Int) = Math.pow(2, level).toInt

  def forEach(action: DoWith): Unit = {
    for (i <- 0 until arr.length) {
      var str: String = null
      if (arr(i) == null) str = "null "
      else str = arr(i).toString + " "
      action.doWith(str)
    }
  }
}