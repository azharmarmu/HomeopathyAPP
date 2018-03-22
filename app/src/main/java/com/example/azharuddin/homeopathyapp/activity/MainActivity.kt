package com.example.azharuddin.homeopathyapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.azharuddin.homeopathyapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isUserExists()
    }

    @Suppress("UNUSED_PARAMETER")
    fun supportClick(view: View) {
        startActivity(Intent(this@MainActivity, ChatScreenActivity::class.java))
    }

    private fun isUserExists() {

    }
}
