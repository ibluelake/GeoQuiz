package cn.greatau.geoquiz

data class UserQA(val question: Question, var useranswer: Boolean,var isUserAnswer: Boolean = false) {

    //根据用户的输入和答案进行比较，告诉用户的答案是对还是错。
    private var userResult = false

    fun getUserResult():Boolean {
        if (isUserAnswer) {
            userResult = (question.answer == useranswer)
        }
        return userResult
    }

    init {
        index ++
    }

    fun getIndex() :Int
    {
        return index
    }

    fun checkAnswer(useranswer: Boolean) {

    }

    companion object {
        var index: Int = 0
    }
}