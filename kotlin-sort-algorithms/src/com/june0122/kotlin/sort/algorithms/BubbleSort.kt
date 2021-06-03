package com.june0122.kotlin.sort.algorithms

/**
 * 버블 정렬 (Bubble Sort)
 * 시간 복잡도 : O(n^2)
 *
 * 서로 인접한 두 원소의 대소를 비교하고, 조건에 맞지 않다면 자리를 교환하며 정렬하는 알고리즘
 */

class BubbleSort : SortAlgorithm {
    override fun sort(arr: Array<Int>): Array<Int> {
        for (i in arr.indices) {
            for (j in 1 until arr.size - i) {
                if (arr[j - 1] > arr[j]) {
                    arr[j - 1] = arr[j].also { arr[j] = arr[j - 1] }  // swap(arr, j-1, j) 도 가능
                }
            }
        }
        return arr
    }

    override fun getName(): String {
        return "BubbleSort"
    }
}