class Fir_20210720{

    //예상 대진표
    //대진 최대 값을 구한 다음 중점을 기준으로 양쪽에 있으면 최대값이기에 한쪽에 몰려있을 때 마다 -1과 중앙값을 변경해준다.
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = n.exponentOfTwo()
        var leftNum = 0
        var rightNum = n
        var middleNum = (leftNum + rightNum) / 2
        while(true){
            if(middleNum >= a && middleNum >= b){
                rightNum = middleNum
                middleNum = (leftNum + rightNum) / 2
            }else if(middleNum < a && middleNum < b){
                leftNum = middleNum
                middleNum = (leftNum + rightNum) / 2
            }else{
                break
            }
            answer--
        }
        return answer
    }

    //최대 경기 수는 지수를 넘길 수 없다.
    private fun Int.exponentOfTwo() : Int{
        var exponent = 0
        var tempNumber = this
        while(tempNumber != 1){
            exponent++
            tempNumber /= 2
        }
        return exponent
    }
}

fun main(){
    val test = Fir_20210720()
    test.solution(16,8,9)
}
