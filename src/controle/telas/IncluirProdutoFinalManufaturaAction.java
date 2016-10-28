package controle.telas;

import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import controle.dao.ProdutoFinalDao;
import controle.modelos.ItemSaida;
import controle.modelos.ProdutoFinal;
import controle.uteis.BigDecimalConverter;
import controle.uteis.Interface;
import controle.uteis.Uteis;

public class IncluirProdutoFinalManufaturaAction extends IncluirProdutoFinalManufatura {

	private List<ProdutoFinal> produtos;
	private ItemSaida item;
	private boolean confirmou = false;

	public IncluirProdutoFinalManufaturaAction(Shell pai) {
		super(pai);
		carregarCombo();
		iniciarTela();

	}

	public IncluirProdutoFinalManufaturaAction(Shell pai, ItemSaida item) {
		super(pai);
		this.item = item;
		carregarCombo();
		carregarDados();
		iniciarTela();
	}

	private void carregarDados() {
		combo.setText(item.getProdutoFinal().getNome());
		combo.setEnabled(false);
		text.setText(BigDecimalConverter.bigDecimalToString(item.getQuantidade(), 4));

	}

	private void iniciarTela() {
		adicionarEventosButton();
		combo.forceFocus();
		shell.open();
		Interface.manterJanelaModal(shell);

	}

	private void carregarCombo() {
		try{
			produtos = new ProdutoFinalDao().listarTodas();
			produtos.stream().map(ProdutoFinal::getNome).forEach(nome -> combo.add(nome));
		} catch (Exception e) {
			Uteis.exibirErro(btnGravar, e);
		}
		
	}

	private void adicionarEventosButton() {
		btnGravar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (Interface.verificarPreenchimentoCampos(shell)) {
					criarBean();
					confirmou = true;
					shell.close();
				}
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (Confirmacao.getConfirmacao(shell, "Tem certeza que deseja sair?")) {
					shell.close();
					confirmou = false;
				}
			}
		});

	}

	private void criarBean() {
		item = new ItemSaida();
		ProdutoFinal produto = produtos.get(combo.getSelectionIndex());
		item.setProdutoFinal(produto);
		item.setQuantidade(BigDecimalConverter.stringToBigDecimal(text.getText()));

	}

	public boolean isConfirmou() {
		return confirmou;
	}

	public ItemSaida getProdutoFinal() {
		return item;
	}

}
