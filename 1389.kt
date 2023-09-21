val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    fun bfs(start: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = Array(n + 1) { false }
        var acc = 0

        queue.addLast(start to 0)
        visited[start] = true
        while (queue.isNotEmpty()) {
            val (vertex, depth) = queue.removeFirst()
            acc += depth

            for (child in graph[vertex]) {
                if (visited[child]) continue
                visited[child] = true
                queue.addLast(child to (depth + 1))
            }
        }

        return acc
    }

    var ans = 0
    var min = Int.MAX_VALUE
    for (vertex in 1 .. n) {
        val acc = bfs(vertex)
        if (acc < min) {
            ans = vertex
            min = acc
        }
    }
    println(ans)
}
