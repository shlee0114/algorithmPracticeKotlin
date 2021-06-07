package DataStructure.Queue

import DataStructure.CustomIterator
import DataStructure.Interface.DataStructureDefaultImplements
import DataStructure.LinkedList.LinkedList
import DataStructure.Node.CustomNode
import FunctionModules.DataStructure.DataStructureType
import FunctionModules.DataStructure.Node.NodeFunction

open class CustomQueue : NodeFunction() {
    fun offer(data : Any?){
        offerData(data)
    }

    fun poll() : Any?{
        cnt--
        val nowNodeData = peek()
        remove()
        deallocateNode()
        return nowNodeData
    }

    override fun peek() = first
    fun remove() {
        firstNodeSetPullBackward()
    }

    override fun iterator(): Iterator<Any?> = CustomIterator(firstNode)

}


