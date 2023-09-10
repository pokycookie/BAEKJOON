val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

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
    val ans = mutableSetOf<Pair<Int, Int>>() // 단절점

    fun dfs(vertex: Int, parent: Int): Int {
        visited[vertex] = ++cnt // 방문 순서를 저장
        var closest = visited[vertex] // 인접한 정점 중 (vertex <-> parent)간선을 사용하지 않는 가장 빠른 방문 순서

        // 자신과 연결된 정점들에 대해 탐색
        for (child in graph[vertex]) {
            if (child == parent) continue
            // 다음으로 탐색할 정점이 이미 방문한 정점인경우
            if (visited[child] != 0) {
                closest = minOf(closest, visited[child]) // 가장 빠른 방문순서를 갱신
                continue
            }
            // 다음으로 탐색할 정점에 대해서도 동일하게 dfs 실행
            dfs(child, vertex).also {
                // 다음으로 탐색할 정점에서 가장 빠른 방문 순서가 지금 정점의 방문순서보다 느린 경우
                // 즉, 다음 정점이 지금 정점(vertex)를 거치지 않고는 이전 정점들을 방문할 수 없는 경우
                if (it > visited[vertex]) {
                    if (vertex < child) ans.add(vertex to child)
                    else ans.add(child to vertex)
                }
                closest = minOf(closest, it) // 가장 빠른 방문순서를 갱신
            }
        }

        // 인접한 정점 중 가장 빠른 방문 순서를 반환
        return closest
    }

    dfs(1, 0)
    val sorted = ans.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })

    bw.write(ans.size.toString())
    bw.newLine()
    sorted.forEach {
        bw.write("${it.first} ${it.second}")
        bw.newLine()
    }
    bw.flush()
    bw.close()
}
