import FunctionModules.deepCopyArrayArray
import FunctionModules.deepCopyMutableMutable
import kotlin.math.abs

fun main(){
    val tmp : Array<Int> = arrayOf(0,0,0,0,0,0,0,0)
    val chessBoard : Array<Array<Int>?> = arrayOf(tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone(),tmp.clone())
    println(eightQueen(mutableListOf(), chessBoard, 0))
}

//역추적을 사용한 퀸 8개 배치 문제
//재귀함수로 0,0부터 7,7까지 미리 배치한 퀸의 이동경로가 없는 곳에 퀸을 일일히 배치하면서 8개를 구하는 방식
//모든 경우의 수를 구하는 것이 아닌 5번 줄에 더이상 퀸이 놓을 곳이 없으면 4번 줄 퀸의 위치를 바꾼 후 5번 줄을 다시 탐색하는 방식
//queenLocate : 퀸의 위치 8개가 되면 재귀가 종료된다
//chessBoard 체스보드 퀸의 이동경로를 저장하며 퀸의 이동경로는 -1 아닌 곳은 0으로 저장한다
//nowLevel : 현재 열
fun eightQueen(queenLocate : MutableList<MutableList<Int>>, chessBoard : Array<Array<Int>?>, nowLevel : Int) : MutableList<MutableList<Int>>?{
    for(i in 0 until 8){
        if(chessBoard[nowLevel]!![i] != -1){
            val tmpChessBoard = deepCopyArrayArray(chessBoard, 8).also {//Array 배열 복사
                setCantBePlacedLocationOnChessBoard(it, nowLevel, i)
            }

            val tmpLocate = deepCopyMutableMutable(queenLocate).apply { //MutableList 배열 복사
                add(mutableListOf(nowLevel, i))
                if(count() >= 8){
                    return this
                }
            }

            eightQueen(tmpLocate, tmpChessBoard, nowLevel + 1).also {
                if (it != null){
                    return it
                }
            }
        }
    }
    return null
}

//입력된 퀸의 위치에 따라 퀸이 갈 수 있는 곳을 체스보드에 마킹하는 작업
//퀸의 가로와 세로에 -1을 저장
//대각선의 경우 현재 퀸의 y값에 체크하는 열을 뺀 후 나온 절대 값으로 x에 - 혹은 +를 하여서 한 열당 최대 2개(0미만 혹은 8이상일 경우 overflow가 발생하기에 수정하지 않음)에 -1을 저장한다
//예) 퀸의 위치 : 3, 1 체크하는 열 : 2 마킹 위치 : 2, 1 - 2 (0미만이므로 마킹하지 않음), 2, 1 + 2
fun setCantBePlacedLocationOnChessBoard(chessBoard : Array<Array<Int>?>, queenLocateVertical: Int, queenLocateHorizontal : Int){
    for(i in 0 until 8){
        chessBoard[queenLocateVertical]!![i] = -1
        chessBoard[i]!![queenLocateHorizontal] = -1
        abs(queenLocateVertical - i).also {
            if(queenLocateHorizontal - it >= 0){
                chessBoard[i]!![queenLocateHorizontal - it] = -1
            }
            if(queenLocateHorizontal + it < 8){
                chessBoard[i]!![queenLocateHorizontal + it] = -1
            }
        }

    }
}



