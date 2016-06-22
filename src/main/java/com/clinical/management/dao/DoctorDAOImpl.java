package com.clinical.management.dao;


import com.clinical.management.model.Doctor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DoctorDAOImpl implements DoctorDAO {

    private SessionFactory sf;

    public DoctorDAOImpl(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Doctor getDoctorByCredentials(String userName, String password) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Doctor where username=:id and password=:pwd");
        query.setString("id", userName);
        query.setString("pwd", password);

        Doctor user = (Doctor) query.uniqueResult();

        tx.commit();
        session.close();
        return user;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(doctor);

        tx.commit();
        session.close();
    }


}
