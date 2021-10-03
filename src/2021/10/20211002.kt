import kotlin.properties.Delegates

class Fir {

    private var increaseInDirection = arrayOf(arrayOf(0, -1),arrayOf(1, 0),arrayOf(0, 1),arrayOf(-1, 0))

    private var useAbleGrid by Delegates.notNull<Array<CharArray>>()
    private var allColumnWays by Delegates.notNull<Array<Array<Array<Boolean>>>>()

    fun solution(grid: Array<String>): IntArray {
        val answer = arrayListOf<Int>()

        useAbleGrid = Array(grid.size){ index ->
            grid[index].toCharArray()
        }

        allColumnWays = Array(useAbleGrid.size){
            Array(useAbleGrid[0].size){
                arrayOf(false, false, false, false)//0 : up, 1 : down, 2 : left, 3 : right
            }
        }

        useAbleGrid.forEachIndexed { rowIndex, rowValue ->
            for(columnIndex in rowValue.indices){
                for(i in 0 until 4){
                    if(!allColumnWays[rowIndex][columnIndex][i]){

                        answer.getRoute(arrayOf(rowIndex, columnIndex, i))
                    }
                }
            }
        }

        return answer.sorted().toTypedArray().toIntArray()
    }

    private fun ArrayList<Int>.getRoute(state : Array<Int>) {
        var wayCount = 0
        while(true){
            if(allColumnWays[state[0]][state[1]][state[2]]){
                break
            }

            allColumnWays[state[0]][state[1]][state[2]] = true
            wayCount++

            state[2] =
                when(useAbleGrid[state[0]][state[1]]){
                    'L' -> state[2] + 1
                    'R' -> state[2] - 1
                    else -> state[2]
                }

            state[2] = if(state[2] < 0) 3 else if(state[2] > 3) 0 else state[2]

            state[0] = state[0] + increaseInDirection[state[2]][0]
            state[1] = state[1] + increaseInDirection[state[2]][1]

            state[0] = if(state[0] < 0) useAbleGrid.size - 1 else if(state[0] >= useAbleGrid.size) 0 else state[0]
            state[1] = if(state[1] < 0) useAbleGrid[0].size - 1 else if(state[1] >= useAbleGrid[0].size) 0 else state[1]

        }
        add(wayCount)
    }
}



fun main(){
    val test = Fir()
    test.solution(arrayOf("SL","LR"))
}