import kotlin.math.max
import kotlin.math.sign

class Fir_20210706{
    //JadenCase 문자열 만들기
    //정규식을 사용해서 첫 글자가 a-z중 하나일 시 대문자로 변경 후 저장
    fun solution(s: String): String {
        var answer = ""

        val words = s.lowercase().split(" ")

        val wordRegex = Regex("^[a-z]")

        for(i in words){
            var word = i
            if(i.isNotEmpty()){
                word = word.replace(wordRegex, word[0].uppercase())
            }
            answer += "$word "
        }

        return answer.substring(0, answer.length - 1)
    }
}

class Snd_20210706{
    //행렬의 곱셈
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size){IntArray(arr2[0].size){0} }
        for(i in arr1.indices){
            for(j in arr1[i].indices){
                for(h in arr2.indices){
                    answer[i][h] += arr1[i][j] * arr2[j][h]
                }
            }
        }
        return answer
    }
}

class Trd_20210706{

    //행렬 테두리 회전하기

    private var minValue = 0
    private var destinationPoint = intArrayOf()
    private var startPoint = intArrayOf()
    private var coveredNumber = -1
    private val square = arrayListOf<ArrayList<Int>>()

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()

        initializeSquare(rows, columns)

        for(i in queries){
            initializeDefaultValues(i)
            square.rotateRight(intArrayOf(i[0], i[1] + 1))
            answer.add(minValue)
        }

        return answer.toIntArray()
    }

    private fun initializeSquare(rows : Int, columns: Int){
        var nowNumber = 1
        for(i in 0 until rows){
            val horizontal = arrayListOf<Int>()
            for(j in 0 until columns){
                horizontal.add(nowNumber)
                nowNumber++
            }
            square.add(horizontal)
        }
    }

    private fun initializeDefaultValues(i : IntArray){
        i.pointConversionToPrograming()
        destinationPoint = intArrayOf(i[2], i[3])
        startPoint = intArrayOf(i[0], i[1])
        coveredNumber = square[i[0]][i[1] + 1]
        minValue = square[i[0]][i[1] + 1]
        square[i[0]][i[1] + 1] = square[i[0]][i[1]]
    }

    private fun IntArray.pointConversionToPrograming(){
        this[0]--
        this[1]--
        this[2]--
        this[3]--
    }

    private fun ArrayList<ArrayList<Int>>.rotateRight(nowPosition : IntArray){
        if(nowPosition[1] >= destinationPoint[1])
            this.rotateDown(nowPosition)
        else{
            this.moveNumber(nowPosition[0], nowPosition[1] + 1)

            nowPosition[1]++
            this.rotateRight(nowPosition)
        }
    }

    private fun ArrayList<ArrayList<Int>>.rotateDown(nowPosition : IntArray){
        if(nowPosition[0] >= destinationPoint[0])
            this.rotateLeft(nowPosition)
        else{
            this.moveNumber(nowPosition[0] + 1, nowPosition[1])
            nowPosition[0]++
            this.rotateDown(nowPosition)
        }

    }

    private fun ArrayList<ArrayList<Int>>.rotateLeft(nowPosition : IntArray){
        if(nowPosition[1] <= startPoint[1])
            this.rotateUp(nowPosition)
        else{
            this.moveNumber(nowPosition[0], nowPosition[1] - 1)
            nowPosition[1]--
            this.rotateLeft(nowPosition)
        }
    }

    private fun ArrayList<ArrayList<Int>>.rotateUp(nowPosition : IntArray){
        if(nowPosition[0] <= startPoint[0]) {
            return
        }
        else{
            this.moveNumber(nowPosition[0] - 1, nowPosition[1])
            nowPosition[0]--
            this.rotateUp(nowPosition)
        }
    }

    private fun ArrayList<ArrayList<Int>>.moveNumber(y : Int, x : Int){
        val tmp = this[y][x]
        this[y][x] = coveredNumber
        coveredNumber = tmp
        checkIsMinValue()
    }

    private fun checkIsMinValue(){
        if(coveredNumber < minValue)
            minValue = coveredNumber
    }
}

class Fth_20210706{

    //문자열 압축

   fun solution(s: String): Int {
        var minAnswer = s.length
        for(i in 1..s.length/2){
            var answer = s.length
            var index = 0
            while(index <= s.length - i){
                val compressionCount = compression(index, i, s)
                if(compressionCount == 0){
                    index += i
                }else{
                    index += i * compressionCount
                    answer = answer - (i * compressionCount) + 1
                    if(compressionCount >= 9)
                        answer++
                }
            }
            if(answer < minAnswer)
                minAnswer = answer
        }
        return minAnswer
    }

    private fun compression(startPoint : Int, size : Int, text : String) : Int{
        val compareText = text.substring(startPoint, startPoint + size)
        var compressionCount = 0
        while(startPoint + ((compressionCount + 1) * size) + size <= text.length){
            if(compareText == text.substring(startPoint + ((compressionCount + 1) * size),startPoint + ((compressionCount + 1) * size) + size )){
                compressionCount++
            }else
                break
        }
        return compressionCount
    }
}

fun main(){
    val test = Fir_20210706()
    test.solution("3people unFollowed me")

    val test2 = Trd_20210706()
    test2.solution(6,6, arrayOf(intArrayOf(2,2,5,4),intArrayOf(3,3,6,6), intArrayOf(5,1,6,3)))

    val test3 = Fth_20210706()
    test3.solution("xxxxxxxxxxyyy")
}
