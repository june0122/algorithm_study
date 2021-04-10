package com.june0122.kotlin.sort.algorithms

interface SortAlgorithm {
    /**
     * 특정 알고리즘을 이용하여 숫자들을 정렬
     */
    fun sort(arr: Array<Int>): Array<Int>

    /**
     * 정렬 알고리즘 이름을 리턴
     */
    fun getName(): String
}