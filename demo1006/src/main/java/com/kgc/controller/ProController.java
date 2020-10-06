package com.kgc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.pojo.Projectinfo;
import com.kgc.servlce.ProServlce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProController {

    @Resource
    ProServlce proServlce;

    @RequestMapping("index")
    public String sel(Model model, HttpServletRequest request){

        String status = request.getParameter("status");
        int statusint = 3;
        if(status != null && !status.equals("")){
            statusint = Integer.parseInt(status);
        }

        int Num=1;
        String pageNum=request.getParameter("pageNum");
        if(pageNum!=null){
            Num=Integer.parseInt(pageNum);
        }
        int pageSize=3;
        PageHelper.orderBy("id desc");
        PageHelper.startPage(Num,pageSize);


        List<Projectinfo> projectinfos = proServlce.selAll(statusint);
        PageInfo<Projectinfo> pageInfo=new PageInfo<>(projectinfos);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    @RequestMapping("/selById/{id}")
    public String selById(@PathVariable Integer id, Model model){
        Projectinfo projectinfo = proServlce.selById(id);
        model.addAttribute("projectinto",projectinfo);
        return "upd";
    }

    @RequestMapping("/upd")
    public String upd(HttpSession session, Projectinfo projectinfo){
        int upd = proServlce.upd(projectinfo);
        if(upd>0){
            session.setAttribute("msg","更新成功");
        }else{
            session.setAttribute("msg","更新失败");
        }
        return "redirect:index";
    }

}

