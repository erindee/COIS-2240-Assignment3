import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;


public class LibraryManagementTest {
	//private LibraryManagement libraryManagement;
	private Library library;
	private Transaction transaction;
	
	@Before
	public void setUp() {
		library = new Library();
		transaction = Transaction.getTransaction();		
	}

	@Test
	public void testBookId() {
		try {
        	 // To test with valid IDs
        	 Book validBook1 = new Book(100, "Valid Book 1");
             assertTrue("Valid book ID should pass", validBook1.isValidId());
             
             Book validBook2 = new Book(999, "Valid Book 2");
             assertTrue("Valid book ID should pass", validBook2.isValidId());
             
             // To test with invalid IDs
             try {
                 Book invalidBook1 = new Book(1000, "Invalid Book 1");
                 fail("Exception should be thrown for invalid book ID");
             } catch (Exception x) {
                 assertEquals("Invalid ID", x.getMessage());
             }
             
             try {
                 Book invalidBook2 = new Book(99, "Invalid Book 2");
                 fail("Exception should be thrown for invalid book ID");
             } catch (Exception x) {
                 assertEquals("Invalid Book ID", x.getMessage());
             }
		 } catch (Exception x) {
		        fail("Exception should not occur: " + x.getMessage());
		 }

    }
	@Test
	 public void testBorrowReturn() throws Exception {
		// To create member and book object
		 Member member = new Member(1, "Erin");
	        Book book = new Book(101, "Programming Book");
	        // To add member and book to library system
	        library.addMember(member);
	        library.addBook(book);
	        
	        // To borrow the book
	        transaction.borrowBook(book, member);
	        
	        // To see if the book has been borrowed right
	        assertTrue("Book should be borrowed", member.getBorrowedBooks().contains(book));
	        
	        // To try borrowing the book again
	        try {
	            transaction.borrowBook(book, member);
	            fail("Book should not be borrowed again");
	        } catch (Exception x) {
	            assertEquals("Book already being borrowed", x.getMessage());
	        }
	        
	        // To return the book
	        transaction.returnBook(book, member);

	        // To see if the book has been returned
	        assertFalse("Book should be removed from borrowed list", member.getBorrowedBooks().contains(book));

	        // To try returning the same book again
	        try {
	            transaction.returnBook(book, member);
	            fail("Book should not be returned again");
	        } catch (Exception x) {
	            assertEquals("Book has already been returned", x.getMessage());
	        }
	}
	
	
	@Test
    public void testSingletonTransaction() {
        try {
            // To get the constructor
            Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
            
            // Make the constructor accessible
            constructor.setAccessible(true);
            
            // To see that the constructor is private
            int modifiers = constructor.getModifiers();
            assertEquals("Constructor should be private", Modifier.PRIVATE, modifiers);
        } catch (Exception x) {
            fail("Exception in testSingletonTransaction: " + x.getMessage());
        }
	}
}
	
	
	
	
	
	
	
	
	
	