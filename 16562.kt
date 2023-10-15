val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    val costs = intArrayOf(0) + readLine().split(" ").map { it.toInt() }.toIntArray()
    val parents = IntArray(n + 1) { it }

    fun find(vertex: Int): Int {
        if (parents[vertex] == vertex) return vertex
        return find(parents[vertex])
    }

    fun union(v1: Int, v2: Int) {
        val p1 = find(v1)
        val p2 = find(v2)

        if (costs[p1] < costs[p2]) parents[p2] = p1
        else parents[p1] = p2
    }

    repeat(m) {
        val (v, w) = readLine().split(" ").map { it.toInt() }
        union(v, w)
    }

    val ans = parents.foldIndexed(0) { i, acc, curr -> if (i == curr) acc + costs[curr] else acc }
    if (k < ans) println("Oh no")
    else println(ans)
}
