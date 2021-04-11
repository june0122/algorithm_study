package com.june0122.kotlin.sort.algorithms

/**
 * 선택 정렬 (Selection Sort)
 * 시간 복잡도 : O(n^2)
 *
 * 데이터가 무작위로 여러 개 있을 때, 이 중에서 가장 작은 데이터를 선택해 맨 앞에 있는 데이터와 바꾸고
 * 그 다음 작은 데이터를 선택해 앞에서 두 번째 데이터와 바꾸는 과정을 반복한다.
 *
 * 해당 순서에 원소를 넣을 위치는 이미 정해져 있고, 어떤 원소를 넣을지 선택하는 알고리즘이다.
 *
 * Selection Sort와 Insertion Sort를 헷갈려하는 사람들이 종종 있는데,
 * Selection Sort는 배열에서 해당 자리를 선택하고 그 자리에 오는 값을 찾는 것이라고 생각하면 편하다.
 */

class SelectionSort : SortAlgorithm {
    override fun sort(arr: Array<Int>): Array<Int> {
        var minIndex : Int
        for (i in arr.indices) {
            minIndex = i
            for (j in i + 1 until arr.size) {
                if (arr[minIndex] > arr[j]) minIndex = j
            }
            arr[i] = arr[minIndex].also { arr[minIndex] = arr[i] }  // swap(array, minIndex, i) 사용 가능
        }
        return arr
    }

    override fun getName(): String {
        return "SelectionSort"
    }
}