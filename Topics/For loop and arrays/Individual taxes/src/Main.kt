const val CENT = 100
fun main() {
    val numOfCompamnies = readln().toInt()
    val income = MutableList(numOfCompamnies) { readln().toFloat() }
    val taxper = MutableList(numOfCompamnies) { readln().toFloat() }
    val netTax = mutableListOf<Float>()

    for (i in income.indices) {
        netTax.add(income[i] * taxper[i] / CENT)
    }

    println(netTax.withIndex().maxByOrNull { it.value }!!.index + 1)
}