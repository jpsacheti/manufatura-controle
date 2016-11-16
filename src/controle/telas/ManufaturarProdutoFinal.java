package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public abstract class ManufaturarProdutoFinal {

	protected Shell shell;
	protected Shell pai;
	protected Group grpMateriaPrima;
	protected Table tableMateriaPrima;
	protected Button btnIncluirMP;
	protected Button btnExcluirMP;
	protected Button btnEditarMP;
	protected TableColumn tblclmnProduto;
	protected TableColumn tblclmnUnd;
	protected TableColumn tblclmnQuantidade;
	protected Group grpProdutofinal;
	protected Table tableProdutoFinal;
	protected TableColumn tableColumn;
	protected TableColumn tableColumn_1;
	protected TableColumn tableColumn_2;
	protected Button btnIncluirPF;
	protected Button btnExcluirPF;
	protected Button btnEditarPF;
	protected Button btnGravar;
	protected Button btnCancelar;

	public ManufaturarProdutoFinal(Shell pai) {
		this.pai = pai;
		createContents();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(500, 515);
		shell.setText("Manufatura de produtos");

		grpMateriaPrima = new Group(shell, SWT.NONE);
		grpMateriaPrima.setText("Mat\u00E9ria-Prima");
		grpMateriaPrima.setBounds(10, 10, 470, 195);

		tableMateriaPrima = new Table(grpMateriaPrima, SWT.BORDER | SWT.FULL_SELECTION);
		tableMateriaPrima.setBounds(10, 20, 450, 134);
		tableMateriaPrima.setHeaderVisible(true);
		tableMateriaPrima.setLinesVisible(true);

		tblclmnProduto = new TableColumn(tableMateriaPrima, SWT.NONE);
		tblclmnProduto.setWidth(286);
		tblclmnProduto.setText("Produto");

		tblclmnUnd = new TableColumn(tableMateriaPrima, SWT.CENTER);
		tblclmnUnd.setWidth(68);
		tblclmnUnd.setText("Unidade");

		tblclmnQuantidade = new TableColumn(tableMateriaPrima, SWT.CENTER);
		tblclmnQuantidade.setWidth(47);
		tblclmnQuantidade.setText("Quantidade");

		btnIncluirMP = new Button(grpMateriaPrima, SWT.NONE);
		btnIncluirMP.setBounds(10, 161, 80, 23);
		btnIncluirMP.setText("Incluir");

		btnExcluirMP = new Button(grpMateriaPrima, SWT.NONE);
		btnExcluirMP.setBounds(102, 161, 80, 23);
		btnExcluirMP.setText("Excluir");

		btnEditarMP = new Button(grpMateriaPrima, SWT.NONE);
		btnEditarMP.setBounds(194, 161, 80, 23);
		btnEditarMP.setText("Editar");

		grpProdutofinal = new Group(shell, SWT.NONE);
		grpProdutofinal.setText("Produto Final");
		grpProdutofinal.setBounds(10, 218, 470, 195);

		tableProdutoFinal = new Table(grpProdutofinal, SWT.BORDER | SWT.FULL_SELECTION);
		tableProdutoFinal.setLinesVisible(true);
		tableProdutoFinal.setHeaderVisible(true);
		tableProdutoFinal.setBounds(10, 20, 450, 134);

		tableColumn = new TableColumn(tableProdutoFinal, SWT.NONE);
		tableColumn.setWidth(286);
		tableColumn.setText("Produto");

		tableColumn_1 = new TableColumn(tableProdutoFinal, SWT.CENTER);
		tableColumn_1.setWidth(68);
		tableColumn_1.setText("Unidade");

		tableColumn_2 = new TableColumn(tableProdutoFinal, SWT.CENTER);
		tableColumn_2.setWidth(47);
		tableColumn_2.setText("Quantidade");

		btnIncluirPF = new Button(grpProdutofinal, SWT.NONE);
		btnIncluirPF.setText("Incluir");
		btnIncluirPF.setBounds(10, 161, 80, 23);

		btnExcluirPF = new Button(grpProdutofinal, SWT.NONE);
		btnExcluirPF.setText("Excluir");
		btnExcluirPF.setBounds(102, 161, 80, 23);

		btnEditarPF = new Button(grpProdutofinal, SWT.NONE);
		btnEditarPF.setText("Editar");
		btnEditarPF.setBounds(194, 161, 80, 23);

		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(400, 424, 80, 23);
		btnGravar.setText("&Gravar");

		btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setBounds(400, 450, 80, 23);
		btnCancelar.setText("&Cancelar");

	}
}
