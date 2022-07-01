package com.example.testminapp.ui.dashboard



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testminapp.R
import com.example.testminapp.WelcomeActivity
import com.example.testminapp.databinding.ActivityDashboardBinding
import com.example.testminapp.ui.dashboard.photo.FragmentPhoto
import com.example.testminapp.ui.dashboard.profile.FragmentProfile
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val profile = FragmentProfile()
    private val photos = FragmentPhoto()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        replaceFragment(profile)
        binding.bottomNav.selectedItemId = R.id.action_profile

        binding.bottomNav.setOnItemSelectedListener { item ->
            val menuId = item.itemId
            if (menuId == R.id.action_profile) {
                replaceFragment(profile)
            } else if (menuId == R.id.action_photos) {
                replaceFragment(photos)
            } else if (menuId == R.id.action_exit) {
                userAction()
            }
            true
        }


    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun onBackPressed() {
        if (binding.bottomNav.selectedItemId === R.id.action_profile) {
            userBackSpace()
        } else {
            replaceFragment(profile)
            binding.bottomNav.setSelectedItemId(R.id.action_profile)
        }
    }

    private fun userAction(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Test Min App")
        builder.setMessage("Are you want to exit the app?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            mGoogleSignInClient?.signOut()?.addOnCompleteListener(this, object:OnCompleteListener<Void>{
                override fun onComplete(p0: Task<Void>) {
                    Toast.makeText(this@DashboardActivity, "Signed Out", Toast.LENGTH_LONG).show()
                }
            })
            dialog.dismiss()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No"){dialog,_->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun userBackSpace(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Test Min App")
        builder.setMessage("Are you want to exit the app?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        builder.setNegativeButton("No"){dialog,_->
            dialog.dismiss()
        }
        builder.show()
    }
}

