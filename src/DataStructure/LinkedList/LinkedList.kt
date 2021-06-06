package DataStructure.LinkedList

import DataStructure.CustomIterator
import DataStructure.Node.CustomNode
import FunctionModules.DataStructure.DataStructureType
import FunctionModules.DataStructure.Node.NodeFunction
//연결리스트 구현
open class LinkedList : NodeFunction(){
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨

    fun poll(): Any? = linkedAndStackPoll()

    fun offer(data: Any?) {
        linkedAndStackOffer(data)
    }

    fun add(data : Any?){
        offer(data)
    }

    //pop과 동일하지만 데이터 삭제는 하지 않음
    override fun peek(): Any? = lastNode?.nodeValue

    fun remove(index: Int) {
        when{
            index >= cnt -> return
            index +1 == cnt -> poll()
            else -> {
                firstNode.getNodeUntilReachNextIndex(index)?.apply {
                    prevNode.giveLeftToRightPrevNode(nextNode)
                    if (prevNode == null) {
                        firstNode = nextNode
                    } else {
                        nextNode.giveLeftToRightNextNode(prevNode)
                    }
                }
                cnt--
            }
        }
        deallocateNode()
    }

    override fun get(index: Int) = linkedAndStackGet(index)

    override fun set(index: Int, data: Any) {
        linkedAndStackSet(index, data)
    }

    override fun iterator(): Iterator<Any?> = CustomIterator(firstNode, lastNode, DataStructureType.LinkedList)
}