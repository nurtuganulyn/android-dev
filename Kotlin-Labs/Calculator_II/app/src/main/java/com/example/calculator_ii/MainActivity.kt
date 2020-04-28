package com.example.calculator_ii

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.tanh

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

    private lateinit var buttonCube: Button
    private lateinit var buttonInv: Button
    private lateinit var buttonSin: Button
    private lateinit var buttonCos: Button
    private lateinit var btnDelChar: Button
    private lateinit var buttonSqr: Button
    private lateinit var buttonSqrt: Button
    private lateinit var buttonChangeSign: Button
    private lateinit var buttonFactorial: Button
    private lateinit var buttonTan: Button

    private lateinit var inputNumber: TextView

    private var number1 = 0.0
    private var number2 = 0.0
    private var number3 = 0.0
    private var result = 0.0
    private var IsDotexits = true
    private var RepeatedOp = false
    private var negativeSign = false


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

        buttonSqrt = findViewById(R.id.btnSqrt)
        buttonChangeSign = findViewById(R.id.btnChangeSign)
        btnDelChar = findViewById(R.id.btnDelChar)
        buttonSqr = findViewById(R.id.btnSqr)
        buttonCube = findViewById(R.id.btnCube)
        buttonInv = findViewById(R.id.btnInv)
        buttonSin = findViewById(R.id.btnSin)
        buttonCos = findViewById(R.id.btnCos)
        buttonFactorial = findViewById(R.id.btnFactorial)
        buttonTan = findViewById(R.id.btnTan)


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

        buttonSqrt.setOnClickListener(this)
        buttonChangeSign.setOnClickListener(this)
        btnDelChar.setOnClickListener(this)
        buttonSqr.setOnClickListener(this)
        buttonCube.setOnClickListener(this)
        buttonInv.setOnClickListener(this)
        buttonSin.setOnClickListener(this)
        buttonCos.setOnClickListener(this)
        buttonTan.setOnClickListener(this)
        buttonFactorial.setOnClickListener(this)

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
        if (v!!.id == R.id.btnSqrt) {
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            if (number1 < 0) {
                return
            } else {
                result = Math.sqrt(number1)
                inputNumber.text = result.toString()
            }
        }
        if (v!!.id == R.id.btnChangeSign) {
            val currentText = inputNumber.text.toString()
            if (currentText == "") {
                return
            }
            number2 = inputNumber.text.toString().toDouble()
            negativeSign = number2 <= 0
            if (sign == Sign.Minus || sign == Sign.Div || sign == Sign.Plus || sign == Sign.Mul) {
                number2 = inputNumber.text.toString().toDouble()
                number2 *= -1
                inputNumber.text = number2.toString()
            }
            number1 = inputNumber.text.toString().toDouble()
            number1 *= -1
            inputNumber.text = number1.toString()
        }
        if (v!!.id == R.id.btnDelChar) {
            IsDotexits = true
            if (inputNumber.text.length > 15) {
                inputNumber.text = ""
            } else {
                val currentText = inputNumber.text.toString()
                if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                    return
                } else if (inputNumber.text.length == 1) {
                    inputNumber.text = ""
                } else {
                    removeLastChar(currentText)
                }
            }
        }
        if (v!!.id == R.id.btnSqr) {
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            result = number1 * number1
            inputNumber.text = result.toString()
        }
        if (v!!.id == R.id.btnFactorial) {
            IsDotexits = true
            var fact = 1
            val currentText = inputNumber.text.toString()
            if (currentText == "") {
                return
            }
            if (currentText == "Infinity" || currentText == "-Infinity") return
            number1 = inputNumber.text.toString().toDouble()
            if (number1 < 0) {
                number1 *= -1
                var i = 1
                while (i <= number1) {
                    fact *= i
                    i++
                }
                result = fact * (-1).toDouble()
            } else {
                var i = 1
                while (i <= number1) {
                    fact *= i
                    i++
                }
                result = fact.toDouble()
            }

            inputNumber.text = result.toString()
        }
        if (v!!.id == R.id.btnSin) {
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "Infinity" || currentText == "-Infinity") return
            if (currentText == "") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            number1 %= 360
            val radians = Math.toRadians(number1)
            result = sin(radians)
            inputNumber.text = result.toString()
        }
        if (v!!.id == R.id.btnCos) {
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "Infinity" || currentText == "-Infinity") return
            if (currentText == "") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            number1 %= 360
            val radians = Math.toRadians(number1)
            result = cos(radians)
            inputNumber.text = result.toString()
        }
        if (v!!.id == R.id.btnTan) {
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "Infinity" || currentText == "-Infinity") return
            if (currentText == "") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            number1 %= 360
            val radians = Math.toRadians(number1)
            result = tan(radians)
            inputNumber.text = result.toString()
        }
        if (v!!.id == R.id.btnCube) {
            IsDotexits = true
            val currentText = inputNumber.text.toString()
            if (currentText == "") {
                return
            }
            number1 = inputNumber.text.toString().toDouble()
            result = number1 * number1 * number1
            inputNumber.text = result.toString()
        }
        if (v!!.id == R.id.btnEqual) {
            IsDotexits = true

            val currentText = inputNumber.text.toString()
            if (currentText == "" || currentText == "Infinity" || currentText == "-Infinity") {
                return
            } else {
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
    }

    private fun removeLastChar(value: String) {
        inputNumber.text = value.substring(0, value.length - 1)
    }
}
