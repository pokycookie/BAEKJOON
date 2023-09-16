import java.util.PriorityQueue
import java.util.StringTokenizer

val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val n = readLine().toInt()
    val meetings = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second }, { it.first }))
    repeat(n) {
        val st = StringTokenizer(readLine())
        meetings.add(st.nextToken().toInt() to st.nextToken().toInt())
    }
    var ans = 1
    var last = meetings.poll().second

    while (meetings.isNotEmpty()) {
        val pop = meetings.poll()
        if (pop.first >= last) {
            last = pop.second
            ans++
        }
    }

    println(ans)
}
