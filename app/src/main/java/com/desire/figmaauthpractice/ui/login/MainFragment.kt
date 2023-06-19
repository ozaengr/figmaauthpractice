package com.desire.figmaauthpractice.ui.login


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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.desire.figmaauthpractice.databinding.FragmentMainBinding
import com.desire.figmaauthpractice.home.HomeActivity


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        openSignupButton()
        onClickTerms()
        onClickForgotPass()
    }


    private fun openSignupButton() {
        var titleIntro = "Don’t have an account? Register"
        var spannableString = SpannableString(titleIntro)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(
                    MainFragmentDirections.actionNavigationMainToNavigationRegister()
                )
            }
        }

        spannableString.setSpan(clickableSpan, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvRegister.text = spannableString
        binding.tvRegister.setText(spannableString, TextView.BufferType.SPANNABLE)
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.find(password) != null
    }

    private fun initView() {
        binding.btnLogIn.setOnClickListener {
            if (binding.textInputEmail.text.toString()
                    .isEmpty() && binding.textInputPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "All Fields are Mandatory", Toast.LENGTH_LONG)
                    .show()
            } else if (!isValidEmail(binding.textInputEmail.text.toString())) {
                Toast.makeText(
                    requireContext(),
                    "Please enter valid email address",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!isValidPassword(binding.textInputPassword.text.toString())) {
                Toast.makeText(requireContext(), "Please enter valid password", Toast.LENGTH_LONG)
                    .show()
            } else {

                loginViewModel.getUser(
                    binding.textInputEmail.text.toString(),
                    binding.textInputPassword.text.toString()
                ).observe(
                    viewLifecycleOwner
                ) {
                    if (it != null) {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                        var intent =
                            android.content.Intent(requireContext(), HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun onClickForgotPass() {

        var titleIntro = "Forgot Password?"
        var spannableString = SpannableString(titleIntro)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                findNavController().navigate(
                    MainFragmentDirections.actionNavigationMainToNavigationForgot()
                )
            }
        }

        spannableString.setSpan(clickableSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvForgotPass.text = spannableString
        binding.tvForgotPass.setText(spannableString, TextView.BufferType.SPANNABLE)
        binding.tvForgotPass.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun onClickTerms() {

        var titleIntro = "By logging, you are agreeing with our Terms of Use and Privacy Policy"
        var spannableString = SpannableString(titleIntro)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                findNavController().navigate(
                    MainFragmentDirections.actionNavigationMainToNavigationTerms()
                )
            }
        }

        val clickableSpan2: ClickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                findNavController().navigate(
                    MainFragmentDirections.actionNavigationMainToNavigationTerms()
                )
            }
        }

        spannableString.setSpan(clickableSpan, 38, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan2, 55, 69, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTerms.text = spannableString
        binding.tvTerms.setText(spannableString, TextView.BufferType.SPANNABLE)
        binding.tvTerms.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}