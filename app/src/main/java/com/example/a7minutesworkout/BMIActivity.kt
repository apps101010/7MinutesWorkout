 package com.example.a7minutesworkout

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding
import com.example.a7minutesworkout.databinding.DialogCustomBackConfirmationBinding
import java.math.BigDecimal
import java.math.RoundingMode

 class BMIActivity : AppCompatActivity() {

     companion object {
         private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
         private const val US_UNITS_VIEW = "US_UNIT_VIEW"
     }

    private var binding: ActivityBmiactivityBinding?=null

     private var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            showCustomDialog()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }


    }

     private fun displayBMIResult(bmi: Float){

         val bmiLabel: String
         val bmiDescription: String

         if (bmi.compareTo(15f) <= 0){
             bmiLabel = "very severely underweight"
             bmiDescription = "Ooops! You really need to take better care of yourself! Eat more"
         }else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
             bmiLabel = "Severely underweight"
             bmiDescription = "Ooops! You really need to take better care of yourself! Eat more"
         }else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
             bmiLabel = "Underweight"
             bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
         }else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
             bmiLabel = "Normal"
             bmiDescription = "Congratualtions! You are in a good shape!"
         }else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
             bmiLabel = "Overweight"
             bmiDescription = "Oops! You really need to take better care of yourself! Workout"
         }else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0){
             bmiLabel = "Obese Class | (Moderately obese)"
             bmiDescription = "Oops! You really need to take better care of yourself! Workout"
         }else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0){
             bmiLabel = "Obese Class || (Severely obese)"
             bmiDescription = "OMG! You are in a very dangerous condition! Act Now!"
         }else {
             bmiLabel = "Obese Class ||| (Very Severely obese)"
             bmiDescription = "OMG! You are in a very dangerous condition! Act Now!"
         }

         val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

         binding?.llDisplayBMIResult?.visibility = View.VISIBLE
         binding?.tvBMIValue?.text = bmiValue
         binding?.tvBMIType?.text = bmiLabel
         binding?.tvBMIDescription?.text = bmiDescription

     }

     private fun showCustomDialog(){
         val customDialog = Dialog(this)
         val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
         customDialog.setContentView(dialogBinding.root)
         customDialog.setCanceledOnTouchOutside(false)
         dialogBinding.btnYes.setOnClickListener {
             this@BMIActivity.finish()
             customDialog.dismiss()
         }

         dialogBinding.btnNo.setOnClickListener {
             customDialog.dismiss()
         }

         customDialog.show()

     }

     private fun validateMetricsUnites(): Boolean{
         var isValid = true
         if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
             isValid = false
         }else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()){
             isValid = false
         }

         return isValid
     }

     private fun validateUsMetricsUnites(): Boolean{
         var isValid = true
         if (binding?.etMetricUnitWeightInPounds?.text.toString().isEmpty()){
             isValid = false
         }else if (binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
             isValid = false
         }else if (binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
             isValid = false
         }

         return isValid
     }

     private fun makeVisibleMetricUnitsView(){
         currentVisibleView = METRIC_UNITS_VIEW
         binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
         binding?.llUsUnits?.visibility = View.GONE
         binding?.etMetricUnitHeight?.visibility = View.VISIBLE
         binding?.etMetricUnitWeight?.visibility = View.VISIBLE


         binding?.etMetricUnitHeight?.text!!.clear()
         binding?.etMetricUnitWeight?.text!!.clear()
         binding?.etMetricUnitWeightInPounds?.text!!.clear()
         binding?.etUsMetricUnitHeightFeet?.text!!.clear()
         binding?.etUsMetricUnitHeightInch?.text!!.clear()
     }

     private fun makeVisibleUsUnitsView(){
         currentVisibleView = US_UNITS_VIEW
         binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
         binding?.llUsUnits?.visibility = View.VISIBLE
         binding?.etMetricUnitHeight?.visibility = View.GONE
         binding?.etMetricUnitWeight?.visibility = View.GONE

         binding?.etMetricUnitHeight?.text!!.clear()
         binding?.etMetricUnitWeight?.text!!.clear()
         binding?.etMetricUnitWeightInPounds?.text!!.clear()
         binding?.etUsMetricUnitHeightFeet?.text!!.clear()
         binding?.etUsMetricUnitHeightInch?.text!!.clear()
     }

     private fun calculateUnits(){
         if (currentVisibleView == METRIC_UNITS_VIEW){
             if (validateMetricsUnites()){
                 val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                 val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                 val bmi = weightValue / (heightValue*heightValue)
                 displayBMIResult(bmi)
             }else{
                 Toast.makeText(this,"Please enter valid values",Toast.LENGTH_SHORT).show()
             }
         }else{
             if (validateUsMetricsUnites()){
                 val usUnitHeightValueFeet: String = binding?.etUsMetricUnitHeightFeet?.text.toString()
                 val usUnitHeightValueInch: String = binding?.etUsMetricUnitHeightInch?.text.toString()
                 val usUnitWeightValuePounds: Float = binding?.etMetricUnitWeightInPounds?.text.toString().toFloat()
                 val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12
                 val bmi = 703 * (usUnitWeightValuePounds / (heightValue * heightValue))
                 displayBMIResult(bmi)
             }else{
                 Toast.makeText(this,"Please enter valid values",Toast.LENGTH_SHORT).show()
             }
         }
     }
}