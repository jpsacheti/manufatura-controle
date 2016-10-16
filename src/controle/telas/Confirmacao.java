package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Confirmacao {
    public static boolean getConfirmacao(Shell pai, String mensagem) {
		MessageBox msg = new MessageBox(pai, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		msg.setMessage(mensagem);
		msg.setText("Atenção");
		int resultado = msg.open();
		return resultado == SWT.YES;
	}
}