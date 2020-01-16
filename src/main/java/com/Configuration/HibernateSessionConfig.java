package com.Configuration;

import com.Model.Courses;
import com.Model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionConfig {

    public Session getSession() {
        Session session = null;
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .addAnnotatedClass(Courses.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();
        return session;
    }
}
