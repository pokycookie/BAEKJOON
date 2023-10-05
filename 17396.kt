// use Dijkstra

import java.util.PriorityQueue

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val sight = readLine().split(" ").map{ it.toInt() }.toIntArray()
    val graph = Array(n) { mutableListOf<Pair<Int, Long>>() }
    val dist = LongArray(n) { Long.MAX_VALUE }

    sight[n - 1] = 0
    repeat(m) {
        val (v1, v2, t) = readLine().split(" ").map { it.toInt() }
        if (sight[v2] == 0) graph[v1].add(v2 to t.toLong())
        if (sight[v1] == 0) graph[v2].add(v1 to t.toLong())
    }

    val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
    queue.add(0 to 0)
    dist[0] = 0

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

    if (dist[n - 1] == Long.MAX_VALUE) bw.write("-1")
    else bw.write(dist[n - 1].toString())

    bw.flush()
    bw.close()
}
