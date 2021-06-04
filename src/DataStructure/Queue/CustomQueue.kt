package DataStructure.Queue

import DataStructure.LinkedList.LinkedList

class CustomQueue : LinkedList() {

    //전체적인 부분은 LinkedList와 비슷하나 poll과정에서 큐는 FIFO LinkedList는 LIFO이기에 poll만 교체
    override fun poll(): Any? {
        cnt--
        val nowNodeData = firstNode?.nodeValue
        firstNode = firstNode?.nextNode
        deallocateNode()
        return nowNodeData
    }

}


