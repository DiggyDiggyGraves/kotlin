// WITH_STDLIB
// ISSUE: KT-67520
// LANGUAGE: +AvoidWrongOptimizationOfTypeOperatorsOnValueClasses
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_0
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_1
// ^^^ Compiler v2.1.0 does not know this language feature

inline class X(val x: String)

fun box(): String {
    val res1 = runCatching {
        val cmp: Comparable<String> = leakInline<String>(X(""))
    }
    require(res1.exceptionOrNull()?.message == "Alas") { res1.toString() }

    val res2 = runCatching {
        val cmp: Comparable<String> = leakInline<String>(X(""))
        "Hey " + cmp
    }
    require(res2.exceptionOrNull()?.message == "Alas") { res2.toString() }
    
    return "OK"
}


inline fun <T> leakInline(a: Any): Comparable<T> {
    if (a is Comparable<*>) return a as Comparable<T>
    error("Alas")
}
