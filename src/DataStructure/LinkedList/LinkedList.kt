package DataStructure.LinkedList

import DataStructure.CustomIterator
import DataStructure.DataStructureDivision
import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.Node.CustomNode

class LinkedList(private val dataStructureType : DataStructureDivision = DataStructureDivision.LinkedList) : DataStructureDefaultImplements {
    override var firstNode: CustomNode? = null
    override var nowNode: CustomNode? = null
    override var lastNode: CustomNode? = null
    override var cnt: Int = 0

    override fun pop(): Any? {
        cnt--
        val nowNodeData = nowNode?.nodeValue
        deleteFirstNodeAndSetSecondNode()
        clearNodesWhenNowNodeIsNull()
        return nowNodeData
    }

    private fun deleteFirstNodeAndSetSecondNode(){
        nowNode = when(dataStructureType){
            DataStructureDivision.Queue -> nowNode?.nextNode
            DataStructureDivision.Stack -> nowNode?.prevNode
            else -> nowNode?.prevNode
        }
        clearFirstNodeRecord()
    }

    private fun clearFirstNodeRecord(){
        nowNode?.let{
            when(dataStructureType){
                DataStructureDivision.Queue -> it.prevNode = null
                DataStructureDivision.Stack -> it.nextNode = null
                else -> it.nextNode = null
            }
        }
    }

    private fun clearNodesWhenNowNodeIsNull(){
        if(nowNode == null){
            firstNode = null
            lastNode = null
        }
    }

    override fun push(data: Any?) {
        cnt++
        CustomNode(data,nextNode = nowNode ,prevNode = nowNode).also { node ->
            setNodeInFirstNode(node)
            lastNode?.nextNode = node
            lastNode = node
            setNodeInNowNode()
        }
    }

    private fun setNodeInFirstNode(node : CustomNode){
        if(firstNode == null){
            firstNode = node
        }else if(firstNode?.nextNode == null){
            firstNode?.nextNode = node
        }
    }

    private fun setNodeInNowNode(){
        nowNode = when(dataStructureType){
            DataStructureDivision.Queue -> firstNode
            DataStructureDivision.Stack -> lastNode
            else -> lastNode
        }
    }


    override fun peek(): Any? {
        return nowNode?.nodeValue
    }

    override fun get(index: Int): CustomNode? {
        return firstNode
    }

    override fun set(index: Int, value: Int) {

    }

    override fun iterator(): Iterator<CustomNode> = CustomIterator(firstNode!!, lastNode!!)


}