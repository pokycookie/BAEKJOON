import java.util.PriorityQueue
import java.util.Stack

fun main(args: Array<String>) {
    val n = readln().toInt()
    val problems = Stack<Pair<Int, Int>>()
    var deadlineMax = 0

    for (i in 0 until n) {
        val (deadline, prize) = readln().split(" ").map { it.toInt() }
        if (deadline > deadlineMax) deadlineMax = deadline
        problems.add(deadline to prize)
    }
    problems.sortBy { it.first }

    var ans = 0
    val heap = PriorityQueue<Int>(compareByDescending { it })

    for (pointer in deadlineMax downTo 1) {
        while (problems.isNotEmpty() && problems.peek().first >= pointer) {
            heap.add(problems.pop().second)
        }
        if (heap.isNotEmpty()) ans += heap.poll()
    }

    print(ans)
}
