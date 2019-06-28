package com.alex.sampleapp

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {


    @Test
    fun allFieldShouldBeEmptyAfterLaunch() {
        var activity = Robolectric.setupActivity(MainActivity::class.java)
        var op1 = activity.findViewById<EditText>(R.id.operator1).text.toString()
        var op2 = activity.findViewById<EditText>(R.id.operator2).text.toString()
        assertEquals("", op1)
        assertEquals("", op2)
        var result = activity.findViewById<TextView>(R.id.result)
        assertEquals(View.GONE, result.visibility)

    }

    @Test
    fun clickAddShouldGetResult() {
        var activity = Robolectric.setupActivity(MainActivity::class.java)
        var op1 = activity.findViewById<EditText>(R.id.operator1)
        var op2 = activity.findViewById<EditText>(R.id.operator2)
        op1.setText("1")
        op2.setText("2")

        var add = activity.findViewById<Button>(R.id.add)
        add.performClick()
        var result = activity.findViewById<TextView>(R.id.result).text.toString()
        assertEquals("Sum of 1 and 2 is 3", result)
    }

    @Test
    fun clickAddShouldGetErrorResultWhenInputIsError() {
        var activity = Robolectric.setupActivity(MainActivity::class.java)

        var op1 = activity.findViewById<EditText>(R.id.operator1)
        var op2 = activity.findViewById<EditText>(R.id.operator2)
        var add = activity.findViewById<Button>(R.id.add)
        var result = activity.findViewById<TextView>(R.id.result)

        op1.setText("ABC")
        op2.setText("1")
        add.performClick()
        assertEquals("Error OP1 is not a valid integer", result.text.toString())

        op2.setText("ABC")
        op1.setText("1")
        add.performClick()
        assertEquals("Error OP2 is not a valid integer", result.text.toString())


        op1.setText("ABC")
        op2.setText("ABC")
        add.performClick()
        assertEquals("Error OP1 is not a valid integer and OP2 is not a valid integer", result.text.toString())

        //Operator overflow
        op1.setText("1234567891012131314151617181920212223242526272829")
        op2.setText("1")
        add.performClick()
        assertEquals("Error OP1 is not a valid integer", result.text.toString())

        op2.setText("1234567891012131314151617181920212223242526272829")
        op1.setText("1")
        add.performClick()
        assertEquals("Error OP2 is not a valid integer", result.text.toString())

        //Result overflow
        op2.setText("2147483647") //0x7FFFFFFF
        op1.setText("1")
        add.performClick()
        assertEquals("Error overflow", result.text.toString())
    }

    @Test
    fun clickClearShouldClearEdit() {
        var activity = Robolectric.setupActivity(MainActivity::class.java)
        var op1 = activity.findViewById<EditText>(R.id.operator1)
        var op2 = activity.findViewById<EditText>(R.id.operator2)
        var clear = activity.findViewById<Button>(R.id.clear)
        var add = activity.findViewById<Button>(R.id.add)
        var result = activity.findViewById<TextView>(R.id.result)


        op1.setText("1")
        op2.setText("1")
        add.performClick()
        assertEquals("Sum of 1 and 1 is 2", result.text.toString())

        clear.performClick()
        assertEquals("", op1.text.toString())
        assertEquals("", op2.text.toString())
        assertEquals("", result.text.toString())
        assertEquals(View.GONE, result.visibility)

    }
}
