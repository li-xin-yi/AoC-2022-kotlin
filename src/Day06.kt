fun main() {
    fun solvePart1(s: String): Int {
        for (i in 4 until s.length) {
            if (s.substring(i - 4, i).toSet().size == 4) {
                return i
            }
        }
        return -1

    }

    fun solvePart2(s: String): Int {
        val chars = MutableList<Int>(26) { 0 }
        // unique chars in the current window
        var window = 0
        for (i in s.indices) {
            if (chars[s[i] - 'a'] == 0) {
                window += 1
            }
            chars[s[i] - 'a'] += 1
            if (i >= 14) {
                chars[s[i - 14] - 'a'] -= 1
                if (chars[s[i - 14] - 'a'] == 0) {
                    window -= 1
                }
            }
            if (window == 14) {
                return i + 1
            }

        }
        return -1

    }

    // last sample input
    val testInput = readInput("input/Day06_test")[0]
    check(solvePart1(testInput) == 11)
    check(solvePart2(testInput) == 26)

    val input = readInput("input/Day06_input")[0]
    println(solvePart1(input))
    println(solvePart2(input))
}