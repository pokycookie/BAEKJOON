val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (x, y, a0, a1, n) = readLine().split(" ").map { it.toInt() }

    when (n) {
        0 -> println(a0.toString().padStart(2, '0'))
        1 -> println(a1.toString().padStart(2, '0'))
        else -> {
            val matrix = Matrix(arrayOf(arrayOf(x.toLong(), y.toLong()), arrayOf(1L, 0L))).pow(n - 1)
            val an = matrix.entries[0][0] * a1 + matrix.entries[0][1] * a0
            println((an % 100).toString().padStart(2, '0'))
        }
    }
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
                    res += (entries[r][i] * target.entries[i][c]) % 100
                }
                res % 100
            }
        })
    }

    fun pow(exp: Int): Matrix {
        if (exp < 1) return this
        if (exp == 1) return Matrix(Array(row) { r ->
            Array(column) { c -> entries[r][c] % 100}
        })
        val res = this.pow(exp / 2)
        return if (exp % 2 == 0) res * res else res * res * this
    }
}
