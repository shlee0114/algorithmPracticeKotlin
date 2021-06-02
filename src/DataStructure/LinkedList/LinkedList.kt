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
        setNextNodeAndDeletePrevNode()
        clearFirstNodeWhenNowNodeIsNull()
        return nowNode?.nodeValue
    }

    private fun setNextNodeAndDeletePrevNode(){
        nowNode = nowNode?.nextNode
        nowNode?.let{
            it.prevNode = null
        }
        cnt--
    }

    private fun clearFirstNodeWhenNowNodeIsNull(){
        if(nowNode == null)
            firstNode = null
    }

    override fun push(node: Any?) {
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