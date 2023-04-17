package com.lmy.mydemo.ui.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.lmy.mydemo.BR
import com.lmy.mydemo.R
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class AllDataViewModel : ViewModel() {
    val recycler = ObservableArrayList<ItemViewModel>()
    val adapter = BindingRecyclerViewAdapter<ItemViewModel>()
    val recyclerBinding = ItemBinding.of<ItemViewModel>(
        BR.viewModel,
        R.layout.item_data_layout
    )
    fun initData(list:ArrayList<String>){
        for(i in list){
            val itemViewModel = ItemViewModel(i)
            recycler.add(itemViewModel)
        }

    }
}