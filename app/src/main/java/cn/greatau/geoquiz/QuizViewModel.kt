package cn.greatau.geoquiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel(): ViewModel() {
    private val questionBank = listOf (
        Question(R.string.question_africa,false),
        Question(R.string.question_asia,true),
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true) )

    var user_score  = MutableLiveData<Int>()

    var userqa = MutableLiveData<UserQA>()

    init {
        user_score.value = 0
        val question = Question(R.string.question_africa,false)

        userqa.value = UserQA(question,UserInput.NOANSWER)
    }

    fun  checkAnswer(userinput:Boolean):Boolean {
        updateQA(userinput)
        return userqa.value!!.question.checkAnswer(userinput)
    }
    fun showAnswer():String {
        return userqa.value!!.getQuestionResult()
    }

    fun showScore():String {
        var score = MyApplication.context.getString(R.string.your_score)
        score = score + user_score.value.toString()
        return score
    }

    fun updateQA(userinput: Boolean) {
        userqa.value!!.setQuestionResult(userinput)
        if (userqa.value!!.checkAnswer(userinput)) {
            user_score.value = user_score.value!! + 1
        }
    }

    fun moveToNext() {

    }

    fun moveToPrevious() {

    }





}