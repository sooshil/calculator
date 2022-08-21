package com.sukajee.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sukajee.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            editField.showSoftInputOnFocus = false
            editField.requestFocus()

            viewModel.currentExpression.observe(this@MainActivity) {
                editField.setText(it.toString())
                if (viewModel.isAddedInTheMiddle.not()) {
                    editField.setSelection(editField.length())
                } else {
                    Log.d(TAG, "cursorPosition: ${viewModel.cursorPosition.value}")
                    editField.setSelection(viewModel.cursorPosition.value?.plus(1) ?: editField.length())
                }
            }

            viewModel.result.observe(this@MainActivity) {
                textViewResult.text = it.toString()
            }

            editField.setOnClickListener {
                if (editField.selectionStart != editField.length()) {
                    viewModel.isAddedInTheMiddle = true
                    viewModel.cursorPosition.postValue((it as EditText).selectionStart)
                } else {
                    viewModel.isAddedInTheMiddle = false
                }
            }

            val buttons = listOf(
                buttonC,
                buttonBrackets,
                buttonPercent,
                buttonDivide,
                buttonMultiply,
                buttonMinus,
                buttonPlus,
                buttonEqual,
                buttonPlusMinus,
                buttonDecimal,
                buttonZero,
                buttonOne,
                buttonTwo,
                buttonThree,
                buttonFour,
                buttonFive,
                buttonSix,
                buttonSeven,
                buttonEight,
                buttonNine
            )

            for (button in buttons) {
                button.setOnClickListener {
                    handleButtonClick(it as Button)
                }
            }
        }
    }

    private fun handleButtonClick(button: Button) {
        with(viewModel) {
            when (button.id) {
                R.id.buttonC -> buttonClicked(Buttons.ButtonC)
                R.id.buttonBrackets -> buttonClicked(Buttons.ButtonBrackets)
                R.id.buttonPercent -> buttonClicked(Buttons.ButtonPercent)
                R.id.buttonDivide -> buttonClicked(Buttons.ButtonDivide)
                R.id.buttonMultiply -> buttonClicked(Buttons.ButtonMultiply)
                R.id.buttonMinus -> buttonClicked(Buttons.ButtonMinus)
                R.id.buttonPlus -> buttonClicked(Buttons.ButtonPlus)
                R.id.buttonEqual -> buttonClicked(Buttons.ButtonEqual)
                R.id.buttonPlusMinus -> buttonClicked(Buttons.ButtonPlusMinus)
                R.id.buttonDecimal -> buttonClicked(Buttons.ButtonDecimal)
                else -> buttonClicked(Buttons.NumberButton(getButton(button.id)))
            }
        }
    }

    private fun getButton(id: Int): Int {
        return when (id) {
            R.id.buttonZero -> 0
            R.id.buttonOne -> 1
            R.id.buttonTwo -> 2
            R.id.buttonThree -> 3
            R.id.buttonFour -> 4
            R.id.buttonFive -> 5
            R.id.buttonSix -> 6
            R.id.buttonSeven -> 7
            R.id.buttonEight -> 8
            else -> 9
        }
    }
}