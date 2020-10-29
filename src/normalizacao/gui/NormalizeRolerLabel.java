package normalizacao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.border.LineBorder;

/**
* GUI class for present the roler normalization.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class NormalizeRolerLabel extends JLabel {
	private Dimension minSize = new Dimension(74, 27);
	private Popup popup;
	private EditablePanel edtPanel;
	private RolerPanel rolerPanel;
	private int position;

	public NormalizeRolerLabel(String text, int pos, EditablePanel edtPanel) {
		setBackground(new Color(255, 255, 255));
		setFont(new Font("Verdana", 1, 14));
		setHorizontalAlignment(0);
		setText(text);
		setBorder(new LineBorder(new Color(0, 102, 102), 1, true));
		setOpaque(true);
		this.edtPanel = edtPanel;
		this.position = pos;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				NormalizeRolerLabel.this.labelUMouseClicked(evt);
			}
		});
	}

	public Dimension getMinimumSize() {
		return this.minSize;
	}

	public Dimension getPreferredSize() {
		return this.minSize;
	}

	private void labelUMouseClicked(MouseEvent evt) {
	}

	public void setIsShowing(boolean isShowing) {
	}

	public void updateRoler(int whiteCards, int startPosition) {
		this.edtPanel.updateRoler(whiteCards, startPosition);
	}
}
