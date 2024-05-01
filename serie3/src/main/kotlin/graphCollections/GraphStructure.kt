package graphCollections

class GraphStructure<I, D> : Graph<I, D> {
    private val vertices: MutableMap<I, Graph.Vertex<I, D>> = mutableMapOf()

    override val size: Int
        get() = vertices.size

    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? {
        val vertex = vertices[id] ?: return null
        val isEdge = vertex.getAdjacencies().contains(Edge(id, idAdj))
        return if (isEdge) Edge(id,idAdj) else null
    }

    override fun getVertex(id: I): Graph.Vertex<I, D>? {
        return vertices[id]
    }

    override fun addEdge(id: I, idAdj: I): I? {
        val sourceVertex = vertices[id] ?: return null
        val edge = Edge(id, idAdj)
        sourceVertex.getAdjacencies().add(edge)
        return idAdj
    }

    override fun addVertex(id: I, d: D): D? {
        val existingVertex = vertices[id]
        if (existingVertex != null) {
            return null
        }
        val newVertex = Vertex(id, d)
        vertices[id] = newVertex
        return newVertex.data
    }

    override fun iterator(): Iterator<Graph.Vertex<I, D>> = vertices.values.iterator()


    data class Vertex<I,D>(override var id: I, override var data: D, override var clr : COLOR = COLOR.WHITE ) : Graph.Vertex<I, D> {
        private val adjacencies: MutableSet<Graph.Edge<I>> = mutableSetOf()
        override fun setData(newData: D): D {
            val oldData = data
            data = newData
            return oldData
        }

        override fun getAdjacencies(): MutableSet<Graph.Edge<I>> {
            return adjacencies
        }
    }

     data class Edge<I>(override var id : I, override var adjacent : I) : Graph.Edge<I>

}

enum class COLOR {WHITE, GRAY, BLACK}