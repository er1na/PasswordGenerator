package com.example.passwordgenerator

class GeneratePassword {

    val passWordList : MutableList<String> = ArrayList()

    fun generatePassword(uppF: Int, lowF: Int, numF: Int, symF: Int) : MutableList<String>{

        val charList = UsableCharList()
        if(uppF == 1){
            passWordList.addAll(charList.uppList)
        }
        if(lowF == 1){
            passWordList.addAll(charList.lowList)
        }
        if(numF == 1){
            passWordList.addAll(charList.numList)
        }
        if(symF == 1){
            passWordList.addAll(charList.symList)
        }

        return passWordList
    }

}