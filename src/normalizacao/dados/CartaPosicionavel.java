package normalizacao.dados;

import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
* Entity class for a positional card.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class CartaPosicionavel {
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

	public static int MAX_INTENSIDADE = 10;
	private String nome;
	private int intensidade;
	private boolean disponivel;

	public CartaPosicionavel() {
		this.disponivel = true;
		this.intensidade = -1;
	}

	public boolean getDisponivel() {
		return this.disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
		action();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		action();
	}

	public int getIntensidade() {
		return this.intensidade;
	}

	public String toString() {
		String intensidade = " ";
		if (this.intensidade == 0) {
			intensidade = " =";
		} else {
			for (int i = 0; i < this.intensidade; i++)
				intensidade = intensidade + "<";
		}
		return this.nome + intensidade;
	}

	public void setIntensidade(int intensidade) {
		if (intensidade >= 0 && intensidade <= MAX_INTENSIDADE)
			this.intensidade = intensidade;
		action();
	}

	public void setUltimaCarta() {
		this.intensidade = -1;
		action();
	}

	public void addActionListener(ActionListener e) {
		this.listeners.add(e);
	}

	private void action() {
		for (ActionListener e : this.listeners)
			e.actionPerformed(null);
	}
}
