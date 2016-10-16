package controle.uteis;

import javax.swing.JOptionPane;

public class Uteis {
	public static void mostrarErro(Throwable e){
		JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}

	public static void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
	}
}
