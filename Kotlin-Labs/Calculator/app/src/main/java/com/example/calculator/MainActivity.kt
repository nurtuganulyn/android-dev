package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonNumber1: Button
    private lateinit var buttonNumber2: Button
    private lateinit var buttonNumber3: Button
    private lateinit var buttonNumber4: Button
    private lateinit var buttonNumber5: Button
    private lateinit var buttonNumber6: Button
    private lateinit var buttonNumber7: Button
    private lateinit var buttonNumber8: Button
    private lateinit var buttonNumber9: Button
    private lateinit var buttonNumber0: Button


    private lateinit var buttonMulti: Button
    private lateinit var buttonDot: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonDel: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonEqual: Button

    private lateinit var inputNumber: TextView

    private var number1 = 0.0
    private var number2 = 0.0
    private var number3 = 0.0
    private var result = 0.0
    private var IsDotexits = true
    private var RepeatedOp = false
    private val negativeSign = false


    internal enum class Sign {
        Plus, Minus, Mul, Div
    }

    private lateinit var sign: Sign

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        inputNumber.text = "";

        buttonNumber1 = findViewById(R.id.btn1)
        buttonNumber2 = findViewById(R.id.btn2)
        buttonNumber3 = findViewById(R.id.btn3)
        buttonNumber4 = findViewById(R.id.btn4)
        buttonNumber5 = findViewById(R.id.btn5)
        buttonNumber6 = findViewById(R.id.btn6)
        buttonNumber7 = findViewById(R.id.btn7)
        buttonNumber8 = findViewById(R.id.btn8)
        buttonNumber9 = findViewById(R.id.btn9)
        buttonNumber0 = findViewById(R.id.btn0)

        buttonPlus = findViewById(R.id.btnPlus)
        buttonEqual = findViewById(R.id.btnEqual)
        buttonMinus = findViewById(R.id.btnSubstr)
        buttonMulti = findViewById(R.id.btnMulti)
        buttonDivide = findViewById(R.id.btnDiv)
        buttonDel = findViewById(R.id.btnDel)
        buttonDot = findViewById(R.id.btnDot)

        buttonNumber1.setOnClickListener(this)
        buttonNumber2.setOnClickListener(this)
        buttonNumber3.setOnClickListener(this)
        buttonNumber4.setOnClickListener(this)
        buttonNumber5.setOnClickListener(this)
        buttonNumber6.setOnClickListener(this)
        buttonNumber7.setOnClickListener(this)
        buttonNumber8.setOnClickListener(this)
        buttonNumber9.setOnClickListener(this)
        buttonNumber0.setOnClickListener(this)

        buttonPlus.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
        buttonMulti.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
        buttonDel.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btn1) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "1";
            }
            else {
                inputNumber.append("1");
            }
        }
        if (v!!.id == R.id.btn2) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "2";
            }
            else {
                inputNumber.append("2");
            }
        }
        if (v!!.id == R.id.btn3) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "3";
            }
            else {
                inputNumber.append("3");
            }
        }
        if (v!!.id == R.id.btn4) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "4";
            }
            else {
                inputNumber.append("4");
            }
        }
        if (v!!.id == R.id.btn5) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "5";
            }
            else {
                inputNumber.append("5");
            }
        }
        if (v!!.id == R.id.btn6) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "6";
            }
            else {
                inputNumber.append("6");
            }
        }
        if (v!!.id == R.id.btn7) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "7";
            }
            else {
                inputNumber.append("7");
            }
        }
        if (v!!.id == R.id.btn8) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "8";
            }
            else {
                inputNumber.append("8");
            }
        }
        if (v!!.id == R.id.btn9) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity")) {
                inputNumber.text = "9";
            }
            else {
                inputNumber.append("9");
            }
        }
        if (v!!.id == R.id.btn0) {
            var currentText = inputNumber.text.toString()
            if (currentText.equals("Infinity") || currentText.equals("-Infinity") || currentText.equals("0")) {
                inputNumber.text = "0";
            }
            else {
                inputNumber.append("0");
            }
        }

        if (v!!.id == R.id.btnDel) {
            IsDotexits = true
            inputNumber.text = ""
        }

        if (v!!.id == R.id.btnDot) {
            val currentText = inputNumber.text.toString()
            val chararr = currentText.toCharArray()
            for (i in 0 until currentText.length) {
                if (chararr[i] == '.') {
                    IsDotexits = false
                }
            }
            if (currentText == "Infinity" || currentText == "-Infinity") {
                return
            } else
                if (currentText == "") {
                    inputNumber.text = "0."
                } else if (IsDotexits) {
                    inputNumber.text = "$currentText."
                }
            }
        if (v!!.id == R.id.btnPlus) {
            RepeatedOp = false
            IsDotexits = true

            val currentText = inputNumber.text.toString()
            if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            inputNumber.text = ""
            sign = Sign.Plus
        }
        if (v!!.id == R.id.btnSubstr) {
            RepeatedOp = false
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            inputNumber.text = ""
            sign = Sign.Minus
        }
        if (v!!.id == R.id.btnMulti) {
            RepeatedOp = false
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            inputNumber.text = ""
            sign = Sign.Mul
        }
        if (v!!.id == R.id.btnDiv) {
            RepeatedOp = false
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            inputNumber.text = ""
            sign = Sign.Div
        }
        if (v!!.id == R.id.btnEqual) {
            IsDotexits = true

            val currentText = inputNumber.text.toString()
            if (currentText == "") {
                return
            }
            if (!RepeatedOp) {
                number3 = inputNumber.text.toString().toDouble()
            }
            number2 = inputNumber.text.toString().toDouble()

            if (sign == Sign.Plus && !RepeatedOp) {
                result = number1 + number2
            } else if (sign == Sign.Plus && RepeatedOp) {
                result = number2 + number3
            }
            if (sign == Sign.Minus && !RepeatedOp) {
                result = number1 - number2
            } else if (sign == Sign.Minus && RepeatedOp) {
                result = number2 - number3
            }
            if (sign == Sign.Mul && !RepeatedOp) {
                result = if (number1 == 0.0 || number2 == 0.0) {
                    0.0
                } else {
                    number1 * number2
                }
            } else if (sign == Sign.Mul && RepeatedOp) {
                result = number2 * number3
            }
            if (sign == Sign.Div) {
                if (number2 == 0.0) {
                    return
                } else if (sign == Sign.Div && !RepeatedOp) {
                    result = number1 / number2
                } else if (sign == Sign.Div && RepeatedOp == true) {
                    result = number2 / number3
                }
            }

            inputNumber.text = result.toString()
            RepeatedOp = true
        }
    }

    private fun removeLastChar(value: String) {
        inputNumber.text = value.substring(0, value.length - 1)
    }
}
