package com.example.vks.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vks.calculatorapp.databinding.ActivityMainBinding;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final List<String> ARITHMETIC_CHARACTERS = Arrays.asList("+", "-", "*", "/");

    private String prevOperator = "";

    private ActivityMainBinding binding;

    private Double valueOne = Double.NaN;
    private Double valueTwo = Double.NaN;

    private Button clear, parenthesis, percent, addition, subtraction, multiplication, division, equals, decimal, posneg;
    private Button zero, one, two, three, four, five, six, seven, eight, nine;
    private TextView textView;
    private EditText editText;

    private char CURRENT_ACTION;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        clear = (Button) findViewById(R.id.buttonClear);
        parenthesis = (Button) findViewById(R.id.buttonParenthesis);
        percent = (Button) findViewById(R.id.buttonPercent);
        addition = (Button) findViewById(R.id.buttonAdd);
        subtraction = (Button) findViewById(R.id.buttonSubtract);
        multiplication = (Button) findViewById(R.id.buttonMultiplication);
        division = (Button) findViewById(R.id.buttonDivision);
        equals = (Button) findViewById(R.id.buttonEqual);
        decimal = (Button) findViewById(R.id.buttonDecimalPoint);
        posneg = (Button) findViewById(R.id.buttonPositiveNegative);

        textView = (TextView) findViewById(R.id.infoTextView);
        editText = (EditText) findViewById(R.id.editText);

        zero = (Button) findViewById(R.id.buttonZero);
        one = (Button) findViewById(R.id.buttonOne);
        two = (Button) findViewById(R.id.buttonTwo);
        three = (Button) findViewById(R.id.buttonThree);
        four = (Button) findViewById(R.id.buttonFour);
        five = (Button) findViewById(R.id.buttonFive);
        six = (Button) findViewById(R.id.buttonSix);
        seven = (Button) findViewById(R.id.buttonSeven);
        eight = (Button) findViewById(R.id.buttonEight);
        nine = (Button) findViewById(R.id.buttonNine);


        clear.setOnClickListener(this);
        parenthesis.setOnClickListener(this);
        percent.setOnClickListener(this);
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        equals.setOnClickListener(this);
        decimal.setOnClickListener(this);
        posneg.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Button b = (Button) view;
        String s = b.getText().toString();

        if (s.equalsIgnoreCase("C"))
        {
            editText.setText("");
            textView.setText("");

            valueOne = Double.NaN;
            valueTwo = Double.NaN;
            prevOperator = "";
        }
        if (NumberUtils.isNumber(s))
        {
            editText.setText((editText.getText().toString() + b.getText()), TextView.BufferType.EDITABLE);
            Toast.makeText(this, "Clicked on " + b.getText(), Toast.LENGTH_SHORT).show();
        }
        else if (ARITHMETIC_CHARACTERS.contains(s) || s.equalsIgnoreCase("=")) // + , - , * , / OR =
        {
            try
            {
                // Parse the EditText to a Double.
                valueTwo = Double.valueOf(editText.getText().toString());
            } catch (Exception e)
            {
                Toast.makeText(this, "Unknown String encounter, cannot format: " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                return;
            }

            // Compute the expression and store into valueone
            valueOne = compute(valueOne, valueTwo, prevOperator);

            if (!valueOne.isNaN())
            {
                textView.setText(textView.getText() + editText.getText().toString() + s);
            }

            // Reset the ValueTwo
            valueTwo = Double.NaN;

            // Clear the Edit Text View
            editText.setText(valueOne.toString());

            if (s.equalsIgnoreCase("="))
            {
                textView.setText(valueOne.toString());

            }

            prevOperator = s;
        }

    }

    /**
     * Method:  Evaluates the expression between valueOne and
     * valueTwo using the operator.
     *
     * @param valueOne
     * @param valueTwo
     * @param operator
     */
    private Double compute(Double valueOne, Double valueTwo, String operator)
    {
        // If ValueOne is NaN return
        // valueTwo as the answer.
        if (valueOne.isNaN() || operator.isEmpty())
        {
            return valueTwo;
        }

        try
        {
            if (operator.equalsIgnoreCase("+"))
            {
                return valueOne + valueTwo;
            }
            else if (operator.equalsIgnoreCase("-"))
            {
                return valueOne - valueTwo;
            }
            else if (operator.equalsIgnoreCase("*"))
            {
                return valueOne * valueTwo;
            }
            else if (operator.equalsIgnoreCase("/"))
            {
                return valueOne / valueTwo;
            }
        } catch (Exception e)
        {
            Toast.makeText(this, "Exception occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            textView.setText("");
        }

        // If case reached this far then return NaN
        // acts as resetting the expression
        return Double.NaN;
    }
}
