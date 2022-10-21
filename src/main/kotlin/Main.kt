import com.sun.org.apache.xpath.internal.operations.Bool
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

fun main(args: Array<String>) {

    println("1: "+capitalizeSentence("paradox paralax"))
    println("2: "+getOdd(listOf(1,2,3,4,5,6,7,8)))
    println("3: "+containsRange(1..12,5..12))
    println("4: "+addUpTo(3))
    println("5: "+getIndex(arrayOf('A','B','C','D'),'B'))
    println("6: "+countDown(5))
    println("7: "+countUpAndDown(5))
    println("8: "+formatTrainRoute(listOf("test","paradox","paralax")))
    println("9: "+getAllPairs(3))
    println("10: "+countUniqueValues(listOf(1,1,1,2,4)))
    println("11: "+printNumber(5))
    println("12: "+power(3,3))
    println("13: "+factorial(2))
    println("14: "+product(arrayOf(1,2,3,4,5)))
    println("15: "+capitalizeFirst(listOf("first","second","last")))
    println("16: "+decapitalizeConst("__FOO_BAR_ZAR"))
    println("17: "+longestWord("hi hello hihihihihi hahosa"))
    println("18: "+palindrome("wacaw"))
    println("19: "+anagrams("rail safety", "fairy tales"))
    println("20: "+maxChar("ccccccorrrrrwwwwwwwwwwwwwwwwwwii"))
    println("21: "+reverse("hello"))
    println("22: "+vowels("Why do you ask?"))
//    println("23: \n"+pyramid(3))
    println("24: "+fizzBuzz(5))
    println("25: "+hasRepeateCharacter("abscda"))
    println("26: "+encodeCaesarCipher("xyz",1))
}

fun capitalizeSentence(string :String) : String{
    var stringOutput = "";
    string.split(" ").map {
        stringOutput += it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } +" "
    }
    return stringOutput.trim()
}

fun getOdd(list:List<Int>): List<Int>{
    return list.filter {
        it % 2 != 0
    }
}

fun containsRange(range1:IntRange,range2:IntRange):Boolean{
    var isContains = false
    range2.forEach {
        isContains = range1.contains(it)
    }

    return isContains
}

fun addUpTo(n:Int):Int{
    var sum = 0
    for (i in 0..n){
        sum+=i
    }
    return sum
}

fun getIndex(list:Array<Char>,char:Char):Int{
    var i = 0
    list.map {
        if (it.equals(char))
            return i
        i++
    }
    return -1
}

fun countDown(n:Int):List<Int>{
    var list = ArrayList<Int>()

    for (i in n downTo 0){
        list.add(i)
    }
    return list
}

fun countUpAndDown(n:Int):List<Int>{
    var list = ArrayList<Int>()

    for (i in 0..n){
        list.add(i)
    }
    for (i in n-1 downTo 0){
        list.add(i)
    }
    return list
}

fun formatTrainRoute(list:List<String>):String{
    var string = "Train is calling at"
    var newString = " "
    for (i in 0..list.size-1){
        if (i==list.size-2){
            newString += "${list.get(i)} and "
        }
        else if(i==list.size-1){
            newString+= list.get(i)
        }
        else {
            newString += "${list.get(i)}, "
        }

    }
    return string + newString
}

fun getAllPairs(n: Int):List<Pair<Int,Int>>{
    var listOfAllNumbers = ArrayList<Int>()
    var pairList = ArrayList<Pair<Int,Int>>()
    for (i in 0..n){
        listOfAllNumbers.add(i)
    }
    listOfAllNumbers.forEach{it1 ->
        listOfAllNumbers.forEach{it2->
            pairList.add(Pair(it1,it2))
        }
    }
    return pairList
}

fun countUniqueValues(list:List<Int>):Int{
    var countUnique = 0

    for (i in 0..list.size-1){
        var isUnique = true
        for (j in 0..list.size-1){
            if (i==j) continue
            if (list.get(i)==list.get(j)){
                isUnique = false
            }

        }
        if (isUnique) countUnique++
    }

    return countUnique
}

fun printNumber(n:Int):List<Int>{
    var list = ArrayList<Int>()

    for (i in n downTo 1){
        list.add(i)
    }
    return list
}

fun power(x:Int,y:Int):Int{
    var result = y;
    for (i in 1..x-1){
        result *= y
    }
    return result
}

fun factorial(n:Int):Int{
    var result = 1;
    for (i in 1..n){
        result*=i
    }
    return result
}

fun product(list:Array<Int>):Int{
    var result = 1
    for (i in list){
        result*=i
    }
    return result
}

fun capitalizeFirst(list:List<String>) = list.map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }  }

fun decapitalizeConst(string: String):String{
    var outputString = ""
    var stringList = string.split("[^a-zA-Z\\d\\s: ]".toRegex()).filter { it != "" }
    for (i in 0..stringList.size-1){
        if (i<1){
            outputString+=stringList.get(i).lowercase()
        }
        else{
            outputString+=stringList.get(i).lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }
    return outputString
}

fun longestWord(string:String):String{
    var max = 0
    var maxString = ""
    string.split(" ").forEach{
        if (it.length>max){
            max = it.length
            maxString = it
        }
    }

    return maxString
        //string.split(" ").sortedBy { it.length }.last()
}

fun palindrome(string: String):Boolean{
    if (string.length % 2 == 0){
        for (i in 0..string.length-1){
            if (string.get(i) != string.get(string.length-1-i)){
                return false
                }
        }
    }
    else{
        for (i in 0..string.length-1)
            if (i!= string.length-1-i){
                if (string.get(i) != string.get(string.length-1-i)){
                    return false
                }
            }
    }
    return true
}

fun anagrams(string1: String,string2: String):Boolean{

    var str1 = string1.replace(("[^A-Za-z0-9 ]").toRegex(),"").lowercase(Locale.getDefault()).filter { !it.isWhitespace() }.toSortedSet()
    var str2 = string2.replace(("[^A-Za-z0-9 ]").toRegex(),"").lowercase(Locale.getDefault()).filter { !it.isWhitespace() }.toSortedSet()

    return str1.equals(str2)
}

fun maxChar(string:String):Char{
    var max = 0
    var maxChar = string.get(0)
    for (i in 0..string.length-1){
        var count = 0
        for(j in 0..string.length-1){
            if (i==j) continue
            if (string.get(i)==string.get(j)){
                count++
            }
        }
        if (count>max) {
            max = count
            maxChar = string.get(i)
        }
    }
    return maxChar
}

fun reverse(string: String):String{
    var reversedString = ""
    for (i in string.length-1 downTo 0){
        reversedString+=string.get(i)
    }
    return reversedString
}

fun vowels(string: String) = string.filter { "aeiouy".contains(it) }.length

fun pyramid(levels:Int):String{
    var character = '#'
    var renderedString = ""
    for (i in levels-1 downTo 0){
        for (j in 0..(levels+levels/1.5).toInt()){

            renderedString += character
        }
        renderedString+="\n"
    }
//    for (i in 0..levels){
//
//    }
    return renderedString
}

fun fizzBuzz(n:Int):ArrayList<Any>{
    var list = ArrayList<Any>()
    for (i in 1..n){
        if (i%5==0 && i%3==0){
            list.add("FizzBuzz")
        }
        else if (i%5==0){
            list.add("Buzz")
        }else if(i%3==0){
            list.add("Fizz")
        }
        else{
            list.add(i)
        }
    }

    var list2 = ArrayList<Any>()
    for (i in 1..n){
        list2.add(i)
    }

    return list
}

fun hasRepeateCharacter(string: String):Boolean{
    for (i in 0..string.length-1){
        for (j in 0..string.length-1){
            if (i==j) continue
            if (string.get(i).equals(string.get(j))) return true
        }
    }
    return false
}

fun encodeCaesarCipher(string: String,n:Int):String{
    var alphabet = "abcdefghijklmnopqrstuvwxyz"
    var outputString = ""
    for (i in 0..string.length-1){
        for (j in 0..alphabet.length-1){
            if (string.get(i) == alphabet.get(j)){
                if (j<alphabet.length-n){
                    outputString+=alphabet.get(j+n)
                }
                else{
                    outputString+=alphabet.get(alphabet.length-1-j)
                }

            }
        }
    }
    return outputString
}