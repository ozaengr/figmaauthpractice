package com.desire.figmaauthpractice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.desire.figmaauthpractice.databinding.FragmentTermsAndPolicyBinding

class TermsAndPolicyFragment : Fragment() {

    private lateinit var binding: FragmentTermsAndPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsAndPolicyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivBack()


    }

    private fun ivBack() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()

        }
    }

}