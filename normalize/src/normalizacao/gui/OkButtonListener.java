package normalizacao.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import normalizacao.dados.CartaPosicionavel;
import normalizacao.dados.Ranqueador;

/**
* GUI class for "OK" button listener.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
class OkButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		okButtonClick();
	}

	private void okButtonClick() {
		Tela t = Tela.getInstance();
		String numString = t.numeroField.getText();
		int numero = -1;
		try {
			numero = Integer.parseInt(numString);
		} catch (NumberFormatException e) {
		}

		if (numero > 0) {

			t.okButton.setEnabled(false);
			t.numeroField.setEditable(false);
			t.ranqueador = new Ranqueador(numero);
			for (CartaPosicionavel c : t.ranqueador.cartas) {
				CartaEditavel ce = new CartaEditavel(c);
				t.medio.add(ce);
				t.medio.validate();
			}
		}
	}
}
