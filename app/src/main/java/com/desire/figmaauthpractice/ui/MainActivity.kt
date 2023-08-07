package com.desire.figmaauthpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.desire.figmaauthpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  Log.i("test","Main Activity onCreate method")
    }

   /* override fun onStart() {
        super.onStart()
        Log.i("test", "Main Activity onStart method")

    }
*/
    /*override fun onResume() {
        super.onResume()
        Log.i("test", "Main Activity onResume method")

    }

    override fun onPause() {
        super.onPause()
        Log.i("test", "Main Activity onPause method")

    }

    override fun onStop() {
        super.onStop()
        Log.i("test", "Main Activity onStop method")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("test", "Main Activity onDestroy method")

    }*/
}