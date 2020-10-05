package cn.greatau.geoquiz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel: ViewModel() {

    var currentIndex = 0
    init {
        Log.d(TAG, "ViewModel instance created")
        this.currentIndex = 0
    }

    private val questionBank = listOf (
        Question(R.string.question_afriac,false),
        Question(R.string.question_asia,true),
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true) )


    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var userQAs = MutableLiveData<MutableList<UserQA>>()
        //mutableListOf<UserQA>()

    var isUserAnswer: Boolean = false


    fun checkAnswer(userAnswer: Boolean): Boolean {
        Log.d(TAG, "checkAnswer($userAnswer)")
        updateUserQA(userAnswer)

//      Log.d(TAG, "userQAs:${userQAs.get(currentIndex).answer}")
        return userQAs.value!!.last().getUserResult()
    }

    fun updateUserQA(userAnswer: Boolean) {

        userQAs.value?.last()?.isUserAnswer = true
        userQAs.value?.last()?.useranswer = userAnswer

    }
    fun addUserQA(index: Int,userAnswer: Boolean = false, isUserAnswer: Boolean) {
        val newQA = UserQA(questionBank[index],userAnswer,isUserAnswer)

        userQAs.value?.add(newQA)
        //userQAs.postValue(userQAs.value.add(newQA))
    }

    fun getMsg(userAnswer: Boolean): Int {
        if (userAnswer == currentQuestionAnswer) {
            return R.string.correct_msg
        } else {
            return R.string.incorrect_msg
        }
    }
    fun showAllWrongUserQA() {

    }
    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        addUserQA(index =currentIndex,isUserAnswer= false)
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex + questionBank.size - 1) % questionBank.size
        addUserQA(index =currentIndex,isUserAnswer= false)
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ViewModel instance about to be destroyed")
    }
}