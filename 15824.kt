val br = System.`in`.bufferedReader()

const val prime = 1_000_000_007

fun main() = with(br) {
    val n = readLine().toInt()
    val menu = readLine().split(" ").map { it.toInt() }.sorted()
    val pow = LongArray(n + 1) { 1L }

    for (i in 1 .. n) pow[i] = (pow[i - 1] * 2) % prime

    var ans = 0L
    for (i in 0 until n) {
        ans += menu[i] * (pow[i] - pow[n - i - 1])
        ans %= prime
    }
    println(ans)
}
