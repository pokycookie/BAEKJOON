fun main(args: Array<String>) {
    val n = readln().toInt()
    val seq = readln().split(" ").map { it.toInt() }
    val ans = mutableListOf<Int>()

    ans.add(seq[0])
    for (i in 1 until n) {
        if (seq[i] > ans.last()) ans.add(seq[i])
        else ans[lowerBound(seq[i], ans)] = seq[i]
    }
    println(ans.size)
}

fun lowerBound(value: Int, list: List<Int>): Int {
    var start = 0
    var end = list.size - 1

    while (start < end) {
        val mid = (start + end) / 2
        if (list[mid] < value) start = mid + 1
        else end = mid
    }

    return end
}
