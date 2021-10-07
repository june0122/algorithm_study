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

### Brute Force

```kotlin
fun bestSum(targetSum: Int, nums: IntArray): IntArray? {
    if (targetSum == 0) return intArrayOf()
    if (targetSum < 0) return null

    var shortestCombination: IntArray? = null

    for (num in nums) {
        val remainder = targetSum - num
        val remainderCombination = bestSum(remainder, nums)
        if (remainderCombination != null) {
            val combination = remainderCombination + num
            if (shortestCombination == null || combination.size < shortestCombination.size) {
                shortestCombination = combination
            }
        }
    }

    return shortestCombination
}
```

`m = targetSum`, `n = nums.length`

- time complexity : O(n<sup>m</sup> * m)
- space complexity : O(m * m) = O(m<sup>2</sup>)
  - 변수 `shortestCombination`으로 인해 m을 곱해야 한다.


### Memoization

```kotlin
fun bestSum(targetSum: Int, nums: IntArray, memo: HashMap<Int, IntArray?> = HashMap()): IntArray? {
    if (targetSum in memo) return memo.getValue(targetSum)
    if (targetSum == 0) return intArrayOf()
    if (targetSum < 0) return null

    var shortestCombination: IntArray? = null

    for (num in nums) {
        val remainder = targetSum - num
        val remainderCombination = bestSum(remainder, nums, memo)
        if (remainderCombination != null) {
            val combination = remainderCombination + num
            if (shortestCombination == null || combination.size < shortestCombination.size) {
                shortestCombination = combination
            }
        }
    }

    memo[targetSum] = shortestCombination
    return shortestCombination
}
```

```
bestSum(7, intArrayOf(5, 3, 4 ,7))    // [7]
bestSum(8, intArrayOf(2, 3, 5))       // [5, 3]
bestSum(8, intArrayOf(1, 4, 5))       // [4, 4]
bestSum(100, intArrayOf(1, 2, 5, 25)  // [25, 25, 25, 25]
```

`m = targetSum`, `n = nums.length`

- time complexity : O(n * m * m) = O(n * m<sup>2</sup>)
- space complexity : O(m * m) = O(m<sup>2</sup>)

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135782247-c51a86e8-082e-4bf7-a8e0-3d56ed8cb58c.png'>
</p>

- `canSum` : "Can you do it? yes or no"           ->   <b>Decision Problem</b>
- `howSum` : "How will you do it?"                ->   <b>Combinatoric Problem<small>(조합론적 문제)</small></b>
- `bestSum` : "What is the 'best' way to do it?"  ->   <b>Optimization Problem</b>

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135782459-832340db-1452-40d1-874c-4436b2043318.png'>
</p>

Dynamic Programming problems aren't just limited to number inputs.

## canConstruct memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135782725-5945dec9-0d68-4d4f-8e2c-26a42e615fd3.png'>
</p>

```kotlin
canConstruct("", arrayOf("cat", "dog", "mouse")) // true
canConstruct("abcdef", arrayOf("ab", "abc", "cd", "def", "abcd")) // true
```

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135783101-1b128240-4f84-40bb-9ff0-7e71aa9f2813.png'>
</p>

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135783760-2f2037bc-2e87-4bcc-8065-7772b7b5ac28.png'>
</p>

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135784031-6945783b-638d-4ea8-88b3-8f9e76f81395.png'>
</p>

## Brute Force

```kotlin
fun canConstruct(target: String, wordBank: Array<String>): Boolean {
    if (target == "") return true

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            if (canConstruct(suffix, wordBank)) return true
        }
    }

    return false
}
```

> `CharSequence.indexOf(…)`

```kotlin
/*
Returns the index within this char sequence of the first occurrence of the specified string, starting from the specified startIndex.
Params: ignoreCase - true to ignore character case when matching a string. By default false.
Returns: An index of the first occurrence of string or -1 if none is found.
Samples: samples.text.Strings.indexOf
*/
public fun CharSequence.indexOf(string: String, startIndex: Int = 0, ignoreCase: Boolean = false): Int {
    return if (ignoreCase || this !is String)
        indexOf(string, startIndex, length, ignoreCase)
    else
        nativeIndexOf(string, startIndex)
}
```

```kotlin
"potato".indexOf("pot")  // 0
"potato".indexOf("ota")  // 1
"potato".indexOf("to")   // 4
"potato".indexOf("rot")  // -1
```

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135787025-630891dd-c702-4b05-89b1-f9d4473155a1.png'>
</p>

`m = target.length`, `n = workBank.size`

- time complexity : O(n<sup>m</sup> * m)
  - n<sup>m</sup>에 `target.substring(word.length)`가 최악의 경우 추가적인 m 연산을 하므로 n<sup>m</sup> * m
  - java `substring`의 시간복잡도는 O(n)
- space complexity : O(m * m) = O(m<sup>2</sup>)
  - 트리의 높이는 최대 스택 프레임 수를 의미한다.
  - 트리의 높이 `m` * substring이 반환하는 새로운 문자열의 길이가 최대 `m`

## Memoization

```kotlin
fun canConstruct(target: String, wordBank: Array<String>, memo: HashMap<String, Boolean> = HashMap()): Boolean {
    if (target in memo) return memo.getValue(target)
    if (target == "") return true

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            if (canConstruct(suffix, wordBank, memo)) {
                memo[target] = true
                return true
            }
        }
    }

    memo[target] = false
    return false
}
```

```
canConstruct("", arrayOf("cat", "dog", "mouse")) // true
canConstruct("abcdef", arrayOf("ab", "abc", "cd", "def", "abcd")) // true
canConstruct("skateboard", arrayOf("bo", "rd", "ate", "t", "ska", "sk", "boar")) // false
canConstruct("enterapotentpot", arrayOf("a", "p", "ent", "enter", "ot", "o", "t")) // true
canConstruct(
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
    arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
) // false
```

`m = target.length`, `n = workBank.size`

- time complexity : O(n * m * m) = O(n * m<sup>2</sup>)
- space complexity : O(m * m) = O(m<sup>2</sup>)

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135787846-f097859a-1d5a-4cfb-a842-3ffa8dd5d92d.png'>
</p>

## countConstruct memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135787959-1d78b100-43b0-497b-bad2-5d9acfb67307.png'>
</p>

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135939496-ac77f2cc-345e-4a0d-99d6-5a6ffe32d7c1.png'>
</p>

### Brute Force

```kotlin
fun countConstruct(target: String, wordBank: Array<String>): Int {
    if (target == "") return 1

    var count = 0

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            val numWaysForRest = countConstruct(suffix, wordBank)
            count += numWaysForRest
        }
    }

    return count
}
```

`m = target.length`, `n = workBank.size`

- time complexity : O(n<sup>m</sup> * m)
  - n<sup>m</sup>에 `target.substring(word.length)`가 최악의 경우 추가적인 m 연산을 하므로 n<sup>m</sup> * m
  - java `substring`의 시간복잡도는 O(n)
- space complexity : O(m * m) = O(m<sup>2</sup>)
  - 트리의 높이는 최대 스택 프레임 수를 의미한다.
  - 트리의 높이 `m` * substring이 반환하는 새로운 문자열의 길이가 최대 `m`

## Memoization

```kotlin
fun countConstruct(target: String, wordBank: Array<String>, memo: HashMap<String, Int> = HashMap()): Int {
    if (target in memo) return memo.getValue(target)
    if (target == "") return 1

    var count = 0

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            val numWaysForRest = countConstruct(suffix, wordBank, memo)
            count += numWaysForRest
        }
    }

    memo[target] = count
    return count
}
```

```
countConstruct("", arrayOf("cat", "dog", "mouse"))                                    // 1
countConstruct("purple", arrayOf("purp", "p", "ur", "le", "purpl"))                   // 2
countConstruct("abcdef", arrayOf("ab", "abc", "cd", "def", "abcd"))                   // 1
countConstruct("skateboard", arrayOf("bo", "rd", "ate", "t", "ska", "sk", "boar"))    // 0
countConstruct("enterapotentpot", arrayOf("a", "p", "ent", "enter", "ot", "o", "t"))  // 4
countConstruct(
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
        arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
) // 0
```

`m = target.length`, `n = workBank.size`

- time complexity : O(n * m * m) = O(n * m<sup>2</sup>)
- space complexity : O(m * m) = O(m<sup>2</sup>)

## allConstruct memoization

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135943563-57170230-3a01-4f27-9dc9-c94f1b3f927e.png'>
</p>

### Brute Force

```kotlin
fun allConstruct(target: String, wordBank: Array<String>): Array<Array<String>> {
    if (target == "") return arrayOf(arrayOf())

    var result = arrayOf<Array<String>>()

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            val suffixWays = allConstruct(suffix, wordBank)
            val targetWays = suffixWays.map { way -> arrayOf(word) + way }
            result += targetWays
        }
    }

    return result
}
```

### Memoization

```kotlin
fun allConstruct(target: String, wordBank: Array<String>, memo: HashMap<String, Array<Array<String>>> = HashMap()): Array<Array<String>> {
    if (target in memo) return memo.getValue(target)
    if (target == "") return arrayOf(arrayOf())

    var result = arrayOf<Array<String>>()

    for (word in wordBank) {
        if (target.indexOf(word) == 0) {
            val suffix = target.substring(word.length)
            val suffixWays = allConstruct(suffix, wordBank, memo)
            val targetWays = suffixWays.map { way -> arrayOf(word) + way }
            result += targetWays
        }
    }

    memo[target] = result
    return result
}
```

```kotlin
println(allConstruct("purple", arrayOf("purp", "p", "ur", "le", "purpl")).contentDeepToString())
println(allConstruct("abcdef", arrayOf("ab", "abc", "cd", "def", "abcd", "ef", "c")).contentDeepToString())
println(allConstruct("skateboard", arrayOf("bo", "rd", "ate", "t", "ska", "sk", "boar")).contentDeepToString())
println(
    allConstruct(
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
        arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
    ).contentDeepToString()
)
```

```
[[purp, le], [p, ur, p, le]]
[[ab, cd, ef], [ab, c, def], [abc, def], [abcd, ef]]
[]
[]
```

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/135952395-74cd38ab-c8c4-43e0-a658-812d464d06ed.png'>
</p>

`m = target.length`, `n = wordBank.size`

- time complexity : O(n<sup>m</sup>)
  - O(n<sup>m</sup>) leaves = O(n<sup>m</sup>) combinations = O(n<sup>m</sup>) subarrays
- space complexity : O(m)

target 문자열을 만드는 모든 조합을 반환해야하므로 worst case에 큰 영향을 끼치지 못한다. memoization을 해도 약간의 최적화만이 이루어진다. 즉, 브루트 포스든 메모이제이션이든 어떤 방법을 사용해도 위와 같은 시간 복잡도를 가진다.

## fib tabulation

<p align = 'center'>
<img width = '600' src = 'https://user-images.githubusercontent.com/39554623/136392579-1d8e3f06-ff00-4969-b335-fb4607e1234d.png'>
</p>

```kotlin
fun fib(n: Int): Long {
    val table = LongArray(n + 3)
    table[1] = 1

    for (i in 0..n) {
        table[i + 1] += table[i]
        table[i + 2] += table[i]
    }

    return table[n]
}
```

- time complexity : O(n)
- space complexity : O(n)

## gridTraveler tabulation

