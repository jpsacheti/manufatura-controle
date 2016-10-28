package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public abstract class CadastrarMateriaPrima {

	protected Shell shell;
	private Shell pai;
	private Text txtNome;
	private Group grpDadosDaMatriaprima;
	private Label lblPreo;

	public CadastrarMateriaPrima(Shell pai) {
		this.pai = pai;
		createContents();
	}


	private void createContents() {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(421, 183);
		shell.setText("Cadastro de matéria prima");
		shell.setLayout(null);
		
		grpDadosDaMatriaprima = new Group(shell, SWT.NONE);
		grpDadosDaMatriaprima.setText("Dados da mat\u00E9ria prima");
		grpDadosDaMatriaprima.setBounds(10, 12, 397, 112);
		
		Label lblNome = new Label(grpDadosDaMatriaprima, SWT.NONE);
		lblNome.setBounds(0, 3, 75, 17);
		lblNome.setAlignment(SWT.RIGHT);
		lblNome.setText("Nome");
		
		txtNome = new Text(grpDadosDaMatriaprima, SWT.BORDER);
		txtNome.setBounds(81, 0, 293, 22);
		
		lblPreo = new Label(grpDadosDaMatriaprima, SWT.NONE);
		lblPreo.setText("Pre\u00E7o");
		lblPreo.setAlignment(SWT.RIGHT);
		lblPreo.setBounds(0, 29, 75, 17);
		
		Spinner spinnerPreco = new Spinner(grpDadosDaMatriaprima, SWT.BORDER);
		spinnerPreco.setDigits(2);
		spinnerPreco.setMaximum(99999999);
		spinnerPreco.setBounds(81, 26, 128, 22);
		
		Label lblUnidade = new Label(grpDadosDaMatriaprima, SWT.NONE);
		lblUnidade.setText("Unidade");
		lblUnidade.setAlignment(SWT.RIGHT);
		lblUnidade.setBounds(0, 59, 75, 17);
		
		Combo combo = new Combo(grpDadosDaMatriaprima, SWT.NONE);
		combo.setBounds(81, 54, 293, 22);
		
		Button btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(233, 130, 82, 27);
		btnGravar.setText("Gravar");
		
		Button btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(325, 130, 82, 27);
	}
}
