package cn.com.scitc.bysj.service.impl;

import cn.com.scitc.bysj.dao.PatientDao;
import cn.com.scitc.bysj.model.Patient;
import cn.com.scitc.bysj.service.PatientService;
import cn.com.scitc.bysj.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDao patientDao;

    @Override
    public Page<Patient> findByStateAndUsername(PageVO pageVO) {
        Pageable pageable = PageRequest.of(pageVO.getPageNum(), pageVO.getPageSize());
        Page<Patient> byStateAndUsername = patientDao.findByStateAndUsername(pageVO.getKeywords(), pageable);
        return byStateAndUsername;
    }
}
