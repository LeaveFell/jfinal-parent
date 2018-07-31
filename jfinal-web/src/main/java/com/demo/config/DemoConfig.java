package com.demo.config;

import com.controller.KMController;
import com.controller.StudentController;
import com.demo.model.KeMu;
import com.demo.model.Student;
import com.jfinal.config.*;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;


public class DemoConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("jdbc_config.properties");
        me.setDevMode(true);
    }

//    public void configPlugin(Plugins me) {
////        // 非第一次使用use加载的配置，需要通过每次使用use来指定配置文件名再来取值
////        String redisHost = PropKit.use("redis_config.txt").get("host");
////        int redisPort = PropKit.use("redis_config.txt").getInt("port");
////        RedisPlugin rp = new RedisPlugin("myRedis", redisHost, redisPort);
////        me.add(rp);
//
//
//    }

    public static void main(String[] args) {
        JFinal.start("src/main", 80, "/",5);
    }


    public void configRoute(Routes me) {
        me.add("/student", StudentController.class);
        me.add("/kemu", KMController.class);
    }

    public void configEngine(Engine me) {
//        me.addSharedFunction("/view/common/layout.html");
//        me.addSharedFunction("/view/common/paginate.html");
//        me.addSharedFunction("/view/admin/common/layout.html");
    }

    public void configPlugin(Plugins me) {
        // 非第一次使用 use加载的配置，也可以先得到一个Prop对象，再通过该对象来获取值
        loadPropertyFile("jdbc_config.properties");
//        Prop p = PropKit.use("jdbc_config.properties");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("username"), PropKit.get("password"),PropKit.get("driver"));
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        arp.addMapping("student", "id", Student.class);
        arp.addMapping("kemu", "id", KeMu.class);//写数据库表的名字

//        此方法用来配置JFinal的Plugin，如下代码配置了Druid数据库连接池插件与ActiveRecord
//        数据库访问插件。通过以下的配置，可以在应用中使用 ActiveRecord 非常方便地操作数据库。
    }

    public void configInterceptor(Interceptors me) {
        //me.add(new AuthInterceptor());

//        此方法用来配置 JFinal 的全局拦截器，全局拦截器将拦截所有 action 请求，除非使用
//        @Clear 在 Controller 中清除，如下代码配置了名为 AuthInterceptor 的拦截器。
    }

    public void configHandler(Handlers me) {
    }

}