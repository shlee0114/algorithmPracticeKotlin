package FunctionModules.DataStructure.Node

import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.Node.CustomNode

abstract class NodeFunction : DataStructureDefaultImplements {
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨
    override var firstNode: CustomNode? = null //첫 노드
    override var lastNode: CustomNode? = null //마지막 노드
    override var cnt: Int = 0
    override var first: Any? = null //첫 노드의 값
    override var last: Any? = null  //마지막 노드의 값


    protected fun offerData(data: Any?) {
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
            it.nextNode = null
        }
    }

    protected fun firstNodeSetPullBackward(){
        firstNode = firstNode?.nextNode
        firstNode?.let {
            it.prevNode = null
        }
    }

    private fun insertNodeInFirstNodeWhenIsNull(node : CustomNode?){
        if(firstNode == null){
            insertFirstNode(node)
        }
    }

    protected fun insertNodeInLastNodeWhenIsNull(node : CustomNode?){
        if(lastNode == null){
            insertLastNode(node)
        }
    }

    //총 개수가 0개이면 전부 값 할당 해제
    protected fun deallocateNode(){
        if(cnt == 0){
            insertFirstNode(null)
            insertLastNode(null)
        }
    }

    protected fun CustomNode?.giveLeftToRightNextNode(node : CustomNode?){
        node?.nextNode = this
    }

    protected fun CustomNode?.giveLeftToRightPrevNode(node : CustomNode?){
        node?.prevNode = this
    }

    //재귀사용 index가 0이 될때까지 nextNode를 해주며 0이 되면 해당 nextNode를 반환한다
    protected fun CustomNode?.getNodeUntilReachNextIndex(index : Int) : CustomNode?{
        return if(index == 0){
            this
        } else{
            this?.nextNode?.getNodeUntilReachNextIndex(index-1)
        }
    }

    //재귀사용 index가 0이 될때까지 prevNode를 해주며 0이 되면 해당 prevNode를 반환한다
    protected fun CustomNode?.getNodeUntilReachPrevIndex(index : Int) : CustomNode?{
        return if(index == 0){
            this
        } else{
            this?.prevNode?.getNodeUntilReachPrevIndex(index-1)
        }
    }

    override fun get(index: Int) : Any? {
        if(index >= cnt)
            return null
        return firstNode.getNodeUntilReachNextIndex(index)?.nodeValue
    }

    override fun set(index: Int, data: Any) {
        if(index < cnt){
            firstNode.getNodeUntilReachNextIndex(index)?.nodeValue = data
        }else if(index == cnt){
            offerData(data)
        }
    }
}