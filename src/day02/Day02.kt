package day02

import util.readInput

fun main(){

    //three cases: 1.forward  2.up 3.down
    //2,3 matter the depth
    fun processDive(input :List<String>): Int {
        var depth = 0; var forward = 0;
        for (move in input){
            val action = move.substring(0, endIndex = move.indexOf(' '))
            val value = move.substring(move.indexOf(' ') + 1).toInt()

            when(action){
                "forward"->{ forward += value }
                "down" -> { depth += value }
                "up" -> { depth -= value }
            }
        }

        return depth * forward
    }

    //part 2
    fun processDive2(input :List<String>): Int {
        var depth = 0; var forward = 0; var aim = 0;
        for (move in input){
            val action = move.substring(0, endIndex = move.indexOf(' '))
            val value = move.substring(move.indexOf(' ') + 1).toInt()

            when(action){
                "forward"->{
                    forward += value
                    depth += (value * aim)
                }
                "down" -> { aim += value }
                "up" -> { aim -= value }
            }
        }

        return depth * forward
    }


    //expected 150
    val test = readInput("day02/text")
    println(processDive(test))

    val input = readInput("day02/input")
    println(processDive(input))

    //expected 900
    println(processDive2(test))

    println(processDive2(input))
}
