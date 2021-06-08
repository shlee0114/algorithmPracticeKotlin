package FunctionModules.DataStructure.Heap

import kotlin.math.floor

class CustomHeap(val sortType : HeapType = HeapType.ASC) {

    var data = arrayListOf<Int>()

    fun addAll(insertDataArray : ArrayList<Int>){
        for(i in insertDataArray){
            offer(i)
        }
    }

    fun offer(insertData : Int){
        data.add(insertData)
        sort()
    }

    fun poll() : Int {
        val tmpData = peek()
        remove()
        return tmpData
    }

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
        sort()
    }

    fun peek() = data[0]

    private fun sort(){
        var nowIndex = data.size - 1
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

    private fun getParentNode(index: Int) = floor(index / 2f).toInt()

    private fun compareData(index: Int, index2 : Int) : Int{
        return if(data[index] < data[index2]){
            swapData(index, index2)
            index
        }else{
            0
        }
    }

    private fun compareDataRemoveVer(index: Int) : Int {
        return if(sortType == HeapType.ASC){
            when {
                index * 2 + 1 < data.size && data[index] > data[index * 2 + 1] -> {
                    swapData(index, index * 2 + 1)
                    index * 2 + 1
                }
                index * 2 + 2 < data.size && data[index] > data[index * 2 + 2] -> {
                    swapData(index, index * 2 + 2)
                    index * 2 + 2
                }
                else -> data.size
            }
        }else{
            when {
                index * 2 + 1 < data.size && data[index] < data[index * 2 + 1] -> {
                    swapData(index, index * 2 + 1)
                    index * 2 + 1
                }
                index * 2 + 2 < data.size && data[index] < data[index * 2 + 2] -> {
                    swapData(index, index * 2 + 2)
                    index * 2 + 2
                }
                else -> data.size
            }
        }

    }

    private fun swapData(index: Int, index2 : Int){
        val tmp = data[index]
        data[index] = data[index2]
        data[index] = tmp
    }

    operator fun get(index : Int) = data[index]

    operator fun set(index : Int, insertData : Int){
        data[index] = insertData
    }

    operator fun iterator() = data.iterator()
}

