fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val positive = mutableListOf<Int>(0)
    val negative = mutableListOf<Int>(0)

    readln().split(" ").map { it.toInt().also { it ->
        if (it > 0) positive.add(it)
        else negative.add(-it)
    } }
    positive.sortDescending()
    negative.sortDescending()

    var ans = 0

    for (i in 0 until positive.size step m) ans += positive[i] * 2
    for (i in 0 until negative.size step m) ans += negative[i] * 2

    if (positive[0] > negative[0]) ans -= positive[0]
    else ans -= negative[0]

    println(ans)
}
