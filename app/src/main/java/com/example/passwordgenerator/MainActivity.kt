package com.example.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    var numOfChar:Int = 0

    var uppFlg = 0 //大文字
    var lowFlg = 0 //小文字
    var numFlg = 0 //数字
    var symFlg = 0 //記号

    var listIndex = 0

    val passwordList : MutableList<String> = ArrayList()
    val usableList :MutableList<String> = ArrayList()
    var usableString : String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickGenerateButton = findViewById<Button>(R.id.generateButton)
        val generateListener = GenerateListener()

        clickGenerateButton.setOnClickListener(generateListener)

        val toSubButton = findViewById<TextView>(R.id.confirm)
        val toSubListener = ToSubActivityTrans()
        toSubButton.setOnClickListener(toSubListener)

        val saveButton = findViewById<Button>(R.id.saveButton)

    }

    private inner class GenerateListener : View.OnClickListener {
        override fun onClick(view: View) {

            usableList.removeAll(usableList)

            val input = findViewById<EditText>(R.id.numOfChar) //文字数
            val indicatePassword = findViewById<TextView>(R.id.resGene)
            val inputStr = input.text.toString()
            numOfChar = inputStr.toInt()

            val uppCheck : CheckBox = findViewById(R.id.checkBox_upp)
            val lowCheck : CheckBox = findViewById(R.id.checkBox_low)
            val numCheck : CheckBox = findViewById(R.id.checkBox_num)
            val symCheck : CheckBox = findViewById(R.id.checkBox_sym)

            if(uppCheck.isChecked) {
                uppFlg++
            }
            if(lowCheck.isChecked){
                lowFlg++
            }
            if(numCheck.isChecked){
                numFlg++
            }
            if(symCheck.isChecked){
                symFlg++
            }

            val generatePassword = GeneratePassword()
            passwordList.addAll(generatePassword.generatePassword(uppFlg, lowFlg, numFlg, symFlg))

            listIndex = passwordList.size - 1

            for(i in 1..numOfChar){
                passwordList.shuffle()
                val random = (0..listIndex).random()
                usableList.add(passwordList[random])
            }

            usableString = usableList.joinToString("")
            indicatePassword.text = usableString
        }
    }

    private inner class ToSubActivityTrans: View.OnClickListener{
        override  fun onClick(view: View){
            val toSubIntent = Intent(applicationContext, SubActivity::class.java)
            startActivity(toSubIntent)
        }
    }

    private inner class SaveListener : View.OnClickListener{
        override fun onClick(view: View) {


        }
    }



}