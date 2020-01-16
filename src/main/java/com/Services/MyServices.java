package com.Services;

import com.Model.Courses;
import com.Model.Student;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface MyServices {
    Student authenticate(String username, String password);

    void addCourse(Courses courses);

    List<Courses> getAvailableCourses();

    void applyAsRep(String title, HttpSession httpSession);

    void generatePDF(HttpSession httpSession, HttpServletResponse httpServletResponse);

}
