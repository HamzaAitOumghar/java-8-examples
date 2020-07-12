package j8.collection;

import java.util.Arrays;
import java.util.Comparator;

class Student implements Comparable<Student>{

    String id;
    String name;
    Double score;

    public Student(String id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return " \n " + id + " \t " + name + " \t " + score;
    }

    @Override
    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }
}

class ScoreComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        return o2.getScore().compareTo(o1.getScore());
    }
}


public class ComparableTest {

    public static void main(String[] args) {
        Student[] students = {new Student("cs091","hamza",3.2),
                              new Student("cs042","ayoub",3.1),
                              new Student("cs021","ikram",2.9)};

        System.out.println("before sorting : ");
        System.out.println(Arrays.asList(students));
        Arrays.sort(students);
        System.out.println("after sorting sorting : ");
        System.out.println(Arrays.asList(students));

        //using ScoreComparator :

        System.out.println("before sorting : ");
        System.out.println(Arrays.asList(students));
        Arrays.sort(students,new ScoreComparator());
        System.out.println("after sorting sorting : ");
        System.out.println(Arrays.asList(students));


    }

}
