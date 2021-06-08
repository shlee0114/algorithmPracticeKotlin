package DataStructure.LinkedList

import DataStructure.Queue.CustomQueue
//연결리스트 구현
class LinkedList : CustomQueue(){

    fun push(data : Any?){
        offer(data)
    }

    fun pop() = poll()

    fun add(data : Any?){
        offer(data)
    }

}