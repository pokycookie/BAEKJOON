import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val schedule = mutableListOf<Pair<Int, Int>>()
    val rooms = PriorityQueue<Int>()

    // 모든 일정을 schedule에 넣고 시작시간을 기준으로 오름차순 정렬
    repeat(n) {
        val (s, t) = readln().split(" ").map { it.toInt() }
        schedule.add(Pair(s, t))
    }
    schedule.sortBy { it.first }

    // 우선순위 큐에 첫 번째 수업을 추가 (종료시간을 추가)
    rooms.add(schedule[0].second)
    // 두 번째 수업부터 마지막 수업까지 순환
    for (i in 1 until n) {
        if (schedule[i].first < rooms.peek()) {
            // 현재 수업중인 강의실 중에서 가장 빨리 끝날 강의실을 항상 선택
            // 해당 강의실 수업이 끝나기 전 현재 수업이 시작되는 경우
            rooms.add(schedule[i].second) // 새로운 강의실을 추가하여 이용
        } else {
            // 해당 강의실 수업이 끝나고 현재 수업을 진행할 수 있는 경우
            // 이전 수업을 종료 후 현재 수업을 우선순위 큐에 추가 (종료시간을 추가)
            // 새로운 강의실을 추가하지 않으므로, poll 후 add 하여 rooms.size를 그대로 유지
            rooms.poll()
            rooms.add(schedule[i].second)
        }
    }

    println(rooms.size)
}
