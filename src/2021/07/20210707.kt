class Fir_20210707{

    //오픈채팅방
    //맵을 사용하면 되는 간단한 문제
    //ID를 키 값으로 nickname을 저장 혹은 수정하고
    //사용자가 들어왔는 지 나갔는 지 하고 해당 시점의 ID를 저장했다가
    //마지막에 ID에 매칭되어있는 nickname과 출입 여부를 같이출력하는 문제
    private val ACTION = 0
    private val PLAYER_ID = 1
    private val PLAYER_NAME = 2

    fun solution(record: Array<String>): Array<String> {
        val answer = arrayListOf<String>()
        val nickname = mutableMapOf<String, String>()
        val visitLog = arrayListOf<String>()
        val visitLogNickname = arrayListOf<String>()
        for(i in record){
            val recordDivision = i.split(" ")
            val action = checkInOrOut(recordDivision[ACTION])?.also{
                visitLog.add(it)
                visitLogNickname.add(recordDivision[PLAYER_ID])
            }
            if(action != "나갔습니다."){
                nickname[recordDivision[PLAYER_ID]] = recordDivision[PLAYER_NAME]
            }
        }

        for(i in visitLog.indices){
            answer.add("${nickname[visitLogNickname[i]]}님이 ${visitLog[i]}")
        }

        return answer.toTypedArray()
    }

    private fun checkInOrOut(text : String) : String?{
        return when (text) {
            "Enter" -> {
                "들어왔습니다."
            }
            "Leave" -> {
                "나갔습니다."
            }
            else -> {
                null
            }
        }
    }
}

fun main(){
    val test = Fir_20210707()
    test.solution(arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"))
}