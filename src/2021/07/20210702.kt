import kotlin.math.abs

class Fir_20210702{
    //시저 암호
    fun solution(s: String, n: Int): String {
        var answer = ""
        val alphabets = s.toCharArray()

        for(i in alphabets){
            var moveAlphabet = i + n
            if(i == ' ')
                moveAlphabet = ' '
            if(i in 'A'..'Z'){
                if(moveAlphabet > 'Z'){
                    moveAlphabet -= 26
                }
            }else if(i in 'a'..'z'){
                if(moveAlphabet > 'z'){
                    moveAlphabet -= 26
                }
            }
            answer += moveAlphabet.toString()
        }

        return answer
    }
}

class Snd_20210702{
    //문자열 내 마음대로 정렬하기
    fun solution(strings: Array<String>, n: Int): Array<String> {
        val map = mutableMapOf<String, ArrayList<String>>()
        val answer = arrayListOf<String>()
        for(i in strings){
            if(!map.containsKey(i[n].toString())){
                map[i[n].toString()] = arrayListOf()
                map[i[n].toString()]?.add(i)
            }else{
                map[i[n].toString()]?.add(i)
            }
        }

        map.forEach { (_, list) ->
            list.sort()
        }

        map.toSortedMap().forEach { (_, list) ->
            for(i in list){
                answer.add(i)
            }
        }
        return answer.toTypedArray()
    }
}

class Trd_20210702{
    //두 개 뽑아서 더하기
    fun solution(numbers: IntArray): IntArray {
        val map = mutableMapOf<Int, Int>()
        val answer = ArrayList<Int>()
        for(i in numbers.indices){
            for(j in i + 1 until numbers.size){
                map[numbers[i] + numbers[j]] = 0
            }
        }

        map.forEach { (t, _) ->
            answer.add(t)
        }
        answer.sort()
        return answer.toIntArray()
    }
}

class fth_20210702{
    //실패율
    fun solution(N: Int, stages: IntArray): IntArray {
        val map = mutableMapOf<Int, Double>()
        val answer = arrayListOf<Int>()
        stages.sort()

        for(i in 1 .. N){
            val firstIndex = stages.indexOf(i)
            val failedCount = stages.filter { it == i }.size
            val challengeCount = stages.size - firstIndex
            map[i] = if(failedCount == 0) 0.0 else failedCount.toDouble()/challengeCount.toDouble()
        }

        for(i in map.toList().sortedByDescending { it.second }.toMap().keys){
            answer.add(i)
        }

        return answer.toIntArray()
    }
}

class fifth_20210702{
    //키패드 누르기
    private var nowLeftHand = intArrayOf(3, 0)
    private var nowRightHand = intArrayOf(3, 2)
    private val keyPad = arrayOf(intArrayOf(3,1), intArrayOf(0,0), intArrayOf(0,1), intArrayOf(0,2), intArrayOf(1,0), intArrayOf(1,1), intArrayOf(1,2),intArrayOf(2,0), intArrayOf(2,1), intArrayOf(2,2))

    var answer = ""
    var handType = ""

    fun solution(numbers: IntArray, hand: String): String {
        handType = hand[0].toString().uppercase()

        for(i in numbers){
            setPosition(i)
        }

        return answer
    }

    private fun setPosition(number : Int){
        val leftHandDistance = nowLeftHand.getDistance(number)
        val rightHandDistance = nowRightHand.getDistance(number)
        val distanceDifference = leftHandDistance - rightHandDistance
        val hand =  when(number){
            1,4,7 -> {
                "L"
            }
            3,6,9 -> {
                "R"
            }
            else ->{
                when{
                    distanceDifference > 0 -> {
                        "R"
                    }
                    distanceDifference < 0 -> {
                        "L"
                    }
                    else -> {
                        handType
                    }
                }
            }
        }

        if(hand == "L")
            nowLeftHand.keypadInput(number, hand)
        else
            nowRightHand.keypadInput(number, hand)
    }

    private fun IntArray.getDistance(number : Int) : Int{
        val verticalDistance = this[0] - keyPad[number][0]
        val horizontalDistance = this[1] - keyPad[number][1]

        return abs(verticalDistance) + abs(horizontalDistance)
    }

    private fun IntArray.keypadInput(number : Int, type : String){
        this[0] = keyPad[number][0]
        this[1] = keyPad[number][1]

        answer += type
    }
}

fun main(){
    val test = fth_20210702()
    test.solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3))
}