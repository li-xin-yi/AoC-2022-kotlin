fun main() {
    fun solvePart1(input: List<String>): Int {
        var res = 0
        for (line in input) {
            val (interval1, interval2) = line.split(",")
            val (a, b) = interval1.split("-").map(String::toInt)
            val (c, d) = interval2.split("-").map(String::toInt)
            if ((a <= c && b >= d) || (a >= c && b <= d)) {
                res += 1
            }
        }
        return res
    }

    fun solvePart2(input: List<String>): Int {
        var res = input.size
        for (line in input) {
            val (interval1, interval2) = line.split(",")
            val (a, b) = interval1.split("-").map(String::toInt)
            val (c, d) = interval2.split("-").map(String::toInt)
            if ((b < c) || (a > d)) {
                res -= 1
            }
        }
        return res
    }

    val testInput = readInput("input/Day04_test")
    check(solvePart1(testInput) == 2)
    check(solvePart2(testInput) == 4)
    val input = readInput("input/Day04_input")
    println(solvePart1(input))
    println(solvePart2(input))
}