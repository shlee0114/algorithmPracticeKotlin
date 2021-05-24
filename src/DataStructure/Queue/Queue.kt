package DataStructure.Queue

import DataStructure.LinkedList.SinglyLinkedList

object Queue {
    private var node : SinglyLinkedList? = null
    private var lastNode : SinglyLinkedList? = null
    private var cnt = 0
    private var nowIndex = 0

    fun pop() : Any?{
        val tmp = node?.value
        node = (node?.nextNode ?: null) as SinglyLinkedList
        cnt--
        return tmp ?: null
    }

    fun push(data : Any){
        val tmpNode = SinglyLinkedList
        tmpNode.value = data
        if(node == null)
            node = tmpNode
        if(lastNode != null) {
            tmpNode.also { lastNode?.nextNode = it }
        }
        lastNode = tmpNode
        cnt++
    }

    operator fun get(index: Int): Any?{
        if(index > cnt)
            return null
        nowIndex = index
        var returnValue = node?.value
        var nowNode = node
        for(i in 0 until index){
            nowNode = nowNode?.nextNode
            returnValue = nowNode?.value
        }
        return returnValue
    }

    operator fun set(index: Int, value : Any){
        var nowNode = node
        for(i in 0 until index){
            nowNode = nowNode?.nextNode
        }
        nowNode?.value = value
    }


    operator fun iterator() : Iterator<SinglyLinkedList>{
        return QueueIterator(node!!, lastNode!!)
    }

    operator fun Any?.plus(data: Any) {
        var nowNode = node
        for(i in 0 until nowIndex){
            nowNode = nowNode?.nextNode
        }
        return nowNode?.value + data
    }

    fun empty() = node == null

    fun count() = cnt
    private class QueueIterator(start : SinglyLinkedList, val end : SinglyLinkedList) : Iterator<SinglyLinkedList>{
        val initVal = start
        override fun hasNext(): Boolean {
            return initVal != end
        }

        override fun next(): SinglyLinkedList {
            return initVal.nextNode!!
        }
    }
}


