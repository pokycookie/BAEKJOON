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
        // 각 의상 종류마다 가지고 있는 의상수에 + 1을 한 값을 모아 서로 곱함
        // 추가된 1개의 경우는 해당 의상 종류를 입지 않는 경우
        for (key in map.keys) ans *= map[key]!!.size + 1
        // 이렇게 만들어진 결과는 아무것도 입지 않는 경우도 포함하므로, 그 경우 1개를 제외하여 답을 구함
        bw.write((ans - 1).toString())
        bw.newLine()
    }
    bw.flush()
    bw.close()
}
