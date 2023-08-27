// Segment Tree

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val tree = SegmentTree(LongArray(n) { readLine().toLong() })

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        bw.write(tree.minMax(a - 1, b - 1).toString())
        bw.newLine()
    }

    bw.flush()
    bw.close()
}

class SegmentTree(private val array: LongArray) {
    private val tree = Array<MinMax>(array.size * 4) { MinMax(0, 0) }

    init {
        build(0, array.size - 1, 1)
    }

    private fun build(start: Int, end: Int, node: Int): MinMax {
        val mid = (start + end) / 2
        if (start == end) tree[node] = MinMax(array[start], array[start])
        else tree[node] = build(start, mid, node * 2) + build(mid + 1, end, node * 2 + 1)
        return tree[node]
    }

    private fun privateMinMax(start: Int, end: Int, node: Int, left: Int, right: Int): MinMax {
        if (left > end || right < start) return MinMax(Long.MAX_VALUE, Long.MIN_VALUE) // 범위를 완전히 벗어남
        if (left <= start && end <= right) return tree[node] // 범위에 완전히 들어옴
        // 범위에 어중간하게 걸침
        val mid = (start + end) / 2
        return privateMinMax(start, mid, node * 2, left, right) + privateMinMax(mid + 1, end, node * 2 + 1, left, right)
    }

    fun minMax(start: Int, end: Int): MinMax {
        return privateMinMax(0, array.size - 1, 1, start, end)
    }
}

class MinMax(private val min: Long, private val max: Long) {
    operator fun plus(target: MinMax): MinMax {
        return MinMax(minOf(this.min, target.min), maxOf(this.max, target.max))
    }

    override fun toString(): String {
        return "$min $max"
    }
}
