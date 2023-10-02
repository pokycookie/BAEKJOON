// use Dijkstra

import java.util.PriorityQueue

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(m) {
        val (s, e, w) = readLine().split(" ").map { it.toInt() }
        graph[s].add(e to w)
    }

    val (start, end) = readLine().split(" ").map { it.toInt() }

    val dist = IntArray(n + 1) { Int.MAX_VALUE }
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

    dist[start] = 0 // 시작노드 거리를 0으로 초기화
    queue.add(0 to start) // 우선순위 큐에 시작노드 추가

    while (queue.isNotEmpty()) {
        val (currPath, currVertex) = queue.poll()

        if (dist[currVertex] < currPath) continue

        for ((nextVertex, nextPath) in graph[currVertex]) {
            val newPath = nextPath + currPath
            if (newPath < dist[nextVertex]) {
                dist[nextVertex] = newPath
                queue.add(newPath to nextVertex)
            }
        }
    }

    println(dist[end])
}
