package com.example.testminapp.ui.dashboard.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.testminapp.R
import com.example.testminapp.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class FragmentProfile : Fragment(R.layout.fragment_profile) {
    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
        if (acct != null) {
            binding.userName.text = acct.displayName
            binding.userEmail.text = acct.email
            Glide.with(binding.root)
                .load(acct.photoUrl)
                .into(binding.userImg)
        }
    }
}