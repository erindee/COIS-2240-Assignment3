public class Book {
    private int id;
    private String title;
    private boolean isAvailable;
   
    // Constructor to validate book ID
    public Book(int id, String title) throws Exception {
    	this.id = id;
        if (!isValidId()) {
        	throw new Exception("Invalid Book ID");
        }
        this.title = title;
        this.isAvailable = true;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    // Method to borrow the book
    public void borrowBook() throws Exception{
       if (isAvailable) {
           isAvailable = false;
       } else {
    	   throw new Exception("Book is already being borrowed");
       }
    }

    // Method to return the book
    public void returnBook() throws Exception{
    	if (!isAvailable) {
    		isAvailable = true;
    } else {
    	throw new Exception("Book has already been returned");
    }
    }
    
    public boolean isValidId() {
        return this.id >= 100 && this.id <= 999;
    }

}

