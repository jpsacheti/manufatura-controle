package controle.uteis;

import java.math.BigDecimal;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Spinner;

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

	public static BigDecimal getSpinnerValueAsBigDecimal(Spinner spinnerPreco) {
		int selecao = spinnerPreco.getSelection();
		int digitos = spinnerPreco.getDigits();
		return new BigDecimal(selecao/Math.pow(10, digitos));
	}
	
	public static int getBigDecimalAsSpinnerValue(BigDecimal valor, int digits){
		return valor.movePointRight(digits).intValue();
	}
}
