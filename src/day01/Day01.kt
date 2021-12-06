package day01

import util.readInput
import java.util.*


fun main() {

    fun part1(input: List<String>): Int {
        var count = 0;
        for (i in 0..input.size - 2){
            if (input[i]<=input[i+1]) count++
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0 //count three item-width window is increasing
        var index = 0 //
        val queue = LinkedList<Int>()
        var tempSum = 0; var previousMax = Int.MAX_VALUE
        while (index < input.size){
            //join integer
            while (queue.size < 3) {
                val value = Integer.parseInt(input[index++])
                tempSum += value
                queue.push(value)
            }

            //compare and add
            if (tempSum > previousMax ) count++
            previousMax = tempSum

            //leave integer
            while (queue.size >= 3){
                val value = queue.pollLast()
                tempSum -= value
            }
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/input")
//    check(part1(testInput) == 1)
    println(part1(testInput))


    val testInput2 = readInput("day01/part-2-input")
    //expected 1618
    println(part2(testInput2))
}
