class Fir_20210726{
    //메뉴 리뉴얼
    private val combinationCountByCourseCount = mutableMapOf<Int, MutableMap<String, Int>>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = arrayListOf<String>()

        for(i in course){
            combinationCountByCourseCount[i] = mutableMapOf()
            for(j in orders){
                j.toCharArray().sortedArray().combination(CharArray(i), i, 0, 0)
            }
        }

        combinationCountByCourseCount.sortCombinationByCount()

        for(i in combinationCountByCourseCount.values){
            i.getMostUsedString(answer)
        }

        return answer.sorted().toTypedArray()
    }

    private fun CharArray.combination(combinationTempStorage : CharArray, number : Int, index : Int, depth : Int){
        when {
            number == 0 -> {
                combinationCountByCourseCount[combinationTempStorage.size]?.run {
                    set(combinationTempStorage.joinToString(""), (get(combinationTempStorage.joinToString(""))?:0) + 1)
                }
                return
            }
            depth == size -> {
                return
            }
            else -> {
                combinationTempStorage[index] = get(depth)
                combination(combinationTempStorage, number - 1, index + 1, depth + 1)
                combination(combinationTempStorage, number, index, depth + 1)
            }
        }
    }

    private fun MutableMap<Int, MutableMap<String, Int>>.sortCombinationByCount(){
        forEach { (key, mutableMap) ->
            combinationCountByCourseCount[key] = mutableMap.toList().sortedByDescending { it.second }.toMap().toMutableMap()
        }
    }

    private fun MutableMap<String, Int>.getMostUsedString(array: ArrayList<String>){
        var maxCount = Int.MAX_VALUE
        forEach { (string, count) ->
            if(count == 1 || (maxCount > count && maxCount != Int.MAX_VALUE)){
                return@forEach
            }
            maxCount = count
            array.add(string)
        }
    }
}

fun main(){
    val test = Fir_20210726()
    test.solution(arrayOf("XYZ", "XWY", "WXA"), intArrayOf(2,3,4))
}