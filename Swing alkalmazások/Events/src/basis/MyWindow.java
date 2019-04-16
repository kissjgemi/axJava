package basis;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author KissJGabi
 */
public class MyWindow extends JFrame {

    public MyWindow(int width, int height, String title) {
        super(title);
        super.setSize(width, height);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public void showInWindow(List<EveningEvent> events, String[] columns) {
        String[][] datas = new String[events.size()][columns.length];
        for (int ii = 0; ii < events.size(); ii++) {
            datas[ii][0] = events.get(ii).getTitle();
            datas[ii][1] = events.get(ii).getDate();
            datas[ii][2] = String.valueOf(events.get(ii).getPrice());
        }
        TableModel tableModel = new DefaultTableModel(datas, columns);
        JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);

        this.add(jScrollPane);
        this.revalidate();
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
