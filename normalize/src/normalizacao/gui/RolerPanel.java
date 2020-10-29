package normalizacao.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.Popup;

/**
* GUI class for present the roler.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class RolerPanel extends JPanel {
	private Popup popup;
	private NormalizacaoPanel normalizacaoPanel;
	private boolean insertion;
	private JButton jButton1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JTextField jTextField2;

	public RolerPanel(NormalizacaoPanel label) {
		initComponents();
		this.normalizacaoPanel = label;
		this.insertion = true;
	}

	private void initComponents() {
		this.jTextField1 = new JTextField();
		this.jLabel1 = new JLabel();
		this.jButton1 = new JButton();
		this.jLabel2 = new JLabel();
		this.jTextField2 = new JTextField();

		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createTitledBorder("White Cards"));
		setFont(new Font("Tahoma", 1, 12));

		this.jTextField1.setFont(new Font("Times New Roman", 1, 14));
		this.jTextField1.setBorder(BorderFactory.createEtchedBorder());
		this.jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RolerPanel.this.jTextField1ActionPerformed(evt);
			}
		});

		this.jLabel1.setFont(new Font("Times New Roman", 1, 14));
		this.jLabel1.setText("Position:");

		this.jButton1.setFont(new Font("Times New Roman", 1, 12));
		this.jButton1.setText("Update");
		this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RolerPanel.this.jButton1ActionPerformed(evt);
			}
		});

		this.jLabel2.setFont(new Font("Times New Roman", 1, 14));
		this.jLabel2.setText("White Cards:");

		this.jTextField2.setFont(new Font("Times New Roman", 1, 14));
		this.jTextField2.setBorder(BorderFactory.createEtchedBorder());
		this.jTextField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RolerPanel.this.jTextField2ActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout
										.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addGroup(GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addComponent(this.jLabel1, -2, 89, -2)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1,
																32767)
														.addComponent(this.jTextField2, -2, 114, -2))
										.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
												.addGap(2, 2, 2).addComponent(this.jLabel2, -2, 89, -2)
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(this.jButton1, -2, 84, -2)
														.addComponent(this.jTextField1, -2, 114, -2))))
								.addContainerGap(102, 32767)));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.jLabel1, -2, 31, -2).addComponent(this.jTextField2, -1, 31, 32767))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.jLabel2, -1, 31, 32767).addComponent(this.jTextField1, -1, 31, 32767))
				.addGap(18, 18, 18).addComponent(this.jButton1).addContainerGap()));
	}

	private void jTextField1ActionPerformed(ActionEvent evt) {
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		try {
			int whiteCards = Integer.parseInt(this.jTextField1.getText());
			int position = Integer.parseInt(this.jTextField2.getText());
			if (whiteCards > 0) {
				this.normalizacaoPanel.updateRoler(whiteCards, position, this.insertion);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.normalizacaoPanel.setIsShowing(false);
		this.jTextField1.setText("");
		this.popup.hide();
	}

	private void jTextField2ActionPerformed(ActionEvent evt) {
	}

	void setPopup(Popup popup) {
		this.popup = popup;
	}

	void setInsertion(boolean b) {
		this.insertion = b;
		if (!this.insertion) {
			setBorder(BorderFactory.createTitledBorder("Remove White Cards"));
		} else {
			setBorder(BorderFactory.createTitledBorder("Insert White Cards"));
		}
	}
}
