import java.util.PriorityQueue

fun main(args: Array<String>) {
    // n: 보석의 정보 수, k: 가방의 정보 수
    val (n, k) = readln().split(" ").map { it -> it.toInt() }
    val jewels = mutableListOf<Pair<Int, Int>>()
    val bags = IntArray(k)

    // jewels에 값 추가
    for (i in 0 until n) {
        val (m, v) = readln().split(" ").map { it -> it.toInt() }
        jewels.add(Pair(m, v))
    }
    // 내림차순으로 값 정렬
    jewels.sortByDescending { it.first }

    // bags에 값 추가
    for (i in 0 until k) {
        bags[i] = readln().toInt()
    }
    // 오름차순으로 값 정렬
    bags.sort()

    var ans = 0L
    val heap = PriorityQueue<Int>(compareByDescending { it }) // 최대힙 heap

    // 가방을 오름차순으로 순회
    for (bag in bags) {
        // 현재 가방이 담을 수 있는 무게가 나오는 한 계속해서 보석을 뽑음
        while (jewels.isNotEmpty() && bag >= jewels.last().first) {
            // 뽑은 보석의 가격을 최대힙 heap에 추가
            heap.add(jewels.removeLast().second)
        }
        // 최대힙에 저장해 둔 보석 중에서 가장 비싼 보석을 뽑아 현재 가방에 담음
        // 이전에 뽑아둔 보석을 담을 수도 있음
        // 가방은 오름차순이므로 항상 이전의 가방보다 용량이 크기 때문에, 이전에 뽑아둔 보석은 반드시 담을 수 있음
        if (!heap.isEmpty()) ans += heap.poll()
    }

    print(ans)
}
