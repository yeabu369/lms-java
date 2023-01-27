package librarysystem.librarian;

import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.HashMap;

public class CheckoutRecordWindow extends JPanel {
    static SystemController sc = SystemController.getInstance();

    private CheckoutRecordWindow() {
        setupLayout();
    }

    public static CheckoutRecordWindow getInstance() {
        return new CheckoutRecordWindow();
    }

    void setupLayout() {
        setLayout(new GridLayout());
        JTable table = new JTable(new CheckoutRecordDataModel());

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row == 0) {
                    System.out.println("Header row clicked");
                    return;
                }

                System.out.println(CheckoutRecordDataModel.data.get(CheckoutRecordDataModel.memberIds[row - 1]).getCheckoutRecord().getCheckoutEntries());
            }
        });

        table.setShowGrid(true);
        table.setGridColor(Color.BLUE);
        table.setTableHeader(null);
        add(new JScrollPane(table));
    }

    private class CheckoutRecordDataModel extends AbstractTableModel {
        static final HashMap<String, LibraryMember> data = sc.getAllLibraryMembers();
        final String[] keys = {"Member ID", "Checkout Record"};
        static final String[] memberIds = data.keySet().toArray(new String[data.size()]);

        @Override
        public int getRowCount() {
            return data.size() + 1;
        }

        @Override
        public int getColumnCount() {
            return keys.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex == 0) {
                return keys[columnIndex];
            }

            if (columnIndex == 0) {
                return memberIds[rowIndex - 1];
            }

            return data.get(memberIds[rowIndex - 1]).getCheckoutRecord().getCheckoutEntries().size() + " books checked out";
        }
    }
}
