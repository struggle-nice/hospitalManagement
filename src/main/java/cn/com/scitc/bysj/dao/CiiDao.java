package cn.com.scitc.bysj.dao;

import cn.com.scitc.bysj.model.Cii;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CiiDao extends CrudRepository<Cii,Integer> {
    List<Cii> findByPId(@Param("pId") Integer pId);
    @Transactional
    @Modifying
    @Query(value = "update cii set charged = ? where pId = ?",nativeQuery = true)
    void update(@Param("charged") String charged,@Param("pId")Integer pId);
}
