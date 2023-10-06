// use Dijkstra

import java.util.PriorityQueue

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(m) {
        val (v1, v2, w) = readLine().split(" ").map { it.toInt() }
        graph[v1].add(v2 to w)
        graph[v2].add(v1 to w)
    }

    val (s, t) = readLine().split(" ").map { it.toInt() }

    val dist = IntArray(n + 1) { Int.MAX_VALUE }
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    queue.add(s to 0)
    dist[s] = 0

    while (queue.isNotEmpty()) {
        val (currVertex, currPath) = queue.poll()

        if (dist[currVertex] < currPath) continue

        for ((nextVertex, nextPath) in graph[currVertex]) {
            val newPath = currPath + nextPath

            if (newPath < dist[nextVertex]) {
                dist[nextVertex] = newPath
                queue.add(nextVertex to newPath)
            }
        }
    }

    bw.write(dist[t].toString())
    bw.flush()
}
