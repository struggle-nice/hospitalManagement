package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.Ordering;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderingDao extends CrudRepository<Ordering,Integer> {
    Ordering findByUid(@Param("uid") Integer uid);
    @Query(value = "select * from ordering where doctorName = ? and ordering_time = current_date order by booking_time",nativeQuery = true)
    List<Ordering> findByDoctorName(@Param("doctorName") String doctorName);
    @Transactional
    @Modifying
    @Query(value = "delete  from ordering where uid = ? and ordering_time = current_date",nativeQuery = true)
    void deleteByUid(@Param("uid") Integer uid);
}
