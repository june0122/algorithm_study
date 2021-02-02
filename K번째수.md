# 프로그래머스 Level 1, ['K번째수'](https://programmers.co.kr/learn/courses/30/lessons/42748)

## 소스

### kotlin

```kotlin
class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()

        commands.forEach {
            val sortedArray = array.copyOfRange(it[0] - 1, it[1]).sorted()
            answer.add(sortedArray[it[2] - 1])
        }

        return answer.toIntArray()
    }
}
```