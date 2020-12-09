package com.android.pkqup.androidnote.kotlin

import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * @author liucun
 * @date 2020/9/18 15:25
 * @description
 */
class Test {

    val empty = emptyList<String>()

    var name: String = ""
        get() {
            return field + ""
        }
        set(value) {
            field = value + ""
        }
    var array: IntArray = intArrayOf(1, 2)

    var lists = listOf<String>()
    var list = mutableListOf<String>()


    var intArr = IntArray(4) { i -> i }

    fun test() {

        thread {

            callBack

        }

        name.let {  }

        with(name){

        }

    }

    var a = ::test

    val d = fun(param: Int): String {
        return param.toString()
    }

    var callBack = {
        println("")
    }


    val continuation = suspend {  }.createCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Unit>) {
            TODO("Not yet implemented")
        }
    })


    val continuation1 = suspend {  }.startCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = TODO("Not yet implemented")

        override fun resumeWith(result: Result<Unit>) {
            TODO("Not yet implemented")
        }
    })

    val continuation2 = suspend {  }.startCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = TODO("Not yet implemented")

        override fun resumeWith(result: Result<Unit>) {
            TODO("Not yet implemented")
        }
    })

    val valueA:String by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
        ""
    }
}

object  User{
    var a =1
}