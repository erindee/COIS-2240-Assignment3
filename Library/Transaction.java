import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	//Singleton
	private static Transaction instance;
	
	// Constructor for preventing instantiation
	private Transaction() {}
	
	// Get the singleton
	public static Transaction getTransaction() {
		if (instance == null) {
			instance = new Transaction ();
		}
		return instance;
    }
	// To perform a book being borrowed
	public boolean borrowBook(Book book, Member member) {
		if (book.isAvailable()) {
			book.borrowBook();
			member.borrowBook(book);
			System.out.println(getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle());
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
        
	} // To perform the return of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            System.out.println(getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
        
    } // Get the current date and time
    private String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}