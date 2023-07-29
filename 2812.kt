import java.util.Stack

fun main(args: Array<String>) {
    var (n, k) = readln().split(" ").map { it.toInt() }
    val nums = readln().map { it.toString().toInt() }

    val stack = Stack<Int>()
    // 자릿수 순서대로 숫자를 하나씩 탐색
    for (num in nums) {
        // 스택의 가장 최근 요소가 현재 숫자보다 작다면 해당 스택 요소를 pop
        // 빼낼 수 있는 수 k를 모두 사용하였다면 더 이상 스택에서 요소를 빼지 않음
        while (stack.isNotEmpty() && stack.peek() < num && k-- > 0) stack.pop()
        // 현재 숫자를 스택에 추가
        stack.add(num)
    }
    // 모든 숫자를 넣었음에도 더 빼야하는 수가 있다면, 뒷자리부터 순서대로 제거
    while (k-- > 0) stack.pop()

    // 스택에 쌓인 순서대로 숫자를 합침
    println(stack.joinToString(""))
}
