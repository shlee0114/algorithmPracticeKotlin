import kotlin.math.abs

fun eightQueen(queenLocate : MutableList<MutableList<Int>>, chessBoard : Array<Array<Int>?>, nowLevel : Int) : MutableList<MutableList<Int>>?{
    for(i in 0 until 8){
        if(chessBoard[nowLevel]!![i] != -1){
            val tmpChessBoard = arrayOfNulls<Array<Int>>(8)
            for(j in chessBoard.indices){
                tmpChessBoard[j] = chessBoard[j]!!.clone()
            }
            setCantBePlacedLocationOnChessBoard(tmpChessBoard, nowLevel, i)
            queenLocate.add(mutableListOf(nowLevel, i))
            val tmpLocate = mutableListOf<MutableList<Int>>()
            for(j in queenLocate){
                tmpLocate.add(j.toMutableList())
            }
            if(eightQueen(tmpLocate, tmpChessBoard, nowLevel + 1) != null) {
                println(tmpLocate.toString())
                return tmpLocate
            }
        }
    }
    return if(queenLocate.count() >= 8){
        queenLocate
    }else{
        null
    }
}

fun setCantBePlacedLocationOnChessBoard(chessBoard : Array<Array<Int>?>, queenLocateVertical: Int, queenLocateHorizontal : Int){
    for(i in 0 until 8){
        chessBoard[queenLocateVertical]!![i] = -1
        chessBoard[i]!![queenLocateHorizontal] = -1
        val crossInterval = abs(queenLocateVertical - i)
        if(queenLocateHorizontal - crossInterval >= 0){
            chessBoard[i]!![queenLocateHorizontal - crossInterval] = -1
        }
        if(queenLocateHorizontal + crossInterval < 8){
            chessBoard[i]!![queenLocateHorizontal + crossInterval] = -1
        }

    }
}

fun main(){
    val tmp : Array<Int> = arrayOf(0,0,0,0,0,0,0,0)
    val chessBoard : Array<Array<Int>?> = arrayOf(tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone())
    val queenLocate = mutableListOf<MutableList<Int>>()
    eightQueen(queenLocate, chessBoard, 0)
}

