package controle.telas;

import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import controle.uteis.Interface;
import controle.uteis.Uteis;

public class TelaPesquisa<E> {

	private Shell shell;
	private Shell pai;
	private PesquisaEntidade<E> pesquisaEntidade;
	private List<E> list = Collections.emptyList();
	private Text text;
	private Table table;
	private Button btnpesquisar;
	private TableColumn tblclmnNome;
	private TableColumn tblclmnUnodade;
	private TableColumn tblclmnQuantidade;

	public TelaPesquisa(Shell pai, PesquisaEntidade<E> pesquisaEntidade) {
		this.pai = pai;
		this.pesquisaEntidade = pesquisaEntidade;
		createContents();
		shell.open();
		Interface.manterJanelaModal(shell);
	}

	private void createContents() {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(683, 508);
		shell.setText(pesquisaEntidade.getTitulo());

		text = new Text(shell, SWT.BORDER);
		text.setFont(new Font(null, "Tahoma", 8, SWT.BOLD));
		text.setBounds(new Rectangle(183, 16, 384, 19));
		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		text.setBounds(10, 12, 562, 19);
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				if (Uteis.isTeclaEnter(e.keyCode)) {
					mostrarDados();
				}
			}
		});

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setSortDirection(SWT.DOWN);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 43, 661, 410);

		tblclmnNome = new TableColumn(table, SWT.NONE);
		tblclmnNome.setWidth(433);
		tblclmnNome.setText("Nome");

		tblclmnUnodade = new TableColumn(table, SWT.CENTER);
		tblclmnUnodade.setWidth(108);
		tblclmnUnodade.setText("Unidade");

		tblclmnQuantidade = new TableColumn(table, SWT.CENTER);
		tblclmnQuantidade.setWidth(100);
		tblclmnQuantidade.setText("Quantidade");

		table.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				if (e.keyCode == 127) {
					excluirRegistro();
				} else if (Uteis.isTeclaEnter(e.keyCode)) {
					alterarRegistro();
				}
			}
		});
		table.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
				alterarRegistro();
			}
		});

		Button button = new Button(shell, SWT.NONE);
		button.setText("&Cancelar");
		button.setBounds(591, 459, 80, 23);
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				shell.close();
			}
		});

		btnpesquisar = new Button(shell, SWT.NONE);
		btnpesquisar.setText("&Pesquisar");
		btnpesquisar.setBounds(578, 10, 91, 23);
		btnpesquisar.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				mostrarDados();
			}
		});

	}

	private void mostrarDados() {
		try {
			table.removeAll();
			list = pesquisaEntidade.pesquisar(text.getText(), table);
			text.forceFocus();
			text.selectAll();
		} catch (Exception e) {
			Uteis.exibirErro(shell, e);
		}
	}

	private void alterarRegistro() {
		try {
			if (table.getSelectionIndex() != -1) {
				shell.setVisible(false);
				E entidade = list.get(table.getSelectionIndex());
				if (pesquisaEntidade.alterar(pai, entidade)) {
					mostrarDados();
				}
				shell.setVisible(true);
				table.forceFocus();
			}
		} catch (Exception e) {
			Uteis.exibirErro(shell, e);
		}

	}

	private void excluirRegistro() {
		try {
			if (table.getSelectionIndex() >= 0) {
				if (Uteis.getConfirmacao(table, "Deseja realmente excluir este registro?")) {
					E entidade = list.get(table.getSelectionIndex());
					pesquisaEntidade.excluir(entidade);
					table.remove(table.getSelectionIndex());
					table.setSelection(table.getSelectionIndex());
				}
			}
		} catch (Exception e) {
			Uteis.exibirErro(shell, e);
		}

	}

}
