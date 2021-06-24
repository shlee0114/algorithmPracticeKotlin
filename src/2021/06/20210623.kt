class Fir_20210623 {
    private lateinit var hasCleared : Array<Boolean>
    private var answer = 0
    private var leftMoveCount = 0
    private var rightMoveCount = 0

    private val NO_RETURN_INDEX = -1

    //프로그래머스 문제 : 조이스틱
    //그리드를 이용해서 글자를 가장 빠르게 만드는 움직이는 수를 구하는 문제
    //모든 글자를 만들 때 필요한 움직임을 미리 넣은 다음 커서가 움직이는 값만 추가해준다
    //추가로 이동할 항목이 없거나 남은 문자들이 전부 A일 경우 종료한다.
    //name : 이동거리를 구할 문자
    fun solution(name: String): Int {
        hasCleared = Array(name.length) { false }

        val nameArray = name.toCharArray()

        var nextIndex = 0

        for(i in nameArray){
            answer += getMoveCount(i)
        }
        hasCleared[0] = true

        while(true){
            //매 루프마다 남은 문자가 전부 A인지 체크
            if(nameArray.checkIsAllA())
                return answer
            //왼쪽과 오른쪽 중 더 짧은 것을 answer에 더한 후 해당 인덱스 반환
            nextIndex = nameArray.compareLeftAndRight(nextIndex)
            leftMoveCount = 0
            rightMoveCount = 0
            if(nextIndex == NO_RETURN_INDEX)
                return answer
        }
    }

    //남은 문자가 전부 A인지 확인
    private fun CharArray.checkIsAllA() : Boolean{
        for(i in this.indices){
            if(!hasCleared[i])
                if(this[i] != 'A')
                    return false
        }
        return true
    }

    //왼쪽의 수와 오른쪽의 수 중 작은 수 적용 후 반환
    //index : 현재 수의 번호
    private fun CharArray.compareLeftAndRight(index : Int) : Int{
        //가장 가까운 왼쪽 수의 인덱스
        val leftIndex = this.getLeftIndex(index, index)
        //가장 가까운 오른쪽 수의 인덱스
        val rightIndex = this.getRightIndex(index)

       return  when{
           //양 쪽 다 이미 완료가 되어있을 경우
           leftIndex + rightIndex == NO_RETURN_INDEX * 2 -> -1

           //왼쪽이 완료가 되어있을 경우
           leftIndex == NO_RETURN_INDEX -> moveIndexAndAddCount(rightIndex, rightMoveCount)

           //오른쪽이 완료가 되어있을 경우
           rightIndex == NO_RETURN_INDEX -> moveIndexAndAddCount(leftIndex, leftMoveCount)
           else -> {
               //왼쪽과 오른쪽을 비교해서 짧은 쪽으로 반환
               //같을 시 오른쪽을 반환하는 이유는 좌측으로 가면 우측 끝으로 가지만 우측은 좌측 끝으로 갈 수 없기에
                if(leftMoveCount < rightMoveCount)
                    moveIndexAndAddCount(leftIndex, leftMoveCount)
                else
                    moveIndexAndAddCount(rightIndex,  rightMoveCount)
           }
        }

    }

    //왼쪽에 유효한 글자까지의 길이
    //0일 경우 우측 끝부터 다시 시작하며, 처음 들어온 숫자와 같아질경우 반환 숫자 없음으로 표기한다.
    //leftMoveCount가 이동 거리이다.
    //만약 A이거나 이전에 이미 완성했던 작업이라면 지나간다.
    //index : 현재 인덱스
    //firstIndex : 왼쪽을 구하기 시작한 시점의 초기 인덱스
    private fun CharArray.getLeftIndex(index : Int, firstIndex : Int) : Int{
        val leftIndex = if(index <= 0) this.size - 1 else index - 1
        leftMoveCount++
        if(leftIndex == firstIndex)
            return NO_RETURN_INDEX
        if(this[leftIndex] == 'A' || hasCleared[leftIndex])
            return this.getLeftIndex(leftIndex, firstIndex)
        return leftIndex
    }

    //오른쪽에 유효한 글자까지의 길이
    //만약 사이즈와 크기가 같다면 반환 숫자 없음으로 표기한다.
    //rightMoveCount 이동 거리이다.
    //만약 A이거나 이전에 이미 완성했던 작업이라면 지나간다.
    //index : 현재 인덱스
    private fun CharArray.getRightIndex(index : Int) : Int{
        val rightIndex = index + 1
        rightMoveCount++
        if(rightIndex == this.size)
            return NO_RETURN_INDEX
        if(this[rightIndex] == 'A' || hasCleared[rightIndex])
            return this.getRightIndex(rightIndex)
        return rightIndex
    }

    //해당 인덱스에 완료 체크 후 인덱스 까지의 길이를 총합에 더한 후 인덱스 반환
    //index : 이동할 위치
    //count : 이동할 위치까지의 길이
    private fun moveIndexAndAddCount(index : Int, count : Int) : Int{
        if(count == 0)
            return index
        hasCleared[index] = true
        answer += count
        return index
    }

    //해당 글자까지의 길이
    //A부터와 Z(A에서 한 칸 내려가야하기에 1을 더함)부터를 잰 다음 둘 중 짧은 것을 반환
    //name : 글자
    private fun getMoveCount(name : Char) : Int{
        val countFromA = kotlin.math.abs('A' - name)
        val countFromZ = kotlin.math.abs('Z' - name) + 1

        return if(countFromA < countFromZ)
            countFromA
        else
            countFromZ
    }
}

fun main(){
    val test = Fir_20210623()
    println("${test.solution("AABAAAAAAABBB")} 12")
}