fun main() {
    val n = readln().toInt()
    val positiveSeq = mutableListOf<Int>()
    val negativeSeq = mutableListOf<Int>()
    var ans = 0

    for (i in 0 until n) {
        readln().toInt().also {
            if (it > 1) positiveSeq.add(it)
            else if (it == 1) ans += 1
            else negativeSeq.add(it)
        }
    }
    positiveSeq.sortDescending()
    negativeSeq.sort()

    for (i in 0 until positiveSeq.size step 2) {
        val a = positiveSeq[i]
        val b = if (i + 1 < positiveSeq.size) positiveSeq[i + 1] else 1
        ans += a * b
    }
    for (i in 0 until negativeSeq.size step 2) {
        val a = negativeSeq[i]
        val b = if (i + 1 < negativeSeq.size) negativeSeq[i + 1] else 1
        ans += a * b
    }

    println(ans)
}
