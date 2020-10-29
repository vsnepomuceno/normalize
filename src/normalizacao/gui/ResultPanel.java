package normalizacao.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
* GUI class for present the result.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class ResultPanel extends JPanel {
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTable resultTable;

	public ResultPanel() {
		initComponents();
	}

	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.jScrollPane2 = new JScrollPane();
		this.resultTable = new JTable();

		this.resultTable.setBackground(new Color(255, 255, 153));
		this.resultTable.setBorder(BorderFactory.createEtchedBorder());
		this.resultTable.setFont(new Font("Tahoma", 1, 12));
		this.resultTable.setModel(
				new DefaultTableModel(new Object[0][], (Object[]) new String[] { "Alternative", "Position", "Value" }) {

					Class[] types = new Class[] { String.class, String.class, Integer.class };

					boolean[] canEdit = new boolean[] { false, false, false };

					public Class getColumnClass(int columnIndex) {
						return this.types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return this.canEdit[columnIndex];
					}
				});
		this.resultTable.setGridColor(new Color(204, 255, 204));
		this.resultTable.setRowMargin(0);
		this.resultTable.getTableHeader().setReorderingAllowed(false);
		this.jScrollPane2.setViewportView(this.resultTable);
		this.resultTable.getColumnModel().getColumn(0).setResizable(false);
		this.resultTable.getColumnModel().getColumn(1).setResizable(false);
		this.resultTable.getColumnModel().getColumn(2).setResizable(false);

		this.jScrollPane1.setViewportView(this.jScrollPane2);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -2, 638, -2));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.jScrollPane1, -1, 552, 32767));
	}

	public void setResultTable(ArrayList<NormalizacaoAlterLabel> alterLabels) {
		DefaultTableModel dtm = (DefaultTableModel) this.resultTable.getModel();

		for (int i = alterLabels.size() - 1; i >= 0; i--) {
			dtm.addRow(new Object[] { ((NormalizacaoAlterLabel) alterLabels.get(i)).getDescription(),
					((NormalizacaoAlterLabel) alterLabels.get(i)).getuValue(),
					((NormalizacaoAlterLabel) alterLabels.get(i)).getuValueNormalized() });
		}
	}

	public void clear() {
		DefaultTableModel dtm = (DefaultTableModel) this.resultTable.getModel();
		while (dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}
}
