package cn.greatau.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean) {

    /*
        check the user answer is correct or incorrect.
     */
    public fun checkAnswer(userAnswer: Boolean): Boolean
    {
        return userAnswer == answer
    }
}