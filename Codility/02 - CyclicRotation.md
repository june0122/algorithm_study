# [Codility] Lesson 2 : [CyclicRotation](https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/)

## 소스

### kotlin

> 배열 인덱스 활용

```kotlin
fun solution(A: IntArray, K: Int): IntArray {
    val rotatedArray = IntArray(A.size)

    for (i in A.indices) {
        val index = (i + K) % A.size
        rotatedArray[index] = A[i]
    }

    return rotatedArray
}
```

> 덱 활용

```kotlin
import java.util.*

fun solution(A: IntArray, K: Int): IntArray {
    val dequeue = ArrayDeque<Int>()
    val rotation = when(A.size) {
        0 -> 0
        else -> K % A.size
    }

    dequeue.addAll(A.toList())
    repeat(rotation) { dequeue.addFirst(dequeue.removeLast()) }

    return dequeue.toIntArray()
}
```

IntArray A의 크기가 0인 경우 `java.lang.ArithmeticException: / by zero`이 발생하므로 A가 0인 경우 회전하지 않도록 값을 0으로 설정해줘야 한다.

```kotlin
import java.util.*

fun solution(arr: IntArray, n: Int): IntArray {
    if (arr.isEmpty()) return intArrayOf()

    val deque = ArrayDeque<Int>()

    arr.forEach {
        deque.add(it)
    }

    repeat(n) {
        val temp = deque.removeLast()
        deque.addFirst(temp)
    }

    return deque.map { it }.toIntArray()
```