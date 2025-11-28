package com.base.androidappwithcompose.data.local.kotpref

import android.content.Context
import com.chibatching.kotpref.Kotpref

class KotPref {

    private var TAG="KotPref"

    fun initialize(context: Context){
        Kotpref.init(context)
    }

    /*
    fun exampleInit1(){
        Test.name = "chibatching"
        Test.code = "DAEF2599-7FC9-49C5-9A11-3C12B14A6898"
        Test.age = 30
        Test.highScore = 49219902
        Test.rate = 49.21F
        Test.prizes.add("Bronze")
        Test.prizes.add("Silver")
        Test.prizes.add("Gold")
    }

    fun exampleInit2(){
        Test.bulk {
            name = "chibatching Jr"
            code = "451B65F6-EF95-4C2C-AE76-D34535F51B3B"
            age = 2
            highScore = 3901
            rate = 0.4F
            prizes.clear()
            prizes.add("New Born")
        }
    }*/
}