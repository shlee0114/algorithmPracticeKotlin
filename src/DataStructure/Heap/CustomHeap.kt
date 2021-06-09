package FunctionModules.DataStructure.Heap

import kotlin.math.floor

//커스텀 힙
//sortType : 최소값 힙인지 최대값 힙인지 결정 기본값 최소값
class CustomHeap(private val sortType : HeapType = HeapType.ASC) {

    private val data = arrayListOf<Int>()

    /*
        배열을 힙에 넣는 함수
        insertDataArray : 데이터를 복사할 배열
    */
    fun addAll(insertDataArray : ArrayList<Int>){
        for(i in insertDataArray){
            offer(i)
        }
    }

    /*
        데이터를 저장하는 함수
        저장 후 정렬을 해준다
        insertData : 입력하는 데이터
    */
    fun offer(insertData : Int){
        data.add(insertData)
        sort()
    }

    /*
        최소 혹은 최대 값 반한 후 해당 값을 삭제하는 함수
        return : 삭제되는 최대 혹은 최소 값
     */
    fun poll() : Int {
        val tmpData = peek()
        remove()
        return tmpData
    }

    /*
        최소 값 혹은 최대 값을 삭제하는 함수
    */
    fun remove(){
        data[0] = data[data.size - 1]
        data.removeAt(data.size - 1)

        var nowIndex = 0
        while(true){
            nowIndex = if(sortType == HeapType.ASC){
                compareDataRemoveVer(nowIndex)
            }else{
                compareDataRemoveVer(nowIndex)
            }
            if(nowIndex >= data.size - 1)
                return
        }
    }

    /*
        최소 값 혹은 최대 값을 가져오는 함수
    */
    fun peek() = data[0]

    /*
        데이터 입력 후 입력된 데이터의 크기에 맞게 정렬해주는 함수
    */
    private fun sort(){
        var nowIndex = data.size - 1
        if(nowIndex == 0)
            return
        while(true){
            val parent = getParentNode(nowIndex)
            nowIndex = if(sortType == HeapType.ASC){
                compareData(parent, nowIndex)
            }else{
                compareData(nowIndex, parent)
            }
            if(nowIndex == 0)
                return
        }
    }

    /*
        현재 노드의 상위노드드 indx를 반환
        index : 현재 노드 index
        return : 상위 노드 index
     */
    private fun getParentNode(index: Int) = floor((index - 1) / 2f).toInt()

    /*
        데이터를 비교 후 교체한 다음 좌측 값을 반환
        index : 비교할 좌측 값
        index : 비교할 우측 값
        return : 좌측index의 데이터가 더 클 시 좌측 index 아닐 시 0
    */
    private fun compareData(index: Int, index2 : Int) : Int{
        return if(data[index] > data[index2]){
            swapData(index, index2)
            index
        }else{
            0
        }
    }

    /*
        삭제 시 상위 노드부터 시작해서 하위노드로 가면서 데이터를 정렬하는 함수
        index : 현재 노드
        return : 하위 노드에 값이 현 값보다 더 크거나 작은 값이 있을 시 해당 하위 노드 없을 시 배열의 사이즈를 반환
    */
    private fun compareDataRemoveVer(index: Int) : Int {
        val firstDataIndex = index * 2 + 1
        val secondDataIndex = index * 2 + 2
        if(firstDataIndex < data.size){
            val firstInterval = data[index] - data[firstDataIndex] //우측 값이 더 클 시 음수가 되는 원리를 이용
            if(secondDataIndex < data.size){
                val secondInterval = data[index] - data[secondDataIndex]
                if(dataVerification(secondInterval)){
                    return if(dataVerification(firstInterval)){
                        when (sortType) {
                            HeapType.ASC -> {
                                if(secondInterval > firstInterval){
                                    swapAndReturn(index, secondDataIndex)
                                }else{
                                    swapAndReturn(index, firstDataIndex)
                                }
                            }
                            HeapType.DESC -> {
                                if(secondInterval < firstInterval){
                                    swapAndReturn(index, secondDataIndex)
                                }else{
                                    swapAndReturn(index, firstDataIndex)
                                }
                            }
                        }
                    }else{
                        swapAndReturn(index, secondDataIndex)
                    }
                }
            }

            if(dataVerification(firstInterval)) { //만약 index * 2 + 2가 배열 사이즈 보다 클 시 index * 2 + 1만 비교해서 리턴
                return swapAndReturn(index, firstDataIndex)
            }
        }
        return data.size
    }

    /*
        비교할 데이터가 더 크거나 작은 지 확인하는 함수
        data : 비교할 두 값을 사전에 연산한 데이터
        return : 우측의 데이터가 더 크거나 작을 시 true 아닐 시 false
     */
    private fun dataVerification(data : Int) : Boolean{
        return when (sortType) {
            HeapType.ASC -> data > 0
            HeapType.DESC -> data < 0
        }
    }

    /*
        데이터 스왑 후 index2를반환하는 함수
        스왑 후 같은 데이터를 리턴하는 경우가 많아서 중복을 줄이고자 생성
     */
    private fun swapAndReturn(index : Int, index2 : Int) : Int{
        swapData(index, index2)
        return index2
    }

    //데이터를 교체하는 함수
    private fun swapData(index: Int, index2 : Int){
        val tmp = data[index]
        data[index] = data[index2]
        data[index2] = tmp
    }

    operator fun get(index : Int) = data[index]

    operator fun iterator() = data.iterator()
}

