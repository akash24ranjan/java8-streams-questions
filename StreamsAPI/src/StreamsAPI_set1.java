import com.example.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;

public class StreamsAPI_set1 {
    public static void main(String[] args) {
        Student student1 = new Student("Jayesh",20,new Address("1234"),
                Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

        Student student2 = new Student("Khyati",20,new Address("1235"),
                Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

        Student student3 = new Student("Jason",20,new Address("1236"),
                Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        List<Student> students = Arrays.asList(student1, student2, student3);

        // 1. Get student with exact match name "jayesh"
        Optional<Student> stud = students.stream().
                filter(student -> student.getName().equals("Jayesh"))
                .findFirst();
        System.out.println(stud.isPresent() ? stud.get().getName() : "No student Found");
        System.out.println("========================================");

        // 2. Get student with matching address "1235"
        Optional<Student> stud1 = students.stream().
                filter(student -> student.getAddress().getZipcode().equals("1235")).
                findFirst();
        System.out.println(stud1.isPresent() ? stud1.get().getName() : "No student Found");
        System.out.println("========================================");

        // 3. Get all student having mobile numbers 3333.
        List<Student> stud3 = students.stream().
                filter(student -> student.getMobileNumbers().stream().anyMatch(mobileNumber -> Objects.equals(mobileNumber.getNumber(),"3333"))).collect(Collectors.toList());
        String result2 = stud3.stream().map(Student::getName).collect(Collectors.joining(",","[","]"));
        System.out.println(result2);
        System.out.println("========================================");

        // 4. Get all student having mobile number 1233 and 1234
        List<Student> stud4 = students.stream().
                filter(student ->student.getMobileNumbers().stream()
                        .allMatch(mobileNumber -> Objects.equals(mobileNumber.getNumber(),"1233") || Objects.equals(mobileNumber.getNumber(),"1234")))
                .toList();
        String result4 = stud4.stream().map(Student::getName).collect(Collectors.joining(",","[","]"));
        System.out.println(result4);
        System.out.println("========================================");

        // 5. Create a List<Student> from the List<TempStudent>
        TempStudent tmpStud1 = new TempStudent(
                "Jayesh1",
                201,
                new Address("12341"),
                Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

        TempStudent tmpStud2 = new TempStudent(
                "Khyati1",
                202,
                new Address("12351"),
                Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

        List<TempStudent> tmpStudents = Arrays.asList(tmpStud1, tmpStud2);

        List<Student> studentList = tmpStudents.stream()
                .map(tmpStud -> new Student(tmpStud.name, tmpStud.age, tmpStud.address, tmpStud.mobileNumbers))
                .collect(Collectors.toList());

        System.out.println(studentList);
        System.out.println("========================================");


        // 6. Convert List<Student> to List<String> of student name
        List<String> studentsName = students.stream().map(Student::getName).collect(Collectors.toList());
        String studentName = studentsName.stream().collect(Collectors.joining(",","[","]"));
        System.out.println(studentName);
        System.out.println("========================================");

        // 7. Convert List<students> name to String
        String studentString = students.stream().
                map(Student::getName).collect(Collectors.joining(",","[","]"));
        System.out.println(studentString);

        System.out.println("========================================");

        // 8. Change the case of List<String>
        studentString = students.stream().map(Student::getName).
                map(String::toUpperCase)
                .collect(Collectors.joining(",","[","]"));
        System.out.println(studentString);

        System.out.println("========================================");

        // 9. Sort List<String>
        students.stream().map(Student::getName).sorted().forEach(System.out::println);

        System.out.println("========================================");

        // 10. Conditionally apply Filter condition, say if flag is enabled then.

        Stream<Student> conditionalFilterResult = students.stream()
                .filter(std -> std.getName().startsWith("J"));

        conditionalFilterResult = conditionalFilterResult.sorted(Comparator.comparing(Student::getName));

        System.out.println("Before sorting :");
        students.forEach(s -> System.out.println(s.getName()));

        List<Student> list = conditionalFilterResult.collect(Collectors.toList());
        System.out.println("After filter and conditional sorting :");
        list.forEach(s -> System.out.println(s.getName()));


        //11. find character with max freq.
        System.out.println("========================================");
        String str = "akashranjan";
        var ch=str.chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(x->x, Collectors.counting()))
                .entrySet()
                .stream()
                .max(comparingByValue())
                .get()
                .getKey();
        System.out.println(ch);
    }
}
