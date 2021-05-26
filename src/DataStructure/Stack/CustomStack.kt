package DataStructure.Stack

import DataStructure.LinkedList.DoublyLinkedList

object CustomStack {
    //첫 노드와 마지막 노드만 별도로 저장해서 데이터 추가 및 pop을 함에 빠른 속도로 하기 위하여 첫 노드와 마지막 노드만 저장
    private var firstNode : DoublyLinkedList? = null
    private var node : DoublyLinkedList? = null
    private var cnt = 0

    //stack 처음 값을 반환 후 삭제
    fun pop() : Any?{
        val value = node?.value
        node = node?.nextNode
        node?.prevNode = null
        if(node == null)
            firstNode = null
        return value
    }

    //처음 데이터는 firstNode와 node에 저장 그 후 데이터가 들어올 떄마다 node에 저장하면서
    //nextNode는 현재 값 기준 앞의 값 prevNode는 현재 값 기준 뒤의 값
    fun push(data : Int){
        val tmpNode = DoublyLinkedList()
        tmpNode.value = data
        tmpNode.nextNode = node
        if(firstNode == null){
            firstNode = tmpNode
        }else{
            if(firstNode?.prevNode == null){
                tmpNode.also { firstNode?.prevNode = it }
            }
        }
        if(node != null) {
            tmpNode.also { node?.prevNode = it }
        }
        node = tmpNode
        cnt++
    }

    //stack 처음 값을 삭제 없이 반환
    fun peek() = node?.value

    //비었는 지 안 비었는 지 체크
    fun isEmpty() = node == null
    fun isNotEmpty() = node != null

    //노드의 총 수
    fun count() = cnt

    //대괄호 작업 대괄호의 있는 수만큼 노드를 이동시킨 후 해당 노드 값을 반환해준다.
    operator fun get(index: Int): DoublyLinkedList?{
        if(index > cnt)
            return null
        var nowNode = firstNode
        for(i in 0 until index){
            nowNode = nowNode?.prevNode
        }
        return nowNode
    }

    //대괄호 작업 대괄호의 있는 수만큼 노드를 이동시킨 후 해당 노드의 value값을 변경한다
    operator fun set(index: Int, value : Int){
        var nowNode = firstNode
        for(i in 0 until index){
            nowNode = nowNode?.prevNode
        }
        nowNode?.value = value
    }

    //저장되어있는 노드들을 수열로 만든 후 반환
    operator fun iterator() : Iterator<DoublyLinkedList> = StackIterator(firstNode!!, node!!)

    //inner class로 첫 노드와 마지막 노드로 다음 노드가 없을 떄 까지 next를 하여서 수열을 생성한다.
    private class StackIterator(val start : DoublyLinkedList, val end : DoublyLinkedList) : Iterator<DoublyLinkedList>{
        var initVal : DoublyLinkedList? = null
        override fun hasNext(): Boolean = initVal != end

        override fun next(): DoublyLinkedList {
            if(initVal == null){
                initVal = start
            }else{
                initVal = initVal?.prevNode
            }
            return initVal!!
        }
    }
}