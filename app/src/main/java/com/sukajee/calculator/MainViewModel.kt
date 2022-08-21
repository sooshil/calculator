package com.sukajee.calculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"

    var result = MutableLiveData("")
    var currentExpression = MutableLiveData("")
    var thisNumber = ""
    var cursorPosition = MutableLiveData(0)
    var isAddedInTheMiddle = false

    private fun cClicked() {
        //result.postValue("")
        thisNumber = ""
        currentExpression.postValue("")
        isAddedInTheMiddle = false
    }

    private fun bracketsClicked() {
//        currentExpression.value?.let {
//            if(it.isNotEmpty()) {
//                if(it.contains('('))
//            }
//        }
    }

    private fun percentClicked() {
        updateCurrentExpression("%")
        thisNumber = ""
//        currentExpression.postValue("${currentExpression.value}%")
    }

    private fun divideClicked() {
        updateCurrentExpression("/")
        thisNumber = ""
//        currentExpression.postValue("${currentExpression.value}/")
    }

    private fun multiplyClicked() {
        updateCurrentExpression("x")
        thisNumber = ""
//        currentExpression.postValue("${currentExpression.value}x")
    }

    private fun minusClicked() {
        updateCurrentExpression("-")
        thisNumber = ""
//        currentExpression.postValue("${currentExpression.value}-")
    }

    private fun plusClicked() {
        updateCurrentExpression("+")
        thisNumber = ""
//        currentExpression.postValue("${currentExpression.value}+")
    }

    private fun equalClicked() {
//        currentExpression.postValue(result.value.toString())
//        result.postValue("")
    }

    private fun plusMinusClicked() {

    }

    private fun decimalClicked() {
        if (thisNumber.isBlank()) {
            thisNumber = "0."
            currentExpression.postValue("0.")
            return
        }
        if (canPlaceDecimal()) {
            updateCurrentExpression(".")
//            thisNumber = "$thisNumber."
//            currentExpression.postValue("${currentExpression.value}.")
        }
    }

    private fun numberClicked(num: Int) {
        if (thisNumber == "0" && num == 0) return
        updateCurrentExpression(num.toString())
//        thisNumber = "$thisNumber$num"
//        currentExpression.postValue("${currentExpression.value}$num")

    }

    private fun canPlaceDecimal() = thisNumber.contains('.').not()

    private fun evaluate() {
        val result1 = ""
        result.postValue(result1)
    }

    fun buttonClicked(buttons: Buttons) {
        when (buttons) {
            is Buttons.ButtonBrackets -> bracketsClicked()
            is Buttons.ButtonC -> cClicked()
            is Buttons.ButtonDecimal -> decimalClicked()
            is Buttons.ButtonDivide -> divideClicked()
            is Buttons.ButtonEqual -> equalClicked()
            is Buttons.ButtonMinus -> minusClicked()
            is Buttons.ButtonMultiply -> multiplyClicked()
            is Buttons.ButtonPercent -> percentClicked()
            is Buttons.ButtonPlus -> plusClicked()
            is Buttons.ButtonPlusMinus -> plusMinusClicked()
            is Buttons.NumberButton -> numberClicked(buttons.num)
        }
    }

    private fun updateCurrentExpression(pressedKey: String) {
        val index = cursorPosition.value
        val text = currentExpression.value ?: ""

        if (isAddedInTheMiddle.not()) {
            thisNumber = "$thisNumber$pressedKey"
            currentExpression.postValue("${currentExpression.value}$pressedKey")
        } else {
            if (text.length > 1) {
                val firstPart = text.substring(0, index ?: text.length)
                val lastPart = text.substring(index?: text.length)
                Log.d(TAG, "firstPart is: $firstPart and lastPart is $lastPart")
                currentExpression.postValue("$firstPart$pressedKey$lastPart")
                cursorPosition.postValue(cursorPosition.value?.plus(1))
            }
        }
    }
}