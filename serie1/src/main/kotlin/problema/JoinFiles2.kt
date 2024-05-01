package problema
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime



@OptIn(ExperimentalTime::class)
fun main(args : Array<String>) {
        val m = args.size - 1
        val readers = List(m) { createReader(args[it + 1]) }
        val lines = PriorityQueue<Pair<String?, Int>> (compareBy {it.first })
        for (i in 0 until m) {
            lines.add(Pair(readers[i].readLine(), i))
        }
        val outputFile = createWriter(args[0])
        var lastLine = ""   //Para verificar se o elemento atual é maior que o anterior

        while (true) {
            val (minLine, minIndex) = lines.poll()

            if (minLine == null) break  //Termina se todos os ficheiros tiverem acabado de ler

            if (minLine != lastLine) {
                outputFile.println(minLine)
                lastLine = minLine
            }
            lines.add(Pair(readers[minIndex].readLine(), minIndex)) //Atualiza a linha do ficheiro que continha o menor elemento
        }
        outputFile.close()

        for (reader in readers) {
            reader.close()
        }
    }





