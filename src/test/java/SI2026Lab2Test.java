import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SI2026Lab2Test {

    @Test
    public void searchBookEveryStatementTest() {
        Library library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));


        assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));

        List<Book> result = library.searchBookByTitle("Clean Code");
        assertNotNull(result);
        assertEquals(1, result.size());

        List<Book> result2 = library.searchBookByTitle("Harry Potter");
        assertNull(result2);


    }

    @Test
    public void borrowBookEveryBranchTest() {
        Library library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));

        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("", ""));

        assertThrows(RuntimeException.class, () -> library.borrowBook("Unknown", "Unknown"));

        library.borrowBook("Clean Code", "Robert C. Martin");
        assertTrue(library.countAvailableBooks() == 0);
        assertThrows(RuntimeException.class, () -> library.borrowBook("Clean Code", "Robert C. Martin"));
    }
    @Test
    public void searchBookMultipleConditionTest() {
        Library library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));

        assertNotNull(library.searchBookByTitle("Clean Code"));

        assertNull(library.searchBookByTitle("UnknownTitle"));

        library.borrowBook("Clean Code", "Robert C. Martin");
        assertNull(library.searchBookByTitle("Clean Code"));
    }

    @Test
    public void borrowBookMultipleConditionTest() {

        Library library = new Library();
        library.addBook(new Book(
                "Clean Code",
                "Robert C. Martin",
                "Programming"));

        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("", ""));

        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("SomeTitle", ""));

        library.borrowBook("Clean Code", "Robert C. Martin");

        assertTrue(library.countAvailableBooks() == 0);
    }
}