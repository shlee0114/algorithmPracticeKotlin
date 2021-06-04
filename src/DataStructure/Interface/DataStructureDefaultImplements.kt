package DataStructure.Interface

import DataStructure.CustomIterator
import DataStructure.Node.CustomNode

interface DataStructureDefaultImplements {

    var firstNode : CustomNode?
    var lastNode : CustomNode?
    var cnt : Int

    fun poll() : Any?
    fun offer(data : Any?)
    fun peek() : Any?

    fun count() = cnt

    fun isEmpty() = firstNode == null
    fun isNotEmpty() = firstNode != null


    //대괄호 작업 대괄호의 있는 수만큼 노드를 이동시킨 후 해당 노드 값을 반환해준다.
    operator fun get(index: Int): Any?

    //대괄호 작업 대괄호의 있는 수만큼 노드를 이동시킨 후 해당 노드의 value값을 변경한다
    operator fun set(index: Int, value : Any)

    //저장되어있는 노드들을 수열로 만든 후 반환
    operator fun iterator() : Iterator<CustomNode>


}