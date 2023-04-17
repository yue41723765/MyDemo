package com.lmy.mydemo.ui.main

import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lmy.mydemo.MainActivity.Companion.listChange
import com.lmy.mydemo.utils.LiveDataBus
import java.text.DecimalFormat


class MainViewModel : ViewModel() {
    companion object{
        const val TIME_TYPE = 1
        const val AMOUNT_TYPE=2

    }
    val time = ObservableField("");
    val amount = ObservableField("")
    val edTime = ObservableField("")
    val edAmount =ObservableField("")
    fun onSure(){
        if(edTime.get().isNullOrEmpty()){
            return
        }
        if(edAmount.get().isNullOrEmpty()){
            return
        }
        LiveDataBus().get().with(listChange).postValue("${edTime.get()!!.toLong()*edAmount.get()!!.toLong()}")
    }
    fun onEdTextChange(ed: Editable,type:Int){

        try {
            when(type){
                TIME_TYPE->{
                  val b =  judgeNumber(ed)
                    if(b){
                        time.set(cal(ed.toString().toInt()))
                    }else{
                      edTime.set("")
                    }

                }
                AMOUNT_TYPE->{
                    val b = judgeNumber(ed)
                    if(b){
                        amount.set(addCommaDots(ed.toString()))
                    }else{
                        edAmount.set("")
                    }

                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    fun cal(second: Int): String {
        var h = 0
        var d = 0
        var s = 0
        val temp = second % 3600
        if (second > 3600) {
            h = second / 3600
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60
                    if (temp % 60 != 0) {
                        s = temp % 60
                    }
                } else {
                    s = temp
                }
            }
        } else {
            d = second / 60
            if (second % 60 != 0) {
                s = second % 60
            }
        }
        return h.toString() + "h" + d + "m" + s + "s"
    }
    fun addCommaDots(str: String): String {
        val myFormat = DecimalFormat()
        myFormat.applyPattern(",##0.00")
        return myFormat.format(str.toDouble())
    }

    /**
     * 金额输入框中的内容限制（最大：小数点后2位）
     *
     * @param edt
     */
    fun judgeNumber(edt: Editable):Boolean {
        val temp = edt.toString()
        val len = temp.length
        //若0或.开头就设置不显示，必须是大于0的正整数开头
        return !(len >= 1 && temp.startsWith("0") || temp.startsWith("."))

    }
}