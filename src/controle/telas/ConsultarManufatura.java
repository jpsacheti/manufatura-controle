package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public abstract class ConsultarManufatura{

	protected Shell shell;
	private Shell parent;
	protected Table table;
	protected Text txtDataBase;
	private Text txtDataTeto;
	protected Button btnPesquisar;
	protected Button btnCancelar;
	
	public ConsultarManufatura(Shell parent) {
		this.parent = parent;
		createContents();
	}
	
	private void createContents() {
		shell = new Shell(parent, SWT.DIALOG_TRIM);
		shell.setSize(380, 523);
		shell.setText("Consulta de manufatura");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setSortDirection(SWT.DOWN);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 50, 355, 410);
		
		TableColumn tblclmnData = new TableColumn(table, SWT.CENTER);
		tblclmnData.setWidth(107);
		tblclmnData.setText("Data");
		
		TableColumn tblclmnTotalProdutos = new TableColumn(table, SWT.CENTER);
		tblclmnTotalProdutos.setWidth(108);
		tblclmnTotalProdutos.setText("Materia-prima");
		
		TableColumn tblclmnProdutoFinal = new TableColumn(table, SWT.CENTER);
		tblclmnProdutoFinal.setWidth(120);
		tblclmnProdutoFinal.setText("Produto Final");
		
		txtDataBase = new Text(shell, SWT.BORDER);
		txtDataBase.setFont(new Font(null, "Tahoma", 8, SWT.BOLD));
		txtDataBase.setBounds(new Rectangle(183, 16, 384, 19));
		txtDataBase.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
		txtDataBase.setBounds(10, 25, 108, 19);
		
		btnPesquisar = new Button(shell, SWT.NONE);
		btnPesquisar.setText("&Pesquisar");
		btnPesquisar.setBounds(274, 23, 91, 23);
		
		txtDataTeto = new Text(shell, SWT.BORDER);
		txtDataTeto.setFont(new Font(null, "Tahoma", 8, SWT.BOLD));
		txtDataTeto.setBounds(new Rectangle(183, 16, 384, 19));
		txtDataTeto.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
		txtDataTeto.setBounds(145, 25, 108, 19);
		
		Label lblDe = new Label(shell, SWT.NONE);
		lblDe.setBounds(10, 10, 62, 13);
		lblDe.setText("De");
		
		Label lblAt = new Label(shell, SWT.NONE);
		lblAt.setText("Até");
		lblAt.setBounds(145, 10, 62, 13);
		
		btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(274, 466, 91, 23);
	}
}
