package normalizacao;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import normalizacao.gui.FramePrincipal;


/**
* The main system class.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel((LookAndFeel) new AcrylLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String) null, ex);
				}
				(new FramePrincipal()).setVisible(true);
			}
		});
	}
}