package DataStructure.LinkedList

import DataStructure.CustomIterator
import DataStructure.Node.CustomNode
import DataStructure.Queue.CustomQueue
import FunctionModules.DataStructure.DataStructureType
import FunctionModules.DataStructure.Node.NodeFunction
//연결리스트 구현
class LinkedList : CustomQueue(){

    fun push(data : Any?){
        offerData(data)
    }

    fun pop() = poll()

    fun add(data : Any?){
        offerData(data)
    }

}