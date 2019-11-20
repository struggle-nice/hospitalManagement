package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserDao extends CrudRepository<User,Integer> {
    User getById(@Param("id") Integer id);
}
