/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
// WITH_STDLIB
// IGNORE_NATIVE: compatibilityTestMode=BACKWARD_2_1
// ^^^ Compiler v2.1.0 creates non-private backing field which does not pass IR Validation in compiler v2.2.0

import kotlin.test.*

val sb = StringBuilder()

class A {
    lateinit var s: String

    fun foo() {
        sb.appendLine(::s.isInitialized)
    }
}

fun box(): String {
    val a = A()
    a.foo()
    a.s = "zzz"
    a.foo()

    assertEquals("""
        false
        true

    """.trimIndent(), sb.toString())
    return "OK"
}