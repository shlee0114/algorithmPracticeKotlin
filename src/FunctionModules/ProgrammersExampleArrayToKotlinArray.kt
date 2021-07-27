package FunctionModules

class ProgrammersExampleArrayToKotlinArray {
    fun toArrayText(text : String) = text.replace("[", "arrayOf(").replace("]", ")")

}

fun main(){
    val text = ProgrammersExampleArrayToKotlinArray()
    print(text.toArrayText("[[\"100\",\"ryan\",\"music\",\"2\"],[\"200\",\"apeach\",\"math\",\"2\"],[\"300\",\"tube\",\"computer\",\"3\"],[\"400\",\"con\",\"computer\",\"4\"],[\"500\",\"muzi\",\"music\",\"3\"],[\"600\",\"apeach\",\"music\",\"2\"]]"))

}