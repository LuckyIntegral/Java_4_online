package ua.com.alevel.persistence.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.configuration.HibernateConfig;
import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.dto.StudentNumberByCourseDto;
import ua.com.alevel.persistence.entity.Course;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {
    private final SessionFactory factory = HibernateConfig.getInstance().getFactory();
    @Override
    public void create(Course entity) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentNumberByCourseDto> getNumberOfStudentByCourses() {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select new ua.com.alevel.persistence.dto.StudentNumberByCourseDto(c, count(c.id)) from Course c join c.studentSet group by c.id");
            List<StudentNumberByCourseDto> courses = query.getResultList();
            transaction.commit();
            return courses;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Course entity) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Course entity) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Course c where c.id = :id")
                            .setParameter("id", id);
            Course course = (Course) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(course);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAll() {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Course");
            List<Course> courses = query.getResultList();
            transaction.commit();
            return courses;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
