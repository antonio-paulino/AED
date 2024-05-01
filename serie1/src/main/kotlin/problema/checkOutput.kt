package problema

fun main(){
    println(checkvalid("Output.txt"))
}


fun checkvalid(string: String) : Boolean {
    val listcheck = mutableSetOf<String>()
    val reader = createReader(string)
    var line = reader.readLine()
    var lastline = ""
    while (line != null){
        if ((!(listcheck.add(line)) || lastline > line)) return false
        lastline = line
        line = reader.readLine()
    }
    return true
}
