import java.util.StringTokenizer

val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val parents = IntArray(n + 1) { it }
    val parties = Array(m) { mutableListOf<Int>() }
    var truthParent = 0

    fun find(vertex: Int): Int {
        if (parents[vertex] == vertex) return vertex
        return find(parents[vertex])
    }

    fun union(v1: Int, v2: Int) {
        val p1 = find(v1)
        val p2 = find(v2)

        if (p1 < p2) parents[p2] = p1
        else parents[p1] = p2

        if (p1 == truthParent || p2 == truthParent) truthParent = minOf(p1, p2)
    }

    StringTokenizer(readLine()).also { st ->
        var prev = 0
        repeat(st.nextToken().toInt()) {
            val vertex = st.nextToken().toInt()
            if (prev != 0) union(vertex, prev)
            prev = vertex
        }
        truthParent = find(prev)
    }

    repeat(m) { i ->
        StringTokenizer(readLine()).also { st ->
            repeat(st.nextToken().toInt()) { parties[i].add(st.nextToken().toInt()) }
        }
    }

    for (party in parties) {
        var prev = 0
        for (vertex in party) {
            if (prev != 0) union(vertex, prev)
            prev = vertex
        }
    }

    var ans = 0
    for (party in parties) {
        var flag = true
        for (vertex in party) {
            if (find(vertex) == truthParent) {
                flag = false
                break
            }
        }
        if (flag) ans++
    }

    println(ans)
}
