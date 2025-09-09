package com.example.mvvmdemo.bindingutils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvmdemo.R
import com.example.mvvmdemo.RoomUtil.DatabaseActivity
import com.example.mvvmdemo.databinding.BindingAdapterActivityBinding
import com.example.mvvmdemo.retrofitmvvm.ui.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ActivityBindingAdapter : AppCompatActivity() {

    lateinit var bindingAdapterActivity: BindingAdapterActivityBinding
    lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAdapterActivity =
            DataBindingUtil.setContentView(this, R.layout.binding_adapter_activity)

        val post = Post("Title Hello", "Description Hi", "https://picsum.photos/200")
        bindingAdapterActivity.post = post

        bindingAdapterActivity.mButtonDb.setOnClickListener {
            startActivity(Intent(this, DatabaseActivity::class.java))
        }
        bindingAdapterActivity.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        println("Hello")
        println("Second commit")
        println("Third commit")
        println("Forth commit")
        println("Fifth commit")
        println("Six commit")

        val fn = ::sum
        println(fn(2.0, 3.0))
        calculator(15.0, 5.0, ::sum)
        //Using lambda expression
        calculator(1.0,2.0) { a, b -> a + b }

        //TODO:Lambda expression
        val lambda1 = { x: Int, y: Int -> x + y }
        println("Single Line Lambda: ${lambda1(10, 20)}")

        //TODO(Multi line Lambda)
        val multiLineLambda = {
            println("Hello Lambda")
            2 + 6
        }
        println(multiLineLambda())

        val singleParamLambda: (Int) -> Int = { it * it }
        val lambda: (Int) -> Int = { x: Int -> x * x }

        /*TODO:Flow Concept*/
        GlobalScope.launch(Dispatchers.Main) {
            val result = stateFlow()
            /*.onStart { emit(-1)}
            .onCompletion { emit(6)}
            .onEach { println("About to emit $it") }
            .map { it * 2 }
            .filter { it < 8 }*/
            println("Received " + result.value.toString())
            /* result.collect {
                     println("Received $it")
                 }*/
        }
        /*  runBlocking {
              val data: Flow<Int> = producer()
              data.collect {
                  println("Received using runblocking ${it.toString()}")
              }
          }*/

        //launch
        runBlocking {
            launch {
                delay(1000)
                Log.e("TAG","Hello From launch")
            }
            Log.e("TAG","Hello From runBlocking")
        }

        //async
        runBlocking {
            val defferred = async{
                delay(1000)
               "Result from async"
            }
            Log.e("TAG","Waiting for result...")
            Log.e("Async Result",defferred.await()) // Waits for result
        }

        //runblocking
        runBlocking {
            Log.e("runBlocking","Started From runBlocking")
            delay(1000)
            Log.e("runBlocking","finished From runBlocking")
        }

        runBlocking {
            val result = withContext(Dispatchers.IO){
                "Data from background"
            }
            Log.e("withContext: ",result)
        }

    }
}

//TODO: Flow Example
fun producer() = flow {
    val list = listOf(1, 2, 3, 4, 5)
    list.forEach {
        delay(1000)
        //println("Emitter Thread is ${Thread.currentThread().name}")
        emit(it)
    }
}

//TODO: Mutable State Flow & Shared Flow Example
private fun mutableSharedFlow(): SharedFlow<Int> /*Flow<Int>*/ {
    val mutableSharedFlow = MutableSharedFlow<Int>()
    GlobalScope.launch {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            mutableSharedFlow.emit(it)
            println("Emitting- $it")
            delay(1000)
        }
        println("Fifth commit")
        println("Six commit")
    }
    return mutableSharedFlow
}

//TODO: Mutable State Flow with String Example
private fun example(): SharedFlow<String> /*Flow<Int>*/ {
    val mutableSharedFlow = MutableSharedFlow<String>()
    GlobalScope.launch {
        val list = listOf("Hi", "Hello", "Bye", "Jay", "Deep", "4", "5")
        list.forEach {
            mutableSharedFlow.emit(it)
            delay(1000)
        }
    }
    return mutableSharedFlow
}

//TODO: State Flow Example
private fun stateFlow(): StateFlow<Int> /*Flow<Int>*/ {
    val mutableStateFlow = MutableStateFlow(10)
    GlobalScope.launch {
        delay(2000)
        mutableStateFlow.emit(20)
        delay(2000)
        mutableStateFlow.emit(30)
    }
    return mutableStateFlow
}


//TODO:Simple Function
fun sum(a: Double, b: Double): Double {
    return a + b
}

//TODO:Higher order function
fun calculator(a: Double, b: Double, gn: (Double, Double) -> Double) {
    val result = gn(a, b)
    println("Higher Order Fun: $result")
}



