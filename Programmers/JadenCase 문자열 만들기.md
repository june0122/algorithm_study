# 프로그래머스 Level 2, ['JadenCase 문자열 만들기'](https://programmers.co.kr/learn/courses/30/lessons/12951)

## 소스

### kotlin

> 첫 풀이

```kotlin
class Solution {
    fun solution(s: String): String {
        val words = s.toLowerCase().toCharArray()

        words[0] = words[0].toUpperCase()

        for (i in 1 until words.size) {
            if (words[i].isLowerCase() && words[i - 1] == ' ') {
                words[i] = words[i].toUpperCase()
            }
        }

        return String(words)  // words.joinToString("") 사용 가능
    }
}
```

> `capitallize()` 사용

```kotlin
class Solution {
     fun solution(s: String): String {
          return s.toLowerCase().split(" ").map {
                it.capitalize()
            }.joinToString(" ")
    }
}
```