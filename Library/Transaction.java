import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	//Singleton
	private static Transaction transactionInstance;
	
	// Constructor for preventing direct instantiation
	private Transaction() {}
	
	// Get the singleton
	public static Transaction getTransaction() {
		if (transactionInstance == null) {
			transactionInstance = new Transaction ();
		}
		return transactionInstance;
    }
	// To perform a book being borrowed
	public void borrowBook(Book book, Member member) {
		if (book.isAvailable()) {
			book.borrowBook();
			member.borrowBook(book);
			String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
			System.out.println(transactionDetails);
			saveTransaction(transactionDetails);
        } else {
            System.out.println("The book is not available.");
        }
        
	} // To perform the return of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }
    private void saveTransaction(String transactionDetails) {
    	try (FileWriter writer = new FileWriter("transactions.txt", true)) {
    		writer.write(transactionDetails + "\n");
    	} catch (IOException e) {
    		System.out.println("Error occured while saving transaction: " + e.getMessage());
    	}
    }
    // Get the current date and time
    private String getCurrentDateTime() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());    
    }
}