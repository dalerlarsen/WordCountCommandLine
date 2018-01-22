import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    if (args.size == 0) {
        println("You must provide a file name")
        exitProcess(1)
    }

    // Read from a file and turn into a string.. test.txt > allTheWords
    val inputStream: FileInputStream = File(args[0]).inputStream()

    val allTheWords = inputStream.bufferedReader().use { it.readText() }

//    val allTheWords = "Hello, there my name is nick. So now we have punctuation. Hello there again"

    // Make a list of all the words separated out
    val words = allTheWords
            .replace("\n", " ")
            .replace(",", "")
            .replace(".", "")
            .replace("!", "")
            .replace("?", "")
            .split(" ")

    // Get a counted list of all words
    val wordMap = mutableMapOf<String, Int>()

    for (word in words) {
        val capitalize = word.capitalize()
        if (wordMap[capitalize] == null) {
            wordMap[capitalize] = 1
        } else {
            var count = wordMap[capitalize]!!
            wordMap[capitalize] = count + 1
        }
    }

    // Sort the list
    val wordList = wordMap.toList()
    val sortedList = wordList.sortedWith(compareBy({it.second}))

    // Print a sorted list of the most popular words
    for (word in sortedList) {
        println("${word.first} - ${word.second}")
    }
}