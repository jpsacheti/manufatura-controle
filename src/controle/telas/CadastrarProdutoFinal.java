package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public abstract class CadastrarProdutoFinal {

	protected Shell shlCadastroDeProduto;
	private Shell pai;
	private Text txtNome;
	private Group grpDadosDoProdutoFinal;
	private Label lblPreo;

	public CadastrarProdutoFinal(Shell pai) {
		this.pai = pai;
		createContents();
	}


	private void createContents() {
		shlCadastroDeProduto = new Shell(pai, SWT.DIALOG_TRIM);
		shlCadastroDeProduto.setSize(421, 183);
		shlCadastroDeProduto.setText("Cadastro de produto final");
		shlCadastroDeProduto.setLayout(null);
		
		grpDadosDoProdutoFinal = new Group(shlCadastroDeProduto, SWT.NONE);
		grpDadosDoProdutoFinal.setText("Dados do produto final");
		grpDadosDoProdutoFinal.setBounds(10, 12, 397, 112);
		
		Label lblNome = new Label(grpDadosDoProdutoFinal, SWT.NONE);
		lblNome.setBounds(0, 3, 75, 17);
		lblNome.setAlignment(SWT.RIGHT);
		lblNome.setText("Nome");
		
		txtNome = new Text(grpDadosDoProdutoFinal, SWT.BORDER);
		txtNome.setBounds(81, 0, 293, 22);
		
		lblPreo = new Label(grpDadosDoProdutoFinal, SWT.NONE);
		lblPreo.setText("Pre\u00E7o");
		lblPreo.setAlignment(SWT.RIGHT);
		lblPreo.setBounds(0, 29, 75, 17);
		
		Spinner spinnerPreco = new Spinner(grpDadosDoProdutoFinal, SWT.BORDER);
		spinnerPreco.setDigits(2);
		spinnerPreco.setMaximum(99999999);
		spinnerPreco.setBounds(81, 26, 128, 22);
		
		Label lblUnidade = new Label(grpDadosDoProdutoFinal, SWT.NONE);
		lblUnidade.setText("Unidade");
		lblUnidade.setAlignment(SWT.RIGHT);
		lblUnidade.setBounds(0, 59, 75, 17);
		
		Combo combo = new Combo(grpDadosDoProdutoFinal, SWT.NONE);
		combo.setBounds(81, 54, 293, 22);
		
		Button btnGravar = new Button(shlCadastroDeProduto, SWT.NONE);
		btnGravar.setBounds(233, 130, 82, 27);
		btnGravar.setText("Gravar");
		
		Button btnCancelar = new Button(shlCadastroDeProduto, SWT.NONE);
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(325, 130, 82, 27);
	}
}
