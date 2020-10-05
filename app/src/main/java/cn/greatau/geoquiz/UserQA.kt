package cn.greatau.geoquiz

/*
    question：试卷的一个题目（包含问题和答案）。
    userInput：用户回答的内容。
    userResult: 用户回答是对还是错。如果错误，则errorTimes++，如果正确，则userScore++
    questionPoint: 每个题目的分数，默认为1分。
    userScore:用户试卷分数，默认为0分。
    errorTimes:该题目用户回答错误次数，默认为0次。
 */
data class UserQA(  val question: Question,
                    var userInput: UserInput = UserInput.NOANSWER,
                    var userResult:Boolean = false,
                    val questionPoint: Int = 1,
                    private var userScore:Int = 0,
                    private var errorTimes:Int = 0) {

    //var userinput用户输入值。缺省是用户没有回答问题
    var ur = false                  //用户回答是否正确。如果缺省用户没有回答问题，缺省回答错误。
    private var _userResult = UserResult()   //缺省显示“Answer"

    constructor(qu:Question,ui: Boolean) : this(qu) {
        if(ui) {
            userInput = UserInput.TRUE
        } else {
            userInput = UserInput.FALSE
        }
    }

    private fun addUserScore() {
        userScore += questionPoint
    }

    fun getUserScore():Int {
        return userScore
    }

    private fun addErrorTimes() {
        errorTimes ++
    }

    fun getQuestionText() :String {
        return question.getQuestionText()
    }

    fun getQuestionAnswer(): Boolean {
        return question.getQuestionAnswer()
    }


    fun getQuestionResult() : String {
        return _userResult.getUserResult()
    }

    private fun setQuestionResult(ui: Boolean) {
        _userResult.setUserResultText(ui)
    }


    //根据用户的输入和答案进行比较，告诉用户的答案是对还是错。
    fun checkAnswer(userinput: Boolean):Boolean {
        val result: Boolean
        userInput = when (userinput) {
            true -> UserInput.TRUE
            false -> UserInput.FALSE
        }
        result = ( question.answer == userinput )
        userResult = result     //根据用户回答设置题目用户结果是正确还是错误。
        setQuestionResult(userResult)

        //if (result && (userResult == false)) {
        if (result) {
            addUserScore()
        } else {
            addErrorTimes()
        }

        return result
    }



    fun showAnswer():String {
        return getQuestionResult()
    }

}