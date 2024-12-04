package com.course.ex1


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.course.ex1.ui.MyApp
import com.course.ex1.ui.theme.Ex1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Ex1Theme {
                MyApp()
            }
        }


    }
}

