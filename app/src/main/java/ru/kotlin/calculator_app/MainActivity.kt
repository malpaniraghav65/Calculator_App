package ru.kotlin.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView? = null
     var lastNumeric:Boolean = false
     var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.Text1)

    }
    fun OnDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onClear(view:View){
        tvInput?.text=""
    }
    fun OnDecimalPoint(view:View){
        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
}
    fun OnOperator(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
                }
        }
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
         try {
             if (tvValue.startsWith('-')){
                 prefix="-"
                 tvValue = tvValue.substring(1)
             }
             if(tvValue.contains('-')){
             val splitedvalue = tvValue.split("-")
             var num1 = splitedvalue[0]
                 var num2 = splitedvalue[1]
             if (prefix.isNotEmpty()){
                 num1 = prefix + num1
                 }
                 var result = num1.toDouble()- num2.toDouble()
                 tvInput?.text = result.toString()}
             if(tvValue.contains('+')){
                 val splitedvalue = tvValue.split("+")
                 var num1 = splitedvalue[0]
                 var num2 = splitedvalue[1]
                 if (prefix.isNotEmpty()){
                     num1 = prefix + num1
                 }
                 var result = num1.toDouble()+ num2.toDouble()
                 tvInput?.text = result.toString()}
             if(tvValue.contains('*')){
                 val splitedvalue = tvValue.split("*")
                 var num1 = splitedvalue[0]
                 var num2 = splitedvalue[1]
                 if (prefix.isNotEmpty()){
                     num1 = prefix + num1
                 }
                 var result = num1.toDouble()* num2.toDouble()
                 tvInput?.text = result.toString()}
             if(tvValue.contains('/')){
                 val splitedvalue = tvValue.split("/")
                 var num1 = splitedvalue[0]
                 var num2 = splitedvalue[1]
                 if (prefix.isNotEmpty()){
                     num1 = prefix + num1
                 }
                 var result = num1.toDouble()/ num2.toDouble()
                 tvInput?.text = result.toString()}
         }   catch (e: ArithmeticException){
             e.printStackTrace()
         }
        }
    }
    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){false}
            else{
                          value.contains("-")
                        ||value.contains("+")
                        ||value.contains("*")
                        ||value.contains("/")

            }
    }
}