package com.DataAccessObject;

import com.Configuration.HibernateSessionConfig;
import com.Model.Courses;
import com.Model.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class DAOImplementation implements MyDataAccessObject {


    @Autowired
    HibernateSessionConfig hibernateSessionConfig;

    private Session session;

 
    public void addCourse() {
        
    }

    public Student authenticateStudent(String username, String password) {
        session = hibernateSessionConfig.getSession();
        Query query = session.createQuery("from Student where username = :username and Password = :Password");
        query.setParameter("username", username);
        query.setParameter("Password", password);
        Student student = (Student) query.uniqueResult();
        System.out.println((Student) student);
        System.out.println(student.getCourses());
        System.out.println((Courses) student.getCourses());
        session.close();
        return student;
    }

    public void addCourse(Courses courses) {
        session = hibernateSessionConfig.getSession();
        session.save(courses);
        session.close();
    }


    public List<Courses> getAvailableCourses() {
        session = hibernateSessionConfig.getSession();
        Query query = session.createQuery("from Courses where Course_rep_ID = null ");
        List<Courses> courses = query.getResultList();
        session.close();
        return courses;
    }


    public void applyAsRep(String title, HttpSession httpSession) {
        session = hibernateSessionConfig.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Courses where Course_title =:Course_title ");
        query.setParameter("Course_title", title);
        Courses courses = (Courses) query.uniqueResult();
        Student student = (Student) httpSession.getAttribute("studentDetail");
        session.getTransaction().commit();
        session.beginTransaction();
        courses.setStudent(student);
        session.update(courses);
        session.getTransaction().commit();
        session.close();
    }

    public void generatePDF(HttpSession httpSession, HttpServletResponse httpServletResponse) {
        session = hibernateSessionConfig.getSession();
        Student student = session.get(Student.class,
                ((Student) httpSession.getAttribute("studentDetail")).getID());
        Student student1 = (Student) student;
        System.out.println(student.getUsername());
        if (student != null) {
            List<Student> list = new ArrayList<>();
            list.add(student);
            System.out.println(student.getUsername());
//            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/resources/report/studentProfile2.jrxml");
            JasperReport jasperReport;
            try {
                JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\Mason\\IdeaProjects\\maggy\\src\\main\\webapp\\resources\\report\\studentProfile.jrxml");
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrBeanCollectionDataSource);
                JRPdfExporter pdfExporter = new JRPdfExporter();
                pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
                pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
                pdfExporter.exportReport();
//               JasperExportManager.exportReportToPdf(jasperPrint);
                JasperViewer.viewReport(jasperPrint);
                httpServletResponse.setContentType("application/pdf");
               httpServletResponse.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
                httpServletResponse.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
                OutputStream responseOutputStream = httpServletResponse.getOutputStream();
                responseOutputStream.write(pdfReportStream.toByteArray());
                responseOutputStream.close();
                pdfReportStream.close();
            } catch (JRException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        session.close();
    }
}
