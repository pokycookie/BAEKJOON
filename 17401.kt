val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

const val prime = 1_000_000_007

fun main() = with(br) {
    val (t, n, d) = readLine().split(" ").map { it.toInt() }
    val paths = Array(t) { Array(n) { Array(n) { 0L } } }

    repeat(t) { i ->
        repeat(readLine().toInt()) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            paths[i][a - 1][b - 1] = c.toLong()
        }
    }

    if (d == 0) {
        val unit = Array(n) { Array(n) { 0L } }
        for (i in 0 until n) unit[i][i] = 1L
        val unitMatrix = Matrix(unit)
        unitMatrix.print()
    } else {
        val matrix = Array(t) { Matrix(paths[it]) }
        var matrixSet = matrix[0]
        for (i in 1 until matrix.size) matrixSet *= matrix[i]

        var ans = matrixSet.pow(d / t)
        for (i in 0 until d % t) ans *= matrix[i]
        ans.print()
    }
    bw.close()
}


class Matrix(val entries: Array<Array<Long>>) {
    private val row = entries.size
    private val column = if (row > 0) entries[0].size else 0

    operator fun times(target: Matrix): Matrix {
        if (this.column != target.row) throw IllegalArgumentException("Invalid matrix dimensions for multiplication")
        val result = Array(this.row) { Array(target.column) { 0L } }
        for (r in 0 until this.row) {
            for (c in 0 until target.column) {
                for (v in 0 until this.column) {
                    result[r][c] += (this.entries[r][v] * target.entries[v][c]) % prime
                    result[r][c] = result[r][c] % prime
                }
            }
        }
        return Matrix(result)
    }

    fun pow(exp: Int): Matrix {
        if (exp < 1) {
            val n = this.entries.size
            val unit = Array(n) { Array(n) { 0L } }
            for (i in 0 until n) unit[i][i] = 1L
            return Matrix(unit)
        }
        if (exp == 1) return this
        val res = this.pow(exp / 2)
        return if (exp % 2 == 0) res * res else res * res * this
    }

    fun print() {
        entries.forEach {
            bw.write(it.joinToString(" "))
            bw.newLine()
        }
        bw.flush()
    }
}
