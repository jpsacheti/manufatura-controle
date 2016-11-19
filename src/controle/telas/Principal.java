package controle.telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import controle.modelos.MateriaPrima;
import controle.modelos.ProdutoFinal;

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
		grpMatriaprima.setBounds(10, 21, 179, 141);

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
		btnPesquisarMP.setBounds(47, 45, 83, 29);

		Button btnHistoricoMP = new Button(grpMatriaprima, SWT.NONE);
		btnHistoricoMP.setText("Histórico");
		btnHistoricoMP.setBounds(47, 84, 83, 29);
		btnHistoricoMP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelecionarItem<MateriaPrima> selecionar = new SelecionarItem<>(shell, new MateriaEstocavel());
				selecionar.getSelecionada().ifPresent(s -> new AnalisarEstoqueMateriaPrimaAction(shell, s));
			}
		});

		btnPesquisarMP.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				new TelaPesquisa<>(shell, new PesquisarMateriaPrima());
			}
		});

		Group grpProdutoFinal = new Group(shell, SWT.NONE);
		grpProdutoFinal.setText("Produto final");
		grpProdutoFinal.setBounds(219, 21, 179, 141);

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
		btnPesquisarPF.setBounds(47, 45, 83, 29);

		Button btnHistoricoPF = new Button(grpProdutoFinal, SWT.NONE);
		btnHistoricoPF.setText("Histórico");
		btnHistoricoPF.setBounds(47, 84, 83, 29);
		btnHistoricoPF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelecionarItem<ProdutoFinal> selecionar = new SelecionarItem<>(shell, new ProdutoEstocavel());
				selecionar.getSelecionada().ifPresent(s -> new AnalisarEstoqueProdutoFinalAction(shell, s));
			}
		});

		btnPesquisarPF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new TelaPesquisa<>(shell, new PesquisarProdutoFinal());
			}
		});

		Group grpManufatura = new Group(shell, SWT.NONE);
		grpManufatura.setText("Manufatura");
		grpManufatura.setBounds(115, 197, 179, 66);

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
