package com.june0122.kotlin.sort.algorithms

/**
 * 삽입 정렬 (Insertion Sort)
 * 시간 복잡도 : 최악 - O(n^2) / 최선 - O(N)
 *
 * 2번째 원소부터 시작하여 그 앞(왼쪽)의 원소들과 비교하여 삽입할 위치를 지정한 후,
 * 원소를 뒤로 옮기고 지정된 자리에 자료를 삽입하여 정렬하는 알고리즘이다.
 *
 * 최선의 경우 O(N)이라는 엄청나게 빠른 효율성을 가지고 있어, 다른 정렬 알고리즘의 일부로 사용될 만큼 좋은 정렬 알고리즘이다.
 */

class InsertionSort : SortAlgorithm {
    override fun sort(arr: Array<Int>): Array<Int> {
        for (i in 1 until arr.size) {
            for (j in i downTo 1) {
                if (arr[j - 1] > arr[j]) {
                    arr[j] = arr[j - 1].also { arr[j - 1] = arr[j] } // swap(arr, j - 1, j) 사용 가능
                } else break
            }
        }
        return arr
    }

    override fun getName(): String {
        return "InsertionSort"
    }
}