import dataStructures.MyArrayList;
import dataStructures.MyHashSet;
import models.Book;
import models.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student("Кирил", Arrays.asList(
                        new Book("Книга 1", 1990, 200),
                        new Book("Книга 2", 1980, 300),
                        new Book("Книга 3", 1960, 100),
                        new Book("Книга 4", 2014, 1000),
                        new Book("Книга 5", 1995, 500)
                )),
                new Student("Анатолий", Arrays.asList(
                        new Book("Книга 6", 1998, 600),
                        new Book("Книга 7", 1999, 50),
                        new Book("Книга 8", 2016, 200),
                        new Book("Книга 9", 2020, 400),
                        new Book("Книга 10", 1995, 600)
                )),
                new Student("Михаил", Arrays.asList(
                        new Book("Книга 1", 1990, 200),
                        new Book("Книга 2", 1980, 300),
                        new Book("Книга 8", 2016, 200),
                        new Book("Книга 9", 2020, 400),
                        new Book("Книга 10", 1995, 600)
                )),
                new Student("Петя", Arrays.asList(
                        new Book("Книга 6", 1998, 600),
                        new Book("Книга 7", 1999, 50),
                        new Book("Книга 8", 2016, 200),
                        new Book("Книга 4", 2014, 1000),
                        new Book("Книга 5", 1995, 500)
                ))
        );
        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Book::getPageNumber))
                .distinct()
                .filter(book -> book.getPublicationDate() > 2000)
                .limit(3)
                .findFirst()
                .map(Book::getPublicationDate)
                .ifPresentOrElse(
                        publicationDate -> System.out.println("Книга выпущена: " + publicationDate),
                        () -> System.out.println("Такой книги нет!")
                );
    }
}
