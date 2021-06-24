# 프로그래머스 Level 2, ['H-Index'](https://programmers.co.kr/learn/courses/30/lessons/42747)

`정렬`

## 소스

### kotlin

> 나의 풀이

```kotlin
class Solution {
    fun solution(citations: IntArray): Int {
        var h = 0
        var count = 0
        val citedList = mutableListOf<Int>()
        val uncitedList = mutableListOf<Int>()

        citations.sort()

        while (count <= citations.size) {
            citations.forEach {
                if (it >= count) citedList.add(it)
                else uncitedList.add(it)

                if (citedList.size >= count && uncitedList.size <= count) h = count
            }
            count += 1
            citedList.clear()
            uncitedList.clear()
        }
        return h
    }
}
```

> 다른 사람의 풀이

```kotlin
import kotlin.math.min

class Solution {
    fun solution(citations: IntArray) = citations.sortedDescending().mapIndexed { idx, item -> min(idx + 1, item) }.max()
}
```
