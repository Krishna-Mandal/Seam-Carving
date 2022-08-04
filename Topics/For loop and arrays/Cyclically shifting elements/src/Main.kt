import java.util.Collections

fun main() {
    val num = readln().toInt()
    val numbers = MutableList(num) { readln().toInt() }
    Collections.rotate(numbers, 1)
    println(numbers.joinToString(separator = " "))
}