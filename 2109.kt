import java.util.PriorityQueue

fun main(args: Array<String>) {
    val n = readln().toInt()
    val prices = mutableListOf<Pair<Int, Int>>()
    var heap = PriorityQueue<Int>()

    for (i in 0 until n) {
        val (p, d) = readln().split(" ").map { it -> it.toInt() }
        prices.add(Pair(p, d))
    }
    prices.sortBy { it.second }

    for ((p, d) in prices) {
        heap.add(p)
        if (heap.size > d) heap.poll()
    }

    print(heap.sum())
}
