package normalizacao.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import normalizacao.dados.CartaPosicionavel;
import normalizacao.dados.Ranqueador;

/**
* GUI class for present the initial view.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class Tela extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static Tela instance;
	protected Ranqueador ranqueador;
	protected JFileChooser c = new JFileChooser();
	protected JPanel superior;
	protected JPanel medio;
	protected JPanel inferior;
	protected JLabel numeroLabel = new JLabel("Numero de cartas: ");
	protected JTextField numeroField = new JTextField(3);
	protected JButton okButton = new JButton("OK");
	protected MenuBar mb = new MenuBar();
	protected Menu mf = new Menu("Arquivo");
	protected MenuItem novoItem = new MenuItem("Limpar tudo", new MenuShortcut(78));
	protected MenuItem limparItem = new MenuItem("Limpar ordem", new MenuShortcut(79));
	protected MenuItem salvarItem = new MenuItem("Salvar Resultados", new MenuShortcut(83));
	protected MenuItem exitItem = new MenuItem("Sair", new MenuShortcut(88));

	Tela() {
		this.mb.add(this.mf);
		this.mf.add(this.salvarItem);
		this.mf.addSeparator();
		this.mf.add(this.novoItem);
		this.mf.add(this.limparItem);
		this.mf.addSeparator();
		this.mf.add(this.exitItem);
		this.salvarItem.addActionListener(this);
		this.novoItem.addActionListener(this);
		this.limparItem.addActionListener(this);
		this.exitItem.addActionListener(this);
		setMenuBar(this.mb);
		this.superior = new JPanel();
		this.superior.setSize(600, 50);
		this.superior.setBounds(0, 0, 600, 50);
		this.medio = new JPanel();
		this.medio.setSize(800, 300);
		this.medio.setBounds(0, 50, 800, 300);
		this.inferior = new JPanel();
		this.inferior.setSize(800, 300);
		this.superior.add(this.numeroLabel);
		this.superior.add(this.numeroField);
		this.okButton.addActionListener(new OkButtonListener());
		this.superior.add(this.okButton);

		add(this.superior, "First");
		add(this.medio, "Center");
		add(this.inferior, "Last");
		setSize(800, 600);
		setVisible(true);
		setResizable(false);
		validate();
		setDefaultCloseOperation(3);
	}

	public void limparTudo() {
		this.inferior.removeAll();
		this.medio.removeAll();
		this.numeroField.setText("");
		this.numeroField.setEditable(true);
		this.okButton.setEnabled(true);
		this.medio.repaint();
		this.medio.validate();
		this.inferior.repaint();
		this.inferior.validate();
	}

	public static Tela getInstance() {
		if (instance == null) {
			instance = new Tela();
		}
		return instance;
	}

	public void addCartaOrdenada(CartaPosicionavel carta) {
		this.inferior.add(new CartaOrdenada(carta));
		this.ranqueador.addCartaOrdenada(carta);
		this.inferior.validate();
		validate();
	}

	public void actionPerformed(ActionEvent evt) {
		Object cmp = evt.getSource();
		if (cmp == this.exitItem) {
			System.exit(0);
		} else if (cmp == this.limparItem) {
			this.ranqueador.limparOrdem();
			this.inferior.removeAll();
			this.inferior.validate();
		} else if (cmp == this.novoItem) {
			limparTudo();
		} else if (cmp == this.salvarItem) {

			String filename = null;
			String dir = null;
			int rVal = this.c.showSaveDialog(this);
			if (rVal == 0) {
				filename = this.c.getSelectedFile().getName();
				dir = this.c.getCurrentDirectory().toString();
			} else if (rVal == 1) {
				return;
			}
			if (filename != null && dir != null) {
				if (!filename.endsWith(".xls"))
					filename = filename + ".xls";
				String content = this.ranqueador.toString();
				this.ranqueador.setNormalizacao();
				content = content + "\n\n" + this.ranqueador.normalizacao.toString();
				System.out.println(content);
				File f = new File(dir + '\\' + filename);

				try {
					FileWriter fstream = new FileWriter(f);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(content);

					out.close();
				} catch (Exception e) {
				}
			}
		}

		validate();
	}
}
