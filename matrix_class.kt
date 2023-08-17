class Matrix(val entries: Array<Array<Long>>) {
    private val row = entries.size
    private val column = if (row > 0) entries[0].size else 0

    operator fun times(target: Matrix): Matrix {
        if (this.column != target.row) throw throw IllegalArgumentException("Invalid matrix dimensions for multiplication")
        val result = Array(this.row) { Array(target.column) { 0L } }
        for (r in 0 until this.row) {
            for (c in 0 until target.column) {
                for (v in 0 until this.column) {
                    result[r][c] += this.entries[r][v] * target.entries[v][c]
                }
            }
        }
        return Matrix(result)
    }

    fun pow(exp: Int): Matrix {
        if (exp == 1) return this
        val res = this.pow(exp / 2)
        return if (exp % 2 == 0) res * res else res * res * this
    }
}
