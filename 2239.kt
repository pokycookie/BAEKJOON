val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val sudoku = Array(9) {
        readLine().map { it.toString().toInt() }.toIntArray()
    }
    val emptySpace = mutableListOf<Pair<Int, Int>>()

    for (row in 0 until 9) {
        for (column in 0 until 9) {
            if (sudoku[row][column] == 0) emptySpace.add(row to column)
        }
    }

    var ans = Array(9) { IntArray(9) { 0 } }
    var flag = false

    fun dfs(idx: Int) {
        if (flag) return
        if (idx >= emptySpace.size) return
        val (row, column) = emptySpace[idx]
        val tmp = find(row, column, sudoku)
        if (tmp.isEmpty()) return
        for (t in tmp) {
            sudoku[row][column] = t
            dfs(idx + 1)
            if (idx != emptySpace.size - 1) sudoku[row][column] = 0
        }
        if (idx == emptySpace.size - 1) {
            ans = Array(9) { sudoku[it].copyOf() }
            flag = true
        }
    }
    dfs(0)

    ans.forEach { println(it.joinToString("")) }
}

fun find(row: Int, column: Int, sudoku: Array<IntArray>): IntArray {
    val res = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    for (tr in 0 until 9) res.remove(sudoku[tr][column])
    for (tc in 0 until 9) res.remove(sudoku[row][tc])
    for (tr in (row / 3) * 3 until (row / 3 + 1) * 3) {
        for (tc in (column / 3) * 3 until (column / 3 + 1) * 3) res.remove(sudoku[tr][tc])
    }

    return res.toIntArray()
}
