package DataStructure.Stack

import FunctionModules.DataStructure.Node.NodeFunction

class CustomStack : NodeFunction(){

    fun push(data : Any?){
        offerData(data)
    }

    fun pop() : Any?{
        val data = peek()
        remove()
        return data
    }

    fun remove(){
        lastNodeSetPullForward()
    }

    override fun peek() = last
}