val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val seq = readLine().split(" ")

        var ans = Int.MAX_VALUE

        // MBTI의 종류는 16가지 이므로, n > 16이면 최소 1개의 MBTI는 서로 겹친다.
        // 만약 n > 32이면 위의 경우의 2배이므로 MBTI가 최소 2개씩은 겹칠 수 있다.
        // 즉 n > 32인 경우, 무조건 같은 MBTI가 3개 존재할 수 있으므로, 세 사람의 최소 거리는 0이 된다.
        if (n > 32) ans = 0
        else {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    for (k in 0 until n) {
                        if (i == j || j == k || k == i) continue
                        ans = minOf(ans, diff(seq[i], seq[j], seq[k]))
                    }
                }
            }
        }
        bw.write(ans.toString())
        bw.newLine()
    }
    bw.flush()
    bw.close()
}

fun diff(m1: String, m2: String, m3: String): Int {
    if (m1.length != 4) throw Error()
    if (m2.length != 4) throw Error()
    if (m3.length != 4) throw Error()

    var res = 0
    for (i in 0 .. 3) {
        if (m1[i] != m2[i]) res ++
        if (m2[i] != m3[i]) res ++
        if (m3[i] != m1[i]) res ++
    }
    return res
}
