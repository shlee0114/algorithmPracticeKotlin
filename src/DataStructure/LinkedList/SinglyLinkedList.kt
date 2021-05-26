package DataStructure.LinkedList


//단일 연결 리스트
 class SinglyLinkedList (var nextNode : SinglyLinkedList? = null, var value : Int = -1) {

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