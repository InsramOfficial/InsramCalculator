package com.example.insramcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_c);
        assignId(buttonBrackOpen, R.id.button_open_bracket);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_Plus);
        assignId(buttonMinus, R.id.button_subtract);
        assignId(buttonEquals, R.id.button_equals);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonDot, R.id.button_dot);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(dataToCalculate.equals("0"))
        {
            dataToCalculate = "";
        }

        if (buttonText.equals("=")) {
            String result = getResult(dataToCalculate);
            resultTv.setText(result);
            return;
        }
        if (buttonText.equals("C")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solutionTv.setText(dataToCalculate);
    }

    // Function to perform the calculation
    String getResult(String data) {
        try {
            double result = evaluateExpression(data);
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    // Simple evaluation of the expression (no library used)
    double evaluateExpression(String expression) {
        // Split by operators and calculate the result
        char[] arr = expression.toCharArray();

        StringBuilder operand1 = new StringBuilder();
        StringBuilder operand2 = new StringBuilder();
        char operator = ' ';
        boolean foundOperator = false;

        for (char c : arr) {
            if (c >= '0' && c <= '9' || c == '.') {
                if (!foundOperator) {
                    operand1.append(c);
                } else {
                    operand2.append(c);
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                operator = c;
                foundOperator = true;
            }
        }

        double num1 = Double.parseDouble(operand1.toString());
        double num2 = Double.parseDouble(operand2.toString());

        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Divide by Zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
