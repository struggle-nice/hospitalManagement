package cn.com.scitc.bysj.controller;
import cn.com.scitc.bysj.dao.DoctorDao;
import cn.com.scitc.bysj.dao.HospitalDao;
import cn.com.scitc.bysj.dao.OrderingDao;
import cn.com.scitc.bysj.dao.PatientDao;
import cn.com.scitc.bysj.model.Doctor;
import cn.com.scitc.bysj.model.Hospital;
import cn.com.scitc.bysj.model.Ordering;
import cn.com.scitc.bysj.model.Patient;
import cn.com.scitc.bysj.service.PatientService;
import cn.com.scitc.bysj.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/doctor")
public class patientController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired private PatientDao patientDao;
    @Autowired private DoctorDao doctorDao;
    @Autowired private PatientService patientService;
    @Autowired private HospitalDao hospitalDao;
    @Autowired private OrderingDao orderingDao;
//    @GetMapping("/management")
//    private String management( Model model, HttpSession session,@RequestParam(defaultValue = "1") int current){
//        String username = (String) session.getAttribute("loginUser");
////        Pageable pageable = PageRequest.of(pageNum,pageSize);
//        Page page=new Page();
//        long count = patientDao.count(username);
//        page.setSize(4);
//        page.setCurrent(current);
//        int offset = (current - 1) * page.getSize();
//        page.setOffset(offset);
//        List<Patient> opt = patientDao.findByUsernameAndState(username,page.getOffset(),page.getSize());
//        page.setCount(count);
//        page.setList(opt);
//        model.addAttribute("patient",opt);
//        model.addAttribute("page",page);
//
////        if (opt != null) {
////            model.addAttribute("patient",opt);
////            model.addAttribute("patient",opt.getContent());
////            model.addAttribute("total",opt.getTotalElements());
////            model.addAttribute("pages",opt.getTotalPages());
////            return "/patient/management";
////        }
//
//        return "/patient/management";
//    }

    @GetMapping("/management")
    private String management(Model model, HttpSession session,
                              @RequestParam(value = "pageNum",defaultValue = "0",required = false) Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
        if (pageNum != 0) {
            pageNum = pageNum -1;

        }
        String image=(String) session.getAttribute("image");
        String username = (String) session.getAttribute("loginUser");
        Doctor byUsername = doctorDao.findByUsername(username);
        PageVO pageVO = new PageVO(byUsername.getId(), pageNum, pageSize);
        Page<Patient> result = patientService.findByStateAndUsername(pageVO);
        model.addAttribute("result", result.getContent());
        model.addAttribute("first",result.isFirst());
        model.addAttribute("last",result.isLast());
        model.addAttribute("totalElements",result.getTotalElements());
        model.addAttribute("totalPages",result.getTotalPages());
        model.addAttribute("number",result.getNumber());
        return "/patient/management";
    }
    @PostMapping("/edit")
    private String edit(@RequestParam("state") String state, Integer id){
        log.info("state --->" +state);
        log.info("id --->" + id);
        Patient opt = patientDao.getByPId(id);
        if (opt != null) {
            patientDao.updateState(id,state);
        }
        return"redirect:/doctor/management";
    }
    @GetMapping("/add")
    private String add(){
        return "/patient/add";
    }
    @PostMapping("/savenow")
    private String save(Patient patientInp,String disease, HttpSession session){
        String username=(String)session.getAttribute("loginUser");
        Doctor doctor=doctorDao.findByUsername(username);
        int id=doctor.getId();
        patientInp.setState("否");
        patientInp.setDoctorId(id);
        patientInp.setHospitalTime(new java.sql.Date(new java.util.Date().getTime()));
        patientDao.save(patientInp);
        return "redirect:/doctor/management";
    }
    @PostMapping("/search")
    private String search(Model model, String name){
        List<Patient> patients = patientDao.findByNameLike("%"+name+"%");
        model.addAttribute("patients",patients);
        return "/patient/search";
    }
    @GetMapping("/process")
    private String process(){
        List<Patient> byHid =patientDao.findByHid();
        System.out.println(byHid);
        if (byHid.isEmpty()){
            return "redirect:/doctor/not";
        }else{
            return "redirect:/doctor/out";
        }
    }
    @GetMapping("/not")
    private String not(){
        return "patient/not";
    }
    @GetMapping("/out")
    private String out(Model model,HttpSession session,
                       @RequestParam(value = "pageNum",defaultValue = "0",required = false) Integer pageNum){
        if (pageNum != 0) {
            pageNum = pageNum -1;
        }
        Pageable pageable= PageRequest.of(pageNum,5);
        Page<Patient> result = patientDao.getAll(pageable);
        for (Patient patient : result){
            Hospital hospital = hospitalDao.getById(patient.gethId());
            patient.setHospitals(hospital);
        }
        model.addAttribute("result", result.getContent());
        model.addAttribute("first",result.isFirst());
        model.addAttribute("last",result.isLast());
        model.addAttribute("totalElements",result.getTotalElements());
        model.addAttribute("totalPages",result.getTotalPages());
        model.addAttribute("number",result.getNumber());
        return "patient/out";
    }
}
