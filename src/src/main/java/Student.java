import java.security.PublicKey;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String course_name;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", course_name='" + course_name + '\'' +
                '}';
    }

    // В случае при не поступлении данных, функция не выполняет никаких операций, предотвращение ошибки!
    public Student() {}

    //Конструктор для сохранение значений переменных
    public Student(int id, String name, String surname, String course_name) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course_name = course_name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
