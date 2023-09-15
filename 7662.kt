import java.util.PriorityQueue
import java.util.StringTokenizer

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    repeat(readLine().toInt()) {
        val k = readLine().toInt()
        val minQueue = PriorityQueue<ID>(compareBy { it.value })
        val maxQueue = PriorityQueue<ID>(compareByDescending { it.value })
        val visited = BooleanArray(k) { false }

        for(id in 0 until k) {
            val st = StringTokenizer(readLine())
            val operator = st.nextToken()
            val n = st.nextToken().toInt()

            if (operator == "I") {
                maxQueue.add(ID(n, id))
                minQueue.add(ID(n, id))
                visited[id] = true
                continue
            }

            if (n == 1) {
                while (maxQueue.isNotEmpty() && !visited[maxQueue.peek().id]) maxQueue.poll()
                if (maxQueue.isNotEmpty()) visited[maxQueue.poll().id] = false
            } else {
                while (minQueue.isNotEmpty() && !visited[minQueue.peek().id]) minQueue.poll()
                if (minQueue.isNotEmpty()) visited[minQueue.poll().id] = false
            }
        }
        while (minQueue.isNotEmpty() && !visited[minQueue.peek().id]) minQueue.poll()
        while (maxQueue.isNotEmpty() && !visited[maxQueue.peek().id]) maxQueue.poll()

        if (minQueue.isNotEmpty() && maxQueue.isNotEmpty()) bw.write("${maxQueue.poll().value} ${minQueue.poll().value}")
        else bw.write("EMPTY")
        bw.newLine()
    }
    bw.flush()
    bw.close()
}

data class ID(val value: Int, val id: Int)
