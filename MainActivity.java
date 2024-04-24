package com.example.calc;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageView imageView;
    Handler handler;
    boolean isNew = true;
    String oldNumber;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        handler = new Handler();
    }

    public void clickNumber(View view) {

        if(isNew){
            editText.setText("");
        }
        isNew = false;

        String number = editText.getText().toString();
        int viewId = view.getId();

        if (viewId == R.id.bu1) {
            number = number + "1";
        } else if (viewId == R.id.bu2) {
            number = number + "2";
        } else if (viewId == R.id.bu3) {
            number = number + "3";
        } else if (viewId == R.id.bu4) {
            number = number + "4";
        } else if (viewId == R.id.bu5) {
            number = number + "5";
        } else if (viewId == R.id.bu6) {
            number = number + "6";
        } else if (viewId == R.id.bu7) {
            number = number + "7";
        } else if (viewId == R.id.bu8) {
            number = number + "8";
        } else if (viewId == R.id.bu9) {
            number = number + "9";
        } else if (viewId == R.id.bu0) {
            if(!(zeroIsFirst(number) && number.length() == 1)){
                number = number + "0";
            }else{
                number = "0";
            }
        } else if (viewId == R.id.buDot) {
            if(!(dotExist(number))){
                number = number + ".";
            }
        } else if (viewId == R.id.buPlusMinus) {
            if(!(numberIsZero(number))){
                if(plusMinusExist(number)){
                    number = number.substring(1);
                }else{
                    number = "-" + number;
                }
            }else{
                number = "0";
            }
        }

        editText.setText(number);
    }

    public boolean zeroIsFirst(String number) {
        return (number.equals("") || number.charAt(0) == '0');
    }

    public boolean numberIsZero(String number){
        if(number.equals("0") || number.equals("")){
            return true;
        }else{
            return false;
        }
    }

    public void operation(View view){
        isNew = true;
        oldNumber = editText.getText().toString();
        int viewId = view.getId();

        if (viewId == R.id.buMinus) {
            operator = "-";
        } else if (viewId == R.id.buPlus) {
            operator = "+";
        } else if (viewId == R.id.buDiv) {
            operator = "/";
        } else if (viewId == R.id.buMultiple1) {
            operator = "*";
        }
    }

    public void ACclick(View view){
        editText.setText("0");

        isNew = true;
    }

    public boolean dotExist(String number){

        if(number.indexOf(".") == -1 ){
            return false;
        }else{
            return true;
        }
    }

    public boolean plusMinusExist(String number){
        if (number.charAt(0) == '-') {
            return true;
        }else{
            return false;
        }
    }

    public void clickPercent(View view){
        String number = editText.getText().toString();
        double result = 0.0;

        if (operator.isEmpty()) {
            double temp = Double.parseDouble(number) / 100;
            editText.setText(String.valueOf(temp));
        } else {
            String newNumber = editText.getText().toString();
            if (operator.equals("+")) {
                result = Double.parseDouble(oldNumber) + (Double.parseDouble(newNumber) * Double.parseDouble(oldNumber) / 100);
            } else if (operator.equals("-")) {
                result = Double.parseDouble(oldNumber) - (Double.parseDouble(newNumber) * Double.parseDouble(oldNumber) / 100);
            } else if (operator.equals("/")) {
                result = Double.parseDouble(oldNumber) / (Double.parseDouble(newNumber) * Double.parseDouble(oldNumber) / 100);
            } else if (operator.equals("*")) {
                result = Double.parseDouble(oldNumber) * (Double.parseDouble(newNumber) / 100);
            }
            editText.setText(String.valueOf(result));
        }
        operator = "";
    }

    public void clickEqual(View view) {
        String newNumber = editText.getText().toString();
        double result = 0.0;

        if (!newNumber.equals("")) {
            if (operator != null) {
                if (operator.equals("-")) {
                    result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                } else if (operator.equals("+")) {
                    result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                } else if (operator.equals("/")) {
                    result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                } else if (operator.equals("*")) {
                    result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                }
            }
        }
        if (!hasDecimalPart(result) && result < 50) {
            result = (int) result;
            editText.setText(String.valueOf(result));
            imageView.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setVisibility(View.INVISIBLE);
                }
            }, 1500);
        }else{
            editText.setText(String.valueOf(result));
        }

    }

    public static boolean hasDecimalPart(double result) {
        return result % 1 != 0;
    }

}
