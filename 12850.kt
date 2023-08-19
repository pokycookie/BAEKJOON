val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

const val prime = 1_000_000_007

fun main() = with(br) {
    val d = readLine().toLong()
    val matrixArray = arrayOf<Array<Long>>(
        arrayOf(0, 1, 1, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 1, 1, 0, 0, 0, 0),
        arrayOf(1, 1, 0, 1, 1, 0, 0, 0),
        arrayOf(0, 1, 1, 0, 1, 1, 0, 0),
        arrayOf(0, 0, 1, 1, 0, 1, 0, 1),
        arrayOf(0, 0, 0, 1, 1, 0, 1, 0),
        arrayOf(0, 0, 0, 0, 0, 1, 0, 1),
        arrayOf(0, 0, 0, 0, 1, 0, 1, 0),
    )
    val matrix = Matrix(matrixArray).pow(d)
    println(matrix.entries[0][0])
}

class Matrix(val entries: Array<Array<Long>>) {
    private val row = entries.size
    private val column = if (row > 0) entries[0].size else 0

    operator fun times(target: Matrix): Matrix {
        if (this.column != target.row) throw throw IllegalArgumentException("Invalid matrix dimensions for multiplication")
        return Matrix(Array(row) { r ->
            Array(target.column) { c ->
                var res = 0L
                for (i in 0 until this.column) {
                    res += (entries[r][i] * target.entries[i][c]) % prime
                }
                res % prime
            }
        })
    }

    fun pow(exp: Long): Matrix {
        if (exp < 1) return this
        if (exp == 1L) return Matrix(Array(row) { r ->
            Array(column) { c -> entries[r][c] % prime}
        })
        val res = this.pow(exp / 2)
        return if (exp % 2 == 0L) res * res else res * res * this
    }
}
