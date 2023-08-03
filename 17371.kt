import kotlin.math.abs
import kotlin.math.pow

fun main(args: Array<String>) {
    val n = readln().toInt()
    val conveniences = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        conveniences.add(x to y)
    }

    var minDistance = Double.MAX_VALUE
    var ans = 0 to 0

    for (i in 0 until n) {
        var maxDistance = Double.MIN_VALUE
        val (x1, y1) = conveniences[i]
        for (j in 0 until n) {
            val (x2, y2) = conveniences[j]
            val dist = abs(x1 - x2).toDouble().pow(2) + abs(y1 - y2).toDouble().pow(2)
            if (dist > maxDistance) maxDistance = dist
        }
        if (maxDistance < minDistance) {
            minDistance = maxDistance
            ans = x1 to y1
        }
    }

    println("${ans.first} ${ans.second}")
}
