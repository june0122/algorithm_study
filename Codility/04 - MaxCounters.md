# [Codility] Lesson 4 : [MaxCounters](https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/)

#### 1. 88%

- extreme_large: ✘ TIMEOUT ERROR

```kotlin
fun solution(n: Int, arr: IntArray): IntArray {
    var counters = IntArray(n)

    arr.forEach {
        if (it <= n) { 
            counters[it - 1] += 1
        } else {
            val max = counters.max()!!
            counters = IntArray(n) { max }
        }
    }

    return counters
}
```

```kotlin
fun solution(n: Int, arr: IntArray): IntArray {
    val counters = IntArray(n)
    var max = 0

    arr.forEach {
        if (it <= n) {
            counters[it - 1] += 1
            max = Math.max(max, counters[it - 1])
        } else {
            counters.fill(max)
        }
    }

    return counters
}
```

#### max 값 저장? - 66%

- https://jeongupark-study-house.tistory.com/63

```kotlin
fun solution(n: Int, arr: IntArray): IntArray {
    val counters = IntArray(n)
    var currentMax = 0
    var maxCounterCache = 0

    arr.forEach {
        if (it == n + 1) {
            maxCounterCache = currentMax
        } else {
            val idx = it - 1

            if (counters[idx] < maxCounterCache) {
                counters[idx] = maxCounterCache + 1
            } else {
                counters[idx]++
            }

            if (counters[idx] > currentMax) {
                currentMax = counters[idx]
            }
        }

        for (i in 0 until n) {
            if (counters[i] < maxCounterCache) {
                counters[i] = maxCounterCache
            }
        }
    }

    return counters
}
```

- https://app.codility.com/demo/results/training6BTURM-FF6/