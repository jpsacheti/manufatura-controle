package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class Principal {

	protected Shell shell;

	public static void main(String[] args) {
		try {
			Principal window = new Principal();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setSize(410, 300);
		shell.setText("Controle de manufatura");
		shell.setLayout(null);

		Group grpMatriaprima = new Group(shell, SWT.NONE);
		grpMatriaprima.setText("Matéria-prima");
		grpMatriaprima.setBounds(10, 21, 179, 108);

		Button btnCadastrarMP = new Button(grpMatriaprima, SWT.NONE);
		btnCadastrarMP.setBounds(47, 10, 83, 29);
		btnCadastrarMP.setText("Cadastrar");
		btnCadastrarMP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new CadastrarMateriaPrimaAction(shell);
			}
		});

		Button btnPesquisarMP = new Button(grpMatriaprima, SWT.NONE);
		btnPesquisarMP.setText("Pesquisar");
		btnPesquisarMP.setBounds(47, 51, 83, 29);
		btnPesquisarMP.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				new TelaPesquisa<>(shell, new PesquisarMateriaPrima());
			}
		});

		Group grpProdutoFinal = new Group(shell, SWT.NONE);
		grpProdutoFinal.setText("Produto final");
		grpProdutoFinal.setBounds(219, 21, 179, 108);

		Button btnCadastrarPF = new Button(grpProdutoFinal, SWT.NONE);
		btnCadastrarPF.setText("Cadastrar");
		btnCadastrarPF.setBounds(47, 10, 83, 29);
		btnCadastrarPF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new CadastrarProdutoFinalAction(shell);
			}
		});

		Button btnPesquisarPF = new Button(grpProdutoFinal, SWT.NONE);
		btnPesquisarPF.setText("Pesquisar");
		btnPesquisarPF.setBounds(47, 51, 83, 29);
		btnPesquisarPF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new TelaPesquisa<>(shell, new PesquisarProdutoFinal());
			}
		});

		Group grpManufatura = new Group(shell, SWT.NONE);
		grpManufatura.setText("Manufatura");
		grpManufatura.setBounds(114, 135, 179, 108);

		Button btnConsultar = new Button(grpManufatura, SWT.NONE);
		btnConsultar.setText("Consultar");
		btnConsultar.setBounds(47, 51, 83, 29);
		btnConsultar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO: implementar
			}
		});

		Button btnCadastrarManufatura = new Button(grpManufatura, SWT.NONE);
		btnCadastrarManufatura.setText("Cadastrar");
		btnCadastrarManufatura.setBounds(47, 10, 83, 29);
		btnCadastrarManufatura.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				new ManufaturarProdutoFinalAction(shell);
			}
		});

	}
}
