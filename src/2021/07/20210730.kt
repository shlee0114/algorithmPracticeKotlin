class Fir_20210730{
    //멀리 뛰기
    fun solution(n: Int): Long {
        val factorial = Array(n + 2){0L}
        factorial[1] = 1L
        factorial[2] = 2L
        for(i in 3 .. n){
            factorial[i] = (factorial[i-1] + factorial[i-2]) % 1234567L
        }
        return factorial[n]
    }
}

fun main(){
    val test = Fir_20210730()
    print(test.solution(30))
}