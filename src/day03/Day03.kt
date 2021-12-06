package day03

import util.readInput
import java.lang.StringBuilder

fun main(){

    fun sumByMultiplyBinary(first: String, second: String): Long{
        var firstDigit = 0L; var secondDigit = 0L; var base = 1
        for ( i in first.length - 1 downTo 0){
            firstDigit += (first[i]- '0') * base
            secondDigit += (second[i] - '0') * base
            base *= 2
        }
        return firstDigit * secondDigit
    }

    /**
     * @param input are list of binary number
     * @return is value by multiplying gamma rate and the epsilon rate
     * gamma rate is most common bit corresponding through every column
     * epsilon rate is least common bit corresponding through every column
     */
    fun processData(input :List<String>): Long {
        val n = input.size
        val m = input.takeIf { it.isNotEmpty() }?.first()?.length ?: 0
        val array = Array(m) { 0 }
        for(binaryLine in input){
            binaryLine.forEachIndexed { index, c ->
                if (c=='1') array[index]++ else array[index]--
            }
        }
        var gamma = StringBuilder(); var epsilon = StringBuilder()

        array.forEach {
            if (it > 0){ //most common is 1
                gamma.append('1')
                epsilon.append('0')
            }else{ //most common is 0
                gamma.append('0')
                epsilon.append('1')
            }
        }
        return sumByMultiplyBinary(gamma.toString(), epsilon.toString())
    }



    fun processLifeSupportRating(lines:List<String>): Long {
        //find oxygen rating
        var startBit = 0
        var remainList = lines
        while (remainList.size>1){
            val take1 :Int = remainList.sumOf {
                (if(it[startBit] == '1') 1 else -1).toInt()
            }
            remainList = if (take1>=0){
                remainList.filter { it[startBit] == '1' }
            }else{
                remainList.filter { it[startBit] == '0' }
            }
            startBit++
        }
        //find CO2 scrubber rating
        val oxygen = remainList.first()
        startBit = 0
        remainList = lines

        while (remainList.size>1){
            val take1 :Int = remainList.sumOf {
                (if(it[startBit] == '1') 1 else -1).toInt()
            }
            remainList = if (take1>=0){
                remainList.filter { it[startBit] == '0' }
            }else{
                remainList.filter { it[startBit] == '1' }
            }
            startBit++
        }
        val co2 = remainList.first()

        return sumByMultiplyBinary(oxygen, co2)
    }

    val test = readInput("day03/test")


    //expected 198
    println(processData(test))

    //expected 230
    println(processLifeSupportRating(test))

    val input = readInput("day03/input")

    println(processLifeSupportRating(input))

    println(processData(input))
}