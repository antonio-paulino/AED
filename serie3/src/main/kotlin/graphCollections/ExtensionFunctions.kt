package graphCollections

operator fun <I,D> MutableSet<Graph.Edge<I>>.contains(vertex: Graph.Vertex<I, D>):Boolean{
      for(edge in this) { if(edge.adjacent == vertex.id) return true }
      return false
}
