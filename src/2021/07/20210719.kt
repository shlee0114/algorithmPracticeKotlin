class Fir_20210719{

    //튜플

    private val tuple = arrayListOf<Int>()

    fun solution(s: String): IntArray {
        val sortedTupleElement = s.sortedTupleElement()

        for(i in sortedTupleElement){
            tuple.add(i.findNextElement())
        }

        return tuple.toIntArray()
    }

    private fun String.sortedTupleElement() : List<Array<String>>{
        val tmp = trim().split("},{")
        val tupleElement = ArrayList<Array<String>>()

        for(i in tmp){
            tupleElement.add(i.replace("{", "").replace("}", "").split(",").toTypedArray())
        }
        return tupleElement.sortedBy { it.size }
    }

    private fun Array<String>.findNextElement() : Int{
        forEach { it ->
            if(tuple.isEmpty() || !tuple.contains(it.toInt()))
                return it.toInt()
        }
        return 0
    }
}

fun main(){
    val test = Fir_20210719()
    test.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")
}