package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface PatientDao extends CrudRepository<Patient,Integer> {
    List<Patient> findByDoctorId(Integer id);
    Patient getByPId(Integer pId);
    @Query(value = "select * from patient p where p.state ='否' and  p.DoctorId= ?",nativeQuery = true)
    Page<Patient> findByStateAndUsername(@Param("doctorId") Integer doctorId, Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = "update Patient p set p.state =:state where pId =:pId")
    void updateState(@Param("pId") Integer pId,@Param("state") String state);
    @Query(value = "select count(1) from doctor,patient where doctor.id = patient.DoctorId and patient.state ='否' and  doctor.username = ?",nativeQuery = true)
    long count(@Param("username") String username);
    List<Patient> findByNameLike(@Param("name")String name);
    @Query(value = "select * from patient where hid != ''",nativeQuery = true)
    Page<Patient> getAll(Pageable pageable);
    @Query(value = "select * from patient where hid != ''",nativeQuery = true)
    List<Patient> findByHid();
}
