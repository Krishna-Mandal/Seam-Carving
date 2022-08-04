fun main() {
    val num = readln().toInt()
    val numbers = IntArray(num) { readln().toInt() }
    val find = readln().toInt()
    println("YES".takeIf { numbers.contains(find) } ?: "NO")
}