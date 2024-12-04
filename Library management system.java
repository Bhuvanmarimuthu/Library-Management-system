import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryManagementSystem extends Frame {
    private TextField titleField, authorField, isbnField, searchField;
    private TextArea displayArea;
    private ArrayList<Book> books;

    public LibraryManagementSystem() {
        books = new ArrayList<>();

        // Set layout
        setLayout(new FlowLayout());

        // Add components
        add(new Label("Book Title:"));
        titleField = new TextField(20);
        add(titleField);

        add(new Label("Author:"));
        authorField = new TextField(20);
        add(authorField);

        add(new Label("ISBN:"));
        isbnField = new TextField(15);
        add(isbnField);

        Button addButton = new Button("Add Book");
        add(addButton);

        Button updateButton = new Button("Update Book");
        add(updateButton);

        Button deleteButton = new Button("Delete Book");
        add(deleteButton);

        add(new Label("Search:"));
        searchField = new TextField(20);
        add(searchField);

        Button searchButton = new Button("Search");
        add(searchButton);

        displayArea = new TextArea(10, 50);
        add(displayArea);

        // Set button actions
        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        searchButton.addActionListener(e -> searchBooks());

        // Window settings
        setTitle("Library Management System");
        setSize(600, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
            books.add(new Book(title, author, isbn));
            displayArea.append("Book added: " + title + "\n");
            clearFields();
        } else {
            displayArea.append("Please fill in all fields.\n");
        }
    }

    private void updateBook() {
        String isbn = isbnField.getText();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(titleField.getText());
                book.setAuthor(authorField.getText());
                displayArea.append("Book updated: " + book.getTitle() + "\n");
                clearFields();
                return;
            }
        }
        displayArea.append("Book not found.\n");
    }

    private void deleteBook() {
        String isbn = isbnField.getText();
        books.removeIf(book -> book.getIsbn().equals(isbn));
        displayArea.append("Book deleted (if existed): " + isbn + "\n");
        clearFields();
    }

    private void searchBooks() {
        String query = searchField.getText().toLowerCase();
        displayArea.append("Search results:\n");
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query)) {
                displayArea.append(book + "\n");
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }

    class Book {
        private String title, author, isbn;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIsbn() {
            return isbn;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn;
        }
    }
}