// Dijkstra + Binary_Search

import java.util.PriorityQueue
import java.util.StringTokenizer

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt() // 마을의 수
    val m = st.nextToken().toInt() // 골목의 수
    val start = st.nextToken().toInt() // 출발지점
    val end = st.nextToken().toInt() // 도착지점
    val assets = st.nextToken().toLong() // 가진 돈

    val graph = Array(n + 1) { mutableListOf<Pair<Int, Long>>() }
    val binarySet = mutableSetOf<Long>()

    repeat(m) {
        StringTokenizer(readLine()).also {
            val v1 = it.nextToken().toInt()
            val v2 = it.nextToken().toInt()
            val w = it.nextToken().toLong()

            graph[v1].add(v2 to w)
            graph[v2].add(v1 to w)
            binarySet.add(w)
        }
    }
    val binary = binarySet.sorted().toList()

    // 다익스트라를 이용한 경계조건
    // limit 파라미터 이상의 비용이 드는 골목은 선택하지 않음 (즉, 수치심은 항상 limit 이하임)
    // 도착지점까지 가는 최소 비용 경로를 가진 돈으로 이용할 수 있는지 여부를 반환
    fun dijkstra(limit: Long): Boolean {
        val dist = LongArray(n + 1) { Long.MAX_VALUE }
        val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (currVertex, currCost) = queue.poll()
            if (dist[currVertex] < currCost) continue

            for ((nextVertex, nextCost) in graph[currVertex]) {
                val newCost = currCost + nextCost
                if (newCost < dist[nextVertex] && nextCost <= limit) {
                    dist[nextVertex] = newCost
                    queue.add(nextVertex to newCost)
                }
            }
        }
        return dist[end] <= assets
    }

    // 이분탐색을 이용해 최적의 수치심을 선택
    // 가진 돈으로 갈 수 있는 방법 중 수치심을 가장 적게 받는 경로를 선택
    fun binarySearch(): Long {
        var left = -1
        var right = binary.size

        while (left + 1 < right) {
            val mid = (left + right) / 2
            if (dijkstra(binary[mid])) right = mid
            else left = mid
        }

        return if (right == binary.size) -1 else binary[right]
    }

    bw.write(binarySearch().toString())
    bw.flush()
}
