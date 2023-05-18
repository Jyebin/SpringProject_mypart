package SWTeam2.vocabulary.SWTeam2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//로그인

@Controller
public class PageController { //다른 컨트롤러랑 이름 겹치면 안됨
    @GetMapping("loginPage")
    public String loginPage(Model model){
        return "loginPage";
    }
    @GetMapping("home")
    public String mainPate(Model model){
        return "home";
    }
    @GetMapping("levelTest")
    public String levelTest(Model model){
        return "levelTest";
    }
    @GetMapping("pwFind")
    public String PasswordFindPage(Model model){
        return "pwFind";
    }
    @PostMapping("joinMem")
    public String JoinMemPage(Model model){
        return "joinMem";
    }
}
