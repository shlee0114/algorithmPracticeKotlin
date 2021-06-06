package FunctionModules.DataStructure.Node

import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.Node.CustomNode

abstract class NodeFunction : DataStructureDefaultImplements {
    //firstNode는 데이터 검색 lastNode는 데이터 입력에 주로 사용됨
    override var firstNode: CustomNode? = null //첫 노드
    override var lastNode: CustomNode? = null //마지막 노드
    override var cnt: Int = 0

    //마지막 노드를 제거하고 마지막 앞에 있는 노드를 마지막 노드로 교체
    fun lastNodeSetPullForward(){
        lastNode = lastNode?.prevNode
        lastNode?.let {
            it.nextNode = null
        }
    }

    fun insertNodeWhenFirstNodeIsNull(node : CustomNode?){
        if(firstNode == null)
            firstNode = node
    }

    //총 개수가 0개이면 전부 값 할당 해제
    fun deallocateNode(){
        if(cnt == 0){
            firstNode = null
            lastNode = null
        }
    }

    fun CustomNode?.giveLeftToRightNextNode(node : CustomNode?){
        node?.nextNode = this
    }

    fun CustomNode?.giveLeftToRightPrevNode(node : CustomNode?){
        node?.prevNode = this
    }

    //재귀사용 index가 0이 될때까지 nextNode를 해주며 0이 되면 해당 nextNode를 반환한다
    fun CustomNode?.getNodeUntilReachNextIndex(index : Int) : CustomNode?{
        return if(index == 0){
            this
        } else{
            this?.nextNode?.getNodeUntilReachNextIndex(index-1)
        }
    }

    //재귀사용 index가 0이 될때까지 prevNode를 해주며 0이 되면 해당 prevNode를 반환한다
    fun CustomNode?.getNodeUntilReachPrevIndex(index : Int) : CustomNode?{
        return if(index == 0){
            this
        } else{
            this?.prevNode?.getNodeUntilReachPrevIndex(index-1)
        }
    }

}