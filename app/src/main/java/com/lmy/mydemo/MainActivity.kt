package com.lmy.mydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.lmy.mydemo.ui.fragment.AllDataFragment
import com.lmy.mydemo.ui.main.MainFragment
import com.lmy.mydemo.utils.LiveDataBus

class MainActivity : AppCompatActivity() {
    companion object{
        const val fragmentChange = "fragmentChange"
        const val listChange = "listChange"
    }
    lateinit var fragment:Fragment
    val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            fragment =  MainFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment::class.java.name)
                .replace(R.id.container,fragment)
                .commit()
        }

        LiveDataBus().get().with(listChange,String::class.java).observe(this){
            list.add(it)
            fragment =  AllDataFragment.newInstance(list)
            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment::class.java.name)
                .replace(R.id.container, fragment)
                .commit()
        }
    }

}