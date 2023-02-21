package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.persistence.config.JdbcConfig;
import ua.com.alevel.persistence.config.impl.JdbcConfigImpl;
import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.dto.CourseDto;
import ua.com.alevel.persistence.entity.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {
    private final JdbcConfig jdbcConfig = new JdbcConfigImpl();
    private static final String CREATE_COURSE = "insert into courses values(default, ?, ?, CURRENT_TIMESTAMP);";
    private static final String UPDATE_COURSE = "update courses set course_name = ?, course_subject = ? where id = ?";
    private static final String DELETE_COURSE = "delete from courses where id = ?;";
    private static final String FIND_COURSE_BY_ID = "select * from courses where id = ";
    private static final String FIND_ALL_COURSES = "select * from courses;";
    private static final String FIND_COURSES_BY_STUDENT_ID = "select id, course_name, course_subject, created_time from " +
            "courses left join stud_curses_deps on courses.id = stud_curses_deps.course_id where stud_id = ";
    private static final String FIND_STUDENTS_NUMBER_FOR_EACH_COURSE = "select id, course_name, course_subject, created_time, " +
            "count(stud_id) as number from courses left join stud_curses_deps scd on courses.id = scd.course_id group by id";

    @Override
    public List<CourseDto> findTheNumberOfStudentsForEachCourse() {
        ArrayList<CourseDto> list = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_STUDENTS_NUMBER_FOR_EACH_COURSE)) {
            while (resultSet.next()) {
                list.add(getCourseDtoFromResultSet(resultSet));
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public List<Course> findCoursesByStudentId(Long id) {
        ArrayList<Course> courses = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_COURSES_BY_STUDENT_ID + id)) {
            while (resultSet.next()) {
                courses.add(getCourseFromResultSet(resultSet));
            }
            return courses;
        } catch (Exception e) {
            return courses;
        }
    }

    @Override
    public Optional<Course> findById(Long id) {
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_COURSE_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(getCourseFromResultSet(resultSet));
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
        ArrayList<Course> list = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_ALL_COURSES)) {
            while (resultSet.next()) {
                list.add(getCourseFromResultSet(resultSet));
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(DELETE_COURSE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Course entity) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(UPDATE_COURSE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSubject());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Course entity) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(CREATE_COURSE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSubject());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CourseDto getCourseDtoFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setName(resultSet.getString("course_name"));
        course.setSubject(resultSet.getString("course_subject"));
        course.setCreatedTime(resultSet.getTimestamp("created_time"));
        int number = resultSet.getInt("number");
        return new CourseDto(course, number);
    }

    private Course getCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setName(resultSet.getString("course_name"));
        course.setSubject(resultSet.getString("course_subject"));
        course.setCreatedTime(resultSet.getTimestamp("created_time"));
        return course;
    }
}
