fun main() {

    val directions = listOf((0 to 1), (1 to 0), (0 to -1), (-1 to 0))

    fun solvePart1(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        var (startX, startY) = (0 to 0)
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (input[i][j] == 'S') {
                    startX = i
                    startY = j
                    break
                }
            }
        }

        val queue = ArrayDeque<List<Int>>()
        val seen = List(n) { MutableList(m) { false } }
        seen[startX][startY] = true
        queue.add(listOf(startX, startY, 0, 'a'.code))
        while (queue.isNotEmpty()) {
            val (x, y, step, key) = queue.removeFirst()
            for ((dx, dy) in directions) {
                val (nx, ny) = (x + dx to y + dy)
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || seen[nx][ny]) continue
                if (input[nx][ny] == 'E') {
                    if (key + 1 >= 'z'.code)
                        return step + 1
                    continue
                }
                if (input[nx][ny].code > key + 1) continue
                seen[nx][ny] = true
                queue.add(listOf(nx, ny, step + 1, input[nx][ny].code))
            }
        }


        return 0
    }

    fun solvePart2(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        var (startX, startY) = (0 to 0)
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (input[i][j] == 'E') {
                    startX = i
                    startY = j
                    break
                }
            }
        }

        val queue = ArrayDeque<List<Int>>()
        val seen = List(n) { MutableList(m) { false } }
        seen[startX][startY] = true
        queue.add(listOf(startX, startY, 0, 'z'.code))

        while (queue.isNotEmpty()) {
            val (x, y, step, key) = queue.removeFirst()
            for ((dx, dy) in directions) {
                val (nx, ny) = (x + dx to y + dy)
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || seen[nx][ny]) continue
                if (input[nx][ny] == 'S' || input[nx][ny] == 'a') {
                    if (key <= 'a'.code + 1)
                        return step + 1
                    continue
                }
                if (input[nx][ny].code < key - 1) continue
                seen[nx][ny] = true
                queue.add(listOf(nx, ny, step + 1, input[nx][ny].code))
            }
        }
        return 0
    }

    val testInput = readInput("input/Day12_test")
    check(solvePart1(testInput) == 31)
    check(solvePart2(testInput) == 29)

    val input = readInput("input/Day12_input")
    println(solvePart1(input))
    println(solvePart2(input))
}