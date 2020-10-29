package normalizacao.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import normalizacao.dados.CartaPosicionavel;

/**
* GUI class for present the ordered cards.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class CartaOrdenada extends JPanel implements ActionListener {
	private static final long serialVersionUID = -389610475282948375L;
	public CartaPosicionavel carta;
	public JLabel nome = new JLabel();
	public JLabel intensidade = new JLabel();
	public JLabel menosLabel = new JLabel("(-)");
	public JLabel maisLabel = new JLabel("(+)");
	public JButton removerButton = new JButton("Remover");

	public CartaOrdenada(CartaPosicionavel c) {
		this.carta = c;
		this.carta.addActionListener(this);
		this.menosLabel.setForeground(Color.RED);
		this.maisLabel.setForeground(Color.BLUE);
		Font font = new Font("Arial", 1, 20);

		this.nome.setFont(font);
		this.intensidade.setFont(font);
		add(this.nome);
		add(this.menosLabel);
		add(this.intensidade);
		add(this.maisLabel);

		MouseListener alMais = new MouseListener() {
			public void mouseExited(MouseEvent e) {
				CartaOrdenada.this.maisLabel.setCursor(Cursor.getDefaultCursor());
			}

			public void mouseEntered(MouseEvent e) {
				CartaOrdenada.this.maisLabel.setCursor(new Cursor(12));
			}

			public void mouseClicked(MouseEvent e) {
				CartaOrdenada.this.maisClicked();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		};
		this.maisLabel.addMouseListener(alMais);
		MouseListener alMenos = new MouseListener() {
			public void mouseExited(MouseEvent e) {
				CartaOrdenada.this.menosLabel.setCursor(Cursor.getDefaultCursor());
			}

			public void mouseEntered(MouseEvent e) {
				CartaOrdenada.this.menosLabel.setCursor(new Cursor(12));
			}

			public void mouseClicked(MouseEvent e) {
				CartaOrdenada.this.menosClicked();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		};
		this.menosLabel.addMouseListener(alMenos);
		displayCarta();
	}

	private void maisClicked() {
		int intensidade = this.carta.getIntensidade();
		this.carta.setIntensidade(intensidade + 1);
	}

	private void menosClicked() {
		int intensidade = this.carta.getIntensidade();
		this.carta.setIntensidade(intensidade - 1);
	}

	private void displayCarta() {
		int intensidade = this.carta.getIntensidade();
		boolean showIntensidade = (intensidade >= 0);
		if (showIntensidade) {
			String intString = "";
			if (intensidade == 0) {
				intString = "=";
			} else {
				for (int i = 0; i < intensidade; i++)
					intString = intString + "<";
			}
			this.intensidade.setText(intString);
		}
		this.maisLabel.setVisible(showIntensidade);
		this.menosLabel.setVisible(showIntensidade);
		this.intensidade.setVisible(showIntensidade);
		this.nome.setText(this.carta.getNome());
	}

	public void actionPerformed(ActionEvent e) {
		displayCarta();
	}
}
