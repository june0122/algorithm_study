# [Codility] Lesson 6 : [MaxProductOfThree](https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/)

#### 1.

```kotlin
fun solution(A: IntArray): Int {
    A.sort()

    var (a, b) = 0 to 0

    if (A[0] < 0 && A[1] < 0) {
        a = A[0] * A[1] * A[A.size - 1]
    } 

    b = A[A.size - 1] * A[A.size - 2] * A[A.size - 3]

    return Math.max(a, b)
}
```
