package com.june0122.kotlin.sort.algorithms

import kotlin.math.roundToInt

/**
 * Contains a set of utility methods like printing array contents and so on
 */

/**
 * Prints contents of an array
 * @param arr an array to be converted to string
 */
fun <T> arrayToString(arr: Array<T>): String {
    var str = "Array("
    var isFirst = true
    for (element in arr) {
        if (!isFirst) {
            str += ", "
        }
        str += element
        if (isFirst) {
            isFirst = false
        }
    }
    str += ")"
    return str
}

/**
 * Returns whether given array is sorted in ascending order
 * @param arr array to be checked
 */
fun isSortedAsc(arr: Array<Int>): Boolean {
    for (i in 0..arr.size - 2) {
        if (arr[i] > arr[i + 1]) {
            return false
        }
    }
    return true
}

/**
 * Swaps i-th and j-th elemens of the array
 * @param arr
 * @param i
 * @param j
 */
fun swap(arr: Array<Int>, i: Int, j: Int): Array<Int> {
    val tmp = arr[i]
    arr[i] = arr[j]
    arr[j] = tmp
    return arr
}

/**
 * Creates random array or Long of given length and max value
 * @param length length of an array to be generated
 * @param maxValue
 */
fun randomNumericArray(length: Int, maxValue: Int = 10): Array<Int> {
    return Array(length) { (maxValue * Math.random()).roundToInt() }
}
