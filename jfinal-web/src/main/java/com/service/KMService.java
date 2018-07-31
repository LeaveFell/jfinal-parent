package com.service;

import java.util.List;


import com.demo.model.KeMu;
import com.demo.model.Student;
import com.jfinal.plugin.activerecord.Page;




public class KMService {
    /**
     * 所有的 dao 对象也放在 Service 中
     */
    private static final KeMu dao = new KeMu();
    private static final Student dao1 = new Student();

    public Page<KeMu> paginate(int pageNumber, int pageSize) {
        return dao.paginate(pageNumber, pageSize, "select *", "from kemu order by id asc");
    }

    public Page<Student> paginate1(int pageNumber, int pageSize) {
        return dao1.paginate(pageNumber, pageSize, "select *", "from student order by id asc");
    }

    public KeMu findById(int id) {
        return dao.findById(id);
    }

    public void deleteById(int id) {
        dao.deleteById(id);

    }

    public List<Student> find1() {
        return dao1.find("select * from student order by id asc");
    }
    public List<KeMu> find() {
        return dao.find("select * from kemu order by id asc");
    }
}