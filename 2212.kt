import java.util.Stack

fun main(args: Array<String>) {
    val n = readln().toInt()
    val k = readln().toInt()
    val diff = Stack<Int>()

    readln().split(" ").map { it.toInt() }.toIntArray().sorted().also {
        for (i in 0 until n - 1)  diff.push(it[i + 1] - it[i])
        diff.sort()
    }

    repeat(k - 1) { if (diff.isNotEmpty()) diff.pop() }
    println(diff.sum())
}
