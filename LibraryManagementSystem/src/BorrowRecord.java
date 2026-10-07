public class BorrowRecord {

    private Book book;
    private Member member;
    private boolean returned;

    public BorrowRecord(Book book, Member member) {

        this.book = book;
        this.member = member;
        this.returned = false;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {

        return "Book: " + book.getTitle()
                + " | Member: " + member.getName()
                + " | Status: "
                + (returned ? "Returned" : "Borrowed");
    }
}