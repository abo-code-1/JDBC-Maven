import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CRUDUtils {
    private static String INSERT_STUDENT = "INSERT INTO students(name, surname, course_name) VALUES(?, ?, ?)";
    private static String UPDATE_STUDENT = "UPDATE students SET course_name = ? WHERE id = ?";
    private static String DELETE_STUDENT = "DELETE FROM students WHERE id = ?";

    public static List<Student> getStudentData(String query) throws SQLException {
        List<Student> students = new ArrayList<Student>();

        /* В приведенном коде используется конструкция try-with-resources, которая автоматически
           управляет закрытием ресурсов (таких как соединения с базой данных, файлы и т.д.) после
           завершения блока try. Эти ресурсы должны реализовывать интерфейс AutoCloseable
           (или его более узкий вариант — Closeable).
         */
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String course_name = resultSet.getString("course_name");
                students.add(new Student(id, name, surname, course_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Student> saveStudent(Student student) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getCourse_name());
            ps.executeUpdate();

            PreparedStatement allStudent = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudent.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");
                students.add(new Student(id, name, surname, course_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public static List<Student> UpdateStudent(String courseName, int id) throws SQLException{
        List<Student> students = new ArrayList<>();
        try(Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_STUDENT)) {
            ps.setString(1, courseName);
            ps.setInt(2, id);
            ps.executeUpdate();

            PreparedStatement allStudents = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudents.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");
                students.add(new Student(id, name, surname, course_name));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }


    public static List<Student> DeleteStudent(int id) throws SQLException{
        List<Student> students = new ArrayList<>();
        try(Connection conn = DBUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT);
        ){
            ps.setInt(1,id);
            ps.executeUpdate();

            PreparedStatement allStudents = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudents.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");
                students.add(new Student(id, name, surname, course_name));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
}
