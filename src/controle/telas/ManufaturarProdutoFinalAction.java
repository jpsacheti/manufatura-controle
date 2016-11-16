package controle.telas;

import java.time.LocalDate;
import java.util.Optional;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.AjusteEstoqueMateriaPrimaDao;
import controle.dao.ManufaturaDao;
import controle.modelos.ItemEntrada;
import controle.modelos.ItemSaida;
import controle.modelos.Manufatura;
import controle.uteis.AppException;
import controle.uteis.BigDecimalConverter;
import controle.uteis.Interface;
import controle.uteis.Uteis;

public class ManufaturarProdutoFinalAction extends ManufaturarProdutoFinal {

	private Manufatura manufatura;
	
	public ManufaturarProdutoFinalAction(Shell pai) {
		super(pai);		
		limparTela();
		iniciarTela();
	}
	
	public ManufaturarProdutoFinalAction(Shell pai, Manufatura manufatura){
		super(pai);
		this.manufatura = manufatura;
		desativarBotoes();
		carregarTabelaMateriaPrima();
		carregarTabelaProdutoFinal();
		shell.open();
		Interface.manterJanelaModal(shell);
	}

	private void desativarBotoes() {
		btnIncluirMP.setEnabled(false);
		btnEditarMP.setEnabled(false);
		btnExcluirMP.setEnabled(false);
		btnGravar.setEnabled(false);
		btnIncluirPF.setEnabled(false);
		btnExcluirPF.setEnabled(false);
		btnEditarPF.setEnabled(false);
		
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
		try {
			validarQuantidadeMateriaPrima();

			criarBean();
			new ManufaturaDao().cadastrar(manufatura);
			Uteis.exibirMensagem(shell, "Produtos processados com sucesso!");

			limparTela();
		} catch (AppException ae) {
			Uteis.exibirErro(shell, ae);
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

	private void validarQuantidadeMateriaPrima() throws AppException {
		AjusteEstoqueMateriaPrimaDao dao = new AjusteEstoqueMateriaPrimaDao();
		/*
		 * for (ItemEntrada ie : manufatura.getListEntrada()) { BigDecimal
		 * estoqueAtualMP = dao.apurarQuantidade(ie.getMateriaPrima()); if
		 * (estoqueAtualMP.compareTo(ie.getQuantidade()) >= 0) {
		 * 
		 * } }
		 */
		Optional<ItemEntrada> opt = manufatura.getListEntrada().stream()
				.filter(ie -> dao.apurarQuantidade(ie.getMateriaPrima()).compareTo(ie.getQuantidade()) < 0)
				.findAny();
		if (opt.isPresent()) {
			throw new AppException("Item insuficiente: " + opt.get().getMateriaPrima().getNome());
		}
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

}
