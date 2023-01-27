package librarysystem.superuser;

import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;
import librarysystem.admin.AddBookPanel;
import librarysystem.admin.AddMemberPanel;
import librarysystem.admin.BookCopyPanel;
import librarysystem.admin.ViewMembersPanel;
import librarysystem.librarian.BookCheckoutWindow;
import librarysystem.librarian.CheckoutRecordWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.Serial;

public class AdminLibrarianWindow extends JFrame implements ListSelectionListener, LibWindow {

    String[] links;
    JList<String> linkList;
    //context for CardLayout
    JPanel cards;
    JPanel buttonBar;
    JLabel bottomLabel;

    String[] items = {"Checkout Book", "Checkout Records", "Add Member", "View Members", "Add Book Copy", "Add Book"};

    private LibrarySystem librarySystem;

    public static AdminLibrarianWindow INSTANCE = null;
    private boolean isInitialized = false;

    private AdminLibrarianWindow(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public static AdminLibrarianWindow getInstance(LibrarySystem librarySystem) {
        if (INSTANCE == null) {
            INSTANCE = new AdminLibrarianWindow(librarySystem);
        }

        return INSTANCE;
    }

    @Override
    public void init() {
        linkList = new JList<>(items);
        bottomLabel = new JLabel("Welcome Super User");

        createPanels();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        splitPane.setDividerLocation(120);
        JButton backButton = new JButton("<= Back to Main");
        addBackButtonListener(backButton);

        //default layout for JFrame is BorderLayout; add method adds to contentpane
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        bottomPanel.add(bottomLabel);

        add(splitPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        this.setSize(librarySystem.getSize());
        linkList.setEnabled(true);
        Util.centerFrameOnDesktop(this);
        setVisible(true);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void updateBottomLabel(String str, Color color) {
        bottomLabel.setText(str);
        bottomLabel.setForeground(color);
    }

    /* Organize panels into a CardLayout */
    public void createPanels() {
        cards = new JPanel(new CardLayout());
        cards.add(BookCheckoutWindow.getInstance(this.librarySystem), "Checkout Book");
        cards.add(CheckoutRecordWindow.getInstance(), "Checkout Records");
        cards.add(AddMemberPanel.getInstance(this), "Add Member");
        cards.add(ViewMembersPanel.getInstance(), "View Members");
        cards.add(BookCopyPanel.getInstance(this), "Add Book Copy");
        cards.add(AddBookPanel.getInstance(this), "Add Book");
        //connect JList elements to CardLayout panels
        linkList.addListSelectionListener(this);
        linkList.setSelectedIndex(0);
    }

    @Serial
    private static final long serialVersionUID = -760156396736751840L;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String value = linkList.getSelectedValue();
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, value);
    }

    private void addBackButtonListener(JButton btn) {
        btn.addActionListener(evt -> {
            setVisible(false);
            LibrarySystem.INSTANCE.setVisible(true);
        });
    }
}


