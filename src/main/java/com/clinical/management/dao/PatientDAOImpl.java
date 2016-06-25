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
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Patient where id=:id");
        query.setInteger("id", id);

        Patient patient = (Patient) query.uniqueResult();

        tx.commit();
        session.close();
        return patient;
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
    public List<Patient> getPatientsByName(int doctorID, String name) {

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Patient where doctorID=:id and (LOWER(firstName) like :name or LOWER(lastName) like :name)");
        query.setInteger("id", doctorID);
        query.setString("name", "%" + name.toLowerCase() + "%");

        List<Patient> patients = (List<Patient>) query.list();

        tx.commit();
        session.close();

        return patients;
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
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(patient);

        transaction.commit();
        session.close();
    }

    @Override
    public void deletePatientById(int id) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Patient patient = getPatientById(id);

        session.delete(patient);

        transaction.commit();
        session.close();
    }
}
