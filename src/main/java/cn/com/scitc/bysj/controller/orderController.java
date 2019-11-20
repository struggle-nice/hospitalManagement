package cn.com.scitc.bysj.controller;

import cn.com.scitc.bysj.dao.OrderingDao;
import cn.com.scitc.bysj.model.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;


@Controller
@RequestMapping("/order")
public class orderController {
    @Autowired
    private OrderingDao orderingDao;
    @ResponseBody
    @GetMapping("/save")
    private Boolean save(Ordering ordering, HttpSession session){
        Integer id = (Integer) session.getAttribute("user");
        ordering.setUid(id);
        Date now =  new Date(new java.util.Date().getTime());
        ordering.setBookingTime(now);
        Ordering ordering1 = orderingDao.findByUid(ordering.getUid());
        if (ordering1 == null){
            orderingDao.save(ordering);
            return true;
        }else {
            return false;
        }
    }
    @GetMapping("/delete")
    private String delete(Integer uid){
        orderingDao.deleteByUid(uid);
        return "redirect:/doctor/login";
    }
}
