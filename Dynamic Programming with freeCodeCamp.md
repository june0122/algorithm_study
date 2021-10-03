# Dynamic Programming with freeCodeCamp

## fib memoization

### Classic Fibonacci Implementation

```kotlin
fun fib(n: Int): Long {
    if (n <= 2) return 1

    return fib(n - 1) + fib(n - 2)
}
```

- time complexity : O(2<sup>n</sup>)
- space complexity : O(n)

fib(50) ≈ 2<sup>50</sup>

### Memoization

```kotlin
fun fib(n: Int, memo: HashMap<Int, Long> = HashMap()): Long {
    if (n in memo) return memo.getValue(n)
    if (n <= 2) return 1
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
    return memo.getValue(n)
}
```

``` 
println(fib(6))   // 8
println(fib(7))   // 13
println(fib(8))   // 21
println(fib(50))  // 12586269025
```

- fib memoized complexity
  - O(2n) ≈ O(n) time
  - O(n) space

## gridTraveler memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135733312-f051b566-603d-47cc-a863-74d6a898a67d.png'>
</p>

### Brute Force

```kotlin
fun gridTraveler(m: Int, n: Int): Long {
    if (m == 0 || n == 0) return 0
    if (m == 1 && n == 1) return 1

    return gridTraveler(m - 1, n) + gridTraveler(m, n - 1)
}
```

- time complexity : O(2<sup>m * n</sup>)
- space complexity : O(m + n)

### Memoization

```kotlin
fun gridTraveler(m: Int, n: Int, memo: HashMap<Pair<Int, Int>, Long> = HashMap()): Long {
    if (m to n in memo) return memo.getValue(m to n)
    if (m == 0 || n == 0) return 0
    if (m == 1 && n == 1) return 1
    memo[m to n] = gridTraveler(m - 1, n, memo) + gridTraveler(m, n - 1, memo)
    return memo.getValue(m to n)
}
```

```
gridTraveler(1, 1)    // 1
gridTraveler(2, 3)    // 3
gridTraveler(3, 2)    // 3
gridTraveler(3, 3)    // 6
gridTraveler(18, 18)  // 2333606220
```

- time complexity : O(m * n)
- space complexity : O(m + n)

## Memoization Recipe

#### 1. Make it work.
   - visualize the problem as a tree
   - implement the tree using recursion
   - test it  
#### 2. Make it efficient.
   - add a memo object
   - add a base case to return memo values
   - store return valuse into the memo

## canSum memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135735661-f8577a81-71da-4df4-a2b0-ba86ad60f0c8.png'>
</p>

```
canSum(7, intArrayOf(5, 3, 4, 7)) // true, 3 + 4 = 7
canSum(7, intArrayOf(2, 4)) // false
```

### Brute Force

```kotlin
fun canSum(targetSum: Int, nums: IntArray): Boolean {
    if (targetSum == 0) return true
    if (targetSum < 0) return false

    for (num in nums) {
        val remainder = targetSum - num
        if (canSum(remainder, nums)) return true
    }

    return false
}
```

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135740419-1e2b8fc4-5006-4498-947a-191d6ee533d1.png'>
</p>

- time complexity : O(n<sup>m</sup>)
- space complexity : O(m)

### Memoization

```kotlin
fun canSum(targetSum: Int, nums: IntArray, memo: HashMap<Int, Boolean> = HashMap()): Boolean {
    if (targetSum in memo) return memo.getValue(targetSum)
    if (targetSum == 0) return true
    if (targetSum < 0) return false

    for (num in nums) {
        val remainder = targetSum - num
        if (canSum(remainder, nums, memo)) {
            memo[targetSum] = true
            return true
        }
    }

    memo[targetSum] = false
    return false
}
```

- time complexity : O(m * n)
- space complexity : O(m)

## howSum memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135740542-b42ed63c-b2c4-48f0-a6b5-2297e7e8c0f6.png'>
</p>

### Brute Force

```kotlin
fun howSum(targetSum: Int, nums: IntArray): IntArray? {
    if (targetSum == 0) return intArrayOf()
    if (targetSum < 0) return null

    for (num in nums) {
        val remainder = targetSum - num
        val remainderResult = howSum(remainder, nums)

        if (remainderResult != null) {
            return remainderResult + num
        }
    }

    return null
}
```

`m = targetSum`, `n = nums.length`

- time complexity : O(n<sup>m</sup> * m)
- space complexity : O(m)

### Memoization

```kotlin
fun howSum(targetSum: Int, nums: IntArray, memo: HashMap<Int, IntArray?> = HashMap()): IntArray? {
    if (targetSum in memo) return memo.getValue(targetSum)
    if (targetSum == 0) return intArrayOf()
    if (targetSum < 0) return null

    for (num in nums) {
        val remainder = targetSum - num
        val remainderResult = howSum(remainder, nums, memo)

        if (remainderResult != null) {
            memo[targetSum] = remainderResult + num
            return memo[targetSum]
        }
    }

    memo[targetSum] = null
    return null
}
```

`m = targetSum`, `n = nums.length`

- time complexity : O(n * m * m) = O(n * m<sup>2</sup>) 
- space complexity : O(m * m) = O(m<sup>2</sup>)
  - memo object에서 m개의 키가 최악의 경우 각각 m개의 요소로 이루어진 배열을 값으로 가지므로 O(m * m)
  - 시간 복잡도를 줄인 대신 공간 복잡도가 증가하는 trade-off가 발생

> O(n * m * m)에서 끝에 m이 또 곱해지는 이유

`memo[targetSum] = remainderResult + num`는 `IntArray.plus(…)`를 이용하므로 기존 배열의 복사가 일어난다. `remainderResult`의 길이는 최악의 경우 `1 + 1 + … + 1 = remainderResult`가 될 수 있으므로 길이가 최대 m인 배열을 복사하는 것이라 볼 수 있다. 그러므로 시간복잡도 계산에서 n * m 이후 다시 한 번 m이 곱해진다.

```kotlin
public actual operator fun IntArray.plus(element: Int): IntArray {
    val index = size
    val result = java.util.Arrays.copyOf(this, index + 1)
    result[index] = element
    return result
}
```

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135742447-b85d312d-0fa0-4dcc-89eb-70648a4d4f22.png'>
</p>

## bestSum memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135742496-00960484-361a-41bc-8f3e-6335378d6c71.png'>
</p>
