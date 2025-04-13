// ISSUE: KT-70417
// JVM_ABI_K1_K2_DIFF: difference in name of anonymous parameter `_` of setter
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_1
// ^^^ Compiler v2.1.0 creates non-private backing field which does not pass IR Validation in compiler v2.2.0

interface A {
    var x: String
}

open class B : A {
    override lateinit var x: String
}

interface C: A

open class D : C  {
    override var x: String
        get() = "OK"
        set(_) {}
}

class E : B(), C by D()

fun box(): String {
    val e = E()
    e.x = "Fail"
    return e.x
}