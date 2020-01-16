package com.Controller;

import com.Model.Courses;
import com.Model.Student;
import com.Services.MyServices;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MyController {

    @Autowired
    MyServices myServices;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("student", new Student());
        return "views/index.jsp";
    }

    @GetMapping("/retrieve")
    public String welcome(@ModelAttribute("student") Student student, HttpSession httpSession, Model model) {
        Student student1;
        System.out.println("hello");
        student1 = myServices.authenticate(student.getUsername(), student.getPassword());
        System.out.println(student1);
        System.out.println("hello");
        if (student1 == null) {
            model.addAttribute("student", new Student());
            return "views/index.jsp";
        } else {
            System.out.println("hello");
            httpSession.setAttribute("studentDetail", student1);
            return "views/welcome.jsp";
        }
    }

    @GetMapping("/addCourses")
    public String addCourses(Model model) {
        model.addAttribute("course", new Courses());
        return "views/AddCourse.jsp";
    }

    @PostMapping("/addCourses")
    public String addCourses(@ModelAttribute("course") Courses courses) {
        myServices.addCourse(courses);
        return "views/welcome.jsp";
    }

    @GetMapping("/applyAsRep")
    public String apply(Model model) {
        if (myServices.getAvailableCourses() == null) {
            String message = "No course available";
            model.addAttribute("message", message);
        } else model.addAttribute("courses", myServices.getAvailableCourses());
        return "views/applyAsRep.jsp";
    }

    @PostMapping("/applyAsRep")
    public String apply(@RequestParam("title") String title, HttpSession httpSession) {
        myServices.applyAsRep(title, httpSession);

        return "views/succes.jsp";
    }

    @GetMapping("/GeneratePDF")
    public String generatePDF(HttpSession httpSession, HttpServletResponse httpServletResponse) throws JRException {
        myServices.generatePDF(httpSession, httpServletResponse);

        return "views/welcome.jsp";
    }


}
