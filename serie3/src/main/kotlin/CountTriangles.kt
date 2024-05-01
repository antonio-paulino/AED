import java.io.File
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import graphCollections.COLOR
import graphCollections.Graph
import graphCollections.GraphStructure
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {
        val inputFile = args[0]
        val outputFile = args[1]

        val isDirected = false

        val graph = createGraphFromFile(inputFile, "\t", 4, isDirected)

        val trianglecounts = getTriangleCount(graph, isDirected)

        writeVerticesWithMostTriangles(outputFile, trianglecounts, trianglecounts.size)

        val sortedTriangles = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })
        sortedTriangles.addAll(trianglecounts)



    var file: String = outputFile
    var k = -1
    while (k != 0) {
        println("Introduza um número positivo k (max = ${sortedTriangles.size}) ou 0 se quiser terminar a aplicação")
        val args = readln().trim().split(' ')
        k = args[0].toInt()
        if (args.size == 2) file = args[1]
        if (k > 0) {
            writeVerticesWithMostTriangles(file, sortedTriangles, k)
        }
    }

}

fun createGraphFromFile(fileName: String, delimiter: String, skip: Int, isDirected: Boolean): Graph<Int, Int> {

    val reader = File(fileName).bufferedReader()
    val graph = GraphStructure<Int, Int>()

    for (i in 0 until skip) {
        reader.readLine()
    }

    var line = reader.readLine()

    while (line != null) {
        val (vertex1, vertex2) = line.split(delimiter).map { it.trim().toInt() }
        graph.addVertex(vertex1, 0)
        graph.addVertex(vertex2, 0)
        graph.addEdge(vertex1, vertex2)
        if (!isDirected) {
            graph.addEdge(vertex2, vertex1)
        }
        line = reader.readLine()
    }
    reader.close()

    return graph
}

@OptIn(ExperimentalTime::class)
fun getTriangleCount(graph : Graph<Int,Int>, isDirected: Boolean): PriorityQueue<Pair<Int, Int>> {
    val vertexTriangleCounts = mutableMapOf<Int, Int>()
    var triangleCount = 0
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy{ it.first })

    for (vertex in graph) {

        val neighbors = graph.getVertex(vertex.id)!!.getAdjacencies()

        for (neighbor1 in neighbors) {

            if (neighbor1.adjacent == neighbor1.id) continue

            val vertex1 = graph.getVertex(neighbor1.adjacent)!!

            if (vertex1.clr == COLOR.BLACK) continue

            val neighbors2 = vertex1.getAdjacencies()

            for (neighbor2 in neighbors2) {

                if (neighbor2.adjacent == neighbor2.id || graph.getVertex(neighbor2.adjacent)!!.clr == COLOR.BLACK) continue

                if (graph.getEdge(neighbor2.adjacent, vertex.id) != null && neighbor2.adjacent != vertex.id) {
                    vertexTriangleCounts[vertex.id] = (vertexTriangleCounts[vertex.id] ?: 0) + 1
                    vertexTriangleCounts[neighbor1.adjacent] = (vertexTriangleCounts[neighbor1.adjacent] ?: 0) + 1
                    vertexTriangleCounts[neighbor2.adjacent] = (vertexTriangleCounts[neighbor2.adjacent] ?: 0) + 1
                }
            }
        }
        vertex.clr = COLOR.BLACK
        val divisor = if (isDirected) 1 else 2
        val count = (vertexTriangleCounts[vertex.id] ?: 0) / divisor
        pq.add(Pair(vertex.id, count))
        triangleCount += count
    }
    println("Triangulos : ${triangleCount/3}")
    return pq
}


fun writeVerticesWithMostTriangles(fileName: String, vertices: PriorityQueue<Pair<Int,Int>>, k : Int) {
    val writer = File(fileName).bufferedWriter()

    val result = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until k) {
        val tmp = vertices.poll()
        writer.write("${tmp.first} -> ${tmp.second}")
        writer.newLine()
        result.add(Pair(tmp.first, tmp.second))
    }

    vertices.addAll(result)

    writer.close()
}
