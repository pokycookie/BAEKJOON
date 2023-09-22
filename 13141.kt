import kotlin.math.abs

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val minEdge = Array(n + 1) { IntArray(n + 1) { 999_999_999 } }
    val maxEdge = Array(n + 1) { IntArray(n + 1) { 0 } }

    // 각 간선에 대한 정보 저장
    repeat(m) {
        val (s, e, l) = readLine().split(" ").map { it.toInt() }
        // s와 e 사이의 간선 중 가장 긴 간선만 maxEdge 배열에 저장
        maxEdge[s][e] = maxOf(maxEdge[s][e], l * 2)
        maxEdge[e][s] = maxOf(maxEdge[e][s] ,l * 2)

        // s와 e 사이의 간선 중 가장 짧은 간선만 minEdge 배열에 저장
        minEdge[s][e] = minOf(minEdge[s][e], l * 2)
        minEdge[e][s] = minOf(minEdge[e][s], l * 2)
    }

    // 플로이드 워셜 알고리즘을 사용해 각 정점 간 최단거리를 2차원 배열에 저장
    for (i in 1 .. n) minEdge[i][i] = 0
    for (waypoint in 1 .. n) {
        for (start in 1 .. n) {
            for (end in 1 .. n) {
                minEdge[start][end] = minOf(minEdge[start][end], minEdge[start][waypoint] + minEdge[waypoint][end])
            }
        }
    }

    fun burn(start: Int): Int {
        var maxTime = 0

        // 시작점이 start 일 때,
        // 정점 v1과 정점 v2 사이의 간선이 완전히 불타는 시간을 계산
        for(v1 in 1 .. n) {
            for (v2 in 1 .. n) {
                // 정점 v1과 정점 v2 사이에 간선이 없는 경우
                if (maxEdge[v1][v2] == 0) continue

                val t1 = minEdge[start][v1] // v1이 불타기 시작한 시간
                val t2 = minEdge[start][v2] // v2가 불타기 시작한 시간

                // 두 정점 사이의 간선이 불타기 시작하는 시간은
                // 두 정점이 불타기 시작하는 시간 중 빠른 것과 같음
                val startTime = minOf(t1, t2)

                // 두 정점 사이의 간선이 불타고 있는 시간은
                // 한쪽으로만 간선이 타는 시간 + 양쪽으로 간선이 타는 시간
                // 한쪽으로만 간선이 타는 시간 = t1과 t2의 차이 (양쪽 정점이 둘 다 불타기 직전까지)
                // 양쪽으로 간선이 타는 시간 = 두 정점 사이의 가장 긴 간선이 타는데 걸리는 시간 중 한쪽으로만 타는 시간을 빼고 2로 나눔(양쪽에서 불타므로 2배로 빨리 탄다)
                val burnTime = abs(t1 - t2) + (maxEdge[v1][v2] - abs(t1 - t2)) / 2

                maxTime = maxOf(maxTime, startTime + burnTime)
            }
        }

        return maxTime
    }

    var ans = Int.MAX_VALUE
    for (vertex in 1 .. n) ans = minOf(ans, burn(vertex))

    if (ans % 2 == 0) println("${ans / 2}.0")
    else println("${(ans - 1) / 2}.5")
}
