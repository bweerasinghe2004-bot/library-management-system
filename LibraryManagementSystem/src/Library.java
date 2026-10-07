import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<BorrowRecord> borrowRecords;

    public Library() {

        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowRecords = new ArrayList<>();
    }

    // ==================== BOOK METHODS ====================

    public boolean addBook(Book book) {

        if (findBook(book.getBookId()) != null) {
            return false;
        }

        books.add(book);
        return true;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book findBook(String bookId) {

        for (Book book : books) {

            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }

        return null;
    }

    public boolean removeBook(String bookId) {

        Book book = findBook(bookId);

        if (book == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        books.remove(book);

        return true;
    }

    // ==================== MEMBER METHODS ====================

    public boolean addMember(Member member) {

        if (findMember(member.getMemberId()) != null) {
            return false;
        }

        members.add(member);

        return true;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member findMember(String memberId) {

        for (Member member : members) {

            if (member.getMemberId()
                    .equalsIgnoreCase(memberId)) {

                return member;
            }
        }

        return null;
    }

    // ==================== BORROW BOOK ====================

    public String borrowBook(String bookId, String memberId) {

        Book book = findBook(bookId);

        Member member = findMember(memberId);

        if (book == null) {
            return "Book not found.";
        }

        if (member == null) {
            return "Member not found.";
        }

        if (!book.isAvailable()) {
            return "Book is already borrowed.";
        }

        book.setAvailable(false);

        BorrowRecord record =
                new BorrowRecord(book, member);

        borrowRecords.add(record);

        return "Book borrowed successfully.";
    }

    // ==================== RETURN BOOK ====================

    public String returnBook(String bookId) {

        Book book = findBook(bookId);

        if (book == null) {
            return "Book not found.";
        }

        if (book.isAvailable()) {
            return "Book is already available.";
        }

        for (BorrowRecord record : borrowRecords) {

            if (record.getBook() == book
                    && !record.isReturned()) {

                record.setReturned(true);

                book.setAvailable(true);

                return "Book returned successfully.";
            }
        }

        return "Borrow record not found.";
    }


    public BorrowRecord findBorrowRecord(String bookId) {

    Book book = findBook(bookId);

    if (book == null) {
        return null;
    }

    for (BorrowRecord record : borrowRecords) {

        if (record.getBook() == book && !record.isReturned()) {
            return record;
        }
    }

    return null;
}

    // ==================== DASHBOARD ====================

    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooks() {

        int count = 0;

        for (Book book : books) {

            if (book.isAvailable()) {
                count++;
            }
        }

        return count;
    }

    public int getBorrowedBooks() {

        return getTotalBooks()
                - getAvailableBooks();
    }

    public int getTotalMembers() {

        return members.size();
    }
}