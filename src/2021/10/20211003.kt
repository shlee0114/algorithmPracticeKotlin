class fir{
    fun solution(table: Array<String>, languages: Array<String>, preference: IntArray): String {
        val totalPoints = Array(table.size){0}
        val occupation = Array(table.size){""}

        for(rowIndex in table.indices){
            val rowValue = table[rowIndex]
            val columns = rowValue.split(" ")
            occupation[rowIndex] = columns[0]

            for(columnIndex in 1 until columns.size){
                val point = checkPoint(columns[columnIndex], languages, preference)
                totalPoints[rowIndex] += point * (6 - columnIndex)
            }
        }

        return getHigherPoint(totalPoints, occupation)
    }

    private fun checkPoint(languages : String, userLanguages : Array<String>, userPreference : IntArray) : Int {
        for (i in userLanguages.indices) {
            if (languages == userLanguages[i]) {
                return userPreference[i]
            }
        }

        return 0
    }

    private fun getHigherPoint(totalPoints : Array<Int>, occupation : Array<String>) : String{
        val maxPoint = totalPoints.maxOrNull()?:0
        var maxOccupation = ""

        for(i in totalPoints.indices){
            if(maxPoint == totalPoints[i]){
                maxOccupation = if(maxOccupation == ""){
                    occupation[i]
                }else{
                    if(maxOccupation < occupation[i]){
                        maxOccupation
                    }else{
                        occupation[i]
                    }
                }
            }
        }
        return maxOccupation
    }
}

fun main(){
    val test = fir()
    test.solution(arrayOf("SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"),
    arrayOf("PYTHON", "C++", "SQL"), intArrayOf(7, 5, 5))
}