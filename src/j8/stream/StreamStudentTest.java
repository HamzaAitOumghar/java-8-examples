package j8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamStudentTest {

    public static void main(String[] args) {

        List<MobileNumber> mobileNumbers1 = new ArrayList<>();
        mobileNumbers1.add(new MobileNumber("3333"));
        mobileNumbers1.add(new MobileNumber("2222"));

        List<MobileNumber> mobileNumbers2 = new ArrayList<>();
        mobileNumbers2.add(new MobileNumber("1111"));
        mobileNumbers2.add(new MobileNumber("4444"));

        Student s1 = new Student("hamza",24,new Address("1412"),mobileNumbers2);
        Student s2 = new Student("ayoub",25,new Address("1234"),mobileNumbers1);
        Student s3 = new Student("merouan",29,new Address("1414"),mobileNumbers1);
        Student s4 = new Student("mohamed",33,new Address("1234"),mobileNumbers2);

        List<Student> students = Arrays.asList(s1,s2,s3,s4);
        //student with exact match name 'hamza'
        Optional<Student>  studentWithSameName =  students.stream().filter(s->s.getName().equals("hamza")).findAny();
        System.out.println(studentWithSameName.orElse(null));
        //student with matching adress 1234
        List<Student> studentsWithSameAddress  = students.stream().filter(s->s.getAdrdress().getZipCode().equals("1234")).collect(Collectors.toList());
        System.out.println(studentsWithSameAddress);
        //student having mobile numbers 3333
        Predicate<Student> checkMobileNumber = s->{
            for ( MobileNumber n: s.getMobileNumbers()) {
                if(n.getNumber().equals("3333")){
                    return true;
                }
            }
            return false;
        };
        List<Student> studentWithSameNumber = students.stream().filter(checkMobileNumber).collect(Collectors.toList());
        System.out.println(studentWithSameNumber);

        //List<TempStudent> from List<Student>
        Function<Student,TempStudent> transformStudentToTempStudent = s->new TempStudent(s.getName(),s.getAge(),s.getAdrdress(),s.getMobileNumbers());
        List<TempStudent> tempStudentList = students.stream().map(transformStudentToTempStudent).collect(Collectors.toList());
        System.out.println("size : " + tempStudentList.size());


        //List<Student> to List<String> of student name
        List<String> studentsNames = students.stream().map(s->s.getName()).collect(Collectors.toList());
        System.out.println(studentsNames);

        //sort List<String>
        List<String> sortedListName = studentsNames.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedListName);

        //Change the case of List<String>
        List<String> changedListName = studentsNames.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(changedListName);

        //List<String> to String
        String joinAllNames = studentsNames.stream().collect(Collectors.joining(", "));
        System.out.println(joinAllNames);



    }
}
