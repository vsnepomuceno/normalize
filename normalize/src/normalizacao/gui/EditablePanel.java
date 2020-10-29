package normalizacao.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;


/**
* GUI class for present the card edition.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class EditablePanel extends JPanel {
	private int uSize;
	private int[] uPositionQuantity;
	private NormalizacaoPanel normaPanel;
	ArrayList<WhiteCardLabel> whiteCards = new ArrayList<WhiteCardLabel>();

	public EditablePanel(NormalizacaoPanel normPanel) {
		initComponents();
		this.normaPanel = normPanel;
	}

	public void insertWhiteCards(ArrayList<NormalizacaoAlterLabel> alternatives) {
		boolean[] hasAlternative = new boolean[this.uSize];
		int maxU = 0;
		for (int i = 0; i < alternatives.size(); i++) {
			NormalizacaoAlterLabel label = alternatives.get(i);
			int uLabel = Integer.parseInt(label.getuValue());
			if (uLabel > maxU) {
				maxU = uLabel;
			}
			hasAlternative[uLabel - 1] = true;
		}

		for (WhiteCardLabel label : this.whiteCards) {
			label.setVisible(false);
			label.repaint();
			revalidate();
			updateUI();
			repaint();
			getParent().repaint();
			remove(label);
		}

		boolean start = false;
		for (int j = 1; j <= maxU; j++) {

			if (hasAlternative[j - 1]) {
				start = true;
			}
			if (start && !hasAlternative[j - 1]) {
				WhiteCardLabel label = new WhiteCardLabel(j, this);
				this.whiteCards.add(label);
				label.setBounds(getX() + 5 + 10, getY() + (j - 1) * 35 + 5, 100, 25);
				add(label);
				label.setVisible(true);
				label.repaint();
				revalidate();
				updateUI();
				repaint();
				getParent().repaint();
			}
		}
	}

	private void initComponents() {
		setBackground(new Color(255, 255, 255));
		setAutoscrolls(true);
		setCursor(new Cursor(0));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
	}

	public void startRoler(int size) {
		this.uPositionQuantity = new int[size];
		this.uSize = size;
	}

	public ArrayList<NormalizacaoAlterLabel> startAlternatives(int size) {
		ArrayList<NormalizacaoAlterLabel> alterLabels = new ArrayList<NormalizacaoAlterLabel>();
		for (int i = 0; i < size; i++) {
			NormalizacaoAlterLabel label = new NormalizacaoAlterLabel("A" + (i + 1), this.uSize, this);
			label.setuValue(String.valueOf(i + 1));
			label.setDescription("A" + (i + 1));
			label.setBounds(getX() + 5 + 10, getY() + i * 35 + 5, 100, 25);

			alterLabels.add(label);
			add(label);
			label.setVisible(true);
			label.repaint();
			revalidate();
			updateUI();
			repaint();
		}

		return alterLabels;
	}

	public void updateAlternatives(ArrayList<NormalizacaoAlterLabel> alternatives) {
		insertWhiteCards(alternatives);
		this.uPositionQuantity = new int[this.uPositionQuantity.length];
		for (int i = 0; i < alternatives.size(); i++) {
			NormalizacaoAlterLabel label = alternatives.get(i);
			label.setText(label.getDescription());

			this.uPositionQuantity[Integer.parseInt(label.getuValue())
					- 1] = this.uPositionQuantity[Integer.parseInt(label.getuValue()) - 1] + 1;

			int desloc = (this.uPositionQuantity[Integer.parseInt(label.getuValue()) - 1] - 1) * 115;
			label.setBounds(getX() + 15 + desloc, getY() + (Integer.parseInt(label.getuValue()) - 1) * 35 + 5, 100, 25);

			add(label);
			label.setVisible(true);
			label.repaint();
			revalidate();
			updateUI();
			repaint();
		}
	}

	public void updateAlternatives() {
		this.normaPanel.updateAlternatives();
	}

	public int maxUPositionQty() {
		int max = 0;
		for (int i = 0; i < this.uPositionQuantity.length; i++) {
			if (this.uPositionQuantity[i] > max) {
				max = this.uPositionQuantity[i];
			}
		}
		return max;
	}

	public int getuSize() {
		return this.uSize;
	}

	public void updateRoler(int whitecards, int startPosition) {
	}
}
