// 다익스트라 사용한 코드 (틀림)
// import java.util.PriorityQueue

// val br = System.`in`.bufferedReader()
// val bw = System.out.bufferedWriter()

// fun main() = with(br) {
//     val (n, m, start, end, assets) = readLine().split(" ").map { it.toInt() }
//     val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

//     repeat(m) {
//         val (v1, v2, w) = readLine().split(" ").map { it.toInt() }
//         graph[v1].add(v2 to w)
//         graph[v2].add(v1 to w)
//     }

//     val queue = PriorityQueue<G>(compareBy<G>{ it.maxCost }.thenBy { it.cost })
//     val dist = IntArray(n + 1) { Int.MAX_VALUE }
//     val maxCostArray = IntArray(n + 1) { Int.MAX_VALUE }

//     queue.add(G(start, 0, 0))
//     dist[start] = 0
//     maxCostArray[start] = 0

//     while (queue.isNotEmpty()) {
//         val (currVertex, currCost, currMaxCost) = queue.poll()

//         if (dist[currVertex] < currCost) continue

//         for ((nextVertex, nextCost) in graph[currVertex]) {
//             val newCost = currCost + nextCost
//             val maxCost = maxOf(currMaxCost, nextCost)

//             if (newCost < dist[nextVertex] && maxCost < maxCostArray[nextVertex] && newCost <= assets ) {
//                 dist[nextVertex] = newCost
//                 maxCostArray[nextVertex] = maxCost
//                 queue.add(G(nextVertex, newCost, maxCost))
//             }
//         }
//     }

//     if (maxCostArray[end] == Int.MAX_VALUE) bw.write("-1")
//     else bw.write(maxCostArray[end].toString())

//     bw.flush()
// }

// data class G(val vertex: Int, val cost: Int, val maxCost: Int)
