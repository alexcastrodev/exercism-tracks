package com.dht

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun blocking() {
    println("starting blocking...")
    (1..50_000_000).map { it * it }
    println("blocking finish")
}

suspend fun suspendingCode() {
    println("starting suspending...")
    (1..50_000_000).map { it * it }
    println("ending suspending...")

}

suspend fun cookRice() {
    println("starting cook rice...")
    delay(6000)
    println("ending cook rice...")
}

suspend fun cookChicken() {
    println("starting cook chicken...")
    delay(7000)
    println("ending cook chicken...")
}

// Threads vs. Kotlin Coroutines vs. Dispatchers - Philipp Lackner
// https://www.youtube.com/watch?v=0Hv5LTxAutw
suspend fun main() {
    println("Start")
    // A unica diferença de numeros de Threads
    // Doc: "In practice this means that, despite not abiding by Dispatchers.IO's parallelism restrictions, its views share threads and resources with it."
    // Default -> Pool limitado (Tamanho ≈ número de cores da CPU) / CPU Bound
    // IO -> Pool elástico (por default são 64 threads) / Bloqueante
    //    A diferença é que o Dispatchers.Default é limitado ao número de núcleos de CPU (com um mínimo de 2), portanto apenas N (onde N == núcleos de CPU) tarefas podem ser executadas em paralelo nesse dispatcher.
    //
    //    No dispatcher de IO, há por padrão 64 threads, então pode haver até 64 tarefas paralelas sendo executadas nesse dispatcher.
    //
    //    A ideia é que o dispatcher de IO passa muito tempo esperando (bloqueado por IO), enquanto o dispatcher Default é destinado a tarefas intensivas de CPU, nas quais há pouco ou nenhum tempo de espera

    // Deve-se observar que o Dispatchers.Default existe porque, se suas corrotinas são limitadas por CPU,
    // executá-las em mais threads do que você tem núcleos apenas desperdiça tempo com troca de contexto e sobrecarga de threads;
    // porém, ainda são necessárias mais threads do que núcleos quando você está fazendo E/S bloqueante, daí o Dispatchers.IO.
    // https://stackoverflow.com/questions/59039991/difference-between-usage-of-dispatcher-io-and-default
    CoroutineScope(Dispatchers.IO).launch {
        launch { cookRice() }
        launch { cookChicken() }
    }.join()

    println("End")
}

suspend fun main2() {
    println("Start")
    // Dispatcher.Default é a coroutine pai ( nosso pool de thread da cpu )
    CoroutineScope(Dispatchers.Default).launch {
        // Essas duas coroutines filhas vai executar ao mesmo tempo (Não com paralelismo, mas com concorrencia)
        launch { cookRice() }
        launch { cookChicken() }
    }.join()

    println("End")
}

fun main1() {
    println("Start")
    // 1 - Block CPU BOUND
    // blocking()
    // 2 - Thread
    // thread {
    //    blocking()
    // }
    // Coroutine
    // Isso vai rodar, um Coroutine após outro, mas quando chegar ao Idle
    CoroutineScope(Dispatchers.Default).launch {
        suspendingCode()
    }
    CoroutineScope(Dispatchers.Default).launch {
        suspendingCode()
    }

    println("End")
    // Aguarda aparecer o Coroutine, antes de matar a Thread principal
    Thread.sleep(2000)
}