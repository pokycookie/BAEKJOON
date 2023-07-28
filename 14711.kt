fun main(args: Array<String>) {
    val n = readln().toInt()
    val k = Array(n) { IntArray(n) { 0 } }
    val board = Array(n) { BooleanArray(n) { false } }

    fun reverse(y: Int, x: Int) {
        for ((dx, dy) in arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
            val cx = x + dx
            val cy = y + dy
            if (cx !in 0 until n) continue
            if (cy !in 0 until n) continue
            k[cy][cx] += 1
        }
    }

    readln().toCharArray().forEachIndexed { i, e ->
        if (e == '#') {
            board[0][i] = true
            reverse(0, i)
        }
    }

    for (y in 1 until n) {
        for (x in 0 until n) {
            if (k[y - 1][x] % 2 == 0) continue
            board[y][x] = true
            reverse(y, x)
        }
    }

    for (line in board) {
        line.map {
            if (it) '#'
            else '.'
        }.also { print(it.joinToString("")) }
        println()
    }
}
