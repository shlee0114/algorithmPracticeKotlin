package DataStructure.Queue

import DataStructure.CustomIterator
import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.LinkedList.LinkedList
import DataStructure.Node.CustomNode
import FunctionModules.DataStructure.DataStructureType
import FunctionModules.DataStructure.Node.NodeFunction

class CustomQueue: NodeFunction() {
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨
    override var firstNode: CustomNode? = null //첫 노드
    override var lastNode: CustomNode? = null //마지막 노드
    override var cnt: Int = 0

    fun offer(data : Any?){
        linkedAndStackOffer(data)
    }

    fun poll() : Any?{
        cnt--
        val nowNodeData = peek()
        remove()
        deallocateNode()
        return nowNodeData
    }

    override fun peek() : Any? {
        return firstNode?.nodeValue
    }


    fun remove() {
        firstNodeSetPullBackward()
    }

    override fun get(index: Int) = linkedAndStackGet(index)

    override fun set(index: Int, data: Any) {
            linkedAndStackSet(index, data)
    }

    override fun iterator(): Iterator<Any?> = CustomIterator(firstNode, lastNode, DataStructureType.LinkedList)

}


