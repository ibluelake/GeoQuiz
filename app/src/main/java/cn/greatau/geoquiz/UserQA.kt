package cn.greatau.geoquiz

data class UserQA(val question: Question,
                  var userinput: UserInput = UserInput.NOANSWER) {

    //var userinput用户输入值。缺省是用户没有回答问题
    var ur = false                  //用户回答是否正确。如果缺省用户没有回答问题，缺省回答错误。
    var userresult = UserResult()   //缺省显示“Answer"

    constructor(qu:Question,ui: Boolean) : this(qu) {
        if(ui) {
            userinput = UserInput.TRUE
        } else {
            userinput = UserInput.FALSE
        }
    }

    fun getQuestionText() :String {
        return question.getQuestionText()
    }

    fun getQuestionAnswer(): Boolean {
        return question.getQuestionAnswer()
    }


    fun getQuestionResult() : String {
        return userresult.getUserResult()
    }

    fun setQuestionResult(ui: Boolean) : UserResult {
        userresult.setUserResultText(question.checkAnswer(ui))
        return userresult
    }


    //根据用户的输入和答案进行比较，告诉用户的答案是对还是错。
    fun checkAnswer(userinput: Boolean):Boolean {
        return question.checkAnswer(userinput)
    }

}