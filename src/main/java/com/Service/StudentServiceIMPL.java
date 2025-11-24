package com.Service;

import com.Config.HibernateUtil;
import com.Entity.Student;
import com.Entity.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentServiceIMPL implements StudentServices {

    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session s = sf.openSession();

    @Override
    public void addStudentWithSubjects() {

        Scanner sc = new Scanner(System.in);

        // Start transaction FIRST
        s.beginTransaction();

        Student st = new Student();
        List<Subject> lib = new java.util.ArrayList<>();

        System.out.println("Enter the Student Name : ");
        st.setSname(sc.next());

        System.out.println("Enter how many subjects you want to add :- ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            Subject subject = new Subject();
            System.out.println("Enter the subject name :- ");
            subject.setSubName(sc.next());
            lib.add(subject);
        }

        st.setListsub(lib);

        s.persist(st);  // persist after setting list

        s.getTransaction().commit(); // commit after persist

        System.out.println("Student Added!");
    }

    @Override
    public void getAllStudents() {

        Transaction tx = s.beginTransaction();

        // Fetch all students
        List<Student> list = s.createQuery("from Student", Student.class).list();

        if (list == null || list.isEmpty()) {
            System.out.println("No students found.");
            tx.commit();
            return;
        }

        for (Student st : list) {
            System.out.println("--------------------------");
            System.out.println("Student ID   : " + st.getSid());
            System.out.println("Student Name : " + st.getSname());

            List<Subject> subjects = st.getListsub();
            if (subjects == null || subjects.isEmpty()) {
                System.out.println("Subjects     : None");
            } else {
                System.out.println("Subjects     :");
                for (Subject sub : subjects) {
                    System.out.println("   -> [" + sub.getSubId() + "] " + sub.getSubName());
                }
            }
        }

        tx.commit();
    }


    @Override
    public void getStudentOnly() {
        Scanner sc = new Scanner(System.in);
        Transaction tx = s.beginTransaction();

        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();
        sc.nextLine();

        Student student = s.get(Student.class, sid);
        if (student != null) {
            System.out.println("Student ID   : " + student.getSid());
            System.out.println("Student Name : " + student.getSname());

            List<Subject> subs = student.getListsub();
            if (subs == null || subs.isEmpty()) {
                System.out.println("No subjects found for this student.");
            } else {
                System.out.println("Subjects:");
                for (Subject sub : subs) {
                    System.out.println("  SubID: " + sub.getSubId() + "  Name: " + sub.getSubName());
                }
            }
            tx.commit();
        } else {
            System.out.println("Invalid Student ID!");
            tx.rollback();
        }
    }

    @Override
    public void getSubjectsOnly() {
        Scanner sc = new Scanner(System.in);
        Transaction tx = s.beginTransaction();

        System.out.print("Enter Subject ID: ");
        int subid = sc.nextInt();
        sc.nextLine();

        Subject subject = s.get(Subject.class, subid);
        if (subject != null) {
            System.out.println("Subject ID   : " + subject.getSubId());
            System.out.println("Subject Name : " + subject.getSubName());
            // if Subject has student collection (many-to-many), you can print count or list
            tx.commit();
        } else {
            System.out.println("Invalid Subject ID!");
            tx.rollback();
        }
    }

    @Override
    public void updateStudentDetailsUsingSID() {
        Scanner sc = new Scanner(System.in);
        Transaction tx = s.beginTransaction();

        System.out.print("Enter Student ID to update: ");
        int sid = sc.nextInt();
        sc.nextLine();

        Student student = s.get(Student.class, sid);
        if (student != null) {
            System.out.print("Enter new student name: ");
            String newName = sc.nextLine().trim();
            student.setSname(newName);

            // If you want to update subjects count/name, add code here

            tx.commit();
            System.out.println("Student updated.");
        } else {
            System.out.println("Invalid Student ID!");
            tx.rollback();
        }
    }

    @Override
    public void updateSpecificSubjectUsingStudentID() {
        Scanner sc = new Scanner(System.in);
        Transaction tx = s.beginTransaction();

        System.out.print("Enter Student ID (to update one of its subjects): ");
        int sid = sc.nextInt();
        sc.nextLine();

        Student student = s.get(Student.class, sid);
        if (student == null) {
            System.out.println("Invalid Student ID!");
            tx.rollback();
            return;
        }

        List<Subject> list = student.getListsub();
        if (list == null || list.isEmpty()) {
            System.out.println("This student has no subjects.");
            tx.rollback();
            return;
        }

        System.out.print("Enter Subject ID to update: ");
        int subid = sc.nextInt();
        sc.nextLine();

        Subject target = null;
        for (Subject sub : list) {
            if (sub != null && sub.getSubId() == subid) {
                target = sub;
                break;
            }
        }

        if (target == null) {
            System.out.println("Subject not found for this student.");
            tx.rollback();
            return;
        }

        System.out.print("Enter new subject name: ");
        String newName = sc.nextLine().trim();
        target.setSubName(newName);

        // managed entity -> changes flushed on commit
        tx.commit();
        System.out.println("Subject updated.");
    }

    @Override
    public void deleteStudentOnlyUsingSID() {
        Scanner sc = new Scanner(System.in);
        Transaction tx = s.beginTransaction();

        System.out.print("Enter Student ID to delete: ");
        int sid = sc.nextInt();
        sc.nextLine();

        Student student = s.get(Student.class, sid);
        if (student != null) {
            // This will delete student. If cascade = ALL on student's list, subjects will be deleted too.
            s.remove(student);
            tx.commit();
            System.out.println("Student deleted.");
        } else {
            System.out.println("Invalid Student ID!");
            tx.rollback();
        }
    }

    @Override
    public void deleteSpecificSubjectFromSpecificStudentUsingSID() {
        Scanner sc = new Scanner(System.in);
        Transaction tx = s.beginTransaction();

        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();
        sc.nextLine();

        Student student = s.get(Student.class, sid);
        if (student == null) {
            System.out.println("Invalid Student ID!");
            tx.rollback();
            return;
        }

        List<Subject> listSub = student.getListsub();
        if (listSub == null || listSub.isEmpty()) {
            System.out.println("This student has no subjects.");
            tx.rollback();
            return;
        }

        System.out.print("Enter Subject ID to delete: ");
        int subid = sc.nextInt();
        sc.nextLine();

        Subject subjectToDelete = null;
        Iterator<Subject> itr = listSub.iterator();
        while (itr.hasNext()) {
            Subject sub = itr.next();
            if (sub != null && sub.getSubId() == subid) {
                subjectToDelete = sub;
                itr.remove(); // unlink from student's list
                break;
            }
        }

        if (subjectToDelete == null) {
            System.out.println("Subject not found for this student.");
            tx.rollback();
            return;
        }

        // If Subject has a student collection (many-to-many), keep both sides consistent if present
        try {
            java.lang.reflect.Method m = subjectToDelete.getClass().getMethod("getStudents");
            Object studentsCol = m.invoke(subjectToDelete);
            if (studentsCol instanceof java.util.Collection) {
                ((java.util.Collection) studentsCol).remove(student);
            }
        } catch (Exception ignored) {
            // ignore if method not present
        }

        // delete subject entity from DB
        s.remove(subjectToDelete);

        tx.commit();
        System.out.println("Subject deleted from student and database.");
    }
}
