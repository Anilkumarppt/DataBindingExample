package com.example.databindingexample.radiobutton

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.databindingexample.R
import com.example.databindingexample.databinding.FragmentRadioButtonExBinding


class RadioButtonEx : Fragment() {
    private lateinit var mBinding:FragmentRadioButtonExBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_radio_button_ex,container,false)
        return mBinding.root
    }


}