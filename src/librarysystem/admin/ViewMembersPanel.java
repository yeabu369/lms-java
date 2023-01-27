package librarysystem.admin;

import business.SystemController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class ViewMembersPanel extends JPanel {
    SystemController sc = SystemController.getInstance();

    private ViewMembersPanel() {
        setupLayout();
    }

    public static ViewMembersPanel getInstance() {
        return new ViewMembersPanel();
    }

    void setupLayout() {
        setLayout(new GridLayout());
        JTable table = new JTable(new ViewMembersDataModel());
        table.setShowGrid(true);
        table.setGridColor(Color.BLUE);
        table.setTableHeader(null);
        add(new JScrollPane(table));
    }

    private class ViewMembersDataModel extends AbstractTableModel {
        private final List<SystemController.FormattedLibraryMember> data = sc.getReadableLibraryMembers();
        private final String[] keys = new String[] {"memberId", "firstName", "lastName", "telephone", "address"};

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

            return data.get(rowIndex - 1).memberDataMap().get(keys[columnIndex]);
        }
    }
}
