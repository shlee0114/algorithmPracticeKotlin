package DataStructure.LinkedList

import DataStructure.CustomIterator
import DataStructure.Node.CustomNode
import FunctionModules.DataStructure.Node.NodeFunction
//연결리스트 구현
open class LinkedList : NodeFunction(){
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨

    fun poll(): Any? {
        cnt--
        val nowNodeData = peek()
        lastNodeSetPullForward()
        deallocateNode()
        return nowNodeData
    }


    fun offer(data: Any?) {
        cnt++
        CustomNode(data,nextNode = null ,prevNode = lastNode).also { node ->
            node.giveLeftToRightNextNode(lastNode)
            lastNode = node
            insertNodeWhenFirstNodeIsNull(node)
        }
    }

    fun add(data : Any?){
        offer(data)
    }

    //pop과 동일하지만 데이터 삭제는 하지 않음
    override fun peek(): Any? = lastNode?.nodeValue

    override fun remove(index: Int) {
        when{
            index >= cnt -> return
            index +1 == cnt -> poll()
            else ->
                firstNode.getNodeUntilReachNextIndex(index)?.apply {
                    prevNode.giveLeftToRightPrevNode(nextNode)
                    if(prevNode == null){
                        firstNode = nextNode
                    }else{
                        nextNode.giveLeftToRightNextNode(prevNode)
                    }
            }
        }
        cnt--
        deallocateNode()
    }

    override fun get(index: Int): Any? {
        if(index >= cnt)
            return null
        return firstNode.getNodeUntilReachNextIndex(index)?.nodeValue
    }

    override fun set(index: Int, value: Any) {
        when {
            index > cnt -> return
            index == cnt -> offer(value)
            else -> firstNode.getNodeUntilReachNextIndex(index)?.nodeValue = value
        }
    }

    override fun iterator(): Iterator<Any?> = CustomIterator(firstNode, lastNode)
}