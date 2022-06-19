# [Codility] Lesson 4 : [FrogRiverOne](https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/)

### kotlin

```kotlin
fun solution(x: Int, arr: IntArray): Int {
    val set = HashSet<Int>()

    arr.forEachIndexed { idx, v ->
        set.add(v)
        if (set.size == x) return idx
    }

    return -1
}
```