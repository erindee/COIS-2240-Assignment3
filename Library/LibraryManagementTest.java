/*import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagementTest {
	
	// To test for valid Book IDs
	@Test
	public void testBookId() throws Exception{
		Book validBook1 = new Book(100, "Programming");
		Book validBook2 = new Book(999, "AI");
		Book invalidBook1 = new Book(1000, "Invalid");
		Book invalidBook2 = new Book(50, "Invalid");
		
		// Assertions for valid Ids
		assertNotNull(validBook1);
		assertNotNull(validBook2);
		
		// Assertions for invalid Ids
		try {
			invalidBook1 = new Book(1000, "Invalid");
			fail("exception for invalid id > 999");
		} catch (Exception x) {
			assertEquals("Invlaid book Id", x.getMessage());
		}
		
		try {
			invalidBook2 = new Book(50, "Invalid");
			fail("Exception for invalid Id < 100");
		} catch (Exception x) {
			assertEquals("Invalid book Id", x.getMessage());
		}
	} catch (Exception x) {
		fail("Exception should not happen here: " + x.getMessage());
	}
		
	}
	@Test
	public void testBorrowReturn() {
		Book book = new Book(100, "Programming");
		Member member = new Member(1111, "george");
		
		// Assertions for the book being available to borrow
		assertTrue(library.addBook(book));
		assertTrue(library.addMember(member));
		
		// To borrow book
		assertTrue(transaction.borrowBook(book, member));
		
		// Assertion for the book being unavailable
		assertFalse(book.isAvailable());
		
		// Attempt for borrowing the book again
		assertFalse(transaction.borrowBook(book, member));
		
		// To return book
		assertTrue(transaction.returnBook(book, member));
		
		// Assertion for the book being available again
		assertTrue(book.isAvailable());
		
		// To attempt to return book again
		assertFalse(transaction.returnBook(book, member));
	}
	
	@Test
	public void testSingletonTransaction() {
		try {
			// To test if we can retrieve the singleton instance
			Transaction instance1 = Transaction.getInstance();
			Transaction instance2 = Transaction.getInstance();
			
			// Assertion to see that both instances are the same
			
		}
	}
}*/
