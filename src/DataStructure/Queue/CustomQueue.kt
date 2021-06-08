package DataStructure.Queue

import FunctionModules.DataStructure.Node.NodeFunction

open class CustomQueue : NodeFunction() {
    fun offer(data : Any?){
        offerData(data)
    }

    fun poll() : Any?{
        cnt--
        val nowNodeData = peek()
        remove()
        return nowNodeData
    }

    fun remove() {
        firstNodeSetPullBackward()
    }

    override fun peek() = first
}


