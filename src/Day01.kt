import java.util.PriorityQueue

fun main() {
    fun parseInput(input: List<String>): List<Int> {
        val res = mutableListOf<Int>()
        var cur = 0
        for (line in input) {
            if (line == "") {
                res.add(cur)
                cur = 0
            } else {
                cur += line.toInt()
            }
        }
        res.add(cur)
        return res
    }

    fun solvePart1(input: List<String>): Int {
        val lst = parseInput(input)
        return lst.max()
    }

    fun solvePart2(input: List<String>): Int {
        val lst = parseInput(input).map { it -> -it }
        val pq = PriorityQueue<Int>(lst)
        var res = 0
        for (i in 0 until 3) {
            res += pq.poll()
        }
        return -res
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day01_test")

    println(solvePart1(testInput))
    println(solvePart2(testInput))
}
