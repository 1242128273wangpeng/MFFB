package com.wangpeng.firstfirebase.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select

fun testFlatMapMerge() {
    runBlocking {
        val result = arrayListOf<Int>()
        for (index in 1..100) {
            result.add(index)
        }

        listOf(1,2,3,4).asFlow()
            .flatMapMerge {
                flow {
                    emit(it)
                }
                    .flowOn(Dispatchers.IO)
            }
            .collect { println("$it") }
    }
}


fun testEmittin() {
    runBlocking {
        (1..5).asFlow()
            .onEach { println("Emitting $it") }
            .catch { it.printStackTrace() }
            .collect()
    }
}

fun testFlattenMerge() {
    runBlocking {
        val flowA = (1..5).asFlow().onEach { delay(100) }
        val flowB = flowOf("one", "two", "three", "four", "five").onEach { delay(200) }
        flowOf(flowA, flowB)
            .flattenMerge(2)
            .collect { println("testFlattenMerge:$it") }
    }
}

fun testFlattenConcat() {
    runBlocking {
        val flowA = (1..5).asFlow()
        val flowB = flowOf("one", "two", "three")
        flowOf(flowA, flowB)
            .flattenConcat().collect {
                println("testFlattenMerge:$it")
            }
    }
}

fun testAsFlow() {
    runBlocking {
        (1..5).asFlow()
            .transform {
                emit(it * 2)
                delay(100)
                emit(it * 4)
            }
            .collect { println("testAsFlow $it") }
    }
}

fun testFlowZip() {
    runBlocking {
        val nums = (1..4).asFlow()
            .onEach {
                delay(300)
                println("【${Thread.currentThread().name} nums: $it】")
            }
        val strs = flowOf("one", "two", "three")
            .onEach {
                delay(400)
                println("【${Thread.currentThread().name}】 strs: $it")
            }
        nums.combine(strs) { a, b ->
            "$a-> $b->"
        }.collect { value ->
            println("【${Thread.currentThread().name}】$value")
        }
    }
}

fun testFlowBuffer() {
    runBlocking {
        flow<Int> {
            for (i in 1..4) {
                delay(100)
                println("【${Thread.currentThread().name}】 flow $i")
            }
        }.collect { value ->
            delay(200)
            println("【${Thread.currentThread().name}】 collect $value")
        }
    }
}

fun testFlowParallel() {
    runBlocking {
        channelFlow {
            send(1)
            withContext(Dispatchers.IO) {
                send(2)
            }
        }.collect { value ->
            println("${Thread.currentThread().name} collect $value")
        }
    }
}

fun testFlowErrorComplete() {
    runBlocking {
        val foo = flow<Int> {
            emit(1)
            throw ArithmeticException("计算异常了")
            emit(2)
        }.catch { t: Throwable? ->
            println("${Thread.currentThread().name} catch error：$t")
            emit(-1)
        }.onCompletion { t: Throwable? ->
            println("${Thread.currentThread().name} onCompletion：$t")
        }
        foo.collect { value ->
            println("${Thread.currentThread().name} collect：$value")
        }
    }
}

fun testFlowChangeThread() {
    runBlocking {
        val foo = flow<Int> {
            for (i in 1..3) {
                println("${Thread.currentThread().name} flow $i")
                emit(i)
                delay(100)
            }
        }
        foo.flowOn(Dispatchers.IO).collect { value ->
            println("${Thread.currentThread().name} collect $value")
        }
    }
}

fun testFlowCreate() {
    runBlocking {
        listOf<Int>(1, 2, 3).asFlow()
            .onEach {
                delay(100)
            }.collect {
                println("通过asFlow 创建的Flow $it")
            }
        flowOf<Int>(1, 2, 3)
            .onEach {
                delay(100)
            }.collect {
                println("通过Flowof创建的Flow $it")
            }

        channelFlow {
            for (i in 1..3) {
                delay(100)
                send(i)
            }
        }.collect {
            println("通过channelFlow 创建的 Flow $it")
        }
    }
}

fun testFlowCollect() {
    runBlocking {
        val foo = flow {
            for (i in 1..3) {
                println("${Thread.currentThread().name} flow: $i")
                emit(i)
                delay(100)
            }
        }
        foo.collect { value -> println("【${Thread.currentThread().name}】collect $value") }
    }
}

fun testFlow() {
    val foo = sequence<Int> {
        for (i in 1..3) {
            yield(i)
            Thread.sleep(100)
        }
    }
    foo.forEach { value ->
        println("flow value:$value")
    }
}

/**
 * select 选择优先到达的，后面的数据源会被终止
 */
fun testSelect() {
    runBlocking {
        val one = async(Dispatchers.IO) {
            doOne()
        }
        val two = doTwo()
        select<Unit> {
            one.onAwait { value ->
                println("${Thread.currentThread().name} one -> $value")
            }
            two.onReceive { value ->
                println("${Thread.currentThread().name} two -> $value")
            }
        }
    }
}

fun doTwo() = GlobalScope.produce<Int> {
    delay(1500)
    println("【${Thread.currentThread().name}】doTwo 计算中")
    send(2)
}

suspend fun doOne(): Int {
    delay(1000)
    println("【${Thread.currentThread().name}】doOne 计算中")
    return 1
}

/**
 * 一个数据源多数据源发生订阅
 */
fun testBroadcast() {
    runBlocking {
        val boradcastChannel = GlobalScope.broadcast<Int> {
            for (i in 0..3) {
                println("准备发射:$i")
                send(i)
                println("发射完毕:$i")
            }
        }
        List(3) { index ->
            GlobalScope.launch {
                for (i in boradcastChannel.openSubscription()) {
                    println("协程$index 接收$i")
                }
            }
        }.joinAll()
    }
}

/**
 * 单一数据源发生订阅
 */
fun testProductor() {
    runBlocking {
        val channels = Channel<Int>(Channel.RENDEZVOUS)
        val productor = GlobalScope.launch {
            for (i in (0..3)) {
                println("准备发射 $i")
                channels.send(i)
                println("结束发射 $i")
            }
            channels.close()
        }
        val consumer = GlobalScope.launch {
            while (!channels.isClosedForReceive) {
                println("准备接收")
                val value = channels.receiveOrNull()
                println("结束接收:$value")
            }
        }
        val consumer2 = GlobalScope.launch {
            for (i in channels) {
                println("结束接收：$i")
            }
        }
        productor.join()
        consumer.join()
        consumer2.join()
    }
}