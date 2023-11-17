package com.example.faraja_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faraja_app.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener {
            val signupEmail = binding.signupEmail.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            val confirmPassword = binding.signupConfirmPassword.text.toString()

            if (signupEmail.isNotEmpty() && signupPassword.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (signupPassword == confirmPassword) {
                    signupUser(signupEmail, signupPassword)
                } else {
                    Toast.makeText(this@SignupActivity, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SignupActivity, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginRedirect.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun signupUser(email: String, password: String) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignupActivity) { task ->
                    if (task.isSuccessful) {
                        // Send verification email
                        sendVerificationEmail()
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            "Signup Failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this@SignupActivity, "Invalid email address", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendVerificationEmail() {
        val user: FirebaseUser? = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showWaitingPage()
                } else {
                    Toast.makeText(this@SignupActivity, "Failed to send verification email", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun showWaitingPage() {
        val waitingIntent = Intent(this@SignupActivity, LoadingActivity::class.java)
        startActivity(waitingIntent)
        finish()
    }
}
