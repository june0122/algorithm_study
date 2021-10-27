# 프로그래머스 Level 2, ['N개의 최소공배수'](https://programmers.co.kr/learn/courses/30/lessons/12953)

## 문제

### 문제 설명

두 수의 최소공배수(Least Common Multiple)란 입력된 두 수의 배수 중 공통이 되는 가장 작은 숫자를 의미합니다. 예를 들어 2와 7의 최소공배수는 14가 됩니다. 정의를 확장해서, n개의 수의 최소공배수는 n 개의 수들의 배수 중 공통이 되는 가장 작은 숫자가 됩니다. n개의 숫자를 담은 배열 arr이 입력되었을 때 이 수들의 최소공배수를 반환하는 함수, solution을 완성해 주세요.

### 제한 사항

- arr은 길이 1이상, 15이하인 배열입니다.
- arr의 원소는 100 이하인 자연수입니다.

### 입출력 예

|arr|result|
|:--|:--|
|[2,6,8,14]|[2,6,8,14]|
|[1,2,3]|6|

## 소스

### kotlin

```kotlin
class Solution {
    fun solution(arr: IntArray): Int {
        var answer = arr[0]

        for (i in 1 until arr.size) {
            answer = lcm(answer, arr[i])
        }
        
        return answer
    }
    
    fun gcd(a: Int, b: Int): Int {
        val max = Math.max(a, b)
        var result = 0
        
        for (i in 1..max) {
            if (a % i == 0 && b % i == 0) {
                result = i
            }
        }
        
        return result
    }
    
    fun lcm(a: Int, b: Int): Int = a * b / gcd(a, b)
}
```

### python

```python
from math import gcd

def solution(arr):
    answer = arr[0]
    
    for i in range(1, len(arr)):
        answer = lcm(answer, arr[i])
    
    return answer

def lcm(a, b):
    return a * b // gcd(a, b)
```
