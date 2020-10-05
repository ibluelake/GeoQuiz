package cn.greatau.geoquiz

/*
  用户的回答有三种：
  (1)没有回答
  (2)true 用户的回答和答案一致，用户的回答是正确的
  (3)false 用户的回答和答案不一致，用户的回答是错误的
*/
enum class UserInput {
    NOANSWER,
    TRUE,
    FALSE
}