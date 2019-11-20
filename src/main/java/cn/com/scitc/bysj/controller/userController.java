package cn.com.scitc.bysj.controller;

import cn.com.scitc.bysj.dao.DoctorDao;
import cn.com.scitc.bysj.dao.IntroduceDao;
import cn.com.scitc.bysj.dao.UserDao;
import cn.com.scitc.bysj.model.Doctor;
import cn.com.scitc.bysj.model.Introduce;
import cn.com.scitc.bysj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private IntroduceDao introduceDao;
    @RequestMapping("/home")
    private String home(){
        return"user/home";
    }
    @PostMapping("/land")
    private String land(Integer id, String password, Map<Object,String> map, HttpSession session, Model model){
        User byId = userDao.getById(id);
        if (byId == null){
            map.put("error","账号错误！");
            return"redirect:/doctor/home";
        }
        if (!byId.getPassword().equals(password)){
            map.put("error","密码错误！");
            return"redirect:/doctor/home";
        }else{
            session.setAttribute("user",id);
            Iterable<Introduce> introduces = introduceDao.findAll();
            for (Introduce introduce : introduces){
                Optional<Doctor> doctors = doctorDao.findById(introduce.getDoctorId());
                introduce.setDoctors(doctors.get());
            }
            session.setAttribute("list",introduces);
            return"redirect:/user/home";
        }

    }
    @GetMapping("/register")
    private String add(){
        return "user/register";
    }
    @PostMapping("/save")
    private String save(User userInp ,HttpSession session, Map<Object,String> map){
        User user = userDao.getById(userInp.getId());
        if(user == null){
            userDao.save(userInp);
            return "redirect:/doctor/home";
        }else{
            return "redirect:/doctor/home";
        }
    }
    @ResponseBody
    @GetMapping("/option")
    private List<Doctor> option(String department){
        List<Doctor> list = doctorDao.findByDepartment(department);
        return list;
    }
    @ResponseBody
    @GetMapping("/introduce")
    private Introduce introduce(Integer doctorId){
        Introduce introduces =introduceDao.findByDoctorId(doctorId);
        return introduces;
    }
//    查找医生
    @PostMapping("/search")
    private String search(String search,Model model){
        List<Doctor> doctors = doctorDao.findByNicknameLikeAndDepartment('%'+search+'%','%'+search+'%');
        for (Doctor doctor : doctors){
            Introduce byDoctorId = introduceDao.findByDoctorId(doctor.getId());
            doctor.setIntroduces(byDoctorId);
        }
        model.addAttribute("doctors",doctors);
        return "user/search";
    }
    @GetMapping("/doctor")
    private String doctor(Model model){
        List<Doctor> byLimit = doctorDao.findByLimit();
        for (Doctor doctor : byLimit){
            Introduce byDoctorId = introduceDao.findByDoctorId(doctor.getId());
            doctor.setIntroduces(byDoctorId);
        }
        model.addAttribute("doctors",byLimit);
        return "/user/doctor";
    }
    @GetMapping("/clinic")
    private String clinic(){
        return "/user/clinic";
    }
}
