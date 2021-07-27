class Fir_20210728{
    //멀쩡한 사각형
    fun solution(w: Int, h: Int): Long {
        if (w == h) {
            return w.toLong() * h.toLong() - w.toLong()
        }

        if (w == 1 || h == 1) {
            return 0
        }
        val white = w.toLong() + h.toLong() - greatest(w.toLong(), h.toLong())

        return w.toLong() * h.toLong() - white
    }

    fun greatest(number1 : Long, number2 : Long) : Long{
        if(number1 == number2)
            return number1

        if(number1 > number2){
            val number3 = number1 - number2
            return if(number2 > number3) greatest(number2, number3)
            else greatest(number3, number2)
        }

        val number3 = number2 - number1
        return if(number1 > number3) greatest(number1, number3)
        else greatest(number3, number1)
    }
}

fun main(){

}