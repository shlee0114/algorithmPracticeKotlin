package DataStructure.LinkedList


//이중 연결 리스트
class DoublyLinkedList(var nextNode : DoublyLinkedList? = null, var prevNode : DoublyLinkedList? = null, var value : Int = -1) {

    operator fun plus(i: Int): Int {
        return value + i
    }

    operator fun plusAssign(i : Int) {
        value += i
    }

    operator fun compareTo(i: Int) : Int =
        when{
            value > i -> 1
            value < i -> -1
            else -> 0
        }

}