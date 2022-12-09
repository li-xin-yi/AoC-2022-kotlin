import java.util.*

fun main() {
    fun parseInput(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.map { it - '0' }
        }
    }

    fun solvePart1(input: List<String>): Int {
        val grid = parseInput(input)
        val seen = mutableSetOf<Pair<Int, Int>>()
        var maxSoFar: Int
        for (i in grid.indices) {
            maxSoFar = -1
            for (j in grid[i].indices) {
                if (grid[i][j] > maxSoFar) {
                    seen.add((i to j))
                    maxSoFar = grid[i][j]
                }
            }

            maxSoFar = -1
            for (j in grid[i].indices.reversed()) {
                if (grid[i][j] > maxSoFar) {
                    seen.add((i to j))
                    maxSoFar = grid[i][j]
                }
            }
        }
        for (j in grid[0].indices) {
            maxSoFar = -1
            for (i in grid.indices) {
                if (grid[i][j] > maxSoFar) {
                    seen.add((i to j))
                    maxSoFar = grid[i][j]
                }
            }

            maxSoFar = -1
            for (i in grid.indices.reversed()) {
                if (grid[i][j] > maxSoFar) {
                    seen.add((i to j))
                    maxSoFar = grid[i][j]
                }
            }
        }
        return seen.size
    }

    fun solvePart2(input: List<String>): Int {
        val grid = parseInput(input)
        val score = Array(grid.size) { IntArray(grid[0].size) { 1 } }
        val stack = Stack<Int>()
        for (i in grid.indices) {
            stack.clear()
            for (j in grid[i].indices) {
                while (stack.isNotEmpty() && grid[i][stack.peek()] < grid[i][j]) {
                    stack.pop()
                }
                score[i][j] *= j - (if (stack.isNotEmpty()) stack.peek() else 0)
                stack.push(j)
            }
            stack.clear()
            for (j in grid[i].indices.reversed()) {
                while (stack.isNotEmpty() && grid[i][stack.peek()] < grid[i][j]) {
                    stack.pop()
                }
                score[i][j] *= (if (stack.isNotEmpty()) stack.peek() else grid[i].size - 1) - j
                stack.push(j)
            }
        }

        for (j in grid[0].indices) {
            stack.clear()
            for (i in grid.indices) {
                while (stack.isNotEmpty() && grid[stack.peek()][j] < grid[i][j]) {
                    stack.pop()
                }
                score[i][j] *= i - (if (stack.isNotEmpty()) stack.peek() else 0)
                stack.push(i)
            }
            stack.clear()
            for (i in grid.indices.reversed()) {
                while (stack.isNotEmpty() && grid[stack.peek()][j] < grid[i][j]) {
                    stack.pop()
                }
                score[i][j] *= (if (stack.isNotEmpty()) stack.peek() else grid.size - 1) - i
                stack.push(i)
            }
        }

        return score.maxOf { it.maxOrNull()!! }
    }


    val testInput = readInput("input/Day08_test")
    check(solvePart1(testInput) == 21)
    check(solvePart2(testInput) == 8)

    val input = readInput("input/Day08_input")
    println(solvePart1(input))
    println(solvePart2(input))
}