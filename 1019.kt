import kotlin.math.pow

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val pageList = readLine().toCharArray().map { it.digitToInt() }
    val digits = Array(pageList.size) { IntArray(10) { 0 } }
    val ans = IntArray(10) { 0 }

    var prev = 0
    for ((i, digit) in pageList.withIndex()) {
        for (j in 0 .. 9) digits[i][j] += prev
        for (j in 1 .. digit) digits[i][j] += 1
        prev = prev * 10 + digit
    }

    prev = 1
    for (i in pageList.size - 1 downTo 0) {
        val exp = pageList.size - i - 1
        for (j in 0 .. 9) {
            ans[j] += digits[i][j] * 10.0.pow(exp).toInt()
            if (j == pageList[i] && i != pageList.size - 1) ans[j] -= 10.0.pow(exp).toInt() - prev
        }
        prev += pageList[i] * 10.0.pow(exp).toInt()
    }

    println(ans.joinToString(" "))
}
