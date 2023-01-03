package kotlin_module

import java_module.BinaryTree
import java_module.DoWith
import java_module.types.UserType

class BinaryTreeAsArray() : BinaryTree {
    var size = 1 //размер массив
    private var arr: Array<UserType?> = arrayOfNulls(size)
    private var level = 0

    override fun init() {
        size = 1
        level = 0
        arr = arrayOfNulls(size)
    }

    internal constructor(array: Array<UserType>) : this() {
        level = calculateLevelFromSize(array.size)
        size = calculateSizeFromLevel(level)
        arr = array.copyOf(size)
    }

    private fun sizerLSS(n: Int): Int {
        return if (n >= size || arr[n] == null) 0 else 1 + sizerLSS(2 * n + 1) + sizerLSS(2 * n + 2)
    }

    override fun getByIndex(m: Int, n: Int): UserType? {
        var m = m
        if (m < 0 || m >= size || m >= sizerLSS(n)) return null
        val ll = sizerLSS(2 * n + 1)
        if (m < ll) return getByIndex(m, 2 * n + 1)
        m -= ll
        return if (m-- == 0) arr[n] else getByIndex(m, 2 * n + 2)
    }

    override fun insertByIndex(n: Int, element: UserType?) {
        if (element == null) return
        if (n >= size) {
            level++
            size += calculateSizeOfLevel(level)
            arr = arr.copyOf(size)
        }
        if (arr[n] == null) {
            arr[n] = element
            return
        }
        if (element.typeComparator.compare(element, arr[n]) > 0) insertByIndex(2 * n + 2, element) //право
        else insertByIndex(2 * n + 1, element) //влево
    }

    //балансировка
    private fun balance(array: ArrayList<UserType?>, a: Int, b: Int) {
        if (a > b) return
        val m = (a + b) / 2
        insertByIndex(0, array[m])
        balance(array, a, m - 1)
        balance(array, m + 1, b)
    }

    fun sizer(n: Int, arrayList: ArrayList<UserType?>): Int { //подсчет потомков
        if (n >= size || arr[n] == null) return 0
        arrayList.add(arr[n])
        return 1 + sizer(2 * n + 1, arrayList) + sizer(2 * n + 2, arrayList)
    }

    override fun balance() {
        val list = ArrayList<UserType?>()
        val size1 = sizer(0, list)
        list.sortWith(list[0]!!.typeComparator)
        init()
        balance(list, 0, size1 - 1)
    }

    //удаление
    override fun deleteByIndex(index: Int) {
        if (arr[index] == null) return
        delete(0, arr[index])
    }

    private fun delete(root: Int, element: UserType?): UserType? {
        if (2 * root + 2 >= size) return null
        if (arr[root] == null) return arr[root]
        if (element?.typeComparator!!.compare(element, arr[root]) < 0) { //если элемент < зн. узла = к левому потомку
            arr[2 * root + 1] = delete(2 * root + 1, element)!!
            return arr[root]
        }
        if (element.typeComparator!!.compare(element, arr[root]) > 0) { //если элемент > зн. узла = к правому потомку
            arr[2 * root + 2] = delete(2 * root + 2, element)!!
            return arr[root]
        }
        // если element найден
        if (arr[2 * root + 1] == null) {
            val temp = 2 * root + 2
            arr[root] = null
            return arr[temp]
        }
        if (arr[2 * root + 2] == null) {
            val temp = 2 * root + 1
            arr[root] = null
            return arr[temp]
        }
        arr[2 * root + 2] = deleteHelper(2 * root + 2, root)!! //если есть оба потомка
        return arr[root]
    }

    private fun deleteHelper(root: Int, root0: Int): UserType? {
        if (2 * root + 2 < size) {
            if (arr[2 * root + 1] != null) {
                arr[2 * root + 1] = deleteHelper(2 * root + 1, root0)!!
                return arr[root]
            }
        }
        arr[root0] = arr[root]
        arr[root] = null
        return if (2 * root + 2 < size) arr[2 * root + 2] else null
    }

    override fun show() {
        var i = 0
        var cnt = 0
        var lvl = 0
        while (i < size) {
            if (i == cnt) {
                cnt += Math.pow(2.0, lvl.toDouble()).toInt()
                lvl++
                println()
            }
            if (arr[i] == null) {
                print("N ")
                i++
                continue
            }
            print(arr[i].toString() + " ")
            i++
        }
    }

    override fun toString(): String {
        var str = ""
        var i = 0
        var cnt = 0
        var lvl = 0
        while (i < size) {
            if (i == cnt) {
                cnt += Math.pow(2.0, lvl.toDouble()).toInt()
                lvl++
                str += "\n"
            }
            if (arr[i] == null) {
                str += "N "
                i++
                continue
            }
            str += arr[i].toString() + " "
            i++
        }
        return str
    }

    private fun calculateLevelFromSize(sz: Int): Int {
        var sum = 0
        var level = -1
        while (sum < sz) {
            level++
            sum += Math.pow(2.0, level.toDouble()).toInt()
        }
        return level
    }

    private fun calculateSizeFromLevel(level: Int): Int {
        var sz = 0
        for (i in 0..level) {
            sz += Math.pow(2.0, i.toDouble()).toInt()
        }
        return sz
    }

    private fun calculateSizeOfLevel(level: Int): Int {
        return Math.pow(2.0, level.toDouble()).toInt()
    }

    override fun forEach(action: DoWith) {
        for (i in arr.indices) {
            val str: String = if (arr[i] == null) "null " else arr[i].toString() + " "
            action.doWith(str)
        }
    }
}