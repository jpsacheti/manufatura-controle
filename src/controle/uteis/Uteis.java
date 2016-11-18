package controle.uteis;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Spinner;

public class Uteis {
	public static void exibirErro(Control c, Throwable e) {
		e.printStackTrace();
		MessageBox msg = new MessageBox(c.getShell());
		msg.setText("Erro!!");
		msg.setMessage(e.getMessage());
		msg.open();
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
	
	public static boolean getConfirmacao(Control control, String mensagem) {
		MessageBox msg = new MessageBox(control.getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		msg.setMessage(mensagem);
		msg.setText("Atenção");
		int resultado = msg.open();
		return resultado == SWT.YES;
	}

	public static BigDecimal getSpinnerValueAsBigDecimal(Spinner spinnerPreco) {
		int selecao = spinnerPreco.getSelection();
		int digitos = spinnerPreco.getDigits();
		return new BigDecimal(selecao/Math.pow(10, digitos));
	}
	
	public static int getBigDecimalAsSpinnerValue(BigDecimal valor, int digits){
		return valor.movePointRight(digits).intValue();
	}
	
	public static boolean isTeclaEnter(int numcode){
		return numcode == 13 || numcode == 16777296;
	}
}
