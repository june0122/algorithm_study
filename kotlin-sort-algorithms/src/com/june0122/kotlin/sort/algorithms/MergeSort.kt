package com.june0122.kotlin.sort.algorithms

/**
 * 병합 정렬 (Merge Sort)
 * 시간 복잡도 : 평균 - O(nlogn) / 최선 - O(nlogn) / 최악 - O(nlogn)
 *
 * 요소를 쪼갠 후, 다시 합병시키면서 정렬해나가는 방식으로, 쪼개는 방식은 퀵정렬과 유사하다.
 */

class MergeSort : SortAlgorithm {
    private val sortedArr = Array(10) {0}

    override fun sort(arr: Array<Int>): Array<Int> {
        mergeSort(arr, 0, arr.size - 1)
        return arr
    }

    private fun merge(arr: Array<Int>, start: Int, middle: Int, end: Int) {
//        val sortedArr = Array(arr.size) {0}
        var i = start
        var j = middle + 1
        var k = start

        while (i <= middle && j <= end) {
            if (arr[i] <= arr[j]) {
                sortedArr[k] = arr[i++]
            } else {
                sortedArr[k] = arr[j++]
            }
            k += 1
        }

        if (i > middle) {
            for (t in j .. end) {
                sortedArr[k] = arr[t]
                k += 1
            }
        } else {
            for (t in i .. middle) {
                sortedArr[k] = arr[t]
                k += 1
            }
        }

        for (t in start .. end) {
            arr[t] = sortedArr[t]
        }
    }

    private fun mergeSort(arr: Array<Int>, start: Int, end: Int) {
        if (start < end) {
            val middle = (start + end) / 2
            mergeSort(arr, start, middle)
            mergeSort(arr, middle + 1, end)
            merge(arr, start, middle, end)
        }
    }

//    private fun mergeSort(arr: Array<Int>, startIndex: Int, endIndex: Int) {
//        val pieceSize = endIndex - startIndex + 1
//
//        // recursive 기반이므로 method 호출을 멈추는 breaking point 가 필요
//        // 배열에 남은 인자가 하나라면 더 이상 나눌 필요 없음. 해당 조건 명시
//        if (pieceSize == 1) return
//
//        // 배열을 2가지로 나눌 기준을 정함
//        val midIndex = (startIndex + endIndex) / 2
//
//        // left 부분을 기반으로 mergeSort 메소드 호출
//        mergeSort(arr, startIndex, midIndex)
//
//        // right 부분을 기반으로 mergeSort 메소드 호출
//        mergeSort(arr, midIndex + 1, endIndex)
//
//        // partitioning 된 배열을 기반으로 병합 수행
//        merge(arr, startIndex, midIndex, endIndex)
//    }
//
//    private fun merge(arr: Array<Int>, startIndex: Int, midIndex: Int, endIndex: Int) {
//        val leftArr = arr.copyOfRange(startIndex, midIndex + 1)
//        val rightArr = arr.copyOfRange(midIndex + 1, endIndex + 1)
//
//        var i = 0
//        var j = 0
//        for (k in startIndex..endIndex) {
//            if (i <= leftArr.size - 1 && (j >= rightArr.size || leftArr[i] <= rightArr[j])) {
//                arr[k] = leftArr[i++]
//            } else {
//                arr[k] = rightArr[j++]
//            }
//        }
//    }

    override fun getName(): String {
        return "MergeSort"
    }
}