# 이분 탐색<small>(Binary Search)</small>

## 설명

<b>정렬되어 있는 배열</b>에서 특정 데이터를 찾기 위해 모든 데이터를 순차적으로 확인하는 대신 <b>탐색 범위를 절반</b>으로 줄여가며 찾는 탐색 방법

- 선형 탐색 : <i>O(N)</i>
- 이분 탐색 : <i>O(logN)</i>

## 구현

### [BOJ 1920, 수 찾기](https://www.acmicpc.net/problem/1920)

> 이분 탐색 직접 구현

```kotlin
import java.util.Scanner

var n = 0
var m = 0
lateinit var a: IntArray
lateinit var b: IntArray

fun main() {
    init()
    a.sort()
    b.forEach {
        println(binarySearch(it))
    }
}

fun binarySearch(target: Int): Int {
    var st = 0 // start
    var en = n - 1 // end

    while (st <= en) {
        val mid = (st + en) / 2
        when {
            a[mid] < target -> st = mid + 1
            a[mid] > target -> en = mid - 1
            else -> return 1
        }
    }
    return 0
}

fun init() = with(Scanner(System.`in`)) {
    n = nextInt()
    a = IntArray(n)
    for (i in 0 until n) {
        a[i] = nextInt()
    }
    m = nextInt()
    b = IntArray(m)
    for (i in 0 until m) {
        b[i] = nextInt()
    }
}
```

> kotlin.collections의 `binarySearch` 사용

- JVM을 기반으로 하는 코틀린에서의 컬렉션은 자바에서 제공하는 클래스들을 그대로 사용한다.

```kotlin
b.forEach {
    if (a.binarySearch(it) >= 0) println(1)
    else println(0)
}
```

> `contains` 사용

```kotlin
b.forEach {
    if (a.contains(it)) println(1)
    else println(0)
}
```

BOJ를 기준으로 `binarySearch`를 이용한 풀이는 **2164ms**, `contains`는 **4940ms** 시간이 소요되었다.

kotlin.collections의 `contains` 함수의 내부 구현은 다음과 같다.

```kotlin
public operator fun IntArray.contains(element: Int): Boolean {
    return indexOf(element) >= 0
}

public fun IntArray.indexOf(element: Int): Int {
    for (index in indices) {
        if (element == this[index]) {
            return index
        }
    }
    return -1
}
```

내부적으로 `indexOf` 함수를 사용하여 모든 인덱스를 순회하는 형태로 구현되어 있으며, <i>O(N)</i> 시간복잡도를 가진다.

그에 반해 `binarySearch`의 경우 <i>O(logN)</i>의 시간복잡도를 가지므로 더 좋은 성능을 보여준다.

다음 코드는 java.util.Arrays의 `binarySearch` 코드이다.

```java
public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
    rangeCheck(a.length, fromIndex, toIndex);
    return binarySearch0(a, fromIndex, toIndex, key);
}
private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;
    while(low <= high) {
        int mid = low + high >>> 1;
        int midVal = a[mid];
        if (midVal < key) {
            low = mid + 1;
        } else {
            if (midVal <= key) {
                return mid;
            }
            high = mid - 1;
        }
    }
    return -(low + 1);
}
```

### [BOJ 10816, 숫자 카드2](https://www.acmicpc.net/problem/10816)






### 주의사항

1. 이분 탐색을 하고자 한다면 주어진 배열은 정렬되어 있어야 한다.
2. 무한 루프에 빠지지 않게 mid 값을 정해야 한다.



## 연습 문제

### [BOJ 18870, 좌표 압축](https://www.acmicpc.net/problem/18870)

```kotlin

```