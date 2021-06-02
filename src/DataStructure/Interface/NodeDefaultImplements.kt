package DataStructure.Interface

interface NodeDefaultImplements<NodeT, ValueT> {
    var nodeValue : ValueT
    var nextNode : NodeT
    var prevNode : NodeT
}