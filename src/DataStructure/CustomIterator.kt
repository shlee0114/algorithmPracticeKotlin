package DataStructure

import DataStructure.Node.CustomNode

class CustomIterator(private val start : CustomNode, private val end : CustomNode) : Iterator<CustomNode>{
    private var initVal : CustomNode? = null
    override fun hasNext(): Boolean = initVal != end

    override fun next(): CustomNode {
        initVal = if(initVal == null){
            start
        }else{
            initVal?.nextNode
        }
        return initVal!!
    }
}