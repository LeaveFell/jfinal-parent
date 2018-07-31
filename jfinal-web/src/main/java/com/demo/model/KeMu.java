package com.demo.model;

import com.jfinal.plugin.activerecord.Model;

public class KeMu extends Model<KeMu> {
    public static final KeMu dao = new KeMu();


    /**
     *  ActiveRecord 是 jfinal 最核心的组成部分之一，通过 ActiveRecord 来操作数据库，将极大地减少代码量，极大地提升开发效率,配置在后面，我这里用的是Model，Model 是 ActiveRecord 中最重要的组件之一，它充当 MVC 模式中的 Model部分。
     以上代码中的 User 通过继承 Model，便立即拥有的众多方便的操作数据库的方法。在 User 中声明的 dao 静态对象是为了方便查询操作而定义的，该对象并不是必须的。 基于ActiveRecord 的 Model 无需定义属性， 无需定义 getter、 setter方法，无需 XML 配置，无需 Annotation 配置，极大降低了代码量。Model常见方法见官方API。

     JFinal还有 独创 Db + Record 模式，Db 类及其配套的 Record 类， 提供了在 Model 类之外更为丰富的数据库操作功能。使用 Db 与 Record 类时，无需对数据库表进行映射，Record 相当于一个通用的 Model。Db常见方法见官方API。
     */

}