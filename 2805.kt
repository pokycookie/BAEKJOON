val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toLong() }
    val trees = readLine().split(" ").map { it.toLong() }.toLongArray()

    fun check(x: Long): Boolean {
        return trees.fold(0L) { acc, curr -> if (curr > x) acc + curr - x else acc } >= m
    }

    fun binarySearch(maxHeight: Long): Long {
        var left = 0L // true
        var right = maxHeight // false

        while (left + 1 < right) {
            val mid = (left + right) / 2
            if (check(mid)) left = mid
            else right = mid
        }

        return left
    }

    bw.write(binarySearch(1_000_000_000).toString())
    bw.flush()
}
