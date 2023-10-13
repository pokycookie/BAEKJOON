val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
    val weightSet = mutableSetOf<Int>()

    repeat(m) {
        val (v1, v2, w) = readLine().split(" ").map { it.toInt() }
        graph[v1].add(v2 to w)
        graph[v2].add(v1 to w)
        weightSet.add(w)
    }
    val weights = weightSet.sorted()

    val (start, end) = readLine().split(" ").map { it.toInt() }

    fun bfs(limit: Int): Boolean {
        val visited = BooleanArray(n + 1) { false }
        val queue = ArrayDeque<Int>()
        queue.add(start)

        while (queue.isNotEmpty()) {
            val currVertex = queue.removeFirst()
            for ((nextVertex, nextPath) in graph[currVertex]) {
                if (nextPath < limit) continue
                if (visited[nextVertex]) continue
                queue.add(nextVertex)
                visited[nextVertex] = true
            }
        }
        return visited[end]
    }

    fun binarySearch(): Int {
        var left = -1 // true
        var right = weights.size // false

        while (left + 1 < right) {
            val mid = (left + right) / 2
            if (bfs(weights[mid])) left = mid
            else right = mid
        }

        return weights[left]
    }

    println(binarySearch())
}
