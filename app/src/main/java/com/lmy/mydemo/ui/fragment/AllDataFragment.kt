package com.lmy.mydemo.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lmy.mydemo.R
import com.lmy.mydemo.databinding.AllDataFragmentBinding
import com.lmy.mydemo.databinding.MainFragmentBinding
import com.lmy.mydemo.ui.main.MainViewModel

class AllDataFragment : Fragment() {

    companion object {
        private const val list = "list"
        fun newInstance(mList:List<String>) = AllDataFragment().apply {
            arguments= Bundle().apply {
                putStringArrayList(list,mList as ArrayList<String>)
            }
        }
    }

    private lateinit var viewModel: AllDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =  DataBindingUtil.inflate<AllDataFragmentBinding>(inflater,R.layout.all_data_fragment, container, false)
        viewModel = ViewModelProvider(this).get(AllDataViewModel::class.java)
        binding.viewModel = viewModel
        initData()
        return binding.root
    }


    fun initData(){
        val list = arguments?.getStringArrayList(list)
        list?.let { viewModel.initData(it) }
    }
}