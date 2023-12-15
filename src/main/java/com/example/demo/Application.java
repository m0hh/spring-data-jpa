package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        StudentIdCardRepository studentIdCardRepository,
                                        BookRepository bookRepository) {
        return args -> {
//            Student maria = new Student("Maria","Jones","maria@gmail.com",18 );
//            Student maria2 = new Student("Maria","Jones","maria2@gmail.com",21 );
//            Student maria3 = new Student("Maria","Jones","maria3@gmail.com",25 );
//            Student ahmed = new Student("Ahmed","Ali","ahmed@gmail.com",18 );
//
//            System.out.println("Adding Maria and Ahmed");
//            studentRepository.saveAll(List.of(maria,ahmed,maria2,maria3));

//            System.out.println("Number of students: ");
//            System.out.println(studentRepository.count());
//            studentRepository
//                    .findById(2L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> System.out.println("Student with Id 2 Not found"));
//            studentRepository
//                    .findById(3L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> System.out.println("Student with Id 3 Not found"));
//
//            System.out.println("Select all students: ");
//            List<Student> students = studentRepository.findAll();
//            students.forEach(System.out::println);
//
//            System.out.println("Delete maria ");
//            studentRepository.deleteById(1L);
//
//            System.out.println("Number of students ");
//            System.out.println(studentRepository.count());
//            studentRepository.findStudentByEmail("ahmed@gmail.com")
//                    .ifPresentOrElse(System.out::println, ()-> System.out.println("Student with email not found"));
//            System.out.println("Deleting Maria 3");
//            System.out.println(studentRepository.deleteStudentById(3L));
//
//            studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual("Maria", 21)
//                    .forEach(System.out::println);
//
//            studentRepository.findByFNameAndAgeparams("Maria", 21).forEach(System.out::println);
//            generateRandmStudents(studentRepository);
//            PageRequest pageRequest = PageRequest.of(0,5,Sort.by("firstName").ascending());
//            Page<Student> page = studentRepository.findAll(pageRequest);
//            System.out.println(page);
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName,lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17,55)
            );
            student.addBook(new Book("Clean Code", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("Think and Grow Rich", LocalDateTime.now()));
            student.addBook(new Book("Spring Data Jpa", LocalDateTime.now().minusYears(1)));

            StudentIdCard studentIdCard = new StudentIdCard("12345689", student);
            studentIdCardRepository.save(studentIdCard);
            studentRepository.findAll().forEach(System.out::println);

//            studentRepository.findById(1L).ifPresent(System.out::println);
//            studentIdCardRepository.findById(1L).ifPresent(System.out::println);

            //studentRepository.deleteById(1L);


//            Book book = new Book(student, LocalDateTime.now(),"Harry Potter and The Chamber of secrets");
//            bookRepository.save(book);
//            bookRepository.findAll().forEach(System.out::println);


        };
    }

//    private static void sorting(StudentRepository studentRepository) {
//        Sort sort = Sort.by("firstName").ascending()
//                .and(Sort.by("age").descending());
//        studentRepository.findAll(sort)
//                .forEach(student -> System.out.println(student.getFirstName() + " "+ student.getAge()));
//    }

    private static void generateRandmStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i =0; i < 20; i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName,lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17,55)
                    );
            studentRepository.save(student);
        }
    }

}
