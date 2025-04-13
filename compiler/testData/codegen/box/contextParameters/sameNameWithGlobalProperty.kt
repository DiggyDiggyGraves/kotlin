// IGNORE_BACKEND_K1: ANY
// LANGUAGE: +ContextParameters
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_0
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_1
// ^^^ Compiler v2.1.0 does not know this language feature

class A(var x: String) {
    fun foo(): String { return x }
}

var result = ""

val a: A = A("not OK")

context(a: A)
fun test1() {
    result = a.foo()
}

fun box(): String {
    with(A("OK")) {
        test1()
    }
    return result
}