# [Codility] Lesson 4 : [MissingInteger](https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/)

#### 1. 내 풀이

```kotlin
import java.util.*

fun solution(arr: IntArray): Int {
    val set = TreeSet<Int>()
    
    arr.forEach {
        if (it > 0) set.add(it)
    }

    if (set.isEmpty()) return 1

    val iterator = set.iterator()

    var cursor = 1

    while (iterator.hasNext()) {
        val e = iterator.next()
        if (cursor != e) return cursor
        cursor++
    }

    return cursor
}
```

<img width="526" alt="image" src="https://user-images.githubusercontent.com/39554623/174426148-92cc1ff9-6cb5-4878-bb3a-d7ceaa6b3b3e.png">

#### 2. HashSet + `contains`

```kotlin
fun solution(arr: IntArray): Int {
    val set = HashSet<Int>()
    
    arr.forEach {
        if (it > 0) set.add(it)
    }

    for (i in 1..Integer.MAX_VALUE) {
        if (set.contains(i).not()) return i
    }

    return -1
}
```