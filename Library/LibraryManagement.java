import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library();

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        // Access for singleton instance of transaction
        Transaction transaction = Transaction.getTransaction();

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                	System.out.print("Enter member name: ");
                    String memberName = scanner.next();
                  
                    scanner.nextLine();

                    if (library.addMember(new Member(memberId, memberName))) {
                    	System.out.println("Member added.");
                    } else {
                    System.out.println("Failed to add new member. Member ID is duplicate.");
                    }
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                	System.out.print("Enter book title: ");
                    String bookTitle = scanner.next();
                    
                    scanner.nextLine();

                    if (library.addBook(new Book(bookId, bookTitle))) {
                    	System.out.println("Book added.");
                    } else {
                    	System.out.println("Failed to add new book. Book ID is duplicate.");
                    }
                    break;
                case 3:
                	// To borrow book
                	performTransaction(scanner, transaction, true);
                	break;
                case 4:
                	// To return book
                	performTransaction(scanner, transaction, false);
                	break;
                case 5:
                	System.out.println("Enter member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine();
                    
                    Member specificMember = library.findMemberById(memberId);
                   
                    if (specificMember != null) {
                    	System.out.println("books borrowed by: " + specificMember.getName());
                    	for (Book book : specificMember.getBorrowedBooks()) {
                    		System.out.println("-" + book.getTitle());
                    	}
                    	} else {
                    		System.out.println("Invalid member ID.");
                    	}
                    	break;
                case 6:
                	// To display transaction history
                	System.out.println("Transaction Hisotry: ");
                	transaction.displayTransactionHistory();
                	break;
                case 7:
                	System.out.println("Exiting. Goodbye!");
                	running = false;
                	break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
        
}
private void performTransaction(Scanner scanner, Transaction transaction, boolean isBorrow) {
	System.out.print("Enter member ID: ");
	int memberId = scanner.nextInt();
	System.out.print("Enter book ID: ");
	int bookId = scanner.nextInt();
	scanner.nextLine();
	
	Member member = library.findMemberById(memberId);
	Book book = library.findBookById(bookId);
	
	if (member != null && book != null) {
		if (isBorrow) {
			transaction.borrowBook(book, member);
		}else {
			transaction.returnBook(book, member);
		}
	} else {
			System.out.println("Invalid book or member ID.");
		}
	}
}
