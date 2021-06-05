package FunctionModules.FunctionModules

import DataStructure.Node.CustomNode

open class DataStructureRelated {

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