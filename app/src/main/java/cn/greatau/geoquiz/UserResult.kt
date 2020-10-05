package cn.greatau.geoquiz

/*

 */
data class UserResult(var userinput: UserInput = UserInput.NOANSWER) {

    private var userresult: String = "Answer"

    fun getUserResult(): String
    {
        return userresult
    }

    fun setUserResultText(ua:Boolean): String
    {
        when(ua) {
            true -> userresult = "You are correct!"
            false -> userresult = "You are incorrect!"
        }
        return userresult
    }


}