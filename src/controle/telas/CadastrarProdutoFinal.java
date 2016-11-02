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
	protected Spinner spinnerPreco;
	protected Combo comboUnid;
	protected Spinner spinnerEstoque;
	protected Button btnGravar;
	protected Button btnCancelar;

	public CadastrarProdutoFinal(Shell pai) {
		this.pai = pai;
		createContents();
	}

	private void createContents() {
		shlCadastroDeProduto = new Shell(pai, SWT.DIALOG_TRIM);
		shlCadastroDeProduto.setSize(421, 220);
		shlCadastroDeProduto.setText("Cadastro de produto final");
		shlCadastroDeProduto.setLayout(null);

		grpDadosDoProdutoFinal = new Group(shlCadastroDeProduto, SWT.NONE);
		grpDadosDoProdutoFinal.setText("Dados do produto final");
		grpDadosDoProdutoFinal.setBounds(10, 12, 397, 138);

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

		spinnerPreco = new Spinner(grpDadosDoProdutoFinal, SWT.BORDER);
		spinnerPreco.setDigits(2);
		spinnerPreco.setMaximum(99999999);
		spinnerPreco.setBounds(81, 26, 128, 22);

		Label lblUnidade = new Label(grpDadosDoProdutoFinal, SWT.NONE);
		lblUnidade.setText("Unidade");
		lblUnidade.setAlignment(SWT.RIGHT);
		lblUnidade.setBounds(0, 59, 75, 17);

		comboUnid = new Combo(grpDadosDoProdutoFinal, SWT.NONE);
		comboUnid.setBounds(81, 54, 293, 22);

		Label label = new Label(grpDadosDoProdutoFinal, SWT.NONE);
		label.setText("Estoque");
		label.setAlignment(SWT.RIGHT);
		label.setBounds(0, 91, 75, 17);

		spinnerEstoque = new Spinner(grpDadosDoProdutoFinal, SWT.BORDER);
		spinnerEstoque.setMaximum(99999999);
		spinnerEstoque.setDigits(2);
		spinnerEstoque.setBounds(81, 88, 128, 22);

		btnGravar = new Button(shlCadastroDeProduto, SWT.NONE);
		btnGravar.setBounds(235, 156, 82, 27);
		btnGravar.setText("Gravar");

		btnCancelar = new Button(shlCadastroDeProduto, SWT.NONE);
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(327, 156, 82, 27);
	}
}
