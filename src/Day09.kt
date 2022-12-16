import kotlin.math.abs

fun main() {
    val directions = mapOf(
        "R" to (0 to 1),
        "L" to (0 to -1),
        "U" to (1 to 0),
        "D" to (-1 to 0)
    )

    fun solvePart1(input: List<String>): Int {
        val seen = mutableSetOf(0 to 0)
        var (hx, hy) = 0 to 0
        var (tx, ty) = 0 to 0
        for (line in input) {
            val (a, b) = line.split(" ")
            val (dx, dy) = directions[a]!!
            for (i in 1..b.toInt()) {
                hx += dx
                hy += dy
                if (abs(hx - tx) <= 1 && abs(hy - ty) <= 1) {
                    seen.add(tx to ty)
                    continue
                }
                tx += (if (hx > tx) 1 else if (hx < tx) -1 else 0)
                ty += (if (hy > ty) 1 else if (hy < ty) -1 else 0)
                seen.add(tx to ty)
            }

        }
        return seen.size
    }

    fun solvePart2(input: List<String>): Int {
        val seen = mutableSetOf(0 to 0)
        val rope = MutableList(10) { 0 to 0 }

        fun updateTail(i: Int) {
            val (preX, preY) = rope[i]
            val (curX, curY) = rope[i + 1]
            if (abs(preX - curX) <= 1 && abs(preY - curY) <= 1) {
                return
            }
            val newX = curX + (if (preX > curX) 1 else if (preX < curX) -1 else 0)
            val newY = curY + (if (preY > curY) 1 else if (preY < curY) -1 else 0)
            rope[i + 1] = newX to newY
        }

        for (line in input) {
            val (a, b) = line.split(" ")
            val (dx, dy) = directions[a]!!
            for (i in 1..b.toInt()) {
                val (x, y) = rope[0]
                rope[0] = x + dx to y + dy
                for (j in 0 until 9) {
                    updateTail(j)
                }
                seen.add(rope[9])
            }
        }
        return seen.size
    }


    val testInput1 = readInput("input/Day09_test1")
    val testInput2 = readInput("input/Day09_test2")
    check(solvePart1(testInput1) == 13)
    check(solvePart2(testInput1) == 1)
    check(solvePart2(testInput2) == 36)


    val input = readInput("input/Day09_input")
    println(solvePart1(input))
    println(solvePart2(input))

}