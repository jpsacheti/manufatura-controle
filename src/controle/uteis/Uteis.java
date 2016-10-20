package controle.uteis;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;

public class Uteis {
	public static void exibirErro(Control c, Throwable e) {
		MessageBox msg = new MessageBox(c.getShell());
		msg.setText("Erro!!");
		msg.setMessage(e.getMessage());
		msg.open();
		System.out.println(e.getMessage());

	}

	public static void exibirMensagem(Control c, String Mensagem) {
		try {
			MessageBox msg = new MessageBox(c.getShell());
			msg.setText("Mensagem!");
			msg.setMessage(Mensagem);
			msg.open();
			c.forceFocus();
		} catch (Exception exe) {
			System.out.println(exe.getMessage());
		}
	}
}
