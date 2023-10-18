val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    val graph = Array(n + 1) { mutableListOf<Int>() }
    val groups = mutableListOf<MutableList<Int>>()
    val visited = BooleanArray(n + 1) { false }
    val dist = Array(n + 1) { IntArray(n + 1) { 999 } }

    for (i in 1 .. n) dist[i][i] = 0

    fun grouping(start: Int): MutableList<Int> {
        val result = mutableListOf(start)

        visited[start] = true
        for (next in graph[start]) {
            if (visited[next]) continue
            result.addAll(grouping(next))
        }
        return result
    }

    fun floyd(group: List<Int>): Int {
        var resVertex = 0
        var minDist = Int.MAX_VALUE

        for (waypoint in group) {
            for (start in group) {
                for (end in group) {
                    dist[start][end] = minOf(dist[start][end], dist[start][waypoint] + dist[waypoint][end])
                }
            }
        }
        for (vertex in group) {
            val list = mutableListOf<Int>()
            for (target in group) list.add(dist[vertex][target])

            val maxDist = list.max()
            if (maxDist < minDist) {
                minDist = maxDist
                resVertex = vertex
            }
        }
        return resVertex
    }

    repeat(m) {
        val (v1, v2) = readLine().split(" ").map { it.toInt() }
        graph[v1].add(v2)
        graph[v2].add(v1)
        dist[v1][v2] = 1
        dist[v2][v1] = 1
    }
    for (vertex in 1 .. n) if (!visited[vertex]) groups.add(grouping(vertex))

    val ans = mutableListOf<Int>()
    for (group in groups) ans.add(floyd(group))
    ans.sort()

    bw.write(ans.size.toString())
    bw.newLine()

    for (e in ans) {
        bw.write(e.toString())
        bw.newLine()
    }

    bw.flush()
    bw.close()
}
