package librarysystem.admin;

import business.Book;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import librarysystem.LibWindow;
import librarysystem.Util;

import javax.swing.*;
import java.awt.*;

public class BookCopyPanel extends JPanel {
    private LibWindow parentFrame;

    SystemController sc = SystemController.getInstance();
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel outerMiddle = new JPanel();
    JPanel outerMiddlePanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JLabel addBookCopy = new JLabel("Add Book Copy");
    JLabel isbnLabel = new JLabel("Enter ISBN");
    JButton addButton = new JButton("Add Copy");
    JTextField isbnField = new JTextField(10);

    private BookCopyPanel(LibWindow parentFrame) {
        this.parentFrame = parentFrame;
        defineMainPanel();
        defineTopPanel();
        defineOuterMiddle();
    }

    public static JPanel getInstance(LibWindow parentFrame) {
        return new BookCopyPanel(parentFrame).getMainPanel();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void defineMainPanel() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, "North");
        mainPanel.add(outerMiddle, "Center");
    }

    public void defineTopPanel() {
        Util.adjustLabelFont(addBookCopy, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(0));
        topPanel.add(addBookCopy);
    }

    public void defineOuterMiddle() {
        FlowLayout fl = new FlowLayout(1, 30, 30);
        outerMiddle.setLayout(new BorderLayout());
        outerMiddlePanel.setLayout(fl);
        leftPanel.setLayout(new BoxLayout(leftPanel, 1));
        rightPanel.setLayout(new BoxLayout(rightPanel, 1));
        middlePanel.setLayout(new BoxLayout(middlePanel, 1));

        addCopyButtonListener(addButton);

        leftPanel.add(isbnLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        rightPanel.add(isbnField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        middlePanel.add(addButton);
        middlePanel.add(Box.createRigidArea(new Dimension(0, 6)));

        outerMiddlePanel.add(leftPanel);
        outerMiddlePanel.add(rightPanel);
        outerMiddlePanel.add(middlePanel);

        outerMiddle.add(outerMiddlePanel, "North");
    }

    private void addCopyButtonListener(JButton button) {
        button.addActionListener(evt -> {
            String isbn = isbnField.getText();
            if (Util.isEmpty(isbn)) {
                Util.showDialog(this, "Please enter ISBN!");
                return;
            }
            addBookCopy(isbn);
        });
    }

    private void addBookCopy(String isbn) {
        sc.addCopyOfBook(isbn, this);
    }
}
