package com.zhukov.android.myapplication.presentation.listcontact.hostcontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.zhukov.android.myapplication.R

 class MainActivity : AppCompatActivity() {

    lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}