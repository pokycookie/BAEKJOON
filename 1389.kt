// use Floyd-Warshall

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(n) { 999 } }

    for (i in 0 until n) graph[i][i] = 0
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a - 1][b - 1] = 1
        graph[b - 1][a - 1] = 1
    }

    for (waypoint in 0 until n) {
        for (start in 0 until n) {
            for (end in 0 until n) {
                graph[start][end] = minOf(graph[start][end], graph[start][waypoint] + graph[waypoint][end])
            }
        }
    }

    var ans = 0
    var min = Int.MAX_VALUE
    for (vertex in 0 until n) {
        val acc = graph[vertex].sum()
        if (acc < min) {
            ans = vertex + 1
            min = acc
        }
    }

    bw.write(ans.toString())
    bw.flush()
    bw.close()
}
