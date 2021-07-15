class Fir_20210715{

    //숫자 문자열과 영단어

    val eng = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val num = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

    fun solution(s: String): Int {
        var answer = s
        for(i in eng.indices){
            answer = answer.replace(eng[i], num[i])
        }

        return answer.toInt()
    }
}

fun main(){
    val test = Fir_20210715()

    print(test.solution("one4seveneight"))
}