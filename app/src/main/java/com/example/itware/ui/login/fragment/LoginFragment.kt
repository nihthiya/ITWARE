package com.example.itware.ui.login.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.itware.R
import com.example.itware.databinding.FragmentLoginBinding
import com.example.itware.ui.login.viewModel.UserLoginViewModel
import com.example.itware.ui.movies.activity.MoviesActivity
import com.example.itware.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: UserLoginViewModel by viewModels()
    private lateinit var userLoginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return userLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        userLoginBinding.lifecycleOwner = this
        userLoginBinding.loginViewModel = loginViewModel

        userLoginBinding.buttonLogin.setOnClickListener {
            userLoginBinding.progressBar.visibility = VISIBLE
            validateLogin()
        }

        loginViewModel.loginData.observe(this.viewLifecycleOwner) {
            if (it != null && it.isSuccess!!){
                Utils.showToast(requireContext(),"Login Success")
                userLoginBinding.progressBar.visibility = GONE
                activity?.finish()
                val intent = Intent(requireContext(), MoviesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("userName",userLoginBinding.etLoginEmail.text.toString())
                startActivity(intent)
            } else {
                userLoginBinding.progressBar.visibility = GONE
                Utils.showToast(requireContext(),"Invalid Email or Password")
            }
        }
    }

    private fun validateLogin() {
        when {
            userLoginBinding.etLoginEmail.text!!.isEmpty() -> {
                userLoginBinding.progressBar.visibility = GONE
                Utils.showToast(requireContext(),"Enter Valid Email Id")
            }
            userLoginBinding.etLoginPassword.text!!.isEmpty() -> {
                userLoginBinding.progressBar.visibility = GONE
                Utils.showToast(requireContext(),"Invalid Password")
            }
            else -> {
                loginViewModel.postUserDetails(requireContext(),userLoginBinding.etLoginEmail.text.toString(),userLoginBinding.etLoginPassword.text.toString())
            }
        }
    }
}