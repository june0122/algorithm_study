package com.june0122.kotlin.sort.algorithms

class SortLauncher {
    fun launch() {
        val arr: Array<Int> = randomNumericArray(10, 100)
        for (algorithm in getAlgorithmsList()) {
            measureAlgorithm(arr.copyOf(), algorithm)
        }
    }

    /**
     * 알고리즘들을 각각 실행 및 성능 측정
     */
    private fun measureAlgorithm(arr: Array<Int>, algorithm: SortAlgorithm) {
        println("--------------------------------------")
        println("Algorithm name: " + algorithm.getName())
        println("Initial array: " + arrayToString(arr))

        val startTime: Long = System.nanoTime()
        val sortedArr = algorithm.sort(arr)
        val endTime: Long = System.nanoTime()

        println("Sorted array: " + arrayToString(sortedArr))
        println("Is array sorted in ascending order: " + isSortedAsc(sortedArr))
        val avgTimePerElement: Double = ((endTime - startTime).toDouble() / arr.size.toDouble())
        println("Average time per element, ns: $avgTimePerElement")
    }

    /**
     * 테스트할 알고리즘들의 리스트를 리턴
     */
    private fun getAlgorithmsList(): Array<SortAlgorithm> {
        return arrayOf(
            SelectionSort(),
        )
    }
}

fun main() {
    SortLauncher().launch()
}
