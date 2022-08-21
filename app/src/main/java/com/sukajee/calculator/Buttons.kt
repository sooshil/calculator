package com.sukajee.calculator

sealed class Buttons {
    object ButtonC : Buttons()
    object ButtonBrackets : Buttons()
    object ButtonPercent : Buttons()
    object ButtonDivide : Buttons()
    object ButtonMultiply : Buttons()
    object ButtonMinus : Buttons()
    object ButtonPlus : Buttons()
    object ButtonEqual : Buttons()
    object ButtonPlusMinus : Buttons()
    object ButtonDecimal : Buttons()
    data class NumberButton(val num: Int) : Buttons()
}
