package com.Validator;

import com.jfinal.core.Controller;

import com.jfinal.validate.Validator;

public class KMValidator extends Validator {

    //在校验失败时才会调用
    @Override
    protected void handleError(Controller controller) {
        controller.keepPara("stu");//将提交的值再传回页面以便保持原先输入的值
        controller.render("/add.html");
    }

    @Override
    protected void validate(Controller controller) {
        //验证表单域name，返回信息key,返回信息value
        validateRequiredString("stu", "stu",
                "请输入学号!");
    }

}