package com.example.databindingexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.databindingexample.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.apply {
            show() }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initViews()
        return binding.root
    }

    fun initViews() {
        binding.coroutine.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_coroutineDemo)
        }
        binding.databinding.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_dataBinding2)
        }
        binding.roomBtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_roomFragment)
        }
        binding.retrofitBtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_postsFragment)
        }
        binding.btnRadio.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_radioButtons)
        }
        binding.motionLayout.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_motionLayout)
        }
    }
}