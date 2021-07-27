class Fir_20210727{
    //후보키
    private val columnNumberOfCases = arrayListOf<ArrayList<Int>>()

    fun solution(relation: Array<Array<String>>): Int {
        var answer = 0
        var index = 0

        getCombineColumn(relation)

        while(index < columnNumberOfCases.size){
            if(columnNumberOfCases[index].isCandidateKey(relation)){
                answer++
            }else{
                index++
            }
        }

        return answer
    }

    private fun getCombineColumn(relation: Array<Array<String>>){
        val columnCount = relation[0].size
        val columnNumber = IntArray(columnCount){i -> i + 1 }

        for(i in 1..columnCount){
            columnNumber.combinationColumn(IntArray(i){0}, i, 0,0)
        }
    }

    private fun IntArray.combinationColumn(combinationTempStorage : IntArray, number : Int, index : Int, depth : Int){
        when {
            number == 0 -> {
                columnNumberOfCases.add(arrayListOf<Int>().apply { addAll(combinationTempStorage.toTypedArray()) })
                return
            }
            depth == size -> {
                return
            }
            else -> {
                combinationTempStorage[index] = get(depth)
                combinationColumn(combinationTempStorage, number - 1, index + 1, depth + 1)
                combinationColumn(combinationTempStorage, number, index, depth + 1)
            }
        }
    }

    private fun ArrayList<Int>.isCandidateKey(relation: Array<Array<String>>) : Boolean{
        val candidateKey = joinToString("")
        val columnValue = arrayListOf<String>()

        for(i in relation){
            var columns = ""

            forEach { columnNumber ->
                columns += "_" + i[columnNumber - 1]
            }

            if(columnValue.contains(columns)) {
                return false
            }

            columnValue.add(columns)
        }

        deleteNotMinimal(candidateKey)
        return true
    }

    private fun deleteNotMinimal(candidateKey : String){
        var index = 0
        val candidateKeyArray = candidateKey.toCharArray()
        while(index < columnNumberOfCases.size){
            val columnCaseString = columnNumberOfCases[index].joinToString("")

            if(columnCaseString.contains(candidateKeyArray[0])){
                var remove = true
                for(i in candidateKeyArray){
                    if(!columnCaseString.contains(i)){
                        remove = false
                        break
                    }
                }

                if(remove)
                    columnNumberOfCases.removeAt(index)
                else
                    index ++
            }else{
                index++
            }
        }
    }
}

fun main(){
    val test = Fir_20210727()
    test.solution(arrayOf(arrayOf("100","ryan","music","2"),arrayOf("200","apeach","math","2"),arrayOf("300","tube","computer","3"),arrayOf("400","con","computer","4"),arrayOf("500","muzi","music","3"),arrayOf("600","apeach","music","2")))

}