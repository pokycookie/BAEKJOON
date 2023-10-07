import java.util.PriorityQueue

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val n = readLine().toInt()
    val graph = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    val queue = PriorityQueue<G>(compareBy { it.weight })

    queue.add(G(0, 0, 0))
    dist[0][0] = 0

    while (queue.isNotEmpty()) {
        val (currRow, currColumn, currPath) = queue.poll()

        if (dist[currRow][currColumn] < currPath) continue

        val next = arrayOf(currRow + 1 to currColumn, currRow to currColumn + 1)
        for ((nextRow, nextColumn) in next) {
            if (nextRow !in 0 until n) continue
            if (nextColumn !in 0 until n) continue

            val nextPath = maxOf(graph[nextRow][nextColumn] - graph[currRow][currColumn] + 1 , 0)
            val newPath = currPath + nextPath

            if (newPath < dist[nextRow][nextColumn]) {
                dist[nextRow][nextColumn] = newPath
                queue.add(G(nextRow, nextColumn, newPath))
            }
        }
    }

    bw.write(dist[n - 1][n - 1].toString())
    bw.flush()
}

data class G(val row: Int, val column: Int, val weight: Int)
