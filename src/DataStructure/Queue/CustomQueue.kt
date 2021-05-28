package DataStructure.Queue

import DataStructure.LinkedList.SinglyLinkedList

object CustomQueue {
    //첫 노드와 마지막 노드만 별도로 저장해서 데이터 추가 및 pop을 함에 빠른 속도로 하기 위하여 첫 노드와 마지막 노드만 저장
    private var node : SinglyLinkedList? = null
    private var lastNode : SinglyLinkedList? = null
    private var cnt = 0 //node의 수

    //시작 노드를 반환과 동시에 삭제하며 그 다음 노드를 첫 노드로 저장
    fun pop() : Any?{
        val tmp = node?.value
        if(node?.nextNode == null){
            cnt--
            node = null
            return tmp
        }
        node = node?.nextNode
        cnt--
        return tmp
    }

    //노드를 생성 후 저장한 마지막 노드(lastNode)의 다음 노드를 생성한 노드로 지정 후 lastNode를 생성한 노드로 변경
    //만약 첫 생성일 시 처음 노드(node)에도 저장
    fun push(data : Int){
        SinglyLinkedList(null, -1).apply {
            value = data
        }.also {
            if(node == null){
                node = it
            }else{
                if(node?.nextNode == null){
                    node?.nextNode = it
                }
            }
            if(lastNode != null) {
                lastNode?.nextNode = it
            }
            lastNode = it
        }

        cnt++
    }

    //queue의 처음 값을 삭제 없이 반환
    fun peek() = node?.value

    //대괄호 작업 대괄호의 있는 수만큼 노드를 이동시킨 후 해당 노드 값을 반환해준다.
    operator fun get(index: Int): SinglyLinkedList?{
        if(index > cnt)
            return null
        var nowNode = node
        for(i in 0 until index){
            nowNode = nowNode?.nextNode
        }
        return nowNode
    }

    //대괄호 작업 대괄호의 있는 수만큼 노드를 이동시킨 후 해당 노드의 value값을 변경한다
    operator fun set(index: Int, value : Int){
        var nowNode = node
        for(i in 0 until index){
            nowNode = nowNode?.nextNode
        }
        nowNode?.value = value
    }

    //저장되어있는 노드들을 수열로 만든 후 반환
    operator fun iterator() : Iterator<SinglyLinkedList> = QueueIterator(node!!, lastNode!!)

    //비었는 지 안 비었는 지 체크
    fun isEmpty() = node == null
    fun isNotEmpty() = node != null

    //노드의 총 수
    fun count() = cnt

    //inner class로 첫 노드와 마지막 노드로 다음 노드가 없을 떄 까지 next를 하여서 수열을 생성한다.
    private class QueueIterator(val start : SinglyLinkedList, val end : SinglyLinkedList) : Iterator<SinglyLinkedList>{
        var initVal : SinglyLinkedList? = null
        override fun hasNext(): Boolean = initVal != end

        override fun next(): SinglyLinkedList {
            initVal = if(initVal == null){
                start
            }else{
                initVal?.nextNode
            }
            return initVal!!
        }
    }
}


