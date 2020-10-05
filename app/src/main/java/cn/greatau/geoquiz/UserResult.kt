package cn.greatau.geoquiz

/*

 */
data class UserResult(var userinput: UserInput = UserInput.NOANSWER) {

    private var _userResult: String = "Answer"

    fun getUserResult(): String
    {
        return _userResult
    }

    fun setUserResultText(ua:Boolean): String
    {
        when(ua) {
            true -> _userResult = "You are correct!"
            false -> _userResult = "You are incorrect!"
        }
        return _userResult
    }


}