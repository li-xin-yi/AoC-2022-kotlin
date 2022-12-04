fun main() {
    fun solvePart1(input: List<String>): Int {
        var res = 0
        for (line in input) {
            val length = line.length
            val first = line.substring(0, length / 2).toSet()
            val second = line.substring(length / 2).toSet()
            val item = first.intersect(second).first()
            res += if (item.isUpperCase()) (item - 'A' + 27) else (item - 'a' + 1)

        }
        return res
    }

    fun solvePart2(input: List<String>): Int {
        var res = 0
        for (i in 0 until input.size step 3) {
            val first = input[i].toSet()
            val second = input[i + 1].toSet()
            val third = input[i + 2].toSet()
            val item = first.intersect(second).intersect(third).first()
            res += if (item.isUpperCase()) (item - 'A' + 27) else (item - 'a' + 1)
        }
        return res
    }

    val testInput = readInput("input/Day03_test")
    println(solvePart1(testInput))
    println(solvePart2(testInput))
}