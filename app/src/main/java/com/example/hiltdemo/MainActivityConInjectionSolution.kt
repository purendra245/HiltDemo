package com.example.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivityConInjectionSolution : AppCompatActivity() {

    @Inject
    lateinit var someclass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("LOG is ${someclass.doThing()}")
        println("LOG is ${someclass.doSomeOtherThing()}")

    }


    // ### ======   TYPE 1 ERROR ==============

    class SomeClass
    @Inject
    constructor(private val someOtherTask: SomeInterface
    , private val gson: Gson) {

        fun doThing(): String {
            return "Look I did a things"
        }

        fun doSomeOtherThing(): String {
            return someOtherTask.doAThing()
        }
    }

    // ======   TYPE 1 ERROR END ==============

    class SomeOtherTaskImpl
    @Inject
    constructor(private val somedepen: String) : SomeInterface {
        fun doOtherThing(): String {
            return "Look I did Some other things"
        }

        override fun doAThing(): String {
            return "Look I did Some AA things$somedepen"
        }
    }

    interface SomeInterface {
        fun doAThing(): String
    }


    //    @InstallIn(SingletonComponent::class)
//    @InstallIn(ActivityComponent::class)
//    @Module
//    abstract class MyModule {
//
//        //        @Singleton
//        @ActivityScoped
//        @Binds
//        abstract fun bindDependency(
//            someImpl: SomeOtherTaskImpl
//        ): SomeInterface
//
//        //        @Singleton
//        @ActivityScoped
//        @Binds
//        abstract fun bindGson(
//            gson: Gson
//        ): Gson
//
//    }

    @InstallIn(SingletonComponent::class)
    @Module
    class MyModule {

        @Singleton
        @Provides
        fun getSomeString():String{
            return " some string dependency"
        }

        @Singleton
        @Provides
        fun provideSomeOtherTask(
            someOtherDepen: String
        ): SomeInterface{
            return SomeOtherTaskImpl(someOtherDepen)
        }

        @Singleton
        @Provides
        fun provideGson(
        ): Gson{
            return Gson()
        }

    }


}