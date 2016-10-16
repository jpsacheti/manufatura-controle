package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public abstract class IncluirProdutoFinalManufatura {

	protected Shell shell;
	protected Shell pai;
	private Label label;
	protected Combo combo;
	protected Text text;
	private Label label_1;
	protected Button btnGravar;
	protected Button btnCancelar;

	public IncluirProdutoFinalManufatura(Shell pai) {
		this.pai = pai;
		createContents();
	}

	protected void createContents() {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(330, 150);
		shell.setText("Incluir produto para manufatura");

		label = new Label(shell, SWT.RIGHT);
		label.setText("Produto");
		label.setBounds(10, 15, 67, 13);

		combo = new Combo(shell, SWT.READ_ONLY);
		combo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
		combo.setBounds(83, 10, 233, 27);

		text = new Text(shell, SWT.BORDER);
		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
		text.setBounds(83, 39, 92, 19);

		label_1 = new Label(shell, SWT.RIGHT);
		label_1.setText("Quantidade");
		label_1.setBounds(10, 42, 67, 13);

		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setText("&Gravar");
		btnGravar.setBounds(new Rectangle(236, 65, 80, 23));
		btnGravar.setBounds(236, 65, 80, 23);

		btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setText("&Cancelar");
		btnCancelar.setBounds(new Rectangle(236, 91, 80, 23));
		btnCancelar.setBounds(236, 91, 80, 23);

	}

}
