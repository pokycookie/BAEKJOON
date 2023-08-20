import java.util.PriorityQueue

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val seq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
    repeat(m) {
        val (a, b, w) = readLine().split(" ").map { it.toInt() }
        seq.add(Triple(w, a, b))
    }

    val ans = PriorityQueue<Int>(compareByDescending { it })
    val graph = SpanningTree(n + 1)
    while (seq.isNotEmpty()) {
        val (w, a, b) = seq.poll()
        if (graph.add(a, b)) ans.add(w)
    }

    println(ans.sum() - ans.poll())
}

class SpanningTree(private val size: Int) {
    private val parents = Array(size) { it }

    private fun find(vertex: Int): Int {
        return if (parents[vertex] == vertex) vertex else find(parents[vertex])
    }

    private fun union(v1: Int, v2: Int) {
        val p1 = find(v1)
        val p2 = find(v2)

        if (p1 < p2) parents[p2] = p1
        else parents[p1] = p2
    }

    fun add(v1: Int, v2: Int): Boolean {
        return if (find(v1) == find(v2)) false
        else {
            union(v1, v2)
            true
        }
    }
}
