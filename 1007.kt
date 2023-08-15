import java.util.Stack
import kotlin.math.pow
import kotlin.math.sqrt

// ref: https://www.acmicpc.net/source/30505606

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val dots = Array(n) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            Vector(x, y)
        }
        val maxVector = dots.fold(Vector()) { acc, curr -> acc + curr }

        var res = Double.MAX_VALUE
        val stack = Stack<Int>()
        val visited = BooleanArray(n) { false }

        fun dfs(start: Int) {
            if (stack.size == n / 2) {
                val v1 = stack.fold(Vector()) { acc, curr -> acc + dots[curr] }
                val v2 = maxVector - v1
                res = minOf(res, v1.scalar(v2))
                return
            }
            for (i in start until n) {
                if (visited[i]) continue
                visited[i] = true
                stack.push(i)
                dfs(i + 1)
                stack.pop()
                visited[i] = false
            }
        }
        dfs(0)
        bw.write(res.toString())
        bw.newLine()
    }
    bw.flush()
    bw.close()
}

fun Int.pow(): Double {
    return this.toDouble().pow(2)
}

class Vector(var x: Int = 0, var y: Int = 0) {
    operator fun plus(target: Vector): Vector {
        return Vector(x + target.x, y + target.y)
    }
    operator fun minus(target: Vector): Vector {
        return Vector(x - target.x, y - target.y)
    }
    fun scalar(target: Vector): Double {
        return sqrt((x - target.x).pow() + (y - target.y).pow())
    }
}
