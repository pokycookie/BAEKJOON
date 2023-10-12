import java.util.Stack

val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val n = readLine().toInt()
    val heights = IntArray(n) { readLine().toInt() }

    var ans = 0
    val stack = Stack<Int>()

    for(i in 0 until n) {
        while (stack.isNotEmpty()) {
            if (heights[stack.peek()] <= heights[i]) break
            val height = heights[stack.pop()]
            val width = if (stack.isEmpty()) i else i - 1 - stack.peek()
            ans = maxOf(ans, width * height)
        }
        stack.push(i)
    }
    while (stack.isNotEmpty()) {
        val height = heights[stack.pop()]
        val width = if (stack.isEmpty()) n else n - 1 - stack.peek()
        ans = maxOf(ans, width * height)
    }

    println(ans)
}
