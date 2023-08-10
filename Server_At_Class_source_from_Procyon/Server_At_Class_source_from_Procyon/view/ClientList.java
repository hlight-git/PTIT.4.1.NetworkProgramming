// 
// Decompiled by Procyon v0.5.36
// 

package view;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Iterator;
import java.util.Vector;
import model.Answer;
import java.util.Date;
import java.text.SimpleDateFormat;
import model.Student;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class ClientList extends JFrame
{
    DefaultTableModel model;
    private String[] columnNames;
    private Object[][] data;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JScrollPane jScrollPane1;
    private JTable jTabClientList;
    
    public ClientList() {
        this.columnNames = new String[] { "S\u1ed1 TT", "M\u00e3 SV", "H\u1ecd v\u00e0 T\u00ean", "IP", "Nh\u00f3m", "\u0110\u0103ng k\u00fd", "Time" };
        this.initComponents();
        this.model = new DefaultTableModel(this.data, this.columnNames);
        this.jTabClientList.setModel(this.model);
    }
    
    public void addNewRow(final Student sv) {
        final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        final Date currentTime = new Date();
        final String dateString = formatter.format(currentTime);
        this.model.addRow(new Object[] { this.model.getRowCount(), sv.getMaSV(), sv.getHovaten(), sv.getIP(), sv.getGroup(), dateString });
    }
    
    public void addNewRows(final Answer answer) {
        final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        final Date currentTime = new Date();
        final String dateString = formatter.format(currentTime);
        this.model.addRow(new Object[] { this.model.getRowCount() + 1, answer.getStudent().getMaSV(), answer.getStudent().getHovaten(), answer.getStudent().getIP(), answer.getStudent().getGroup(), answer.isAlreadyRegistration() ? "Yes" : "No", dateString });
    }
    
    public void updateAnswerView(final Answer answer) {
        if (this.model.getRowCount() == 0) {
            this.addNewRows(answer);
            return;
        }
        final Vector rows = this.model.getDataVector();
        final Iterator it = rows.iterator();
        int rowPos = 0;
        boolean isUpdate = false;
        while (it.hasNext()) {
            ++rowPos;
            final Vector next = it.next();
            final Iterator itItem = next.iterator();
            if (next != null && next.get(1).toString().equalsIgnoreCase(answer.getStudent().getMaSV())) {
                isUpdate = true;
                this.model.setValueAt(answer.getStudent().getHovaten(), rowPos - 1, 2);
                this.model.setValueAt(answer.getStudent().getIP(), rowPos - 1, 3);
                this.model.setValueAt(answer.getStudent().getGroup(), rowPos - 1, 4);
                this.model.setValueAt(answer.isAlreadyRegistration() ? "Yes" : "No", rowPos - 1, 5);
                break;
            }
        }
        if (!isUpdate) {
            this.addNewRows(answer);
        }
    }
    
    private void initComponents() {
        this.jMenu1 = new JMenu();
        this.jScrollPane1 = new JScrollPane();
        this.jTabClientList = new JTable();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu2 = new JMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jMenu1.setText("jMenu1");
        this.setDefaultCloseOperation(3);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                ClientList.this.windowClosing(evt);
            }
        });
        this.jTabClientList.setModel(new DefaultTableModel(new Object[][] { new Object[0], new Object[0], new Object[0], new Object[0] }, new String[0]));
        this.jScrollPane1.setViewportView(this.jTabClientList);
        this.jMenu2.setText("Export Excel");
        this.jMenuItem1.setText("Save Excel");
        this.jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                ClientList.this.jMenuItem1ActionPerformed(evt);
            }
        });
        this.jMenu2.add(this.jMenuItem1);
        this.jMenuBar1.add(this.jMenu2);
        this.setJMenuBar(this.jMenuBar1);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 507, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 339, 32767).addContainerGap()));
        this.pack();
    }
    
    private void windowClosing(final WindowEvent evt) {
    }
    
    private void jMenuItem1ActionPerformed(final ActionEvent evt) {
        final Workbook wb = (Workbook)new HSSFWorkbook();
        final CreationHelper createhelper = wb.getCreationHelper();
        final Sheet sheet = wb.createSheet("new sheet");
        final Row firstRow = sheet.createRow(0);
        for (int j = 0; j < this.model.getColumnCount(); ++j) {
            firstRow.createCell(j).setCellValue(this.columnNames[j]);
        }
        Row row = null;
        Cell cell = null;
        for (int i = 0; i < this.model.getRowCount(); ++i) {
            row = sheet.createRow(i + 1);
            for (int k = 0; k < this.model.getColumnCount(); ++k) {
                cell = row.createCell(k);
                cell.setCellValue(String.valueOf(this.model.getValueAt(i, k)));
            }
        }
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            final Date currentTime = new Date();
            final String dateString = formatter.format(currentTime);
            final FileOutputStream out = new FileOutputStream("Kip2.xls");
            wb.write((OutputStream)out);
            out.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex2) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
}
