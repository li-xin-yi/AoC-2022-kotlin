fun main() {

    fun solvePart1(input: List<String>): Int {
        var res = 0
        var time = 0
        var value = 1
        var i = 0
        for (line in input) {
            val inst = line.split(" ")
            val newTime = time + inst.size
            if (newTime >= 20 + i * 40) {
                res += value * (20 + i * 40)
                i += 1
            }
            if (i > 5) break
            if (inst.size == 2)
                value += inst[1].toInt()
            time = newTime
        }
        return res
    }

    fun solvePart2(input: List<String>) {
        var time = 0
        var value = 1
        var res = ""
        for (line in input) {
            val inst = line.split(" ")
            for (t in time until time + inst.size) {
                if (Math.abs(value - t % 40) <= 1) {
                    res += '#'
                } else {
                    res += "."
                }
            }
            if (inst.size == 2)
                value += inst[1].toInt()
            time += inst.size
            if (time > 241)
                break
        }
        for (i in 0..200 step 40) {
            println(res.substring(i, i + 40))
        }
    }


    val testInput = readInput("input/Day10_test")
    check(solvePart1(testInput) == 13140)
    solvePart2(testInput)

    val input = readInput("input/Day10_input")
    println(solvePart1(input))

    // Too hard to see the letters, my poor eyes
    solvePart2(input)
}