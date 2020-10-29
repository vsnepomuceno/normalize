package normalizacao.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import normalizacao.dados.CartaPosicionavel;

/**
* GUI class for present the editable cards.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class CartaEditavel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private CartaPosicionavel carta;
	private JTextField nomeField = new JTextField(8);
	private JButton addButton = new JButton("Ok");

	public CartaEditavel(CartaPosicionavel carta) {
		this.carta = carta;
		this.carta.addActionListener(this);
		setSize(100, 100);
		add(this.nomeField);
		add(this.addButton);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartaEditavel.this.addClicked();
			}
		};
		this.addButton.addActionListener(al);
	}

	private void addClicked() {
		Tela t = Tela.getInstance();
		this.carta.setDisponivel(false);
		this.carta.setNome(this.nomeField.getText());
		t.addCartaOrdenada(this.carta);
	}

	private void displayCarta() {
		boolean disponivel = this.carta.getDisponivel();
		this.nomeField.setEditable(disponivel);
		this.addButton.setEnabled(disponivel);
	}

	public void actionPerformed(ActionEvent e) {
		displayCarta();
	}
}
