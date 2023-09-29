package com.example.xmlpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**benefit of using nav component:
         * An XML resource that contains all navigation-related information in one centralized location.
        The Navigation component provides a number of other benefits, including the following:
        Handling fragment transactions.
        Handling Up and Back actions correctly by default.
        Providing ViewModel support—you can scope a ViewModel to a navigation graph to share UI-related
        data between the graph's destinations.
         */

    }
}