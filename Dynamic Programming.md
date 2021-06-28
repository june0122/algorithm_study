# 다이나믹 프로그래밍<small>(Dynamic Programming, DP)</small>

> 여러 개의 하위 문제를 먼저 푼 후 그 결과를 쌓아올려 주어진 문제를 해결하는 알고리즘

### DP를 푸는 과정

1. 테이블 정의하기
2. 점화식 찾기
3. 초기값 정하기

## 연습 문제

### [BOJ 1463 : 1로 만들기](https://www.acmicpc.net/problem/1463)

> DP

```kotlin
import java.util.*
import kotlin.math.*

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val d = IntArray(n + 1)

    for (i in 2..n) {
        d[i] = d[i - 1] + 1
        if (i % 2 == 0) d[i] = min(d[i], d[i / 2] + 1)
        if (i % 3 == 0) d[i] = min(d[i], d[i / 3] + 1)
    }

    println(d[n])
}
```

> BFS

```kotlin
import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    val queue: Queue<Int> = LinkedList()
    val dist = IntArray(n + 1)
    val dx = intArrayOf(1, 2, 3)

    dist[1] = 0
    queue.offer(1)

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (dir in dx.indices) {
            val next = when (dir) {
                0 -> cur + dx[0]
                else -> cur * dx[dir]
            }

            if (next > n) continue
            if (dist[next] != 0) continue

            dist[next] = dist[cur] + 1
            queue.offer(next)
        }
    }
    println(dist[n])
}
```

### [BOJ 9095 : 1, 2, 3 더하기](https://www.acmicpc.net/problem/9095)

```kotlin
import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val t = nextInt()
    val d = IntArray(11)

    d[1] = 1
    d[2] = 2
    d[3] = 4

    for (i in 4 until 11) {
        d[i] = d[i - 1] + d[i - 2] + d[i - 3]
    }

    repeat(t) { println(d[nextInt()]) }
} 
```

### [BOJ 2579 : 계단 오르기](https://www.acmicpc.net/problem/2579)

> 2차원 배열 이용

```kotlin
import java.util.*
import kotlin.math.max

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val s = IntArray(n + 1)
    val d = Array(n + 1) { IntArray(3) }

    for (i in 1..n) s[i] = nextInt()

    if (n == 1) {
        println(s[1])
        return@with
    }

    d[1][1] = s[1]
    d[1][2] = 0
    d[2][1] = s[2]
    d[2][2] = s[1] + s[2]

    for (i in 3..n) {
        d[i][1] = max(d[i - 2][1], d[i - 2][2]) + s[i]
        d[i][2] = d[i - 1][1] + s[i]
    }

    println(max(d[n][1], d[n][2]))
}
```

`D[i][j]` = 현재까지 `j`개의 계단을 연속해서 밟고 `i`번째 계단까지 올라섰을 때 점수 합의 최댓값, 단 `i`번째 계단은 반드시 밟아야 함

이렇게 2차원 배열을 선언한 이유는 지금까지 몇 개의 계단을 밟았는지에 대한 정보가 추가로 있어야 점화식을 세울 때 계단을 오르는 규칙을 고려할 수 있기 때문이다. 그리고 i번째 계단은 반드시 밟아야 한다는 조건이 있어야 점화식을 이끌어낼 수 있다. 이 2차원 배열에서 `j`는 어떤 값을 가지냐 보면 `i`번째 계단을 반드시 밟아야 한다는 조건이 있어서 `j = 1` 혹은 `2`이다. 연속된 세 개의 계단을 모두 밟아서는 안된다는 조건으로 인해 `j`가 `3` 이상일 수는 없다.

> 1차원 배열 이용

```kotlin
import java.util.*
import kotlin.math.max

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val s = IntArray(300)
    val d = IntArray(300)

    for (i in 0 until n) s[i] = nextInt()

    d[0] = s[0]
    d[1] = max(s[0] + s[1], s[1])
    d[2] = max(s[0] + s[2], s[1] + s[2])

    for (i in 3 until n) {
        d[i] = max(d[i - 2] + s[i], d[i - 3] + s[i - 1] + s[i])
    }

    println(d[n - 1])
}
```

### [BOJ 1149 : RGB거리](https://www.acmicpc.net/problem/1149)

```kotlin
import java.util.*
import kotlin.math.min

data class RGB(var red: Int, var green: Int, var blue: Int)

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val colors = Array(n + 1) { RGB(0, 0, 0) }
    val d = Array(n + 1) { IntArray(3) }

    for (i in 0 until n) {
        colors[i].red = nextInt()
        colors[i].green = nextInt()
        colors[i].blue = nextInt()
    }

    d[0][0] = colors[0].red
    d[0][1] = colors[0].green
    d[0][2] = colors[0].blue

    for (i in 1 until n) {
        d[i][0] = min(d[i-1][1], d[i-1][2]) + colors[i].red
        d[i][1] = min(d[i-1][0], d[i-1][2]) + colors[i].green
        d[i][2] = min(d[i-1][0], d[i-1][1]) + colors[i].blue
    }

    println(minOf(d[n - 1][0], d[n - 1][1], d[n - 1][2]))
}
```

### [BOJ 11726 : 2×n 타일링](https://www.acmicpc.net/problem/11726)

```kotlin
import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val d = IntArray(n + 3)
    val mod = 10007

    d[1] = 1
    d[2] = 2

    for (i in 3..n) d[i] = (d[i - 1] + d[i - 2]) % mod

    println(d[n])
}
```

### [BOJ 11659 : 구간 합 구하기 4](https://www.acmicpc.net/problem/11659)

```kotlin

```