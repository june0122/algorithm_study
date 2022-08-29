<p align = 'center'>
<img width = '500' src = 'https://user-images.githubusercontent.com/39554623/186139245-89c2d9f5-e5cd-4fbd-ac4b-9b6eb27adb77.png'>
</p>


```kotlin
import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val size = nextInt()
    makeZigZagTable(size)
}

fun makeZigZagTable(n: Int) {
    val table = Array(n) { IntArray(n) }
    val total = n * n

    var isUp = true
    var (col, row) = -1 to 0
    var count = 1

    while (count <= total) {
        if (row == n - 1 && count <= total) { // 행렬 오른쪽 도달
            println("($col, $row) 오른쪽")
            table[++col][row] = count++
            isUp = false
        }

        if (col == n - 1 && count <= total) { // 행렬 아래쪽 도달
            println("($col, $row) 아래쪽")
            table[col][++row] = count++
            isUp = true
        }

        if (col == 0 && count <= total) { // 행렬 위쪽 도달
            println("($col, $row) 위쪽")
            table[col][++row] = count++
            isUp = false
        }

        if (row == 0 && count <= total) { // 행렬 왼쪽 도달
            println("($col, $row) 왼쪽")
            table[++col][row] = count++
            isUp = true
        }

        if (isUp && row != n - 1 && col != 0 && count <= total) {
            println("($col, $row)")
            table[--col][++row] = count++
        }

        if (!isUp && col != n - 1 && row != 0 && count <= total) {
            println("($col, $row)")
            table[++col][--row] = count++
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            print("${table[i][j]}  ")
        }
        println()
    }
}

//fun makeZigZagTable(n: Int) {
//    val table = Array(n) { IntArray(n) }
//    val total = n * n
//
//    var isUp = true
//    var (col, row) = -1 to 0
//    var count = 1
//
//    while (count <= total) {
//        if (row == n - 1 && count <= total) { // 행렬 오른쪽 도달
//            println("($col, $row) 오른쪽")
//            table[++col][row] = count++
//            isUp = false
//        }
//
//        if (col == n - 1 && count <= total) { // 행렬 아래쪽 도달
//            println("($col, $row) 아래쪽")
//            table[col][++row] = count++
//            isUp = true
//        }
//
//        if (col == 0 && count <= total) { // 행렬 위쪽 도달
//            println("($col, $row) 위쪽")
//            table[col][++row] = count++
//            isUp = false
//        }
//
//        if (row == 0 && count <= total) { // 행렬 왼쪽 도달
//            println("($col, $row) 왼쪽")
//            table[++col][row] = count++
//            isUp = true
//        }
//
//        if (isUp && row != n - 1 && col != 0 && count <= total) {
//            println("($col, $row)")
//            table[--col][++row] = count++
//        }
//
//        if (!isUp && col != n - 1 && row != 0 && count <= total) {
//            println("($col, $row)")
//            table[++col][--row] = count++
//        }
//    }
//
//    for (i in 0 until n) {
//        for (j in 0 until n) {
//            print("${table[i][j]}  ")
//        }
//        println()
//    }
//}
```