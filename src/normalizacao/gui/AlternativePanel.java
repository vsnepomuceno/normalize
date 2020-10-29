package normalizacao.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.Popup;

/**
* GUI class for present the alternatives.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class AlternativePanel extends JPanel {
	private Popup popup;
	private NormalizacaoAlterLabel normalizacaoAlterLabel;
	private JButton cancelButton;
	private JTextField desc;
	private JLabel jLabel1;

	public AlternativePanel() {
		initComponents();
	}

	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JTextArea obs;
	private JButton okButton;
	private JTextField uValue;

	private void initComponents() {
		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();
		this.jLabel3 = new JLabel();
		this.jScrollPane1 = new JScrollPane();
		this.obs = new JTextArea();
		this.desc = new JTextField();
		this.uValue = new JTextField();
		this.okButton = new JButton();
		this.cancelButton = new JButton();

		setBackground(new Color(0, 153, 153));
		setBorder(BorderFactory.createTitledBorder("Alternative"));
		setFont(new Font("Tahoma", 1, 12));

		this.jLabel1.setFont(new Font("Tahoma", 1, 12));
		this.jLabel1.setText("Description:");

		this.jLabel2.setFont(new Font("Tahoma", 1, 12));
		this.jLabel2.setText("Position:");

		this.jLabel3.setFont(new Font("Tahoma", 1, 12));
		this.jLabel3.setText("Obs.:");

		this.obs.setColumns(20);
		this.obs.setRows(5);
		this.obs.setBorder(BorderFactory.createEtchedBorder());
		this.jScrollPane1.setViewportView(this.obs);

		this.desc.setFont(new Font("Tahoma", 0, 12));
		this.desc.setBorder(BorderFactory.createEtchedBorder());

		this.uValue.setFont(new Font("Tahoma", 0, 12));
		this.uValue.setBorder(BorderFactory.createEtchedBorder());

		this.okButton.setFont(new Font("Tahoma", 1, 12));
		this.okButton.setText("OK");
		this.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AlternativePanel.this.okButtonActionPerformed(evt);
			}
		});

		this.cancelButton.setFont(new Font("Tahoma", 1, 12));
		this.cancelButton.setText("Cancel");
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AlternativePanel.this.cancelButtonActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addComponent(this.jLabel1, -1, 77, 32767)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(this.desc, -2, 287, -2))
										.addGroup(layout.createSequentialGroup().addComponent(this.jLabel2, -2, 70, -2)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(this.uValue, -2, 65, -2))
										.addComponent(this.jScrollPane1, -1, 368, 32767)
										.addComponent(this.jLabel3, -2, 70, -2)))
						.addGroup(layout.createSequentialGroup().addGap(106, 106, 106)
								.addComponent(this.okButton, -2, 72, -2).addGap(18, 18, 18)
								.addComponent(this.cancelButton)))
				.addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(this.jLabel1, -2, 28, -2).addComponent(this.desc, -2, -1, -2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(this.jLabel2, -2, 28, -2).addComponent(this.uValue, -2, -1, -2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(this.jLabel3, -2, 28, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jScrollPane1, -2, 106, -2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(this.cancelButton).addComponent(this.okButton))
						.addContainerGap(26, 32767)));
	}

	private void okButtonActionPerformed(ActionEvent evt) {
		if (validateInfo()) {
			this.normalizacaoAlterLabel.setDescription(this.desc.getText());
			this.normalizacaoAlterLabel.setuValue(this.uValue.getText());
			this.normalizacaoAlterLabel.setObs(this.obs.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Description Empty or Invalid U-Value!!",
					"Description Empty or Invalid U-Value!!", 0);
		}

		this.normalizacaoAlterLabel.updateAlternatives();
		this.popup.hide();
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
		this.normalizacaoAlterLabel.updateAlternatives();
		this.popup.hide();
	}

	public void setValues(String description, String uValue, String obs, Popup popu, NormalizacaoAlterLabel normLabel) {
		this.popup = popu;
		this.normalizacaoAlterLabel = normLabel;
		this.desc.setText(description);
		this.uValue.setText(uValue);
		this.obs.setText(obs);
	}

	private boolean validateInfo() {
		boolean ret = true;
		try {
			int uMaxValue = Integer.parseInt(this.uValue.getText());
			if (this.desc.getText().length() <= 0 || uMaxValue > this.normalizacaoAlterLabel.getuMaxValue()) {
				ret = false;
			}
		} catch (NumberFormatException nex) {
			ret = false;
			nex.printStackTrace();
		}
		return ret;
	}
}
