import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    fun solvePart1(input: List<String>): String {
        val stacks = HashMap<Int, Deque<Char>>()
        var start = 0
        for (idx in input.indices) {
            if (input[idx] == "") {
                start = idx + 1
                break
            }
            for (i in 0 until input[idx].length step 4) {
                if (input[idx][i] == '[' && input[idx][i + 2] == ']') {
                    stacks.getOrPut(i / 4 + 1) { ArrayDeque() }.addFirst(input[idx][i + 1])
                }
            }
        }

        for (idx in start until input.size) {
            val (k, from, to) = input[idx].split(" ").slice(setOf(1, 3, 5)).map(String::toInt)
            for (t in 0 until k) {
                val tmp = stacks[from]!!.removeLast()
                stacks[to]!!.addLast(tmp)
            }
        }

        return stacks.map { it.value.last() }.joinToString("")
    }

    fun solvePart2(input: List<String>): String {
        val stacks = HashMap<Int, MutableList<Char>>()
        var start = 0
        for (idx in input.indices) {
            if (input[idx] == "") {
                start = idx + 1
                break
            }
            for (i in 0 until input[idx].length step 4) {
                if (input[idx][i] == '[' && input[idx][i + 2] == ']') {
                    stacks.getOrPut(i / 4 + 1) { ArrayList() }.add(input[idx][i + 1])
                }
            }
        }
        stacks.forEach { it.value.reverse() }

        for (idx in start until input.size) {
            val (k, from, to) = input[idx].split(" ").slice(setOf(1, 3, 5)).map(String::toInt)
            stacks[to]!!.addAll(stacks[from]!!.takeLast(k))
            for (t in 0 until k) {
                stacks[from]!!.removeLast()
            }
        }


        return stacks.map { it.value.last() }.joinToString("")
    }

    val testInput = readInput("input/Day05_test")
    check(solvePart1(testInput) == "CMZ")
    check(solvePart2(testInput) == "MCD")

    val input = readInput("input/Day05_input")
    println(solvePart1(input))
    println(solvePart2(input))
}