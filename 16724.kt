val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val (r, c) = readLine().split(" ").map { it.toInt() }
    val graph = Array(r * c) { mutableListOf<Int>() }
    val visited = BooleanArray(r * c) { false }

    repeat(r) { row ->
        readLine().toCharArray().forEachIndexed { column, char ->
            val currVertex = row * c + column
            var nextVertex = 0
            when (char) {
                'L' -> nextVertex = row * c + (column - 1)
                'R' -> nextVertex = row * c + (column + 1)
                'U' -> nextVertex = (row - 1) * c + column
                'D' -> nextVertex = (row + 1) * c + column
            }
            graph[currVertex].add(nextVertex)
            graph[nextVertex].add(currVertex)
        }
    }

    fun dfs(vertex: Int) {
        visited[vertex] = true
        for (nextVertex in graph[vertex]) {
            if (visited[nextVertex]) continue
            dfs(nextVertex)
        }
    }

    var ans = 0
    for (vertex in 0 until r * c) {
        if (visited[vertex]) continue
        dfs(vertex)
        ans++
    }

    println(ans)
}
