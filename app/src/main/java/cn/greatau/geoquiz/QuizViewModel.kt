package cn.greatau.geoquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel(): ViewModel() {
    private val questionBank = listOf (
        Question(R.string.question_africa,false),
        Question(R.string.question_asia,true),
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true) )


    private val _user_score = MutableLiveData<Int>(0)
    val userscoreLiveData: LiveData<Int> get() = _user_score
    fun addUserScore(n:Int) {
        _user_score.value = _user_score.value?.plus(n)
    }

    val userquiz = MutableLiveData<MutableList<UserQA>>()
       // mutableListOf<MutableLiveData<UserQA>>()

    var currentIndex = 0    //用户答题的序号

    init {
        //val question = Question(R.string.question_africa,false)

        //userqa.value = UserQA(question)
        initQuiz()
    }

    fun initQuiz() {
        userquiz.apply {
            var ml = mutableListOf<UserQA>()
            for (question in questionBank) {
                var userqa = UserQA(question)
                ml.add(userqa)
            }
            this.value = ml
        }
    }

    fun getFirstQuestion(): UserQA? {
        return userquiz.value?.first()
    }

    fun getCurrentQuestion(): UserQA {
        return  userquiz.value!!.get(currentIndex)
    }

    fun  checkAnswer(userinput:Boolean):Boolean {
        var result: Boolean
        var point: Int
        //如果用户已经回答并计算了答案，则直接返回，不再判断答案正确
        if (userquiz.value!![currentIndex].userInput != UserInput.NOANSWER) {
            return userquiz.value!![currentIndex].userResult
        }

        result = userquiz.value!![currentIndex].checkAnswer(userinput)
        addUserScore(userquiz.value!![currentIndex].getUserScore())
        //updateQA(userinput)
        return userquiz.value!![currentIndex].userResult
    }
//    fun showAnswer():String {
//        return userqa.value!!.getQuestionResult()
//    }

    fun showScore():String {
        var score:String
        score = MyApplication.context.getString(R.string.your_score) + userscoreLiveData.value
        //score += "$userscoreLiveData"

        return score
    }

    fun moveToNext() {
        if (currentIndex < userquiz.value!!.size - 1) {
            currentIndex ++
        }
    }

    fun moveToPrevious() {
        if (currentIndex > 0) {
            currentIndex --
        }
    }





}