package pro.sky.java.course4.work6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.java.course4.work6.model.Student;
import pro.sky.java.course4.work6.model.StudentCount;
import pro.sky.java.course4.work6.model.StudentsAverageAge;
import pro.sky.java.course4.work6.model.StudentsFromEndOfTheListById;
import pro.sky.java.course4.work6.repository.StudentRepository;
import pro.sky.java.course4.work6.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.debug("Requesting a method addStudent");
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.debug("Requesting a method findStudent");
        return studentRepository.findById(id).get();
    }

    @Override
    public void removeStudent(long id) {
        logger.debug("Requesting a method removeStudent");
        studentRepository.deleteById(id);
    }

    @Override
    public Student editStudent(Student student) {
        logger.debug("Requesting a method editStudent");
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> filterStudentByAge(int age) {
        logger.debug("Requesting a method filterStudentByAge");
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public Collection<Student> filterStudentsByAgeBetween(int min, int max) {
        logger.debug("Requesting a method filterStudentsByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public List<StudentCount> getCountStudents() {
        logger.debug("Requesting a method getCountStudents");
        return studentRepository.getCountStudents();
    }

    @Override
    public List<StudentsAverageAge> getStudentsAverageAge() {
        logger.debug("Requesting a method getStudentsAverageAge");
        return studentRepository.getStudentsAverageAge();
    }

    @Override
    public List<StudentsFromEndOfTheListById> getStudentsFromEndOfTheListById() {
        logger.debug("Requesting a method getStudentsFromEndOfTheListById");
        return studentRepository.getStudentsFromEndOfTheListById();
    }

    @Override
    public List<Student> findAllStudentsByAlphabet() {
        logger.debug("Requesting a method findAllStudentsByAlphabet");
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    @Override
    public OptionalDouble studentsMiddleAge() {
        logger.debug("Requesting a method studentsMiddleAge");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge).average();
    }

    @Override
    public String studentNameTestThread() {
        TestThread(0);
        TestThread(1);
        new Thread(() -> {
            TestThread(2);
            TestThread(3);
        }).start();
        new Thread(() -> {
            TestThread(4);
            TestThread(5);
        }).start();

        return null;
    }

    private void TestThread(int number) {
        List<Student> studentsName = studentRepository.findAll();
        System.out.println(studentsName.get(number));
    }

    @Override
    public synchronized String studentNameTestSynchronizedThread() {
        TestSynchronizedThread(0);
        TestSynchronizedThread(1);
        new Thread(() -> {
            TestSynchronizedThread(2);
            TestSynchronizedThread(3);
        }).start();
        new Thread(() -> {
            TestSynchronizedThread(4);
            TestSynchronizedThread(5);
        }).start();
        return null;
    }

    private synchronized void TestSynchronizedThread(int number) {
        List<Student> studentsName = studentRepository.findAll();
        System.out.println(studentsName.get(number));
    }


}
