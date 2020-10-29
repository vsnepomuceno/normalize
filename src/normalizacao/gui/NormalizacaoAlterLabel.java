package normalizacao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
* GUI class for present the normalization edition.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class NormalizacaoAlterLabel extends JLabel implements Comparator<NormalizacaoAlterLabel> {
	private String description;
	private String uValue;
	private String obs;
	private Dimension minSize = new Dimension(204, 27);
	private Popup popup;
	private AlternativePanel alterPanel;
	private int uMaxValue;
	private EditablePanel edtPanel;
	private boolean isShowing;
	private Double uValueNormalized;

	NormalizacaoAlterLabel(String text, int uSize, EditablePanel edPanel) {
		setBackground(new Color(0, 153, 153));
		setFont(new Font("Verdana", 1, 14));
		setHorizontalAlignment(0);
		setText(text);
		setBorder(BorderFactory.createBevelBorder(0));
		setOpaque(true);
		this.alterPanel = new AlternativePanel();
		this.uMaxValue = uSize;
		this.edtPanel = edPanel;
		this.isShowing = false;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				NormalizacaoAlterLabel.this.labelUMouseClicked(evt);
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
		if (!this.isShowing) {
			PopupFactory factory = PopupFactory.getSharedInstance();
			this.popup = factory.getPopup(getParent(), this.alterPanel, 400, 300);
			this.alterPanel.setValues(this.description, this.uValue, this.obs, this.popup, this);

			this.popup.show();
			this.isShowing = true;
		}
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getuValue() {
		return this.uValue;
	}

	public void setuValue(String uValue) {
		this.uValue = uValue;
	}

	public int getuMaxValue() {
		return this.uMaxValue;
	}

	public void setuMaxValue(int uMaxValue) {
		this.uMaxValue = uMaxValue;
	}

	void updateAlternatives() {
		this.edtPanel.updateAlternatives();
		this.isShowing = false;
	}

	public Double getuValueNormalized() {
		return this.uValueNormalized;
	}

	public void setuValueNormalized(Double uValueNormalized) {
		this.uValueNormalized = uValueNormalized;
	}

	public int compare(NormalizacaoAlterLabel t, NormalizacaoAlterLabel t1) {
		return (t.getuValueNormalized().doubleValue() > t1.getuValueNormalized().doubleValue()) ? 1
				: ((t.getuValueNormalized() == t1.getuValueNormalized()) ? 0 : -1);
	}

	NormalizacaoAlterLabel() {
	}
}
