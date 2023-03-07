package ua.com.alevel.persistence.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.configuration.HibernateConfig;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.dto.CourseNumberByStudent;
import ua.com.alevel.persistence.entity.Student;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {
    private final SessionFactory factory = HibernateConfig.getInstance().getFactory();
    @Override
    public void create(Student entity) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CourseNumberByStudent> getNumberOfCoursesByStudents() {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select new ua.com.alevel.persistence.dto.CourseNumberByStudent(s, count(s.id)) from Student s join s.courseSet group by s.id");
            List<CourseNumberByStudent> students = query.getResultList();
            transaction.commit();
            return students;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Student entity) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student entity) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Student s where s.id = :id")
                            .setParameter("id", id);
            Student student = (Student) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(student);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Student");
            List<Student> students = query.getResultList();
            transaction.commit();
            return students;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
