package com.example.testminapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.testminapp.databinding.ActivityWelcomeBinding
import com.example.testminapp.ui.dashboard.DashboardActivity
import com.example.testminapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

private const val SPLASH_SCREEN=4000
class WelcomeActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
   private lateinit var binding:ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            auth = FirebaseAuth.getInstance()
            if (auth.currentUser!=null){
                startActivity(Intent(this,DashboardActivity::class.java))
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
            }
            finish()
        } , SPLASH_SCREEN.toLong())

    }
}