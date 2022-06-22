# [Codility] Lesson 6 : [Triangle](https://app.codility.com/programmers/lessons/6-sorting/triangle/)

#### 1.

```kotlin
fun solution(A: IntArray): Int {
    val N = A.size
    
    if (N < 3) return 0

    A.sort()

    // check : A[P] + A[Q] > A[R]
    for (i in 0 until N - 2) {
        val P = A[i].toLong()
        val Q = A[i + 1].toLong()
        val R = A[i + 2].toLong()

        if (P + Q > R) return 1
    }

    return 0
}
```

## References

- https://jobjava00.github.io/algorithm/codility/lesson6/Triangle/