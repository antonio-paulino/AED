package problema
import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun cmpResult(x : Pair2?, y : Pair2?) =
    if(x!!.first != null && y!!.first != null) x.first!!.compareTo(y.first!!)
    else if(x.first != null) 1
    else if(y!!.first != null) -1
    else 0

@OptIn(ExperimentalTime::class)
fun main(args : Array<String>) {
        val m = args.size - 1
        val readers = Array(m) { createReader(args[it + 1]) }
        val minHeap = Array<Pair2?> (m) {null}
        val pq = AEDPriorityQueue(minHeap, 0 ) {x : Pair2?, y : Pair2? -> cmpResult(x, y)}
        for (i in 0 until m) {
            pq.offer(Pair2(readers[i].readLine(), i))
        }
        val outputFile = createWriter(args[0])
        var lastLine = ""   //Para verificar se o elemento atual é maior que o anterior

        while (true) {
            val (minLine, minIndex) = pq.poll()!!

            if (minLine == null) break  //Termina se todos os ficheiros tiverem acabado de ler

            if (minLine != lastLine) {
                outputFile.println(minLine)
                lastLine = minLine
            }

            pq.offer(Pair2(readers[minIndex].readLine(), minIndex)) //Atualiza a linha do ficheiro que continha o menor elemento
        }
        outputFile.close()

        for (reader in readers) {
            reader.close()
        }
}







