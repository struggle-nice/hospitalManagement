package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.Introduce;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface IntroduceDao extends CrudRepository<Introduce, Integer> {
    Introduce findByDoctorId(@Param("doctorId") Integer doctorId);
    @Transactional
    @Modifying
    @Query(value = "update introduce set introduce=?,doctorImg=? where doctor_id=?",nativeQuery = true)
    void update(@Param("introduce") String introduce,@Param("doctorImg") String doctorImg,@Param("doctor_id") Integer doctorId);
}
