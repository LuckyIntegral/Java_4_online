package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.persistence.config.JdbcConfig;
import ua.com.alevel.persistence.config.impl.JdbcConfigImpl;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.dto.StudentDto;
import ua.com.alevel.persistence.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {
    private final JdbcConfig jdbcConfig = new JdbcConfigImpl();
    private static final String CREATE_STUDENT = "insert into students values(default, ?, ?, ?, CURRENT_TIMESTAMP);";
    private static final String UPDATE_STUDENT = "update students set first_name = ?, last_name = ?, age = ? where id = ?;";
    private static final String DELETE_STUDENT = "delete from students where id = ?;";
    private static final String FIND_STUDENT_BY_ID = "select * from students where id = ";
    private static final String FIND_ALL_STUDENTS = "select * from students;";
    private static final String ATTACH_STUD_TO_COURSE = "insert into stud_curses_deps values (?, ?);";
    private static final String REMOVE_STUD_FROM_CURSE = "delete from stud_curses_deps where stud_id = ? and course_id = ?;";
    private static final String FIND_STUDENTS_BY_COURSE_ID = "select id, first_name, last_name, age, created_time from students\n" +
            "left join stud_curses_deps scd on students.id = scd.stud_id where course_id = ";
    private static final String FIND_NUMBER_OF_COURSES_FOR_EACH_STUDENT = "select id, first_name, last_name, age, created_time, " +
            "count(course_id) as number from students left join stud_curses_deps scd on students.id = scd.stud_id group by id";


    @Override
    public List<StudentDto> findTheNumberOfCoursesForEachStudent() {
        ArrayList<StudentDto> list = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_NUMBER_OF_COURSES_FOR_EACH_STUDENT)) {
            while (resultSet.next()) {
                list.add(getStudentDtoFromResultModel(resultSet));
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public List<Student> findStudentsByCourseId(Long id) {
        ArrayList<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_STUDENTS_BY_COURSE_ID + id)) {
            while (resultSet.next()) {
                students.add(getStudentFromResultSet(resultSet));
            }
            return students;
        } catch (Exception e) {
            return students;
        }
    }

    @Override
    public void attachStudentToCourse(Long studId, Long courseId) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(ATTACH_STUD_TO_COURSE)) {
            preparedStatement.setLong(1, studId);
            preparedStatement.setLong(2, courseId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStudentFromCourse(Long studId, Long courseId) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(REMOVE_STUD_FROM_CURSE)) {
            preparedStatement.setLong(1, studId);
            preparedStatement.setLong(2, courseId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_STUDENT_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(getStudentFromResultSet(resultSet));
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        ArrayList<Student> list = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getStatement().executeQuery(FIND_ALL_STUDENTS)) {
            while (resultSet.next()) {
                list.add(getStudentFromResultSet(resultSet));
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Student entity) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Student entity) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(CREATE_STUDENT)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));
        student.setCreatedTime(resultSet.getTimestamp("created_time"));
        student.setId(resultSet.getLong("id"));
        return student;
    }

    private StudentDto getStudentDtoFromResultModel(ResultSet resultSet) throws SQLException {
        int number = resultSet.getInt("number");
        return new StudentDto(getStudentFromResultSet(resultSet), number);
    }
}
