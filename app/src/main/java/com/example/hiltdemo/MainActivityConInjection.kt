//package com.example.hiltdemo
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import com.google.gson.Gson
//import dagger.hilt.android.AndroidEntryPoint
//import dagger.hilt.android.scopes.ActivityScoped
//import dagger.hilt.android.scopes.FragmentScoped
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class MainActivityConInjection : AppCompatActivity() {
//
//    @Inject
//    lateinit var someclass: SomeClass
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        println("LOG is ${someclass.doThing()}")
//        println("LOG is ${someclass.doSomeOtherThing()}")
//    }
//
//
//    //### ======   TYPE 1 ERROR ==============
//
////    class SomeClass
////    @Inject
////    constructor(private val someOtherTask: SomeInterface){
////
////        fun  doThing( ):String{
////            return "Look I did a things"
////        }
////
////        fun doSomeOtherThing(): String{
////            return someOtherTask.doAThing()
////        }
////    }
//
//    // ======   TYPE 1 ERROR END ==============
//
//
//
//    //### ======   TYPE 2 ERROR ==============
//
//    class SomeClass
//    @Inject
//    constructor(
//    private val gson: Gson){
//
//        fun  doThing( ):String{
//            return "Look I did a things"
//        }
//
//        fun doSomeOtherThing(): String{
//            return "Look I did some other things"
//        }
//    }
//
//    // ======   TYPE 1 ERROR END ==============
//
//    class SomeOtherTaskImpl
//    @Inject
//    constructor() : SomeInterface{
//        fun  doOtherThing():String{
//            return "Look I did Some other things"
//        }
//
//        override fun doAThing(): String {
//            return "Look I did Some AA things"
//        }
//    }
//
//    interface  SomeInterface{
//        fun  doAThing():String
//    }
//}