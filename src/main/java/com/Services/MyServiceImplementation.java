package com.Services;

import com.DataAccessObject.MyDataAccessObject;
import com.Model.Courses;
import com.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyServiceImplementation implements MyServices {

    @Autowired
    MyDataAccessObject myDataAccessObject;

    @Transactional
    public Student authenticate(String username, String password) {
        return myDataAccessObject.authenticateStudent(username, password);
    }

    @Transactional
    public void addCourse(Courses courses) {
        myDataAccessObject.addCourse(courses);
    }

    @Transactional
    public List<Courses> getAvailableCourses() {
        return myDataAccessObject.getAvailableCourses();
    }


    public void applyAsRep(String title, HttpSession httpSession) {
        myDataAccessObject.applyAsRep(title, httpSession);
    }

    @Transactional
    public void generatePDF(HttpSession httpSession, HttpServletResponse httpServletResponse) {
        myDataAccessObject.generatePDF(httpSession, httpServletResponse);
    }
}

