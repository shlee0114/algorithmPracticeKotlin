package FunctionModules.DataStructure.Node

import DataStructure.CustomIterator
import DataStructure.Node.CustomNode

abstract class NodeFunction {
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨
    var firstNode: CustomNode? = null //첫 노드
    var lastNode: CustomNode? = null //마지막 노드
    var cnt: Int = 0
    var first: Any? = null //첫 노드의 값
    var last: Any? = null  //마지막 노드의 값

    protected fun offerData(data : Any?){
        cnt++
        CustomNode(data,nextNode = null ,prevNode = lastNode).also { node ->
            node.giveLeftToRightNextNode(lastNode)
            insertLastNode(node)
            insertNodeInFirstNodeWhenIsNull(node)
        }
    }

    private fun insertLastNode(node: CustomNode?){
        lastNode = node
        last = node?.nodeValue
    }

    private fun insertFirstNode(node: CustomNode?){
        firstNode = node
        first = node?.nodeValue
    }

    //마지막 노드를 제거하고 마지막 앞에 있는 노드를 마지막 노드로 교체
    protected fun lastNodeSetPullForward(){
        lastNode = lastNode?.prevNode
        lastNode?.let {
            last = it.nodeValue
            it.nextNode = null
        }
        deallocateNode()
    }

    protected fun firstNodeSetPullBackward(){
        firstNode = firstNode?.nextNode
        firstNode?.let {
            first = it.nodeValue
            it.prevNode = null
        }
        deallocateNode()
    }

    private fun insertNodeInFirstNodeWhenIsNull(node : CustomNode?){
        if(firstNode == null){
            insertFirstNode(node)
        }
    }

    //총 개수가 0개이면 전부 값 할당 해제
    private fun deallocateNode(){
        if(cnt == 0){
            insertFirstNode(null)
            insertLastNode(null)
        }
    }

    private fun CustomNode?.giveLeftToRightNextNode(node : CustomNode?){
        node?.nextNode = this
    }

    //재귀사용 index가 0이 될때까지 nextNode를 해주며 0이 되면 해당 nextNode를 반환한다
    private fun CustomNode?.getNodeUntilReachNextIndex(index : Int) : CustomNode?{
        return if(index == 0){
            this
        } else{
            this?.nextNode?.getNodeUntilReachNextIndex(index-1)
        }
    }

    fun isEmpty() = firstNode == null

    fun isNotEmpty() = firstNode != null

    abstract fun peek() : Any?

    operator fun iterator(): Iterator<Any?> = CustomIterator(firstNode)

    operator fun get(index: Int) : Any? {
        if(index >= cnt)
            return null
        return firstNode.getNodeUntilReachNextIndex(index)?.nodeValue
    }

    operator fun set(index: Int, data: Any) {
        if(index < cnt){
            firstNode.getNodeUntilReachNextIndex(index)?.nodeValue = data
        }else if(index == cnt){
            offerData(data)
        }
    }
}