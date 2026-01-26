val data = listOf("A", "B", "C")

// mapIndexed

val result = data.mapIndexed { idx, str -> "$str ($idx)"}

println(result)

// withIndex

val newResult = data.withIndex().map { (idx, str) -> "$str ($idx)"}

println(newResult)