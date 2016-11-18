package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public abstract class AnalisarEstoqueMateriaPrima {

	protected Shell shell;
	private Shell pai;
	protected Table table;
	protected Button btnFechar;
	protected Label lblTotal;
	protected Label lblItem;
	
	public AnalisarEstoqueMateriaPrima(Shell pai) {
		this.pai = pai;
		createContents();
	}

	private void createContents() {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(409, 394);
		shell.setText("Consulta de estoque de Matéria-prima");
		
		lblItem = new Label(shell, SWT.NONE);
		lblItem.setAlignment(SWT.CENTER);
		lblItem.setBounds(31, 10, 342, 17);
		lblItem.setText("Item");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 45, 385, 272);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnData = new TableColumn(table, SWT.CENTER);
		tblclmnData.setWidth(141);
		tblclmnData.setText("Data");
		
		TableColumn tblclmnQuantidade = new TableColumn(table, SWT.CENTER);
		tblclmnQuantidade.setWidth(155);
		tblclmnQuantidade.setText("Quantidade");
		
		TableColumn tblclmnTipo = new TableColumn(table, SWT.CENTER);
		tblclmnTipo.setWidth(71);
		tblclmnTipo.setText("Tipo");
		
		lblTotal = new Label(shell, SWT.NONE);
		lblTotal.setBounds(10, 323, 143, 17);
		lblTotal.setText("Total");
		
		btnFechar = new Button(shell, SWT.NONE);
		btnFechar.setBounds(313, 337, 82, 27);
		btnFechar.setText("Fechar");

	}
}
