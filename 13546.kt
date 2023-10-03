// use Dijkstra
// 다음엔 BFS를 이용해 도전

import java.util.PriorityQueue

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val dist = Array(100_001) { Int.MAX_VALUE }

    val queue = PriorityQueue<Dist>(compareBy { it.dist })
    queue.add(Dist(n, 0))
    dist[n] = 0

    val maxVertex = minOf(100_000, k * 2)

    while (queue.isNotEmpty()) {
        val (currVertex, currDist) = queue.poll()

        if (dist[currVertex] < currDist ) continue

        val next = mutableListOf<Pair<Int, Int>>()
        if (currVertex + 1 in 0 .. maxVertex) next.add(currVertex + 1 to 1)
        if (currVertex - 1 in 0 .. maxVertex) next.add(currVertex - 1 to 1)
        if (currVertex * 2 in 0 .. maxVertex) next.add(currVertex * 2 to 0)

        for ((nextVertex, nextDist) in next) {
            val newPath = currDist + nextDist
            if (newPath < dist[nextVertex]) {
                dist[nextVertex] = newPath
                queue.add(Dist(nextVertex, newPath))
            }
        }
    }

    println(dist[k])
}

data class Dist(val vertex: Int, val dist: Int)
