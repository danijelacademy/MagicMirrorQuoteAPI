package com.example.Controller;

import com.example.Domain.User;
import com.example.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    Repository repository;

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse res) {
        session.invalidate();
        Cookie cookie = new Cookie("jsessionid", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "redirect:/index.html";
    }

    @PostMapping("/login")
    public String submit(HttpSession session, @RequestParam String username, @RequestParam String password) throws Exception {
        User login = repository.getUser(username, password);
        if (login != null) {
            session.setAttribute("user", username); //sparar användarnamnet i session. Markerar att användaren är inloggad.
            return "redirect:/dashboard";
        }

        return "redirect:/index.html";
    }

}
