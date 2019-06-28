package com.alex.sampleapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var result = findViewById<TextView>(R.id.result)
        var op1Edit = findViewById<EditText>(R.id.operator1)
        var op2Edit = findViewById<EditText>(R.id.operator2)
        var addBtn = findViewById<Button>(R.id.add)
        addBtn.setOnClickListener {
            var op1 = op1Edit.text.toString()
            var op2 = op2Edit.text.toString()
            var sb = StringBuilder()
            if (!Util.canBeConvertToInt(op1)) {
                sb.append("OP1 is not a valid integer")
            }



            if (!Util.canBeConvertToInt(op2)) {
                if (!sb.isEmpty()) {
                    sb.append(" and ")
                }
                sb.append("OP2 is not a valid integer")
            }

            if (sb.isEmpty()) {
                var sum = Integer.parseInt(op1) + Integer.parseInt(op2)
                if (sum < 0) {
                    result.text = "Error overflow"
                } else {
                    result.text = "Sum of " + op1 + " and " + op2 + " is " + sum.toString()
                }
                result.visibility = View.VISIBLE

            } else {
                result.text = "Error " + sb.toString()
                result.visibility = View.VISIBLE
            }
        }

        var clearBtn = findViewById<Button>(R.id.clear)
        clearBtn.setOnClickListener {
            result.text = ""
            result.visibility = View.GONE
            op1Edit.setText("")
            op2Edit.setText("")
        }
    }
}
