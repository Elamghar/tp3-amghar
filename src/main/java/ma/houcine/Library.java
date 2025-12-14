package ma.houcine;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    // Simuler la base de données avec des listes statiques
    private static final List<Book> books = new ArrayList<>();
    private static final List<Member> members = new ArrayList<>();

    // Méthodes statiques pour réinitialiser l'état (utile pour les tests)
    public static void reset() {
        books.clear();
        members.clear();
    }
    public static List<Book> getBooks() {
        return books;
    }

    // --- LOGIQUE D'AJOUT ---

    public static boolean addBook(Book book) {
        if (books.stream().anyMatch(b -> b.getId() == book.getId())) {
            return false; // Livre existe déjà
        }
        books.add(book);
        return true;
    }

    public static boolean addMember(Member member) {
        if (members.stream().anyMatch(m -> m.getId() == member.getId())) {
            return false; // Membre existe déjà
        }
        members.add(member);
        return true;
    }

    // --- LOGIQUE D'EMPRUNT / RETOUR ---

    public static boolean borrowBook(int bookId, int memberId) {
        Optional<Book> bookOpt = books.stream().filter(b -> b.getId() == bookId).findFirst();
        Optional<Member> memberOpt = members.stream().filter(m -> m.getId() == memberId).findFirst();

        if (bookOpt.isEmpty() || memberOpt.isEmpty()) {
            return false; // Livre ou membre non trouvé
        }

        Book book = bookOpt.get();
        if (book.isAvailable()) {
            book.setAvailable(false);
            return true; // Emprunt réussi
        }
        return false; // Livre non disponible
    }

    public static boolean returnBook(int bookId) {
        Optional<Book> bookOpt = books.stream().filter(b -> b.getId() == bookId).findFirst();

        if (bookOpt.isEmpty()) {
            return false; // Livre non trouvé
        }

        Book book = bookOpt.get();
        if (!book.isAvailable()) {
            book.setAvailable(true);
            return true; // Retour réussi
        }
        return false; // Livre déjà rendu ou n'a jamais été emprunté
    }
}