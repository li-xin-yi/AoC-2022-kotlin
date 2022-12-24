fun main() {

    fun gcd(a: Int, b: Int): Int {
        if (a == 0) return b
        return gcd(b % a, a)
    }

    fun solvePart1(input: List<String>): Int {
        val starts = mutableListOf<MutableList<Int>>()
        val funcs = mutableListOf<(Int) -> Unit>()

        val n = (input.size + 1) / 7
        val res = MutableList(n) { 0 }
        for (i in 0 until input.size step 7) {
            val items = input[i + 1].split(":")[1].split(",").map { it.trim().toInt() }
            starts.add(items.toMutableList())
            val divisor = input[i + 3].split(" ").last().toInt()
            val div1 = input[i + 4].split(" ").last().toInt()
            val div2 = input[i + 5].split(" ").last().toInt()
            val expr = input[i + 2].split("=").last().trim().split(" ")
            val func = fun(old: Int) {
                val a = if (expr[0] == "old") old else expr[0].toInt()
                val b = if (expr[2] == "old") old else expr[2].toInt()
                var r = when (expr[1]) {
                    "+" -> a + b
                    "*" -> a * b
                    else -> 0
                }
                r /= 3
                if (r % divisor == 0) {
                    starts[div1].add(r)
                } else {
                    starts[div2].add(r)
                }

            }
            funcs.add(func)
        }

        for (i in 0 until 20) {
            for (j in 0 until n) {
                res[j] += starts[j].size
                starts[j].forEach { funcs[j](it) }
                starts[j].clear()
            }
        }

        val (m1, m2) = res.sorted().takeLast(2)

        return m1 * m2
    }

    fun solvePart2(input: List<String>): Long {
        val starts = mutableListOf<MutableList<Long>>()
        val funcs = mutableListOf<(Long, Int) -> Unit>()

        val n = (input.size + 1) / 7
        val res = MutableList(n) { 0 }
        val divisors = mutableListOf<Int>()
        for (i in 0 until input.size step 7) {
            val items = input[i + 1].split(":")[1].split(",").map { it.trim().toLong() }
            starts.add(items.toMutableList())
            val divisor = input[i + 3].split(" ").last().toInt()
            divisors.add(divisor)
            val div1 = input[i + 4].split(" ").last().toInt()
            val div2 = input[i + 5].split(" ").last().toInt()
            val expr = input[i + 2].split("=").last().trim().split(" ")
            val func = fun(old: Long, mod: Int) {
                val a = if (expr[0] == "old") old else expr[0].toLong()
                val b = if (expr[2] == "old") old else expr[2].toLong()
                var r = when (expr[1]) {
                    "+" -> a + b
                    "*" -> a * b
                    else -> 0
                }
                r %= mod
                if (r % divisor == 0L) {
                    starts[div1].add(r)
                } else {
                    starts[div2].add(r)
                }

            }
            funcs.add(func)
        }

        val mod = divisors.reduce { acc, i -> acc * i / gcd(acc, i) }

        for (i in 0 until 10000) {
            for (j in 0 until n) {
                res[j] += starts[j].size
                starts[j].forEach { funcs[j](it, mod) }
                starts[j].clear()
            }
        }

        val (m1, m2) = res.sorted().takeLast(2)

        return (m1.toLong() * m2)
    }


    val testInput = readInput("input/Day11_test")
    check(solvePart1(testInput) == 10605)
    check(solvePart2(testInput) == 2713310158L)

    val input = readInput("input/Day11_input")
    println(solvePart1(input))
    println(solvePart2(input))

}