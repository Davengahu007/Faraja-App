package com.example.faraja_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.faraja_app.databinding.ActivityLoadingBinding
import java.util.Timer
import java.util.TimerTask

class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simulate a delay (you can replace 3000 with your desired delay in milliseconds)
        val delayMillis = 3000L
        Timer().schedule(object : TimerTask() {
            override fun run() {
                // Update the loading message
                runOnUiThread {
                    binding.loadingTextView.text =
                        "A verification code has been sent to your email. Kindly verify your account and proceed to login."

                    // Show the "Proceed to Login" button
                    binding.proceedToLoginButton.visibility = View.VISIBLE
                }
            }
        }, delayMillis)

        // Set up click listener for the button
        binding.proceedToLoginButton.setOnClickListener {
            // Navigate to the login page
            val loginIntent = Intent(this@LoadingActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}
