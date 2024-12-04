import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
	public void borrowBook(Book book, Member member) throws Exception {
		if (book.isAvailable()) {
			book.borrowBook();
			member.borrowBook(book);
			String transactionDetails = getCurrentDateTime() + ": " + member.getName() + " borrowed " + book.getTitle();
			System.out.println(transactionDetails);
			saveTransaction(transactionDetails);
        } else {
        	 throw new Exception("Book already being borrowed");        }
        
	} // To perform the return of a book
    public void returnBook(Book book, Member member) throws Exception {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + ": " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
        	throw new Exception("Book has already been returned");
        }
    }
    private void saveTransaction(String transactionDetails) {
    	try (FileWriter writer = new FileWriter("transactions.txt", true)) {
    		writer.write(transactionDetails + "\n");
    	} catch (IOException e) {
    		System.out.println("Error occured while saving transaction: " + e.getMessage());
    	}
    }
    public void displayTransactionHistory() {
    	File file = new File("transactions.txt");
    	if (!file.exists()) {
    		System.out.println("No transaction history found.");
    		return;
    	}
    	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
    		String line;
    		System.out.println("Transaction History: ");
    		while ((line = reader.readLine()) != null) {
    			System.out.println(line);
    		}
    	} catch (IOException e) {
    		System.out.println("Error occured while reading transaction history.");
    	}
    }
    // Get the current date and time
    private String getCurrentDateTime() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());    
    }
}