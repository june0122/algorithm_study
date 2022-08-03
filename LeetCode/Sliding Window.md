# 슬라이딩 윈도우<small>(Sliding Window)</small>

> 슬라이딩 윈도우<small>(Sliding Window)</small>란 고정 사이즈의 윈도우가 이동하면서 윈도우 내에 있는 데이터를 이용해 문제를 풀이하는 알고리즘을 말한다.

투 포인터와 비슷하지만 이와 구분하기 위해 일반적으로 고정 사이즈 윈도우를 사용하는 경우를 슬라이딩 윈도우로 따로 구분하기도 한다.

- 투 포인터: 주로 **정렬된 배열**을 대상
- 슬라이딩 윈도우: **정렬 여부에 관계 없이** 활용

|이름|정렬 여부|윈도우 사이즈|이동|
|:--:|:--:|:--:|:--:|
|투 포인터|대부분 O|가변|좌우 포인터 양방향|
|슬라이딩 윈도우|X|고정|좌 또는 우 단방향|

## [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

```kotlin
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var max = 0
        var left = 0
        val n = s.length
        val set = HashSet<Char>()
        
        if(s.isEmpty()) return max
            
        for (c in s) {
            while(set.contains(c)) {
                set.remove(s[left++])
            }
            
            set.add(c)
            
            max = Math.max(max, set.count())
        }
        
        return max
    }
}
```

```kotlin
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var tmp = ""
        var max = 0
        
        if(s.isEmpty()) return max
        
        for (i in s.indices) {
            if (tmp.contains(s[i])) {
                tmp = tmp.takeLastWhile { it != s[i] }
            }
            tmp += s[i]
            
            max = Math.max(tmp.length, max)
        }
        
        return max
    }
}
```