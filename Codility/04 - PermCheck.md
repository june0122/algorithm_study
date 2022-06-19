# [Codility] Lesson 4 : [PermCheck](https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/)

#### 1차 시도: 83%

```kotlin
fun solution(arr: IntArray): Int = if (arr.size == arr.max()!!) 1 else 0
```

- `[1, 3, 3]` 형태로 숫자가 반복될 수 있음을 가정해야 함.

#### 100%

```kotlin
fun solution(A: IntArray): Int {
    val B = BooleanArray(A.size)

    A.forEachIndexed { i, v ->
        if (A[i] > B.size) return 0
        B[v - 1] = true
    }

    B.forEach {
        if (it == false) return 0
    }

    return 1
}
```