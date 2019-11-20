package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HospitalDao extends CrudRepository<Hospital,Integer> {
    @Query(value = "select * from hospital where department = ? and pid is null",nativeQuery = true)
    Page<Hospital> findAllByDepartment(@Param("department") String department, Pageable pageable);
    Hospital findByPId(@Param("pId") Integer pId);
    @Transactional
    @Modifying
    @Query(value = "update Hospital set pId = ?,doctorName = ?,chuFang = ?,pName = ? where hospitalid=?",nativeQuery = true)
    int updateByhId(@Param("pId")Integer pId,@Param("doctorName")String doctorName,@Param("chuFang")String chuFang,@Param("pName")String pName,@Param("hospitalid")Integer id);
    @Query(value = "select * from hospital where pId is not null ",nativeQuery = true)
    Page<Hospital> findAllByPId(Pageable pageable);
    List<Hospital> findByPNameLike(@Param("pName")String pName);
    @Query(value = "select * from hospital where hospitalid = ?",nativeQuery = true)
    Hospital getById(@Param("hospitalid")int hospitalid);
    @Transactional
    @Modifying
    @Query(value = "update Hospital set pId = null,chuFang = null,pName = null,doctorName = null where pId = ?",nativeQuery = true)
    void deleteByPId(@Param("pId") Integer pId);
}
