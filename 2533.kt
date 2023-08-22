val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val n = readLine().toInt()
    val graph = Array(n + 1) { mutableListOf<Int>() }
    val visited = BooleanArray(n + 1) { false }
    val dp = Array(n + 1) { Array(2) { 0 } }

    repeat(n - 1) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    fun dfs(vertex: Int) {
        if (visited[vertex]) return
        visited[vertex] = true
        for (child in graph[vertex]) {
            if (visited[child]) continue
            dfs(child)
            dp[vertex][0] += dp[child][1]
            dp[vertex][1] += minOf(dp[child][0], dp[child][1])
        }
        dp[vertex][1] += 1
    }
    dfs(1)

    println(minOf(dp[1][0], dp[1][1]))
}
