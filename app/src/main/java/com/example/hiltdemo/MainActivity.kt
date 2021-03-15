package com.example.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someclass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("LOG is ${someclass.doThing()}")
        println("LOG is ${someclass.doSomeOtherThing()}")
    }


    class SomeClass
    @Inject
    constructor(private val someOtherTask: SomeOtherTask){

        fun  doThing( ):String{
            return "Look I did a things"
        }

        fun doSomeOtherThing(): String{
            return someOtherTask.doOtherThing()
        }
    }

    class SomeOtherTask
    @Inject
    constructor(){
        fun  doOtherThing():String{
            return "Look I did Some other things"
        }
    }
}