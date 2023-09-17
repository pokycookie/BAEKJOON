val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val (v, e) = readLine().split(" ").map { it.toInt() }
    val graph = Array(v + 1) { mutableListOf<Int>() }

    repeat(e) {
        val (v1, v2) = readLine().split(" ").map { it.toInt() }
        graph[v1].add(v2)
        graph[v2].add(v1)
    }

    var cnt = 0 // 방문 순서를 세기 위한 카운터
    val visited = IntArray(v + 1) { 0 } // 방문 순서 저장을 위한 배열 (0이면 방문한적 없음)
    val ans = mutableSetOf<Int>() // 단절점

    fun dfs(vertex: Int, isRoot: Boolean): Int {
        visited[vertex] = ++cnt // 방문 순서를 저장
        var closest = visited[vertex] // 인접한 정점 중 가장 빠른 방문 순서 (첫 선언은 자기 자신의 방문 순서로 초기화)
        var rootChild = graph[vertex].size

        // 자신과 연결된 정점들에 대해 탐색
        for (child in graph[vertex]) {
            // 다음으로 탐색할 정점이 이미 방문한 정점인경우
            if (visited[child] != 0) {
                closest = minOf(closest, visited[child]) // 가장 빠른 방문순서를 갱신
                rootChild--
                continue
            }
            // 다음으로 탐색할 정점에 대해서도 동일하게 dfs 실행
            dfs(child, false).also {
                // 현재 정점이 루트가 아니고, 다음으로 탐색할 정점에서 가장 빠른 방문 순서가 지금 정점의 방문순서보다 느린 경우
                // 즉, 다음 정점이 지금 정점(vertex)를 거치지 않고는 이전 정점들을 방문할 수 없는 경우
                if (!isRoot && it >= visited[vertex]) ans.add(vertex) // 지금 정점을 단절점으로 판단
                closest = minOf(closest, it) // 가장 빠른 방문순서를 갱신
            }
        }
        // 루트이면서 자식의 수가 둘 이상인 경우 무조건 단절점으로 판단
        if (isRoot && rootChild > 1) ans.add(vertex)

        // 인접한 정점 중 가장 빠른 방문 순서를 반환
        return closest
    }
    // 주어지는 그래프가 연결 그래프가 아닐 수 있으므로 모든 정점에 대해 dfs 실행
    for (vertex in 1 .. v) if (visited[vertex] == 0) dfs(vertex, true)

    val sorted = ans.sorted()
    println(ans.size)
    println(sorted.joinToString(" "))
}
