package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.Doctor;
import cn.com.scitc.bysj.model.Introduce;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Repository
public interface DoctorDao extends CrudRepository<Doctor, Integer> {
     Doctor findByUsername(String username);
     List<Doctor> findByDepartment(@Param("department") String department);
     @Transactional
     @Modifying
     @Query(value = "update doctor set nickname=?,age=?,gender=?,email=?,lastUpdateTime=? where username=?",nativeQuery = true)
     void update(@Param("nickname") String nickname, @Param("age") Integer age, @Param("gender")String gender, @Param("email") String email, @Param("lastUpdateTime") Date lastUpadateTime, @Param("username")String username);
     List<Doctor> findByNicknameLike(@Param("nickname") String nickname);
     @Query(value = "select * from doctor where nickname like ? or department like ? limit 3",nativeQuery =true )
     List<Doctor> findByNicknameLikeAndDepartment(@Param("nickname") String nickname,@Param("department") String department);
     @Query(value = "select * from doctor  limit 4",nativeQuery = true)
     List<Doctor> findByLimit();
}
