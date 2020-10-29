package normalizacao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import normalizacao.dados.Normalizacao;

/**
 * GUI class for present the normalization results.
 * 
 * 
 * @author Vilmar Santos Nepomuceno
 * @version 1.0
 */
public class NormalizacaoPanel extends JPanel {
	private Dimension screencSize = Toolkit.getDefaultToolkit().getScreenSize();
	private FramePrincipal framePrincipalGUI;
	ResultPanel resultPanel = new ResultPanel();
	EditablePanel edtPanel = new EditablePanel(this);
	private boolean isNormalized;
	private Popup popup;
	private ArrayList<NormalizacaoAlterLabel> alterLabels = new ArrayList<NormalizacaoAlterLabel>();

	private int uSize;
	private int lastActionWhiteCards = 0;
	private int lastActionPosition = 0;

	private boolean isShowing;
	private RolerPanel rolerPanel;
	private JPanel PanelAlternatives;

	public NormalizacaoPanel(FramePrincipal fPrinc) {
		initComponents();
		setSize(this.screencSize);
		this.isShowing = false;
		this.rolerPanel = new RolerPanel(this);
		this.PanelConfig.setSize((int) this.screencSize.getWidth(), 50);
		this.PanelAlternatives.setSize((int) this.screencSize.getWidth(), (int) this.screencSize.getHeight() - 50);
		setFramePrincipal(fPrinc);
		initMyComponents();
		this.isNormalized = false;
	}

	private JPanel PanelConfig;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JLabel jLabel1;
	private JLabel picLabel;
	private JScrollPane jScrollPane1;
	private JTextField sizeAlternatives;

	private BufferedImage image;

	private void initComponents() {

		this.PanelConfig = new JPanel();
		this.jLabel1 = new JLabel();
		this.picLabel = new JLabel();
		this.sizeAlternatives = new JTextField();
		this.jButton1 = new JButton();
		this.jButton2 = new JButton();
		this.jButton3 = new JButton();
		this.jButton4 = new JButton();
		this.jButton5 = new JButton();
		this.PanelAlternatives = new JPanel();
		this.jScrollPane1 = new JScrollPane();

		setBorder(BorderFactory.createEtchedBorder());
		setMaximumSize(new Dimension(1400, 2000));

		this.PanelConfig.setBorder(BorderFactory.createEtchedBorder());
		
		try {
			File f = new File("./img/LOGO-DEPLog.png");
			image = ImageIO.read(f);		
			this.picLabel = new JLabel(new ImageIcon(image));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.jLabel1.setFont(new Font("Verdana", 1, 14));
		this.jLabel1.setText("Number of Alternatives:");

		this.sizeAlternatives.setFont(new Font("Tahoma", 1, 18));

		this.jButton1.setText("Generate");
		this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NormalizacaoPanel.this.jButton1ActionPerformed(evt);
			}
		});

		this.jButton2.setText("Reset");
		this.jButton2.setEnabled(false);
		this.jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NormalizacaoPanel.this.jButton2ActionPerformed(evt);
			}
		});

		this.jButton3.setText("Normalize");
		this.jButton3.setEnabled(false);
		this.jButton3.setHorizontalTextPosition(0);
		this.jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NormalizacaoPanel.this.jButton3ActionPerformed(evt);
			}
		});

		this.jButton4.setText("Insert White Cards");
		this.jButton4.setEnabled(false);
		this.jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NormalizacaoPanel.this.jButton4ActionPerformed(evt);
			}
		});

		this.jButton5.setActionCommand("Remove White Cards");
		this.jButton5.setEnabled(false);
		this.jButton5.setLabel("Remove White Cards");
		this.jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NormalizacaoPanel.this.jButton5ActionPerformed(evt);
			}
		});

		GroupLayout PanelConfigLayout = new GroupLayout(this.PanelConfig);
		this.PanelConfig.setLayout(PanelConfigLayout);
		PanelConfigLayout.setHorizontalGroup(PanelConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(PanelConfigLayout.createSequentialGroup().addContainerGap()
						.addComponent(this.picLabel, -2, 215, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.jLabel1, -2, 198, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.sizeAlternatives, -2, 57, -2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton3)
						.addGap(50, 50, 50).addComponent(this.jButton4)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton5)
						.addContainerGap(-1, 32767)));

		PanelConfigLayout.setVerticalGroup(PanelConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(PanelConfigLayout.createSequentialGroup().addContainerGap().addGroup(PanelConfigLayout
						.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(GroupLayout.Alignment.LEADING,
								PanelConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(this.sizeAlternatives, -1, 32, 32767)
										.addComponent(this.jButton1)
										.addComponent(this.jButton2)
										.addComponent(this.jButton3)
										.addComponent(this.jButton4)
										.addComponent(this.jButton5)
										.addComponent(this.jLabel1))
						.addComponent(this.picLabel, GroupLayout.Alignment.LEADING, -2, 96, -2))
						.addContainerGap(21, 32767)));

		this.PanelAlternatives.setBorder(BorderFactory.createEtchedBorder());
		this.PanelAlternatives.setAutoscrolls(true);

		this.jScrollPane1.setBackground(new Color(255, 255, 255));
		this.jScrollPane1.setAutoscrolls(true);

		GroupLayout PanelAlternativesLayout = new GroupLayout(this.PanelAlternatives);
		this.PanelAlternatives.setLayout(PanelAlternativesLayout);
		PanelAlternativesLayout.setHorizontalGroup(PanelAlternativesLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 826, 32767));

		PanelAlternativesLayout.setVerticalGroup(PanelAlternativesLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 531, 32767));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.PanelConfig, -1, -1, 32767).addComponent(this.PanelAlternatives, -1, -1, 32767));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(this.PanelConfig, -2, -1, -2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.PanelAlternatives, -1, -1, 32767).addContainerGap()));
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		try {
			int size = Integer.parseInt(this.sizeAlternatives.getText());
			this.jButton1.setEnabled(false);
			this.jButton2.setEnabled(true);
			this.jButton3.setEnabled(true);
			this.jButton4.setEnabled(true);
			this.jButton5.setEnabled(true);
			this.sizeAlternatives.setText("");
			this.sizeAlternatives.setEditable(false);
			this.edtPanel.startRoler(size);
			this.alterLabels = this.edtPanel.startAlternatives(size);
			this.uSize = size;
			updateScreen();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, this.ui, ex.getMessage(), 0);

			ex.printStackTrace();
		}
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		this.jButton1.setEnabled(true);
		this.jButton2.setEnabled(false);
		this.jButton3.setEnabled(false);

		this.sizeAlternatives.setEditable(true);

		this.jButton3.setText("Normalize");
		this.edtPanel.startRoler(0);
		this.uSize = 0;
		this.alterLabels = this.edtPanel.startAlternatives(0);
		this.edtPanel.removeAll();
		this.edtPanel.revalidate();
		this.edtPanel.updateUI();
		updateScreen();
		this.jButton4.setEnabled(false);
		this.jButton5.setEnabled(false);
	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		Normalizacao.normalize(this.alterLabels);

		Collections.sort(this.alterLabels, new NormalizacaoAlterLabel());

		if (this.isNormalized) {
			this.jButton3.setText("Normalize");
			this.resultPanel.clear();
			this.resultPanel.invalidate();
			this.jButton4.setEnabled(true);
			this.jButton5.setEnabled(true);
			this.popup.hide();
		} else {
			this.jButton3.setText("     Back    ");

			this.resultPanel.setResultTable(this.alterLabels);
			this.jButton4.setEnabled(false);
			this.jButton5.setEnabled(false);
			PopupFactory factory = PopupFactory.getSharedInstance();
			this.popup = factory.getPopup(this, this.resultPanel, 250, 150);
			this.popup.show();
		}

		updateScreen();
		this.isNormalized = !this.isNormalized;
	}

	private void jButton4ActionPerformed(ActionEvent evt) {
		if (!this.isShowing) {
			PopupFactory factory = PopupFactory.getSharedInstance();
			this.popup = factory.getPopup(getParent(), this.rolerPanel, 400, 300);
			this.rolerPanel.setPopup(this.popup);
			this.rolerPanel.setInsertion(true);
			this.popup.show();
			this.isShowing = true;
		}
	}

	private void jButton5ActionPerformed(ActionEvent evt) {
		if (!this.isShowing) {
			PopupFactory factory = PopupFactory.getSharedInstance();
			this.popup = factory.getPopup(getParent(), this.rolerPanel, 400, 300);
			this.rolerPanel.setPopup(this.popup);
			this.rolerPanel.setInsertion(false);
			this.popup.show();
			this.isShowing = true;
		}
	}

	public void setFramePrincipal(FramePrincipal framePrincipal) {
		this.framePrincipalGUI = framePrincipal;
	}

	private void updateScreen() {
		int maxUQty = this.edtPanel.maxUPositionQty();
		int newWidth = getWidth();
		if (maxUQty > 0) {
			newWidth = 90 + 215 * maxUQty;
		}
		this.edtPanel.setPreferredSize(new Dimension(newWidth, this.uSize * 40));
		this.edtPanel.setVisible(true);
		this.jScrollPane1.setVisible(true);
		setVisible(true);
		this.framePrincipalGUI.setVisible(true);
		this.edtPanel.revalidate();
		this.jScrollPane1.revalidate();
		this.PanelAlternatives.revalidate();
		revalidate();
		this.edtPanel.updateUI();
		this.jScrollPane1.updateUI();
		this.PanelAlternatives.updateUI();
		updateUI();
		this.edtPanel.repaint();
		this.jScrollPane1.repaint();
		this.PanelAlternatives.repaint();
		repaint();
		this.framePrincipalGUI.repaint();
	}

	private void initMyComponents() {
		GroupLayout edtPanelLayout = new GroupLayout(this.edtPanel);
		this.edtPanel.setLayout(edtPanelLayout);
		edtPanelLayout.setHorizontalGroup(
				edtPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1004, 32767));

		edtPanelLayout.setVerticalGroup(
				edtPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 494, 32767));

		this.jScrollPane1.setViewportView(this.edtPanel);

		GroupLayout PanelAlternativesLayout = new GroupLayout(this.PanelAlternatives);
		this.PanelAlternatives.setLayout(PanelAlternativesLayout);
		PanelAlternativesLayout.setHorizontalGroup(PanelAlternativesLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 820, 32767));

		PanelAlternativesLayout.setVerticalGroup(PanelAlternativesLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 494, 32767));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.PanelConfig, -1, -1, 32767).addComponent(this.PanelAlternatives, -1, -1, 32767));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(this.PanelConfig, -2, -1, -2)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.PanelAlternatives, -1, -1, 32767).addContainerGap()));
	}

	public void updateAlternatives() {
		this.edtPanel.updateAlternatives(this.alterLabels);
		updateScreen();
	}

	public void updateRoler(int whiteCards, int startPosition, boolean insertion) {
		if (!insertion) {
			whiteCards = -whiteCards;
		}
		this.lastActionPosition = startPosition;
		this.lastActionWhiteCards = whiteCards;
		this.edtPanel.startRoler(this.edtPanel.getuSize() + whiteCards);
		for (NormalizacaoAlterLabel alterlabe : this.alterLabels) {
			int uvalue = Integer.parseInt(alterlabe.getuValue());
			if (uvalue > startPosition) {
				uvalue += whiteCards;
				alterlabe.setuValue(Integer.toString(uvalue));
			}
			alterlabe.setuMaxValue(this.edtPanel.getuSize() + 1);
		}
		this.edtPanel.updateAlternatives(this.alterLabels);
		this.uSize = this.edtPanel.getuSize();

		updateScreen();
		this.jButton4.setEnabled(true);
		this.jButton5.setEnabled(true);
	}

	@Deprecated
	public void undoLastUpdateRoler() {
		if (this.lastActionWhiteCards > 0) {
			this.edtPanel.removeAll();
			this.edtPanel.startRoler(this.edtPanel.getuSize() - this.lastActionWhiteCards);
			for (NormalizacaoAlterLabel alterlabe : this.alterLabels) {
				int uvalue = Integer.parseInt(alterlabe.getuValue());
				if (uvalue > this.lastActionPosition) {
					uvalue -= this.lastActionWhiteCards;
					alterlabe.setuValue(Integer.toString(uvalue));
				}
				alterlabe.setuMaxValue(this.edtPanel.getuSize() - this.lastActionWhiteCards);
			}
			this.edtPanel.updateAlternatives(this.alterLabels);
			this.uSize = this.edtPanel.getuSize() - this.lastActionWhiteCards;
			updateScreen();
			this.jButton4.setEnabled(false);
			this.jButton5.setEnabled(false);
		}
	}

	public int getLastActionPosition() {
		return this.lastActionPosition;
	}

	public int getLastActionWhiteCards() {
		return this.lastActionWhiteCards;
	}

	void setIsShowing(boolean b) {
		this.isShowing = b;
	}
}
