val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val map = mutableMapOf<String, MutableList<String>>()

        repeat(n) {
            val (name, type) = readLine().split(" ")
            if (map.containsKey(type)) map[type]!!.add(name)
            else map[type] = mutableListOf(name)
        }

        var ans = 1
        for (key in map.keys) ans *= map[key]!!.size + 1
        bw.write((ans - 1).toString())
        bw.newLine()
    }
    bw.flush()
    bw.close()
}
