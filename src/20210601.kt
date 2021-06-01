import java.util.*

class Fir_20210601 {

    //트럭이 지나가는 시간을 저장한 배열 위치
    val TRUCK_PASSING_TIME = 0
    //트럭의 무게를 저장한 배열의 위치
    val TRUCK_WEIGHT = 1

    //현재 지나가야하는 트럭의 번호
    var nowTruckNum = 0
    //다리위에 올라가있는 트럭의 총 무게
    var weightOfTrucksOnBridge = 0

    //프로그래머스 문제 : 다리를 지나는 트럭
    //큐를 활용하는 문제로 큐 안에 현재 들어간 시점 + 다리의 길이를 더해서 트럭이 완전히 지나간 시점을 구했으며.
    //weightOfTrucksOnBridge에 다리 위에 올라가 있는 트럭들의 무게들의 합과 nowTruckNum에 해당하는 트럭의 무게를 더해서 weight값과 비교해서 데이터를 넣을 지를 정했으며,
    //큐 가장 앞의 트럭의 TRUCK_PASSING_TIME이 현재 시점인 elapsedTime보다 낮거나 같으면 큐를 제거하면서 weightOfTrucksOnBridge에도 TRUCK_WEIGHT값을 빼주는 방식으로 해결
    //TRUCK_PASSING_TIME은 elapsedTime과 bridge_length의 합이 저장된다.
    //bridge_length : 다리의 길이
    //weight : 다리가 버틸 수 있는 무게
    //truck_weights : 트럭들의 무게
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val truckOnBridgeQueue : Queue<IntArray> = LinkedList()
        truck_weights[0]
        var elapsedTime = 1

        //데이터 입력없이 들어가면 truckOnBridgeQueue에는 값이 없기에 반복문이 돌지 않는다.
        addTruckOnBridge(truckOnBridgeQueue, intArrayOf(bridge_length + elapsedTime, truck_weights[nowTruckNum]))

        while (truckOnBridgeQueue.isNotEmpty()){
            elapsedTime++

            //만약 elapsedTime이 가장 앞에 있는 트럭의 TRUCK_PASSING_TIME보다 크거나 같다면 가장 앞에 있는 트럭을 삭제하면서 weightOfTrucksOnBridge를 TRUCK_WEIGHT만큼 빼준다.
            if(truckOnBridgeQueue.peek()[TRUCK_PASSING_TIME] <= elapsedTime){
                weightOfTrucksOnBridge -= truckOnBridgeQueue.poll()[TRUCK_WEIGHT]
            }

            //nowTruckNum이 truck_weights의 사이즈보다 커질 경우 나오는 stackOverflow에러 방지
            if(nowTruckNum < truck_weights.size){
                if(weightOfTrucksOnBridge + truck_weights[nowTruckNum] <= weight){
                    addTruckOnBridge(truckOnBridgeQueue, intArrayOf(bridge_length + elapsedTime, truck_weights[nowTruckNum]))
                }
            }

        }

        return elapsedTime
    }

    //다리에 트럭을 추가하는 메소드
    //truckOnBridgeQueue : 다리 위에 올라가 있는 트럭들이 저장된 큐
    //truckOnBridgeInfo : 새로 입력될 트럭에 대한 정보
    private fun addTruckOnBridge(truckOnBridgeQueue : Queue<IntArray>, truckOnBridgeInfo : IntArray){
        truckOnBridgeQueue.add(truckOnBridgeInfo)
        weightOfTrucksOnBridge += truckOnBridgeInfo[TRUCK_WEIGHT]
        nowTruckNum++
    }
}

fun main(){
    val fir = Fir_20210601()
    print(fir.solution(2, 10, intArrayOf(7,4,5,6)))

}