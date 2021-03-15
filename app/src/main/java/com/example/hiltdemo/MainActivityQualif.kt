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
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivityQualif : AppCompatActivity() {

    @Inject
    lateinit var someclass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("LOG is ${someclass.doThing()}")
        println("LOG is ${someclass.doSomeOtherThing1()}")
        println("LOG is ${someclass.doSomeOtherThing2()}")

    }


    // ### ======   TYPE 1 ERROR ==============

    class SomeClass
    @Inject
    constructor(@Impl1 private val someOtherTask1: SomeInterface
    ,@Impl2 private val someOtherTask2: SomeInterface) {

        fun doThing(): String {
            return "Look I did a things"
        }

        fun doSomeOtherThing1(): String {
            return someOtherTask1.doAThing()
        }

        fun doSomeOtherThing2(): String {
            return someOtherTask2.doAThing()
        }
    }

    // ======   TYPE 1 ERROR END ==============

    class SomeOtherTaskImpl1
    @Inject
    constructor() : SomeInterface {

        override fun doAThing(): String {
            return "Look I did Some AA things1"
        }
    }


    class SomeOtherTaskImpl2
    @Inject
    constructor() : SomeInterface {

        override fun doAThing(): String {
            return "Look I did Some AA things2"
        }
    }

    interface SomeInterface {
        fun doAThing(): String
    }


    @InstallIn(SingletonComponent::class)
    @Module
    class MyModule {

        @Singleton
        @Provides
        @Impl1
        fun provideSomeOtherTask1(
        ): SomeInterface{
            return SomeOtherTaskImpl1()
        }

        @Singleton
        @Provides
        @Impl2
        fun provideSomeOtherTask2(
        ): SomeInterface{
            return SomeOtherTaskImpl2()
        }


    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Impl1


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Impl2


}