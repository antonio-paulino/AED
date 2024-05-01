package serie2

import serie2.mapCollections.AEDHashMapAED
import serie2.mapCollections.set
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)

fun main(args:Array<String>){
    measureTime {  }
    val time = measureTime {
        val inputreader = createReader(args[1])
        val outputwriter = createWriter(args[0])
        val wordGroups = AEDHashMapAED<String, String>()

        var line = inputreader.readLine()
        while (line != null) {
            var word = ""
            var i = 0
            val linesize = line.length
            while (i < linesize) {

                while (i < linesize && line[i] == ' ') {
                    i++
                }

                while (i < linesize && line[i] != ' ') {
                    word += line[i]
                    i++
                }

                val size = word.length
                val wordArr = CharArray(size) { word[it] }
                sortWord(wordArr, 0, size - 1)
                val sortedWord = String(wordArr)

                if (word.isNotEmpty()) {
                    val group = wordGroups[sortedWord]
                    if (group != null)
                        wordGroups[sortedWord] = "$group, $word"
                    else
                        wordGroups[sortedWord] = word
                    word = ""
                }
            }
            line = inputreader.readLine()
        }
        for (wordGroup in wordGroups) {
            outputwriter.println("[${wordGroup.value}]")
        }
        outputwriter.close()
        println(wordGroups.size)
    }
    println(time)

}

fun sortWord(a: CharArray, left: Int, right: Int) {
    if (left < right) {
        val i = partitionChar(a, left, right)
        sortWord(a, left, i - 1)
        sortWord(a, i + 1, right)
    }
}

fun partitionChar(a: CharArray, left: Int, right: Int): Int {
    var i = left - 1
    var j = right
    val pivot = a[right]
    while (true) {
        while (i < right && a[++i] < pivot);
        while (j > left && a[--j] > pivot);
        if (i >= j) break
        exchangeChar(a, i, j)
    }
    exchangeChar(a, i, right)
    return i
}

fun  exchangeChar(a: CharArray, i: Int, j: Int) {
    val x = a[i]
    a[i] = a[j]
    a[j] = x
}
