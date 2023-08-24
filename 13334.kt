import java.util.PriorityQueue
import java.util.StringTokenizer

val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val n = readLine().toInt()
    val roads = Array(n) {
        val st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()
        minOf(v1, v2) to maxOf(v1, v2)
    }
    roads.sortBy { it.second }
    val d = readLine().toInt()

    var ans = 0
    val heap = PriorityQueue<Int>()
    for ((v1, v2) in roads) {
        if (v2 - v1 > d) continue
        while (heap.isNotEmpty() && heap.peek() < v2 - d) heap.poll()
        heap.add(v1)
        ans = maxOf(ans, heap.size)
    }

    println(ans)
}
