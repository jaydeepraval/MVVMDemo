package com.example.mvvmdemo.retrofitmvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.ActivityLoginBinding
import com.example.mvvmdemo.retrofitmvvm.api.ApiInterface
import com.example.mvvmdemo.retrofitmvvm.api.RetrofitInstance
import com.example.mvvmdemo.retrofitmvvm.repository.LoginRepository
import com.example.mvvmdemo.retrofitmvvm.repository.LoginState
import com.example.mvvmdemo.retrofitmvvm.viewmodels.LoginViewModel
import com.example.mvvmdemo.retrofitmvvm.viewmodels.LoginViewModelFactory
import com.mvvm.jetpacklogindemo.models.LoginRequest

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val service = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        val repository = LoginRepository(service)
        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(repository)
        ).get(LoginViewModel::class.java)

        binding.viewModels = loginViewModel
        binding.lifecycleOwner = this
        Log.e("Debug", "ViewModel: ${binding.viewModels}")
        loginViewModel.loginState.observe(this, Observer {
            when (it) {
                is LoginState.Success -> {
                    hideLoading()
                    Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
                }

                is LoginState.Error -> {
                    hideLoading()
                    showErrorDialog()
                }

                is LoginState.FIELDEMPTY -> {
                    hideLoading()
                    showErrorDialog()
                }

                LoginState.Loading -> showLoading()
            }
        })


    }

    private fun showErrorDialog() {

        val builder = AlertDialog.Builder(this)

        if (binding.mEDPhone.text.toString().isEmpty() && binding.mEDPassword.text.toString()
                .isEmpty()
        ) {
            builder.setTitle("Error")
            builder.setMessage("Please enter username and password.")
        } else {
            builder.setTitle("Error")
            builder.setMessage("Something went wrong. Please try again.")
        }

        builder.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showLoading() {
        binding.mEDPhone.visibility = View.GONE
        binding.mEDPassword.visibility = View.GONE
        binding.pgBar.visibility = View.VISIBLE
        binding.mButtonLogin.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.mEDPhone.visibility = View.VISIBLE
        binding.mEDPassword.visibility = View.VISIBLE
        binding.pgBar.visibility = View.GONE
        binding.mButtonLogin.visibility = View.VISIBLE
    }
}