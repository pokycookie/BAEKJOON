fun main(args: Array<String>) {
    val n = readln().toInt()
    val times = IntArray(n) { 0 }
    val graph = Array<MutableList<Int>>(n) { mutableListOf() }
    readln().split(" ").map { it.toInt() }.also {
        for (i in it.indices) {
            val parent = it[i]
            if (parent >= 0) graph[parent].add(i)
        }
    }

    fun dfs(vertex: Int) {
        if (graph[vertex].isEmpty()) return
        val tmp = mutableListOf<Int>()
        for (child in graph[vertex]) {
            dfs(child)
            tmp.add(times[child])
        }
        tmp.sortDescending()
        val time = mutableListOf<Int>()
        for (i in 1 .. graph[vertex].size) time.add(tmp[i - 1] + i)
        times[vertex] = time.max()
    }

    dfs(0)
    print(times[0])
}
