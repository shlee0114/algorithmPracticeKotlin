package DataStructure

import DataStructure.Node.CustomNode

class CustomIterator(private val start : CustomNode?, private val end : CustomNode?) : Iterator<Any?>{
    private var initVal : CustomNode? = null
    override fun hasNext(): Boolean {
        if(start == null){
            return false
        }
        return initVal != end
    }

    override fun next(): Any? {
        initVal = if(initVal == null){
            start
        }else{
            initVal?.nextNode
        }
        return initVal?.nodeValue
    }
}