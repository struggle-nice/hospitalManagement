package cn.com.scitc.bysj.controller;

import cn.com.scitc.bysj.dao.CiiDao;
import cn.com.scitc.bysj.dao.DoctorDao;
import cn.com.scitc.bysj.dao.HospitalDao;
import cn.com.scitc.bysj.dao.PatientDao;
import cn.com.scitc.bysj.model.Doctor;
import cn.com.scitc.bysj.model.Hospital;
import cn.com.scitc.bysj.model.Patient;
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
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class HospitalController {
    @Autowired private CiiDao ciiDao;
    @Autowired private DoctorDao doctorDao;
    @Autowired private HospitalDao hospitalDao;
    @Autowired private PatientDao patientDao;
    @GetMapping("/hospital")
    private  String hospital(Integer pId, Model model, HttpSession session,
                             String name, @RequestParam(defaultValue = "0")Integer pageNum){
        Hospital hospitals=hospitalDao.findByPId(pId);
        String username = (String)session.getAttribute("loginUser");
        System.out.println(pId);
        if(hospitals != null) {
            model.addAttribute("list", hospitals);
            return "hospital/hospital";
        }else{
            model.addAttribute("pId",pId);
            model.addAttribute("name",name);
            Doctor byUsername=doctorDao.findByUsername(username);
            session.setAttribute("doctorname",byUsername.getNickname());
            System.out.println(byUsername.getDepartment());
            if (pageNum != 0) {
                pageNum = pageNum -1;

            }
            Pageable pageable= PageRequest.of(pageNum,10);
            Page<Hospital> list = hospitalDao.findAllByDepartment(byUsername.getDepartment(),pageable);
            model.addAttribute("bed", list.getContent());
            model.addAttribute("first",list.isFirst());
            model.addAttribute("last",list.isLast());
            model.addAttribute("totalElements",list.getTotalElements());
            model.addAttribute("totalPages",list.getTotalPages());
            model.addAttribute("number",list.getNumber());
            return "hospital/add";
        }
    }
    @PostMapping("/save")
    private String save(String pName,Integer pId,String doctorname,Integer hospitalid,String chuFang){

        int i = hospitalDao.updateByhId(pId,doctorname,chuFang,pName,hospitalid);
        Optional<Patient> byId = patientDao.findById(pId);
        byId.get().sethId(hospitalid);
        patientDao.save(byId.get());
        return "redirect:/patient/live";
    }
    @GetMapping("/live")
    private String live(Model model,@RequestParam(defaultValue = "0") Integer pageNum){
        if (pageNum != 0) {
            pageNum = pageNum -1;

        }
        Pageable pageable=PageRequest.of(pageNum,8);
        Page<Hospital> list = hospitalDao.findAllByPId(pageable);
        model.addAttribute("live",list.getContent());
        model.addAttribute("totalPages",list.getTotalPages());
        model.addAttribute("rows",list.getTotalElements());
        model.addAttribute("number",list.getNumber());
        return "/hospital/live";
    }
    @PostMapping("/search")
    private String search(Model model,String pName){
        List<Hospital> hospitalList = hospitalDao.findByPNameLike("%"+pName+"%");
        model.addAttribute("hospitalList",hospitalList);
        return "/hospital/search";
    }
}
