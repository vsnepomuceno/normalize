package normalizacao.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* GUI class for present the principal frame.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class FramePrincipal extends JFrame {
	private NormalizacaoPanel normPanel;
	private Dimension screencSize = Toolkit.getDefaultToolkit().getScreenSize();

	public FramePrincipal() {
		initComponents();

		this.normPanel = new NormalizacaoPanel(this);
		add(this.normPanel);
		setSize(this.screencSize);
		setResizable(true);
		setContentPane(this.normPanel);
		this.normPanel.setVisible(true);
		this.normPanel.repaint();
		Image icon = new BufferedImage(1, 1, 3);
		setIconImage(icon);
		setVisible(true);
	}

	private void initComponents() {
		setDefaultCloseOperation(3);
		setTitle("Normalize");
		setBackground(new Color(204, 204, 255));
		setName("framePrincipal");
		setResizable(false);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 800, 32767));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 600, 32767));

		getAccessibleContext().setAccessibleDescription("Normalização");

		pack();
	}

	public void centralizandoPrincipalGUI(JPanel painel) {
		int larguraPai = getWidth();
		int alturaPai = getHeight();

		int larguraFilho = painel.getWidth();
		int alturaFilho = painel.getHeight();

		int novoX = (larguraPai - larguraFilho) / 2;
		int novoY = (alturaPai - alturaFilho) / 2;

		painel.setLocation(novoX, novoY);
	}
}
