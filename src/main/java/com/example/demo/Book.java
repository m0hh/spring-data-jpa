package com.example.demo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence",sequenceName = "book_sequence",allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne()
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_book_fk"),
            nullable = false
    )
    private Student student;
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bookName;

    public Book(String bookName, LocalDateTime createdAt) {
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", student=" + student +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
