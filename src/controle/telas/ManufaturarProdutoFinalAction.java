package controle.telas;

import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.ManufaturaDao;
import controle.modelos.ItemEntrada;
import controle.modelos.ItemSaida;
import controle.modelos.Manufatura;
import controle.uteis.Interface;
import controle.uteis.Uteis;

public class ManufaturarProdutoFinalAction extends ManufaturarProdutoFinal {

	private Manufatura manufatura;
	private boolean confirmou;

	public ManufaturarProdutoFinalAction(Shell pai) {
		super(pai);
		limparTela();
		iniciarTela();
	}

	private void limparTela() {
		manufatura = new Manufatura();
		btnIncluirMP.forceFocus();
		tableMateriaPrima.removeAll();
		tableProdutoFinal.removeAll();
	}

	private void iniciarTela() {
		adicionarEventosTable();
		adicionarEventosButton();
		shell.open();
		Interface.manterJanelaModal(shell);
	}

	private void adicionarEventosTable() {
		tableMateriaPrima.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				editarMateriaPrima();
			}
		});
		tableProdutoFinal.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				editarProdutoFinal();
			}
		});
	}

	private void adicionarEventosButton() {
		btnIncluirMP.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				chamarIncluirMateriaPrima();
			}
		});

		btnExcluirMP.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = tableMateriaPrima.getSelectionIndex();
				if (index >= 0) {
					manufatura.removerEntrada(index);
					carregarTabelaMateriaPrima();
				} else {
					Uteis.exibirMensagem(shell, "Selecione um produto!");
				}
			}
		});

		btnEditarMP.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				editarMateriaPrima();
			}

		});

		btnIncluirPF.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				incluirProdutoFinal();
			}
		});

		btnExcluirPF.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = tableProdutoFinal.getSelectionIndex();
				if (index >= 0) {
					manufatura.removeSaida(index);
					carregarTabelaProdutoFinal();
				} else {
					Uteis.exibirMensagem(shell, "Selecione um produto!");
				}
			}
		});
		btnEditarPF.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				editarProdutoFinal();
			}
		});

		btnGravar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				salvar();
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
	}

	private void salvar() {
		if (validarQuantidadeMateriaPrima()) {

			criarBean();
			new ManufaturaDao().cadastrar(manufatura);
			Uteis.exibirMensagem(shell, "Produtos processados com sucesso!");

			limparTela();

		}
	}

	private void criarBean() {
		for (ItemEntrada iem : manufatura.getListEntrada()) {
			iem.setManufatura(manufatura);
		}
		for (ItemSaida ism : manufatura.getListSaida()) {
			ism.setManufatura(manufatura);
		}

		manufatura.setDate(LocalDate.now());
	}

	private boolean validarQuantidadeMateriaPrima() {
		boolean result = true;
		//TODO: fazer aqui
		/*for (ItemEntrada iem : manufatura.getListEntrada()) {
			BigDecimal quantidade = new ProdutoDao().apurarEstoqueDisponivel(iem.getProduto());
			if (quantidade.compareTo(iem.getQuantidade()) < 0) {
				Uteis.exibirMensagem(
						"Não existe quantidade suficiente deste produto: " + iem.getMateriaPrima().getNome());
				result = false;
			}
		}*/

		return result;
	}

	private void editarMateriaPrima() {
		int index = tableMateriaPrima.getSelectionIndex();
		if (index >= 0) {
			IncluirMateriaPrimaManufaturaAction janela = new IncluirMateriaPrimaManufaturaAction(shell,
					manufatura.getListEntrada().get(index));
			if (janela.isConfirmou()) {
				manufatura.getListEntrada().set(index, janela.getMateriaPrima());
				carregarTabelaMateriaPrima();
			}
		}
	}

	private void editarProdutoFinal() {
		int index = tableProdutoFinal.getSelectionIndex();
		if (index >= 0) {
			IncluirProdutoFinalManufaturaAction janela = new IncluirProdutoFinalManufaturaAction(shell,
					manufatura.getListSaida().get(index));
			if (janela.isConfirmou()) {
				manufatura.getListSaida().set(index, janela.getProdutoFinal());
				carregarTabelaProdutoFinal();
			}
		}
	}

	private void chamarIncluirMateriaPrima() {
		IncluirMateriaPrimaManufaturaAction janela = new IncluirMateriaPrimaManufaturaAction(shell);
		if (janela.isConfirmou()) {
			ItemEntrada entrada = janela.getMateriaPrima();
			if (entrada != null) {
				manufatura.adicionarEntrada(entrada);
			}
		}
		carregarTabelaMateriaPrima();
	}

	private void incluirProdutoFinal() {
		IncluirProdutoFinalManufaturaAction janela = new IncluirProdutoFinalManufaturaAction(shell);
		if (janela.isConfirmou()) {
			ItemSaida saida = janela.getProdutoFinal();
			if (saida != null) {
				manufatura.adicionarSaida(saida);
				carregarTabelaMateriaPrima();
			}
		}
		carregarTabelaProdutoFinal();
	}

	private void carregarTabelaMateriaPrima() {
		tableMateriaPrima.removeAll();
		for (ItemEntrada item : manufatura.getListEntrada()) {
			TableItem ti = new TableItem(tableMateriaPrima, SWT.NORMAL);
			ti.setText(0, item.getMateriaPrima().getNome());
			ti.setText(1, item.getMateriaPrima().getUnidade().name());
			ti.setText(2, BigDecimalConverter.bigDecimalToString(item.getQuantidade(), 4));
		}
		tableMateriaPrima.setSelection(tableMateriaPrima.getItemCount() - 1);
	}

	private void carregarTabelaProdutoFinal() {
		tableProdutoFinal.removeAll();
		for (ItemSaida item : manufatura.getListSaida()) {
			TableItem ti = new TableItem(tableProdutoFinal, SWT.NORMAL);
			ti.setText(0, item.getProdutoFinal().getNome());
			ti.setText(1, item.getProdutoFinal().getUnidade().name());
			ti.setText(2, BigDecimalConverter.bigDecimalToString(item.getQuantidade(), 4));
		}
		tableProdutoFinal.setSelection(tableProdutoFinal.getItemCount() - 1);
	}

	public boolean isConfirmou() {
		return confirmou;
	}

}
