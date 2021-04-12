package com.june0122.kotlin.sort.algorithms

/**
 * 퀵 정렬 (Quick Sort)
 * 시간 복잡도 : 최선 - O(nlog₂n) / 최악 - O(n^2)
 *
 * 퀵 정렬은 분할 정복(divide and conquer) 방법을 통해 주어진 배열을 정렬한다.
 *
 * 1. 배열 가운데서 하나의 원소를 고른다. 이렇게 고른 원소를 피벗(pivot) 이라고 한다.
 * 2. 피벗 앞에는 피벗보다 값이 작은 모든 원소들이 오고, 피벗 뒤에는 피벗보다 값이 큰 모든 원소들이 오도록 피벗을 기준으로 배열을 둘로 나눈다.
 *    이렇게 배열을 둘로 나누는 것을 분할(Divide) 이라고 한다. 분할을 마친 뒤에 피벗은 더 이상 움직이지 않는다.
 * 3. 분할된 두 개의 작은 배열에 대해 재귀(Recursion)적으로 이 과정을 반복한다.
 */

class QuickSort : SortAlgorithm {
    override fun sort(arr: Array<Int>): Array<Int> {
        sortArray(arr, 0, arr.size - 1)
        return arr
    }

    private fun sortArray(arr: Array<Int>, start: Int, end: Int) {
        if (start >= end) return
        var pivot = start
        var left = start + 1
        var right = end

        while (left <= right) {
            // 피벗보다 큰 데이터를 찾을 때까지 반복
            while (left <= end && arr[left] <= arr[pivot]) {
                left += 1
            }
            // 피벗보다 작은 데이터를 찾을 때까지 반복
            while (right > start && arr[right] >= arr[pivot]) {
                right -= 1
            }
            if (left > right) { // 엇갈렸다면 작은 데이터와 피벗을 교체
                swap(arr, right, pivot)
            } else { // 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
                swap(arr, right, left)
            }
        }
        // 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
        sortArray(arr, start, right - 1)
        sortArray(arr, right + 1, end)
    }

    /**
     * 짧게 작성한 퀵 정렬 소스코드로 전통 퀵 정렬의 분할 방식과는 조금 다른데,
     * 피벗과 데이터를 비교하는 비교 연산 횟수가 증가하므로 시간 면에서는 조금 비효율적이다.
     * 하지만 더 직관적이고 기억하기 쉽다는 장점이 있다.
     */
    private fun sortArray2(arr: Array<Int>): Array<Int> {
        if (arr.size <= 1) return arr // 리스트가 하나 이하의 원소만을 담고 있다면 종료

        val pivot = arr[0] // 피벗은 첫 번째 원소
        val tail = arr.drop(1) // 피벗을 제외한 리스트

        val left = tail.filter { it <= pivot } // 분할된 왼쪽 부분
        val right = tail.filter { it > pivot } // 분할된 오른쪽 부분

        return sortArray2(left.toTypedArray())
            .plus(pivot)
            .plus(sortArray2(right.toTypedArray()))
    }

    override fun getName(): String {
        return "QuickSort"
    }
}