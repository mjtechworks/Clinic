package com.clinical.management.dao;

import com.clinical.management.model.Patient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private SessionFactory sf;

    public PatientDAOImpl(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Patient getPatientById(int id) {
        //TO DO

        return null;
    }

    @Override
    public List<Patient> getPatientsByDoctorID(int doctorID) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Patient where doctorID=:id");
        query.setInteger("id", doctorID);

        List<Patient> patients = (List<Patient>) query.list();

        tx.commit();
        session.close();

        return patients;
    }

    @Override
    public List<Patient> getPatientsByName(String name) {
        //TO DO
        return null;
    }

    @Override
    public void addPatient(Patient patient) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(patient);

        transaction.commit();
        session.close();
    }

    @Override
    public void updatePatient(Patient patient) {
        //TO DO
    }

    @Override
    public void deletePatientById(int id) {
        //TO DO
    }
}
