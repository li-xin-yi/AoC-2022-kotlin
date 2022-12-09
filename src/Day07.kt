import java.util.*

class Dir {
    val children = mutableMapOf<String, Dir>()
    var size = -1
}

fun main() {
    fun getRootDir(input: List<String>): Dir {
        val root = Dir()
        val stack = Stack<Dir>()
        var cur: Dir = root
        for (line in input) {
            val words = line.split(" ")
            if (words[0] == "$" && words[1] == "cd") {
                when (words[2]) {
                    "/" -> {
                        stack.clear()
                        stack.push(root)
                    }
                    ".." -> stack.pop()
                    else -> stack.push(cur.children[words[2]]!!)
                }
                cur = stack.peek()
            } else if (words[0] != "$") {
                if (cur.children.containsKey(words[1])) continue
                cur.children[words[1]] = Dir()
                if (words[0] != "dir") {
                    cur.children[words[1]]!!.size = words[0].toInt()
                }
            }
        }
        return root
    }

    fun solvePart1(input: List<String>): Int {
        var res = 0
        val root = getRootDir(input)

        fun dfs(root: Dir): Int {
            if (root.size != -1) return root.size
            root.size = root.children.values.sumOf { dfs(it) }
            if (root.size <= 100000) res += root.size
            return root.size
        }

        dfs(root)


        return res

    }

    fun solvePart2(input: List<String>): Int {
        val root = getRootDir(input)
        val lst = mutableListOf<Int>()

        fun dfs(root: Dir): Int {
            if (root.size != -1) return root.size
            root.size = root.children.values.sumOf { dfs(it) }
            lst.add(root.size)
            return root.size
        }

        val target = 30000000 - (70000000 - dfs(root))

        return lst.filter { it >= target }.minOrNull()!!

    }

    val testInput = readInput("input/Day07_test")
    check(solvePart1(testInput) == 95437)
    check(solvePart2(testInput) == 24933642)

    val input = readInput("input/Day07_input")
    println(solvePart1(input))
    println(solvePart2(input))


}