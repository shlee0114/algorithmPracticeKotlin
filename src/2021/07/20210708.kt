
import kotlin.math.abs

class Fir_20210708{

    //거리두기 확인하기

    private var hasFind = false

    private var startWidth =  0
    private var startHeight = 0

    private val widthWay = arrayOf(1,-1,0,0)
    private val heightWay = arrayOf(0,0,1,-1)

    fun solution(places: Array<Array<String>>): IntArray {
        if(places[0].isEmpty())
            return intArrayOf()
        val answer = IntArray(places.size){1}

        for(i in places.indices){
            for(j in places[i].indices){
                if(answer[i] == 0)
                    break
                if(places[i][j].contains('P')){
                    for(h in places[i][j].toCharArray().indices){
                        if(places[i][j][h] == 'P'){
                            hasFind = false
                            startWidth = h
                            startHeight = j
                            if(places[i].findOtherStudentInTwoLength(j,h)){
                                answer[i] = 0
                                break
                            }
                        }
                    }
                }
            }
        }

        return answer
    }


    private fun Array<String>.findOtherStudentInTwoLength(height : Int, width : Int) : Boolean{
        val length = getLength(height, width)
        if(width < 0 || height < 0 || width > 4 || height > 4 || hasFind || length >= 3){
            return false
        }

        if(this[height][width] == 'X')
            return false

        if(this[height][width] == 'P' && !(startHeight == height && startWidth == width)){
            hasFind = true
            return true
        }

        val result = BooleanArray(4){false}

        for(i in 0 until 4){
            if(length < getLength(height + heightWay[i], width + widthWay[i])){
                result[i] = this.findOtherStudentInTwoLength(height + heightWay[i], width + widthWay[i])
            }
        }


        return result.contains(true)
    }

    private fun getLength(height: Int, width: Int) = abs(startHeight - height) + abs(startWidth - width)
}

fun main(){
    val test = Fir_20210708()
    test.solution(arrayOf(arrayOf("PPPPP", "OXXOX", "OPXPX", "OOXOX", "POXXP"), arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"), arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
    arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"), arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PPPPP")))
}