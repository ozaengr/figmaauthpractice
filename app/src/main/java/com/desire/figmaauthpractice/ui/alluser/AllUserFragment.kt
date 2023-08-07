package com.desire.figmaauthpractice.ui.alluser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.desire.figmaauthpractice.database.User
import com.desire.figmaauthpractice.database.UserAdapter
import com.desire.figmaauthpractice.databinding.FragmentAllUserBinding


class AllUserFragment : Fragment() {
    private lateinit var binding: FragmentAllUserBinding
    lateinit var userAdapter: UserAdapter
    private lateinit var userViewModel: AllUserViewModel
    var userListOfData = arrayListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllUserBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(this)[AllUserViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        back()
    }

    private fun back() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        userViewModel.getUsers().observe(viewLifecycleOwner){
            if (it != null) {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                userListOfData.addAll(it)
                userAdapter = UserAdapter(userListOfData)
                binding.rcvAllUsers.adapter = userAdapter
                userAdapter.clickListener(object : UserAdapter.OnClickListener{
                    override fun onDeleteClick(index: Int, user: User) {
                        Log.i("test","${index}")
                        removeItem(index,user)
                    }
                })

            } else {
                Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun removeItem(index: Int, user: User) {
        userViewModel.deleteUser(user).observe(viewLifecycleOwner){
            if (it){
                userAdapter.deleteItem(index,user)
            }
        }
    }
}