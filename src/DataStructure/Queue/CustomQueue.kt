package DataStructure.Queue

import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.LinkedList.LinkedList
import DataStructure.Node.CustomNode

class CustomQueue: DataStructureDefaultImplements {
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨
    override var firstNode: CustomNode? = null //첫 노드
    override var lastNode: CustomNode? = null //마지막 노드
    override var cnt: Int = 0

    fun offer(data : Any?){

    }

    fun poll() : Any?{
        return lastNode
    }

    override fun peek() : Any? {
        return lastNode
    }


    override fun remove(index: Int) {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): Any? {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, value: Any) {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<Any?> {
        TODO("Not yet implemented")
    }

}


