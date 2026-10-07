import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibraryGUI extends JFrame {

    private Library library;

    // Main panels
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // Dashboard labels
    private JLabel totalBooksLabel;
    private JLabel availableBooksLabel;
    private JLabel borrowedBooksLabel;
    private JLabel totalMembersLabel;

    
    // Book management fields
    private JTextField bookIdField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField categoryField;
    private JTextField searchField;

    // Member management fields
private JTextField memberIdField;
private JTextField memberNameField;
private JTextField contactField;
private JTextField memberSearchField;

    // Book table
    private JTable bookTable;
    private DefaultTableModel bookTableModel;

    private JTable memberTable;
private DefaultTableModel memberTableModel;

private JTable borrowTable;
private DefaultTableModel borrowTableModel;
private JTable returnTable;
private DefaultTableModel returnTableModel;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LibraryGUI() {

        library = new Library();

        setTitle("University Library Management System");

        setSize(1100, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createInterface();
    }


    // =========================================================
    // MAIN INTERFACE
    // =========================================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        // ---------------- HEADER ----------------

        JPanel headerPanel =
                new JPanel(new BorderLayout());

                headerPanel.setBackground(
        Color.decode("#1F3C88")
);

        headerPanel.setPreferredSize(
                new Dimension(1100, 80)
        );

        JLabel titleLabel =
                new JLabel(
                        "UNIVERSITY LIBRARY MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(
                titleLabel,
                BorderLayout.CENTER
        );


        // ---------------- LEFT MENU ----------------

        JPanel menuPanel =
                new JPanel();

                menuPanel.setBackground(
        Color.decode("#1F3C88")
);

        menuPanel.setPreferredSize(
                new Dimension(210, 600)
        );

        menuPanel.setLayout(
                new GridLayout(
                        6,
                        1,
                        10,
                        15
                )
        );
for (Component component : menuPanel.getComponents()) {
    if (component instanceof JButton) {
        JButton button = (JButton) component;
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode("#1F3C88"));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setBorder(
        BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        )
);
    }
}



        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        15,
                        20,
                        15
                )
        );


        JButton dashboardButton =
                new JButton("Dashboard");

                dashboardButton.setForeground(Color.WHITE);
dashboardButton.setBackground(Color.decode("#1F3C88"));
dashboardButton.setFocusPainted(false);

        JButton booksButton =
                new JButton("Book Management");

        JButton membersButton =
                new JButton("Member Management");

        JButton borrowButton =
                new JButton("Borrow Book");

        JButton returnButton =
                new JButton("Return Book");

        JButton exitButton =
                new JButton("Exit");


        menuPanel.add(dashboardButton);
        menuPanel.add(booksButton);
        menuPanel.add(membersButton);
        menuPanel.add(borrowButton);
        menuPanel.add(returnButton);
        menuPanel.add(exitButton);


        // ---------------- CONTENT AREA ----------------

        cardLayout =
                new CardLayout();

        contentPanel =
                new JPanel(cardLayout);


        // Create pages
        contentPanel.add(
                createDashboardPanel(),
                "DASHBOARD"
        );

        contentPanel.add(
                createBookManagementPanel(),
                "BOOKS"
        );
        contentPanel.add(
        createMemberManagementPanel(),
        "MEMBERS"
);


          contentPanel.add(
        createBorrowBookPanel(),
        "BORROW"

        
);


contentPanel.add(
        createReturnBookPanel(),
        "RETURN"
);
        // ---------------- BUTTON EVENTS ----------------

        dashboardButton.addActionListener(
                e -> {
                    updateDashboard();
                    cardLayout.show(
                            contentPanel,
                            "DASHBOARD"
                    );
                }
        );


        booksButton.addActionListener(
                e -> {
                    refreshBookTable();

                    cardLayout.show(
                            contentPanel,
                            "BOOKS"
                    );
                }
        );


        membersButton.addActionListener(
        e -> {
            cardLayout.show(
                    contentPanel,
                    "MEMBERS"
            );
        }
);

       borrowButton.addActionListener(
        e -> cardLayout.show(contentPanel, "BORROW")
);


        returnButton.addActionListener(
        e -> cardLayout.show(contentPanel, "RETURN")
);

        exitButton.addActionListener(
                e -> {

                    int answer =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to exit?",
                                    "Exit",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (answer ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
        );


        // ---------------- ADD TO MAIN WINDOW ----------------

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                menuPanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        setContentPane(mainPanel);

        updateDashboard();
    }


    // =========================================================
    // DASHBOARD
    // =========================================================

    private JPanel createDashboardPanel() {

        JPanel dashboardPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                25,
                                25
                        )
                );

        dashboardPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        40,
                        40,
                        40
                )
        );


        // Total Books
        JPanel totalBooksCard =
                createDashboardCard(
                        "Total Books"
                );

        totalBooksLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        totalBooksLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        45
                )
        );

      totalBooksLabel.setForeground(
        Color.decode("#1F3C88")
);

        totalBooksCard.add(
                totalBooksLabel,
                BorderLayout.CENTER
        );


        // Available Books
        JPanel availableBooksCard =
                createDashboardCard(
                        "Available Books"
                );

        availableBooksLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        availableBooksLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        45
                )
        );
availableBooksLabel.setForeground(
        Color.decode("#1F3C88")
);

        availableBooksCard.add(
                availableBooksLabel,
                BorderLayout.CENTER
        );


        // Borrowed Books
        JPanel borrowedBooksCard =
                createDashboardCard(
                        "Borrowed Books"
                );

        borrowedBooksLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        borrowedBooksLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        45
                )
        );

        borrowedBooksLabel.setForeground(
        Color.decode("#1F3C88")
);

        borrowedBooksCard.add(
                borrowedBooksLabel,
                BorderLayout.CENTER
        );


        // Total Members
        JPanel totalMembersCard =
                createDashboardCard(
                        "Total Members"
                );

        totalMembersLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        totalMembersLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        45
                )
        );

        totalMembersLabel.setForeground(
        Color.decode("#1F3C88")
);


        totalMembersCard.add(
                totalMembersLabel,
                BorderLayout.CENTER
        );


        dashboardPanel.add(totalBooksCard);
        dashboardPanel.add(availableBooksCard);
        dashboardPanel.add(borrowedBooksCard);
        dashboardPanel.add(totalMembersCard);


        return dashboardPanel;
    }


    // =========================================================
    // DASHBOARD CARD
    // =========================================================

    private JPanel createDashboardCard(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );
                panel.setBackground(Color.WHITE);

                panel.setBorder(
        BorderFactory.createLineBorder(
                Color.decode("#4DA8DA"),
                2
        )
);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        Color.GRAY,
                        1
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

                titleLabel.setFont(
        new Font(
                "Arial",
                Font.BOLD,
                18
        )
);

titleLabel.setForeground(
        Color.decode("#1F3C88")
);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );


        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        titleLabel.setBorder(
        BorderFactory.createEmptyBorder(
                15,
                5,
                10,
                5
        )
);

panel.setBorder(
        BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
        )
);
        return panel;
    }


    // =========================================================
    // UPDATE DASHBOARD
    // =========================================================

    private void updateDashboard() {

        totalBooksLabel.setText(
                String.valueOf(
                        library.getTotalBooks()
                )
        );


        availableBooksLabel.setText(
                String.valueOf(
                        library.getAvailableBooks()
                )
        );


        borrowedBooksLabel.setText(
                String.valueOf(
                        library.getBorrowedBooks()
                )
        );


        totalMembersLabel.setText(
                String.valueOf(
                        library.getTotalMembers()
                )
        );
    }


    // =========================================================
    // BOOK MANAGEMENT PAGE
    // =========================================================


private JPanel createBookManagementPanel() {

    JPanel mainPanel = new JPanel(
            new BorderLayout(10, 10)
    );
mainPanel.setBackground(
        Color.decode("#F2F4F7")
);
    mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                    20, 20, 20, 20
            )
    );


    JLabel title = new JLabel(
            "BOOK MANAGEMENT",
            SwingConstants.CENTER
    );

    title.setFont(
            new Font("Arial", Font.BOLD, 22)
    );

    title.setForeground(Color.decode("#1F3C88"));

    mainPanel.add(
            title,
            BorderLayout.NORTH
    );


    // FORM

    JPanel formPanel = new JPanel(
            new GridLayout(5, 2, 10, 10)
    );

    bookIdField = new JTextField();
    titleField = new JTextField();
    authorField = new JTextField();
    categoryField = new JTextField();
    searchField = new JTextField();


    formPanel.add(new JLabel("Book ID:"));
    formPanel.add(bookIdField);

    formPanel.add(new JLabel("Book Title:"));
    formPanel.add(titleField);

    formPanel.add(new JLabel("Author:"));
    formPanel.add(authorField);

    formPanel.add(new JLabel("Category:"));
    formPanel.add(categoryField);

    formPanel.add(new JLabel("Search:"));
    formPanel.add(searchField);


    // BUTTONS

    JPanel buttonPanel = new JPanel(
            new FlowLayout()
    );

    JButton addButton = new JButton("Add Book");
    JButton searchButton = new JButton("Search");
    JButton deleteButton = new JButton("Delete");
    JButton clearButton = new JButton("Clear");

    addButton.setBackground(Color.decode("#1F3C88"));
addButton.setForeground(Color.WHITE);
addButton.setFocusPainted(false);

searchButton.setBackground(Color.decode("#1F3C88"));
searchButton.setForeground(Color.WHITE);
searchButton.setFocusPainted(false);

deleteButton.setBackground(Color.decode("#1F3C88"));
deleteButton.setForeground(Color.WHITE);
deleteButton.setFocusPainted(false);

clearButton.setBackground(Color.decode("#1F3C88"));
clearButton.setForeground(Color.WHITE);
clearButton.setFocusPainted(false);


    buttonPanel.add(addButton);
    buttonPanel.add(searchButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(clearButton);


    // BUTTON ACTIONS

    addButton.addActionListener(
            e -> addBook()
    );

    searchButton.addActionListener(
            e -> searchBook()
    );

    deleteButton.addActionListener(
            e -> deleteBook()
    );

    clearButton.addActionListener(
            e -> clearBookFields()
    );


    // TABLE

    String[] columns = {
            "Book ID",
            "Title",
            "Author",
            "Category",
            "Status"
    };


    bookTableModel = new DefaultTableModel(
            columns,
            0
    );


    bookTable = new JTable(
            bookTableModel
    );


    bookTable.setRowHeight(25);

    bookTable.setFont(new Font("Arial", Font.PLAIN, 13));

bookTable.setGridColor(Color.LIGHT_GRAY);
    bookTable.getTableHeader().setBackground(
        Color.decode("#1F3C88")
);

bookTable.getTableHeader().setForeground(Color.WHITE);

bookTable.getTableHeader().setFont(
        new Font("Arial", Font.BOLD, 14)
);


    JScrollPane scrollPane = new JScrollPane(
            bookTable
    );


    // CENTER AREA

    JPanel centerPanel = new JPanel(
            new BorderLayout(10, 10)
    );


    JPanel topPanel = new JPanel(
            new BorderLayout(10, 10)
    );

    topPanel.add(
            formPanel,
            BorderLayout.CENTER
    );

    topPanel.add(
            buttonPanel,
            BorderLayout.SOUTH
    );


    centerPanel.add(
            topPanel,
            BorderLayout.NORTH
    );

    centerPanel.add(
            scrollPane,
            BorderLayout.CENTER
    );


    mainPanel.add(
            centerPanel,
            BorderLayout.CENTER
    );


    return mainPanel;
}
    // =========================================================
// MEMBER MANAGEMENT PAGE
// =========================================================

private JPanel createMemberManagementPanel() {

    JPanel mainPanel = new JPanel(
            new BorderLayout(10, 10)
    );
mainPanel.setBackground(Color.decode("#F2F4F7"));

    mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                    20, 20, 20, 20
            )
    );


    // TITLE

    JLabel title = new JLabel(
            "MEMBER MANAGEMENT",
            SwingConstants.CENTER
    );
title.setFont(new Font("Arial", Font.BOLD, 28));
    title.setFont(
            new Font("Arial", Font.BOLD, 28)
    );

    title.setForeground(Color.decode("#1F3C88"));

    title.setForeground(Color.decode("#1F3C88"));

    mainPanel.add(
            title,
            BorderLayout.NORTH
    );


    // FORM

    JPanel formPanel = new JPanel(
            new GridLayout(4, 2, 10, 10)
    );

    memberIdField = new JTextField();
    memberNameField = new JTextField();
    contactField = new JTextField();
    memberSearchField = new JTextField();


    formPanel.add(
            new JLabel("Member ID:")
    );

    formPanel.add(
            memberIdField
    );


    formPanel.add(
            new JLabel("Member Name:")
    );

    formPanel.add(
            memberNameField
    );


    formPanel.add(
            new JLabel("Contact Number:")
    );

    formPanel.add(
            contactField
    );


    formPanel.add(
            new JLabel("Search:")
    );

    formPanel.add(
            memberSearchField
    );


    // BUTTONS

    JPanel buttonPanel = new JPanel(
            new FlowLayout()
    );

    JButton addMemberButton =
            new JButton("Add Member");

    JButton searchMemberButton =
            new JButton("Search");

    JButton clearMemberButton =
            new JButton("Clear");


            addMemberButton.setBackground(Color.decode("#1F3C88"));
addMemberButton.setForeground(Color.WHITE);
addMemberButton.setFocusPainted(false);

searchMemberButton.setBackground(Color.decode("#1F3C88"));
searchMemberButton.setForeground(Color.WHITE);
searchMemberButton.setFocusPainted(false);

clearMemberButton.setBackground(Color.decode("#1F3C88"));
clearMemberButton.setForeground(Color.WHITE);
clearMemberButton.setFocusPainted(false);


    buttonPanel.add(
            addMemberButton
    );

    buttonPanel.add(
            searchMemberButton
    );

    buttonPanel.add(
            clearMemberButton
    );


    // BUTTON EVENTS

    addMemberButton.addActionListener(
            e -> addMember()
    );

    searchMemberButton.addActionListener(
            e -> searchMember()
    );

    clearMemberButton.addActionListener(
            e -> clearMemberFields()
    );


    // TABLE

    String[] columns = {
            "Member ID",
            "Name",
            "Contact Number"
    };

memberTableModel =
        new DefaultTableModel(
                columns,
                0
        );

memberTable =
        new JTable(
                memberTableModel
        );

    memberTable.setRowHeight(25);

    memberTable.setFont(
        new Font("Arial", Font.PLAIN, 13)
);

memberTable.setGridColor(Color.LIGHT_GRAY);

    memberTable.getTableHeader().setBackground(
        Color.decode("#1F3C88")
);

memberTable.getTableHeader().setForeground(Color.WHITE);

memberTable.getTableHeader().setFont(
        new Font("Arial", Font.BOLD, 14)
);


    JScrollPane scrollPane =
            new JScrollPane(
                    memberTable
            );


    // CENTER

    JPanel centerPanel =
            new JPanel(
                    new BorderLayout(10, 10)
            );


    JPanel topPanel =
            new JPanel(
                    new BorderLayout(10, 10)
            );


    topPanel.add(
            formPanel,
            BorderLayout.CENTER
    );


    topPanel.add(
            buttonPanel,
            BorderLayout.SOUTH
    );


    centerPanel.add(
            topPanel,
            BorderLayout.NORTH
    );


    centerPanel.add(
            scrollPane,
            BorderLayout.CENTER
    );


    mainPanel.add(
            centerPanel,
            BorderLayout.CENTER
    );


    return mainPanel;
}

private JPanel createBorrowBookPanel() {

    JPanel panel = new JPanel();


    panel.setBackground(Color.decode("#F2F4F7"));

    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));


    JLabel titleLabel = new JLabel("Borrow Book");

    titleLabel.setForeground(Color.decode("#1F3C88"));

    titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

    titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    titleLabel.setForeground(Color.decode("#1F3C88"));

    panel.add(titleLabel);

    JLabel bookIdLabel = new JLabel("Book ID:");

    bookIdLabel.setFont(new Font("Arial", Font.BOLD, 14));

    bookIdLabel.setForeground(Color.decode("#333333"));
JTextField borrowBookIdField = new JTextField(15);

borrowBookIdField.setPreferredSize(new Dimension(200, 35));

borrowBookIdField.setFont(new Font("Arial", Font.PLAIN, 14));

panel.add(bookIdLabel);
panel.add(borrowBookIdField);

JLabel memberIdLabel = new JLabel("Member ID:");

memberIdLabel.setFont(new Font("Arial", Font.BOLD, 14));

memberIdLabel.setForeground(Color.decode("#333333"));

JTextField borrowMemberIdField = new JTextField(15);

borrowMemberIdField.setPreferredSize(new Dimension(200, 35));

borrowMemberIdField.setFont(new Font("Arial", Font.PLAIN, 14));

panel.add(memberIdLabel);
panel.add(borrowMemberIdField);

JButton borrowButton = new JButton("Borrow Book");

borrowButton.setPreferredSize(new Dimension(150, 40));

borrowButton.setBackground(Color.decode("#1F3C88"));
borrowButton.setForeground(Color.WHITE);
borrowButton.setFocusPainted(false);
borrowButton.setBorderPainted(false);

borrowButton.setFont(new Font("Arial", Font.BOLD, 14));

panel.add(borrowButton);

String[] columns = {
        "Book ID",
        "Book Title",
        "Member ID",
        "Member Name",
        "Status"
};

borrowTableModel = new DefaultTableModel(columns, 0);

borrowTable = new JTable(borrowTableModel);
borrowTable.setRowSelectionAllowed(false);
borrowTable.getColumnModel().getColumn(0).setPreferredWidth(80);

borrowTable.getColumnModel().getColumn(1).setPreferredWidth(180);

borrowTable.getColumnModel().getColumn(2).setPreferredWidth(100);

borrowTable.getColumnModel().getColumn(3).setPreferredWidth(180);

borrowTable.getColumnModel().getColumn(4).setPreferredWidth(100);

borrowTable.setFont(new Font("Arial", Font.PLAIN, 14));

borrowTable.setShowGrid(true);

borrowTable.setRowHeight(30);

borrowTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

borrowTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

panel.add(
        new JScrollPane(borrowTable)
);

borrowButton.addActionListener(e -> {

    String bookId = borrowBookIdField.getText().trim();
    String memberId = borrowMemberIdField.getText().trim();

    if (bookId.isEmpty() || memberId.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter Book ID and Member ID."
        );

        return;
    }

    String message = library.borrowBook(bookId, memberId);

    JOptionPane.showMessageDialog(
            this,
            message
    );

    if (message.equals("Book borrowed successfully.")) {

   Book book = library.findBook(bookId);
Member member = library.findMember(memberId);

Object[] row = {
        bookId,
        book.getTitle(),
        memberId,
        member.getName(),
        "Borrowed"
};

borrowTableModel.addRow(row);
}

    refreshBookTable();
    updateDashboard();

    borrowBookIdField.setText("");
    borrowMemberIdField.setText("");
});

    return panel;
}

    // =========================================================
    // ADD BOOK
    // =========================================================

    private void addBook() {

    String id = bookIdField.getText().trim();
    String title = titleField.getText().trim();
    String author = authorField.getText().trim();
    String category = categoryField.getText().trim();

    if (id.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Book ID."
        );

        return;
    }

    if (title.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Book Title."
        );

        return;
    }

    if (author.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Author."
        );

        return;
    }

    if (category.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Category."
        );

        return;
    }

    Book book = new Book(
            id,
            title,
            author,
            category
    );

    if (library.addBook(book)) {

        JOptionPane.showMessageDialog(
                this,
                "Book added successfully!"
        );

        clearBookFields();

        refreshBookTable();

        updateDashboard();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Book ID already exists!"
        );
    }
}

private JPanel createReturnBookPanel() {

    JPanel panel = new JPanel();

    panel.setBackground(Color.decode("#F2F4F7"));
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

    JLabel titleLabel = new JLabel("Return Book");

    titleLabel.setForeground(Color.decode("#1F3C88"));

    titleLabel.setFont(
            new Font("Arial", Font.BOLD, 28)
    );

    titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    panel.add(titleLabel);

    JLabel returnBookIdLabel = new JLabel("Book ID:");

    returnBookIdLabel.setFont(new Font("Arial", Font.BOLD, 14));

    returnBookIdLabel.setForeground(Color.decode("#333333"));

JTextField returnBookIdField = new JTextField(15);

returnBookIdField.setPreferredSize(new Dimension(200, 35));

returnBookIdField.setFont(new Font("Arial", Font.PLAIN, 14));

panel.add(returnBookIdLabel);
panel.add(returnBookIdField);

JButton returnBookButton = new JButton("Return Book");

returnBookButton.setPreferredSize(new Dimension(150, 40));

returnBookButton.setBackground(Color.decode("#1F3C88"));
returnBookButton.setForeground(Color.WHITE);
returnBookButton.setFocusPainted(false);
returnBookButton.setBorderPainted(false);

returnBookButton.setFont(new Font("Arial", Font.BOLD, 14));

panel.add(returnBookButton);

String[] columns = {
        "Book ID",
        "Book Title",
        "Member ID",
        "Member Name",
        "Status"
};
returnTableModel = new DefaultTableModel(columns, 0);

returnTable = new JTable(returnTableModel);

returnTable.setRowSelectionAllowed(false);

returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

returnTable.setRowHeight(30);

returnTable.getColumnModel().getColumn(0).setPreferredWidth(80);

returnTable.getColumnModel().getColumn(1).setPreferredWidth(180);

returnTable.getColumnModel().getColumn(2).setPreferredWidth(100);

returnTable.getColumnModel().getColumn(3).setPreferredWidth(180);

returnTable.getColumnModel().getColumn(4).setPreferredWidth(100);

returnTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

returnTable.setShowGrid(true);

returnTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

panel.add(
        new JScrollPane(returnTable)
);

returnBookButton.addActionListener(e -> {

    String bookId = returnBookIdField.getText().trim();

    if (bookId.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter Book ID."
        );

        return;
    }

    // Get borrow record BEFORE returning the book
    BorrowRecord record = library.findBorrowRecord(bookId);

    String message = library.returnBook(bookId);

    JOptionPane.showMessageDialog(
            this,
            message
    );

    if (message.equals("Book returned successfully.")
            && record != null) {

        Book book = record.getBook();
        Member member = record.getMember();

        Object[] row = {
                book.getBookId(),
                book.getTitle(),
                member.getMemberId(),
                member.getName(),
                "Returned"
        };

        returnTableModel.addRow(row);
    }

    refreshBookTable();
    updateDashboard();

    returnBookIdField.setText("");
});

    return panel;
}

// =========================================================
// ADD MEMBER
// =========================================================

private void addMember() {

    String id = memberIdField.getText().trim();
    String name = memberNameField.getText().trim();
    String contact = contactField.getText().trim();


    if (id.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Member ID."
        );

        return;
    }


    if (name.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Member Name."
        );

        return;
    }


    if (contact.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the Contact Number."
        );

        return;
    }


    Member member = new Member(
            id,
            name,
            contact
    );


    if (library.addMember(member)) {

        JOptionPane.showMessageDialog(
                this,
                "Member added successfully!"
        );

        clearMemberFields();

        refreshMemberTable();

        updateDashboard();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Member ID already exists."
        );
    }
}

    // =========================================================
    // REFRESH BOOK TABLE
    // =========================================================

    private void refreshBookTable() {

        bookTableModel.setRowCount(0);


        for (Book book :
                library.getBooks()) {

            Object[] row = {

                    book.getBookId(),

                    book.getTitle(),

                    book.getAuthor(),

                    book.getCategory(),

                    book.isAvailable()
                            ? "Available"
                            : "Borrowed"
            };


            bookTableModel.addRow(row);
        }
    }


    // =========================================================
    // SEARCH BOOK
    // =========================================================

    private void searchBook() {

        String search =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();


        if (search.isEmpty()) {

            refreshBookTable();

            return;
        }


        bookTableModel.setRowCount(0);


        boolean found = false;


        for (Book book :
                library.getBooks()) {


            if (book.getBookId()
                    .toLowerCase()
                    .contains(search)

                    ||

                book.getTitle()
                    .toLowerCase()
                    .contains(search)

                    ||

                book.getAuthor()
                    .toLowerCase()
                    .contains(search)) {


                Object[] row = {

                        book.getBookId(),

                        book.getTitle(),

                        book.getAuthor(),

                        book.getCategory(),

                        book.isAvailable()
                                ? "Available"
                                : "Borrowed"
                };


                bookTableModel.addRow(row);

                found = true;
            }
        }


        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "No book found.",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // =========================================================
    // DELETE BOOK
    // =========================================================

    private void deleteBook() {

        String id =
                bookIdField
                        .getText()
                        .trim();


        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Book ID to delete.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Book book =
                library.findBook(id);


        if (book == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        if (!book.isAvailable()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cannot delete a borrowed book.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int answer =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this book?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );


        if (answer ==
                JOptionPane.YES_OPTION) {


            if (library.removeBook(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                clearBookFields();

                refreshBookTable();

                updateDashboard();
            }
        }
    }


    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearBookFields() {

        bookIdField.setText("");

        titleField.setText("");

        authorField.setText("");

        categoryField.setText("");

        searchField.setText("");

        refreshBookTable();
    }

    // =========================================================
// CLEAR MEMBER FIELDS
// =========================================================

private void clearMemberFields() {

    memberIdField.setText("");

    memberNameField.setText("");

    contactField.setText("");

    memberSearchField.setText("");
}

private void refreshMemberTable() {

    memberTableModel.setRowCount(0);

    for (Member member : library.getMembers()) {

        Object[] row = {

                member.getMemberId(),
                member.getName(),
                member.getContactNumber()
        };

        memberTableModel.addRow(row);
    }
}

// =========================================================
// SEARCH MEMBER
// =========================================================

private void searchMember() {

    String search =
            memberSearchField
                    .getText()
                    .trim()
                    .toLowerCase();


    if (search.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter something to search."
        );

        return;
    }


    boolean found = false;


    for (Member member : library.getMembers()) {

        if (member.getMemberId()
                .toLowerCase()
                .contains(search)

                ||

            member.getName()
                .toLowerCase()
                .contains(search)

                ||

            member.getContactNumber()
                .toLowerCase()
                .contains(search)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member Found!\n\n"
                    + "Member ID: "
                    + member.getMemberId()
                    + "\nName: "
                    + member.getName()
                    + "\nContact Number: "
                    + member.getContactNumber()
            );

            found = true;

            break;
        }
    }


    if (!found) {

        JOptionPane.showMessageDialog(
                this,
                "No member found.",
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
    public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

        LibraryGUI gui = new LibraryGUI();

        gui.setVisible(true);

    });
}
}