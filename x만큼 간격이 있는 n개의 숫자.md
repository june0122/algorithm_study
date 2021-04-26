# 프로그래머스 Level 1, ['x만큼 간격이 있는 n개의 숫자'](https://programmers.co.kr/learn/courses/30/lessons/12954)

## 소스

### kotlin

```kotlin
class Solution {
    fun solution(x: Int, n: Int): LongArray = LongArray(n) { x.toLong() * (it + 1) }
}
```