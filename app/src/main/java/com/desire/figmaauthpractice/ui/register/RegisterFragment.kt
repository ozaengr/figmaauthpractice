package com.desire.figmaauthpractice.ui.register


import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.desire.figmaauthpractice.database.LoginDataClass
import com.desire.figmaauthpractice.databinding.FragmentRegisterBinding
import com.desire.figmaauthpractice.home.HomeActivity

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        onClickLoginBtn()
    }

    private fun initView() {
        binding.btnRegister.setOnClickListener {
            if (binding.textInputName.text.toString().isEmpty() ||
                binding.textInputEmail.text.toString().isEmpty() ||
                binding.textInputPassword.text.toString().isEmpty() ||
                binding.textInputPasswordConfirm.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isValidEmail(binding.textInputEmail.text.toString())) {
                Toast.makeText(requireContext(), "Pelase enter Valid Email ID", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isValidPassword(binding.textInputPassword.text.toString())) {
                Toast.makeText(
                    requireContext(),
                    "Please enter Valid Password Pattern",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (binding.textInputPassword.text.toString() != binding.textInputPasswordConfirm.text.toString()) {
                Toast.makeText(requireContext(), "Confirm Password again", Toast.LENGTH_SHORT)
                    .show()
            } else if (binding.checkboxTerms.isChecked != true) {
                Toast.makeText(
                    requireContext(), "Please accept Terms of Use and Privacy Policy",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT)
                    .show()
                val loginDataClass = LoginDataClass()
                loginDataClass.name = binding.textInputName.text.toString()
                loginDataClass.emailId = binding.textInputEmail.text.toString()
                loginDataClass.password = binding.textInputPassword.text.toString()
                registerViewModel.addUser(loginDataClass)
                findNavController().navigateUp()
            }
        }
    }


    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.find(password) != null
    }

    private fun onClickLoginBtn() {
        val titleIntro = "Already have an account? Log in"
        val spannableString = SpannableString(titleIntro)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigateUp()
            }
        }
        spannableString.setSpan(clickableSpan, 25, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvLoginBack.text = spannableString
        binding.tvLoginBack.setText(spannableString, TextView.BufferType.SPANNABLE)
        binding.tvLoginBack.movementMethod = LinkMovementMethod.getInstance()
    }

}
