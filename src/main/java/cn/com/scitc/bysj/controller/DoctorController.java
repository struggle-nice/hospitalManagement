package cn.com.scitc.bysj.controller;

import cn.com.scitc.bysj.dao.*;
import cn.com.scitc.bysj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private IntroduceDao introduceDao;
    @Autowired
    private OrderingDao orderingDao;
    @Autowired
    private  CiiDao ciiDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private HospitalDao hospitalDao;
    @GetMapping("/home")
    private String home() {
        return "home";
    }

    @PostMapping("/land")
    private String land(@RequestParam("username") String username, @RequestParam("password") String password
            , Map<String, Object> map, HttpSession session, Model model) {
        Doctor doc = doctorDao.findByUsername(username);

        if (doc == null) {
            map.put("msg", "账号错误");
            return "home";
        } else {
            if (doc.getPassword().equals(password)) {
                String dep = doc.getDepartment();
                Introduce introduce = introduceDao.findByDoctorId(doc.getId());
                session.setAttribute("department",dep);
                session.setAttribute("loginUser", username);
                session.setAttribute("headimage",introduce.getDoctorImg());
                return "redirect:/doctor/login";
            } else {
                map.put("msg", "密码错误");
                return "home";
            }


        }
    }

    @PostMapping("/save")
    private String save( Doctor doctorInp) {
        Iterable<Doctor> list = doctorDao.findAll();
        for (Doctor doctor : list){
            if (doctor.getUsername().equals(doctorInp.getUsername())){
                return "redirect:/doctor/home";
            }else if (doctor.getPhoneNumber().equals(doctorInp.getPhoneNumber())){
                return "redirect:/doctor/home";
            }else if (doctor.getEmail().equals(doctorInp.getEmail())){
                return "redirect:/doctor/home";
            }
        }
        doctorInp.setCreateTime(new java.sql.Date(new Date().getTime()));
        doctorInp.setLastUpdateTime(new java.sql.Date(new Date().getTime()));
        doctorDao.save(doctorInp);
        Doctor doctor=doctorDao.findByUsername(doctorInp.getUsername());
        Introduce introduce =new Introduce();
        introduce.setDoctorName(doctorInp.getNickname());
        introduce.setDoctorId(doctor.getId());
        introduceDao.save(introduce);
        return "redirect:/doctor/home";
    }
    @PostMapping("/update")
    private String update(@Valid Doctor doctorInp, BindingResult bindingResult, Model model,
                          HttpSession session, String introduce, @RequestParam("doctorImg") MultipartFile doctorImg,
                          @RequestParam("imgpath") String imgpath){
        if(bindingResult.hasErrors()){
            Object obj=bindingResult.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("errors",obj);
            return "redirect:/doctor/edit";
        }
        String username = (String) session.getAttribute("loginUser");
        doctorInp.setUsername(username);
        doctorInp.setLastUpdateTime(new java.sql.Date(new Date().getTime()));
        doctorDao.update(doctorInp.getNickname(),doctorInp.getAge(),doctorInp.getGender(),
                doctorInp.getEmail(),doctorInp.getLastUpdateTime(),doctorInp.getUsername());
        Introduce intr = new Introduce();
        Doctor doctor = doctorDao.findByUsername(username);
        intr.setDoctorName(doctor.getNickname());
        intr.setDoctorId(doctor.getId());
        intr.setIntroduce(introduce);
        String headImg = doctorImg.getOriginalFilename();
        List<String> fileSuffix = new ArrayList<>();
        fileSuffix.add(".jpeg0");
        fileSuffix.add(".png");
        fileSuffix.add(".jpg");
        fileSuffix.forEach(f ->{
            if (headImg.endsWith(f)) {
                File file1 = new File(imgpath,headImg);
                try {
                    doctorImg.transferTo(file1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        intr.setDoctorImg("/headImg/"+headImg);
        if (introduceDao.findByDoctorId(doctor.getId()) == null){
            introduceDao.save(intr);
        }else{
            introduceDao.update(intr.getIntroduce(),intr.getDoctorImg(),intr.getDoctorId());
        }
        return "redirect:/doctor/edit";
    }

    @GetMapping("/login")
    private String login(HttpSession session,Model model) {
        String username = (String) session.getAttribute("loginUser");
        Doctor byUsername = doctorDao.findByUsername(username);
        List<Ordering> byDoctorName = orderingDao.findByDoctorName(byUsername.getNickname());
        int i =0;
        for (Ordering ordering : byDoctorName){
            i += 1;
        }
        model.addAttribute("total",i);
        model.addAttribute("doctor",byDoctorName);
        return "doctor/login";
    }
    @GetMapping("/doctorHome")
    private String doctorHome(){
        return "/doctor/doctorHome";
    }
    @GetMapping("/plus")
    private String plus(){
        return "/doctor/plus";
    }
    @GetMapping("/edit")
    private String edit(Model model,HttpSession session){
        String username = (String) session.getAttribute("loginUser");
        Doctor byUsername = doctorDao.findByUsername(username);
        model.addAttribute("list",byUsername);
        Doctor doctor = doctorDao.findByUsername(username);
        Introduce introduce = introduceDao.findByDoctorId(doctor.getId());
        model.addAttribute("introduce",introduce);
        return "doctor/edit";
    }
    @PostMapping("/push")
    private String push(){
        return "";
    }
    @GetMapping("/exit")
    private String exit(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/doctor/home";
    }
    @ResponseBody
    @GetMapping("/charged")
    private Cii charged(Integer pId,String charged){
        Patient byPId = patientDao.getByPId(pId);
        Hospital hospitals = hospitalDao.findByPId(pId);
        Cii cii =new Cii();
        cii.setpId(byPId.getpId());
        cii.setBed(hospitals.getBed());
        cii.setChuFang(hospitals.getChuFang());
        cii.setDisease(byPId.getDisease());
        cii.setDoctorId(byPId.getDoctorId());
        cii.setHospitalTime(byPId.getHospitalTime());
        cii.setName(byPId.getName());
        cii.setOutTime(new java.sql.Date(new Date().getTime()));
        cii.setRoom(hospitals.getRoom());
        cii.sethId(hospitals.getHospitalid());
        cii.setCharged(charged);
        ciiDao.save(cii);
        patientDao.deleteById(pId);
        hospitalDao.deleteByPId(pId);
        return cii;
    }
    @ResponseBody
    @GetMapping("/modify")
    private Integer modify(HttpSession session,String password,String newPassword1,String newPassword2){
        String username = (String)session.getAttribute("loginUser");
        Doctor doctor = doctorDao.findByUsername(username);
        if (doctor.getPassword().equals(password)){
            if(newPassword1 != newPassword2){
                return 1;
            }else{
                doctor.setPassword(newPassword1);
                doctorDao.save(doctor);
                return 2;
            }
        }else{
            return 3;
        }
    }
}
