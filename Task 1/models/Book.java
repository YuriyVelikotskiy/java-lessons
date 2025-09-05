package models;

public class Book {
    private String name;
    private int publicationDate;
    private int pageNumber;

    public Book(String name, int publicationDate, int pageNumber) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.pageNumber = pageNumber;
    }

    public String getName() {
        return name;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public String toString() {
        return "Book{" + "name='" + name + '\'' + ", publicationDate=" + publicationDate + ", pageNumber=" + pageNumber + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (publicationDate != book.publicationDate) return false;
        if (pageNumber != book.pageNumber) return false;
        return name != null ? name.equals(book.name) : book.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + publicationDate;
        result = 31 * result + pageNumber;
        return result;
    }
}
