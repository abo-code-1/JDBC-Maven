import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {
        Student student1 = new Student();
        student1.setName("Abror");
        student1.setSurname("Imamsidikov");
        student1.setCourse_name("M55");

        Student student2 = new Student();
        student2.setName("Diana");
        student2.setSurname("Karimova");
        student2.setCourse_name("B23");

        Student student3 = new Student();
        student3.setName("Sofia");
        student3.setSurname("Novikova");
        student3.setCourse_name("G77");


        CRUDUtils.saveStudent(student1);
        CRUDUtils.saveStudent(student2);
        CRUDUtils.UpdateStudent("Cs32", 1);




        List<Student> result3 = CRUDUtils.saveStudent(student3);
        System.out.print(result3);

        List<Student> resultOfDel = CRUDUtils.DeleteStudent(1);
        System.out.print(resultOfDel);





    }
}
