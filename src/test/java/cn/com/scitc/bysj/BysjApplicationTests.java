package cn.com.scitc.bysj;

import cn.com.scitc.bysj.dao.*;
import cn.com.scitc.bysj.model.*;
import cn.com.scitc.bysj.service.PatientService;
import cn.com.scitc.bysj.vo.PageVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BysjApplicationTests {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private CiiDao ciiDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IntroduceDao introduceDao;
    @Autowired
    private PatientService patientService;
    @Autowired
    private HospitalDao hospitalDao;
    @Autowired
    private OrderingDao orderingDao;
    @Test
    public void contextLoads() {
    }
    @Test
    public void test01(){
        Doctor doctor = doctorDao.findByUsername("zhangsan");
        Integer id=doctor.getId();
        System.out.println(id);
        List<Patient> opt = patientDao.findByDoctorId(id);
    }
    @Test
    public void test02(){
        Doctor d=doctorDao.findByUsername("zhangsan");
        System.out.println(d.getId());
    }
    @Test
    public void test03(){
        List<Cii> cii=ciiDao.findByPId(9);
        if (cii.size() == 0){
            System.out.println("null");
        }
        for (Cii ciis : cii) {
            System.out.println(ciis.getBed());
        }
    }
    @Test
    public void test04(){
//        Page page=new Page();
//        page.setSize(4);
//        page.setCurrent(2);
//        int offset =0;
//        offset=(offset - 1)* page.getSize();
//        page.setOffset(offset);
//        long count = patientDao.count("zhangsan");
//        List<Patient> opt = patientDao.findByUsernameAndState("zhangsan",page.getOffset(),page.getSize());
//        page.setCount(count);
//        page.setList(opt);
//        System.out.println(page);
//        List<Hospital> hospitals = hospitalDao.findAllByDepartment("妇科");
//        for (Hospital hospital : hospitals){
//            System.out.println(hospital);
//        }
    }
    @Test
    public void test05(){
        PageVO pageVO = new PageVO();
        pageVO.setKeywords(111);
        pageVO.setPageNum(0);
        pageVO.setPageSize(3);
        org.springframework.data.domain.Page<Patient> result = patientService.findByStateAndUsername(pageVO);
        System.out.println(result.getTotalPages());
    }
    @Test
    public void test06(){
//        List<Hospital> list = hospitalDao.findAllByDepartment("妇科");
//        for (Hospital hospital:list){
//            System.out.println(hospital);
//        }
    }
    @Test
    public void test07(){
        PageVO pageVO=new PageVO(1,0,5);
        Page<Patient> result = patientService.findByStateAndUsername(pageVO);
        System.out.println(result.getContent());
    }
    @Test
    public void test08(){
        Optional<User> byId = userDao.findById(11111);
        System.out.println(byId);
    }
    @Test
    public void test09(){
        Iterable<Introduce> introduces = introduceDao.findAll();
        for (Introduce introduce : introduces){
            Optional<Doctor> doctors = doctorDao.findById(introduce.getDoctorId());
            introduce.setDoctors(doctors.get());
        }
        System.out.println(introduces);
    }
    @Test
    public void test10(){
        Date date = new Date(new java.util.Date().getTime());
        Date date1 = new Date(new java.util.Date().getTime());
        if (date .equals(date1) ){
            System.out.println("相同");
        }else {
            System.out.println("不同");
        }
    }
    @Test
    public void test11(){
        Pageable pageable= PageRequest.of(0,5);
        Iterable<Patient> result = patientDao.findAll();
        for (Patient patient:result){
            if (patient.gethId() != null){
            }
        }
        Page<Patient> all = patientDao.getAll(pageable);
        for (Patient patient : all){
            System.out.println(patient);
        }
    }
    @Test
    public void test12(){
        Ordering ordering1 = orderingDao.findByUid(123);
        System.out.println(ordering1);
    }
}
