package cn.greatau.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private val quizViewModel by viewModels<QuizViewModel>()
    /*val quizViewModel:QuizViewModel by lazy {
        ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(QuizViewModel::class.java)
    }
    */
    //Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0)?: 0
        quizViewModel.currentIndex = currentIndex

        //user select TRUE button, then check the user answer is right or wrong.
        true_button.setOnClickListener{
            showAnswer(true)
        }

        //user select FALSE button, then check the user answer is right or wrong.
        false_button.setOnClickListener{
            showAnswer(false)
        }

        /*
            show next question.
         */
        next_button.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }
        /*
            show previous question.
         */
        previous_button.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        quizViewModel.userQAs.observe(this) {it ->
            var vmstring =
            question_answer.text = (it.last().isUserAnswer) ? "You are corr" :"Answer"

        }

        /* show the first question when app start up
            if user has input the answer, we donot call updateQuestion() to clear the question_answer
         */
        //if (!quizViewModel.isUserAnswer) {
            updateQuestion()
        //}

    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        question_text.setText(questionTextResId)
        question_answer.setText(R.string.question_answer)
    }

    private fun showAnswer(userAnswer: Boolean) {
        if (quizViewModel.checkAnswer(userAnswer) ) {
            question_answer.setText(quizViewModel.getMsg(userAnswer))  //if user answer is right,
        } else {
            question_answer.setText(quizViewModel.getMsg(userAnswer)) //if user answer is wrong
        }
    }
    /*
        show answer with Toast
     */
    /*
    private fun showAnswer(userAnswer: Boolean) {

        val messageResId = if (quizViewModel.checkAnswer(userAnswer)) {
            R.string.correct_msg
        } else {
            R.string.incorrect_msg
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
    }
     */

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

}