package DataStructure.LinkedList

import DataStructure.CustomIterator
import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.Node.CustomNode

//연결리스트 구현
open class LinkedList : DataStructureDefaultImplements {
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨
    override var firstNode: CustomNode? = null //첫 노드
    override var lastNode: CustomNode? = null //마지막 노드
    override var cnt: Int = 0

    override fun poll(): Any? {
        cnt--
        val nowNodeData = lastNode?.nodeValue
        lastNodeSetPullForward()
        deallocateNode()
        return nowNodeData
    }

    //마지막 노드를 제거하고 마지막 앞에 있는 노드를 마지막 노드로 교체
    private fun lastNodeSetPullForward(){
        lastNode = lastNode?.prevNode
        lastNode?.let {
            it.nextNode = null
        }
    }

    //총 개수가 0개이면 전부 값 할당 해제
    protected fun deallocateNode(){
        if(cnt == 0){
            firstNode = null
            lastNode = null
        }
    }

    override fun offer(data: Any?) {
        cnt++
        CustomNode(data,nextNode = null ,prevNode = lastNode).also { node ->
            lastNode?.nextNode = node
            lastNode = node
            if(firstNode == null)
                firstNode = node
        }
    }

    //pop과 동일하지만 데이터 삭제는 하지 않음
    override fun peek(): Any? {
        return lastNode?.nodeValue
    }

    override fun remove(index: Int) {
        when{
            index >= cnt -> return
            index +1 == cnt -> poll()
            else ->
                firstNode.getNodeUntilReachNextIndex(index)?.apply {
                    nextNode?.prevNode = prevNode
                    if(prevNode == null){
                        firstNode = nextNode
                    }else{
                        prevNode?.nextNode = nextNode
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

    //재귀사용 index가 0이 될때까지 nextNode를 해주며 0이 되면 해당 nextNode를 반환한다
    private fun CustomNode?.getNodeUntilReachNextIndex(index : Int) : CustomNode?{
        return if(index == 0){
            this
        } else{
            this?.nextNode?.getNodeUntilReachNextIndex(index-1)
        }
    }

    override fun iterator(): Iterator<Any?> = CustomIterator(firstNode, lastNode)
}