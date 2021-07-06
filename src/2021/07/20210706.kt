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

fun main(){
    val test = Fir_20210706()
    test.solution("3people unFollowed me")
}
