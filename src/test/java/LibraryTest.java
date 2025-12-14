import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ma.houcine.Book;
import ma.houcine.Member;
import ma.houcine.Library;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    // Réinitialiser l'état de la bibliothèque avant chaque test
    @BeforeEach
    void setUp() {
        Library.reset();
    }

    // -----------------------------------------------------
    // Tests 1 & 2 : Ajout de livres
    // -----------------------------------------------------

    @Test
    void test1_addBookSuccess() {
        // 1. Vérifie si l'ajout d'un livre fonctionne.
        Book book = new Book(101, "CI Basics", "J. Doe");
        assertTrue(Library.addBook(book));
        assertEquals(1, Library.getBooks().size());
    }

    @Test
    void test2_addBookDuplicateIdFails() {
        // 2. Vérifie si on ne peut pas ajouter deux livres avec le même ID.
        Library.addBook(new Book(101, "CI Basics", "J. Doe"));
        assertFalse(Library.addBook(new Book(101, "CI Advanced", "A. Smith")));
        assertEquals(1, Library.getBooks().size()); // La taille ne doit pas changer
    }

    // -----------------------------------------------------
    // Tests 3 & 4 : Ajout de membres
    // -----------------------------------------------------

    @Test
    void test3_addMemberSuccess() {
        // 3. Vérifie si l'ajout d'un membre fonctionne.
        Member member = new Member(1, "Alice");
        assertTrue(Library.addMember(member));
    }

    @Test
    void test4_addMemberDuplicateIdFails() {
        // 4. Vérifie si on ne peut pas ajouter deux membres avec le même ID.
        Library.addMember(new Member(1, "Alice"));
        assertFalse(Library.addMember(new Member(1, "Bob")));
    }

    // -----------------------------------------------------
    // Tests 5 à 7 : Emprunt de livres
    // -----------------------------------------------------

    @Test
    void test5_isBookAvailableInitially() {
        // 5. Vérifie qu'un nouveau livre est disponible.
        Library.addBook(new Book(102, "Jenkins Guide", "W. Bouarifi"));
        assertTrue(Library.getBooks().get(0).isAvailable());
    }

    @Test
    void test6_borrowBookSuccess() {
        // 6. Vérifie qu'un emprunt réussi change le statut du livre.
        Library.addBook(new Book(103, "CI/CD Pipeline", "S. Johnson"));
        Library.addMember(new Member(2, "Charlie"));

        assertTrue(Library.borrowBook(103, 2));
        assertFalse(Library.getBooks().get(0).isAvailable());
    }

    @Test
    void test7_borrowBookWhenUnavailableFails() {
        // 7. Vérifie qu'on ne peut pas emprunter un livre déjà emprunté.
        Library.addBook(new Book(104, "DevOps Culture", "M. Brown"));
        Library.addMember(new Member(3, "David"));

        Library.borrowBook(104, 3); // Premier emprunt (réussi)
        assertFalse(Library.borrowBook(104, 3)); // Deuxième emprunt (doit échouer)
    }

    // -----------------------------------------------------
    // Tests 8 à 10 : Retour et erreurs
    // -----------------------------------------------------

    @Test
    void test8_returnBookSuccess() {
        // 8. Vérifie qu'un retour réussi rend le livre disponible.
        Library.addBook(new Book(105, "Unit Testing", "E. White"));
        Library.addMember(new Member(4, "Eve"));
        Library.borrowBook(105, 4);

        assertTrue(Library.returnBook(105));
        assertTrue(Library.getBooks().get(0).isAvailable());
    }

    @Test
    void test9_borrowNonExistentBookFails() {
        // 9. Vérifie qu'on ne peut pas emprunter un livre qui n'existe pas.
        Library.addMember(new Member(5, "Frank"));
        assertFalse(Library.borrowBook(999, 5)); // 999 n'existe pas
    }

    @Test
    void test10_returnBookThatWasNeverBorrowedFails() {
        // 10. Vérifie qu'on ne peut pas rendre un livre disponible.
        Library.addBook(new Book(106, "Java Fundamentals", "G. Green"));
        assertFalse(Library.returnBook(106));
    }
}
