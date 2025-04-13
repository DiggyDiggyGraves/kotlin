/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
// WITH_STDLIB
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_1
// ^^^ Compiler v2.1.0 creates non-private backing field which does not pass IR Validation in compiler v2.2.0

import kotlin.test.*

val sb = StringBuilder()

class Outer(val x: Int) {
    inner class Inner() {
        inner class InnerInner() {

            init {
                sb.appendLine(x)
            }

            lateinit var s: String

            constructor(s: String) : this() {
                this.s = s
            }
        }
    }
}

fun box(): String {
    Outer(42).Inner().InnerInner("zzz")

    assertEquals("42\n", sb.toString())
    return "OK"
}
