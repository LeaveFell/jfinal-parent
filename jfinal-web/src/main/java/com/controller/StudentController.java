package com.controller;

import java.util.List;


import com.Validator.StudentValidator;
import com.demo.model.Student;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import com.service.StudentService;

public class StudentController extends Controller {

    /**
     * 获取studentid那里有多种方法，这个要和前台传参写法一致，Controller 提供了 getPara 系列方法,官网api里很详细

     jfinal用的是原生态sql语句，简单，方便，setAttr("studentList", list);把结果集放到request范围里，

     jfinal也有直接获取表单里分装成对象的方法 getModel(Student.class);就是，和struts2一样，表单name对应上就可以了，非常方便

     添加那里对于oracle用序列维护studentid      student.set("studentid", "mysequence.nextval").save(); jfinal有多种返回方式，也可以返回json数据，render 系列方法,官网api里很详细
     */

    static StudentService service = new StudentService();

    /*    @Before(StudentInterceptor.class)*/
    public void index() {
        List<Student> list = Student.dao.find("select * from student");
        setAttr("list", list);
        //注意下面路径的的前面如果带/则从根目录下开始找，也就是说 下代码 = render("/student/index.html");
        render("student.html");
    }

    public void add() {
        render("add.html");
    }

    public void delete() {
        // 获取表单域名为studentid的值
        Student.dao.deleteById(getPara("id"));
        forwardAction("/student");
    }
    public void delete1(){
        Student.dao.deleteById(getParaToInt());
        forwardAction("/student");
    }
    public void update() {
        Student student = getModel(Student.class);
        student.update();
        forwardAction("/student");
    }

    public void get() {
        Student student = Student.dao.findById(getPara("id"));
        setAttr("student", student);
        render("index2.html");
    }
    public void get1() {
        Student student = Student.dao.findById(getParaToInt());
        setAttr("student", student);
        render("index2.html");
    }

    @Before(StudentValidator.class)
    public void save() {
        /**
         * getModel用来接收页面表单域传递过来的model对象，表单域名称以”modelName.attrName”
         http://www.jfinal.com
         方式命名，getModel 使用的 attrName 必须与数据表字段名完全一样。
         getBean 方法用于支持传统 Java Bean，包括支持使用 jfnal 生成器生成了 getter、setter 方法
         的 Model，页面表单传参时使用与 setter 方法相一致的 attrName，而非数据表字段名。
         getModel与getBean区别在于前者使用数表字段名而后者使用与setter方法一致的属性名进
         行数据注入。建议优先使用 getBean 方法。
         */

        getModel(Student.class).save();
        redirect("/student");
    }


}