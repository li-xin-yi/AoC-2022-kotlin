fun main() {
    fun solvePart1(input: List<String>): Int {
        var res = 0
        for (line in input) {
            val (a, b) = line.split(" ")
            res += (b[0] - 'X') + 1
            when (((a[0] - 'A') - (b[0] - 'X') + 3) % 3) {
                0 -> res += 3
                2 -> res += 6
            }
        }
        return res
    }

    fun solvePart2(input: List<String>): Int {
        var res = 0
        for (line in input) {
            val (a, b) = line.split(" ")
            when (b) {
                "Z" -> res += 6 + (a[0] - 'A' + 1) % 3 + 1
                "Y" -> res += 3 + (a[0] - 'A') + 1
                "X" -> res += (a[0] - 'A' + 2) % 3 + 1
            }
        }
        return res
    }


    val testInput = readInput("input/Day02_test")
    println(solvePart1(testInput))
    println(solvePart2(testInput))
}