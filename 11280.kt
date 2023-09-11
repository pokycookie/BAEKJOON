import java.util.Stack

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n * 2 + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (i, j) = readLine().split(" ").map { it.toInt() }
        graph[(-i).n(n)].add(j.n(n))
        graph[(-j).n(n)].add(i.n(n))
    }

    val stack = Stack<Int>()
    val visited = IntArray(n * 2 + 1) { 0 }
    val finished = BooleanArray(n * 2 + 1) { false }
    val scc = IntArray(n * 2 + 1) { 0 }
    var cnt = 0
    var sccCnt = 1

    fun dfs(vertex: Int): Int {
        visited[vertex] = ++cnt
        stack.push(vertex)

        var parent = visited[vertex]
        for (child in graph[vertex]) {
            if (visited[child] == 0) parent = minOf(parent, dfs(child))
            else if (!finished[child]) parent = minOf(parent, visited[child])
        }

        if (parent == visited[vertex]) {
            while (stack.isNotEmpty()) {
                val popped = stack.pop()
                finished[popped] = true
                scc[popped] = sccCnt
                if (popped == vertex) break
            }
            sccCnt++
        }

        return parent
    }
    for (vertex in 1 .. n * 2) if (visited[vertex] == 0) dfs(vertex)

    var ans = 1
    for (i in 1 .. n) {
        if (scc[i] != scc[(-i).n(n)]) continue
        ans = 0
        break
    }
    println(ans)
}

fun Int.n(n: Int): Int = when {
    this >= 0 -> this
    else -> -this + n
}
