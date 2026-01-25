val mutable = mutableListOf(1,2,3)
val readOnly: List<Int> = mutable

mutable.add(4)

println(readOnly)