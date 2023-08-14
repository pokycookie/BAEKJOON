fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) {
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val durations = listOf(0) + br.readLine().split(" ").map { it.toInt() }
        val graph = Array(n + 1) { mutableListOf<Int>() }
        repeat(k) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            graph[y].add(x)
        }
        val w = br.readLine().toInt()

        val dp = Array(n + 1) { -1 }
        fun dfs(vertex: Int) {
            val tmp = mutableListOf<Int>()
            for (child in graph[vertex]) {
                if (dp[child] == -1) dfs(child)
                tmp.add(dp[child])
            }
            dp[vertex] = (tmp.maxOrNull() ?: 0) + durations[vertex]
        }
        dfs(w)

        bw.write(dp[w].toString())
        bw.newLine()
    }
    bw.flush()
}
