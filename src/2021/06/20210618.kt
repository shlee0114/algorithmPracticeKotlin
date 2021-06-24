
class Fir_20210618 {

    //프로그래머스 문제 : H-index
    //지정한 값보다 높은 숫가자 지정한 값만큼 잇어야 하며, 지정한 값보다 낮은 숫자는 지정한 값보다 적게 있어야 한다.
    //그 중 가장 높은 수를 구하는 문제
    //우선 반을 가른 다음 가운데 값을 지정한 값으로 지정하고 위의 조건을 대입해서 조건에 충족하지 않으면 지정한 값을 조건에 맞을 때 까지 1씩 빼준다
    //빼주면서 지정 값이 낮은 값으로 분류 되어있는 것보다 낮아질 때는 낮은 값을 높은 값으로 보낸다
    //빼주기만 하는 이유는 더하는 순간 낮은 값이 지정 값보다 높은 값보다 많아지기 때문이다
    //citations : 입력 값들
    fun solution(citations: IntArray): Int {
        citations.sort()

        val underCitations = citations.copyOfRange(0, citations.size / 2 )
        val overCitations = citations.copyOfRange(citations.size / 2, citations.size)
        val hIndex = overCitations[0]

        return decreaseHIndex(hIndex, underCitations, overCitations)
    }

    //재귀함수로 지정 값인지 테스트 후 만약 지정값이 낮은 값보다 클 시 1씩 빼준다
    //그 후 지정 값과 아래 값이 같으면 낮은 값을 높은 값으로 보낸다
    //위를 지속적으로 반복 후 지정값이 0이거나 조건에 충족 시 지정값을 반환한다.
    //hIndex : 지정값
    //underCitations : 지정 값보다 낮은 값
    //overCitations : 지정 값보다 높은 값
    private fun decreaseHIndex(hIndex : Int, underCitations : IntArray, overCitations : IntArray) : Int{
        if(hIndex >= underCitations.size && hIndex <= overCitations.size || hIndex == 0){
            return hIndex
        }

        var hIndex = hIndex
        if(underCitations.isEmpty() || underCitations[underCitations.size - 1] < hIndex){
            hIndex -= 1
        }

        var underCitations = underCitations
        var overCitations = overCitations
        if(underCitations.isNotEmpty()){
            if(underCitations.last() >= hIndex){
                overCitations = overCitations.toList().plus(underCitations[underCitations.size - 1]).toIntArray()
                underCitations = underCitations.copyOfRange(0, underCitations.size - 1)
            }
        }
        return decreaseHIndex(hIndex, underCitations, overCitations)
    }
}

fun main(){
    val test = Fir_20210618()
    println("${test.solution(intArrayOf(4, 4, 4))} 3")
}