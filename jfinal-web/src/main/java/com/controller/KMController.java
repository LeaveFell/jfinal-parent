package com.controller;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.Validator.KMValidator;
import com.demo.model.KeMu;
import com.demo.model.Student;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import com.service.KMService;
import com.service.StudentService;


public class KMController extends Controller {

    /**
     * 获取studentid那里有多种方法，这个要和前台传参写法一致，Controller 提供了 getPara 系列方法,官网api里很详细

     jfinal用的是原生态sql语句，简单，方便，setAttr("studentList", list);把结果集放到request范围里，

     jfinal也有直接获取表单里分装成对象的方法 getModel(Student.class);就是，和struts2一样，表单name对应上就可以了，非常方便

     添加那里对于oracle用序列维护studentid      student.set("studentid", "mysequence.nextval").save(); jfinal有多种返回方式，也可以返回json数据，render 系列方法,官网api里很详细
     */

    static KMService service = new KMService();
    static StudentService s = new StudentService();
    /*    @Before(StudentInterceptor.class)*/
    public void index() {
        List<KeMu> list = KeMu.dao.find("select * from kemu");
        setAttr("l", list);
        //注意下面路径的的前面如果带/则从根目录下开始找，也就是说 下代码 = render("/student/index.html");
        render("kemu.html");
    }

    public void list() {
        List<Student> list = Student.dao.find("select * from student");
        List<KeMu> l = KeMu.dao.find("select * from kemu k order by k.scope asc");
        List<Map<String,Object>> list22 = new ArrayList();
        List l2 = new ArrayList();
        for(Student s : list){
            Map m = new HashMap();
            m.put("chinese", 0);
            m.put("math", 0);
            m.put("english", 0);
            int a = 0;
            for(KeMu k : l){
                if(s.get("id")==k.get("num")){
                    if(k.get("km").equals("语文")){
                        a+=(Integer)k.get("scope");
                        m.put("chinese", k.get("scope"));
                    }else if(k.get("km").equals("数学")){
                        a+=(Integer)k.get("scope");
                        m.put("math", k.get("scope"));
                    }else if(k.get("km").equals("英语")){
                        a+=(Integer)k.get("scope");
                        m.put("english", k.get("scope"));
                    }
                }

            }
            m.put("scope", a);
            m.put("num", s.get("id"));
            list22.add(m);
        }
        Collections.sort(list22,new Comparator<Map<String,Object>>() {
            //升序排序
            public int compare(Map<String, Object> o1,
                               Map<String, Object> o2) {
                int map1value = (Integer)o1.get("scope");
                int map2value = (Integer)o2.get("scope");
                return map2value-map1value;
            }

        });
        setAttr("l",list22);
        render("ceshi.html");

    }

    public void add() {
        List<Student> list = Student.dao.find("select * from student");
        setAttr("l",list);
        render("add.html");
    }

    public void delete1(){
        KeMu.dao.deleteById(getParaToInt());
        forwardAction("/kemu");
    }
    public void get() {
        KeMu kemu = KeMu.dao.findById(getPara("id"));
        setAttr("kemu", kemu);
        render("index2.html");
    }
    public void get1() {
        KeMu kemu = KeMu.dao.findById(getParaToInt());
        setAttr("kemu", kemu);
        render("index2.html");
    }

    @Before(KMValidator.class)
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
        KeMu st=getModel(KeMu.class,"");
        st.save();
        redirect("/kemu");
    }


}