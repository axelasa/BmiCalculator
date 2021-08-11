package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import kotlin.text.*

class BmiCalculator : AppCompatActivity() {

    var weightValue: Double? = null
    var heightValue: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        resultView.visibility = View.INVISIBLE

        chart.visibility = View.INVISIBLE

        compute.setOnClickListener {

            when{
                !validate()-> return@setOnClickListener
                else-> {
                    weightValue = weight.text.toString().toDouble()
                    heightValue = height.text.toString().toDouble()

                    Log.i("msg", ">>>>weight is : ${weight}, height is : ${height}")

                    val bmi: Double = (weightValue!!) / (heightValue!! * heightValue!!)

                    Log.e("BMI>>>", bmi.toString())

                    resultView.visibility = View.VISIBLE

                    chart.visibility = View.VISIBLE

                    val result= "Your BMI is : " + "${String.format("%.2f", bmi)}"

                    //, you are ${bmiRes(bmi)}. i used this line to also display the results... eg, your BMI is 28,you are overweight.

                    info.text = result
                    Log.i(">>>>"+"BMI Result", bmiRes(bmi))

                }
            }

        }
    }

    private fun bmiRes(bmi: Double): String {

        if (weightValue == null || height == null) {
            validate()
        } else {
            Log.e(
                "### BMI ", ">>>>>>>Weight : $weightValue,   >>>>>Height :  $heightValue")
        }

        var ans = ""

        if (bmi < 18.5) {
            ans = "Underweight"
        } else if (bmi > 18.5 && bmi < 24.9) {
            ans = " healthy "
        } else if (bmi > 24 && bmi < 29.9) {
            ans = "overweight"
        } else {
            ans = "Obese"
        }
        return ans
    }

    private fun validate(): Boolean {

        //this is a function to do the null check on the edit texts

        var valid = true// okay go on

        val weightValue = weight.text.toString()
        val heightValue = height.text.toString()

        if (TextUtils.isEmpty(weightValue)){
          //  Toast.makeText(this,"Please Enter Your Weight in Kgs",Toast.LENGTH_LONG).show() Try now and send a screenshot
            valid = false
            weight.error = "Please Enter Your Weight  in Kgs"
        }

        if(TextUtils.isEmpty(heightValue)){
           // Toast.makeText(this,"Please Enter Your Height in Meters",Toast.LENGTH_LONG).show()
            valid = false
            height.error = "Please Enter your Height in Metres"

        }
        return valid

    }
}
