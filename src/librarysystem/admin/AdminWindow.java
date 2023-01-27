package librarysystem.admin;

import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class AdminWindow extends JFrame implements ListSelectionListener, LibWindow {

    String[] links;
    JList<String> linkList;
    //context for CardLayout
    JPanel cards;
    JPanel buttonBar;
    JLabel bottomLabel;

    String[] items = {"Add Member", "View Members", "Add Book Copy", "Add Book"};

    private LibrarySystem librarySystem;

    public static AdminWindow INSTANCE = null;
    private boolean isInitialized = false;

    private AdminWindow(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public static AdminWindow getInstance(LibrarySystem librarySystem) {
        if (INSTANCE == null) {
            INSTANCE = new AdminWindow(librarySystem);
        }

        return INSTANCE;
    }

    @Override
    public void init() {
        linkList = new JList<>(items);
        bottomLabel = new JLabel("Welcome Admin");

        createPanels();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        splitPane.setDividerLocation(100);
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

    /**
     * this logic will be moved into a parent class that will be inherited by subclasses
     * A hashmap of labels and corresponding panel can be passed to the create panels function that determines how the layout will be rendered.
     */
    public void createPanels() {
        cards = new JPanel(new CardLayout());
        cards.add(AddMemberPanel.getInstance(this), "Add Member");
        cards.add(ViewMembersPanel.getInstance(), "View Members");
        cards.add(BookCopyPanel.getInstance(this), "Add Book Copy");
        cards.add(AddBookPanel.getInstance(this), "Add Book");

        //connect JList elements to CardLayout panels
        linkList.addListSelectionListener(this);
        linkList.setSelectedIndex(0);
    }

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

