package com.example.testminapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testminapp.databinding.ActivityLoginBinding
import com.example.testminapp.model.User
import com.example.testminapp.ui.dashboard.DashboardActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 1
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        //google sign in
        binding.btnGoogleSign.setOnClickListener {
            userSignIn()
        }

    }

    private fun userSignIn() {
        val intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }


    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)
                val auth = FirebaseAuth.getInstance().currentUser
//                val test = Firebase.database.reference
//                val user = User(account?.displayName.toString(),account?.email.toString(),account?.photoUrl.toString())
//                test.child("user").child(auth?.uid!!).setValue(user).addOnCompleteListener {
//
//                }.addOnFailureListener {
//                    Toast.makeText(this,"Error occured, try again!",Toast.LENGTH_SHORT).show()
//                }

                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()

            } catch (e: ApiException) {
                Log.e("TAG","signInResult:failed code=" + e.statusCode)
            }
        }
    }


}


//DateFormat.getDateTimeInstance().format(created)