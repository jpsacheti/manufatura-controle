package controle.uteis;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Interface {
	private static final String MENSAGEM_PREENCHIMENTO = "Verifique se todos os campos foram preenchidos corretamente";

	public static void manterJanelaModal(Shell shell){
		Display display = Display.getDefault();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static boolean verificarPreenchimentoCampos(Shell shell){
		List<Control> controlesCiano = Arrays.asList(shell.getChildren()).stream().filter(c -> isCiano(c)).collect(Collectors.toList());
		boolean preenchido = true;
		for(Control c : controlesCiano){
			if (c instanceof Text){
				Text t = (Text) c;
				if(t.getText().isEmpty()){
					preenchido = false;
					Uteis.exibirMensagem(t, MENSAGEM_PREENCHIMENTO);
					break;
				}
			} else if(c instanceof Combo){
				Combo combo = (Combo) c;
				if(combo.getText().isEmpty()){
					preenchido = false;
					Uteis.exibirMensagem(c, MENSAGEM_PREENCHIMENTO);
					break;
				}
			}
		}
		return preenchido;
	}

	private static boolean isCiano(Control c) {
		Color background = c.getBackground();
		Color cyan = Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
		return background.equals(cyan);
	}

}
