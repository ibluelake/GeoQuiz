package cn.greatau.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean) {

    /*
        check the user answer is correct or incorrect.
     */
    public fun checkAnswer(userInput: Boolean): Boolean
    {
        return userInput == answer
    }

    fun getQuestionText() :String {
        return MyApplication.context.getString(textResId)
    }

    fun getQuestionAnswer() : Boolean {
        return answer
    }
}