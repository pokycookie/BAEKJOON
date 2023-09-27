import java.util.Stack

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val n = readLine().toInt()
    val graph = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    val ans = mutableListOf<IntArray>()

    fun bfs(start: Int) {
        val stack = Stack<Int>()
        val visited = IntArray(n) { 0 }

        stack.add(start)
        while (stack.isNotEmpty()) {
            val curr = stack.pop()

            for (child in 0 until n) {
                if (graph[curr][child] == 0) continue
                if (visited[child] == 1) continue
                stack.add(child)
                visited[child] = 1
            }
        }

        ans.add(visited)
    }

    for (vertex in 0 until n) bfs(vertex)

    ans.forEach {
        bw.write(it.joinToString(" "))
        bw.newLine()
    }
    bw.flush()
    bw.close()
}
