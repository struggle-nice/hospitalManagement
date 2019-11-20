package cn.com.scitc.bysj.service;

import cn.com.scitc.bysj.model.Patient;
import cn.com.scitc.bysj.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PatientService {
    Page<Patient> findByStateAndUsername(PageVO pageVO);
}
