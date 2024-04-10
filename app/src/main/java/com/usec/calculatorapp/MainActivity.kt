package com.usec.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvDisplayScreen: TextView? = null
    var isLastANumber :Boolean = false
    var isLastADecimal: Boolean = false

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDisplayScreen = findViewById(R.id.tvDisplayScreen)

    }

   fun onDigit(view: View) {
        tvDisplayScreen?.append((view as Button).text)
       isLastANumber = true
       isLastADecimal= false


   }

    fun onClear(view: View){
        tvDisplayScreen?.text = ""
    }

    fun onDecimal(view: View){
        if (isLastANumber && !isLastADecimal){
            tvDisplayScreen?.append(".")
            isLastANumber = false
            isLastADecimal= true
        }
    }

    fun onOperator(view: View){
        tvDisplayScreen?.text?.let {
            if (isLastANumber && !isOperatorAdded(it.toString())){
                tvDisplayScreen?.append((view as Button).text)
                isLastANumber= false
                isLastADecimal= false
            }
        }
    }

    fun onEqual(view: View){
        if (isLastANumber == true){
            var tvValue = tvDisplayScreen?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")){ //the prefix is "-"
                    prefix="-"    //here we acc set the prefix to "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValues = tvValue.split("-")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){  //if the prefix that we set as "-"
                        one = prefix + one
                    }
                    var resultMinus = (one.toDouble() - two.toDouble()).toString()
                    tvDisplayScreen?.text = resultMinus
                    one = prefix + one
                }
                else if (tvValue.contains("+")){
                    val splitValues = tvValue.split("+")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){  //if the prefix that we set as "-"
                        one = prefix + one
                    }
                    var resultAdd = (one.toDouble() + two.toDouble()).toString()
                    tvDisplayScreen?.text = resultAdd
                    one = prefix + one
                }
                else if (tvValue.contains("*")){
                    val splitValues = tvValue.split("*")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){  //if the prefix that we set as "-"
                        one = prefix + one
                    }
                    var resultMultiply = (one.toDouble() * two.toDouble()).toString()
                    tvDisplayScreen?.text = resultMultiply

                    one = prefix + one
                }
                else if (tvValue.contains("/")){
                    val splitValues = tvValue.split("/")

                    var one = splitValues[0]
                    var two = splitValues[1]

                    if (prefix.isNotEmpty()){  //if the prefix that we set as "-"
                        one = prefix + one
                    }
                    var resultDivide = (one.toDouble() / two.toDouble()).toString()
                    tvDisplayScreen?.text = resultDivide

                    one = prefix + one
                }

            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    private fun isOperatorAdded(value:String) : Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
                   // || value.contains("%")
        }
    }

    fun onPercentage(view: View){
        var tvValue = tvDisplayScreen?.text.toString()
        var prefix = ""
        // if (tvValue.contains("%")){
                   // val splitValues = tvValue.split("/")


                     var one = tvValue
                    //var two = splitValues[1]

                    if (prefix.isNotEmpty()){  //if the prefix that we set as "-"
                        one = prefix + one
                    }
                    var resultPercent = (one.toDouble() / 100).toString()
                    tvDisplayScreen?.text = resultPercent
                    one = prefix + one

        // } else {view}
   }
}