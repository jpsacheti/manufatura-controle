package controle.telas;

import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import controle.modelos.ItemEntrada;
import controle.modelos.MateriaPrima;
import controle.uteis.Interface;
import controle.uteis.Uteis;

public class IncluirMateriaPrimaManufaturaAction extends IncluirMateriaPrimaManufatura {

	private List<MateriaPrima> produtos;
	private ItemEntrada item;
	private boolean confirmou = false;

	public IncluirMateriaPrimaManufaturaAction(Shell shell) {
		super(shell);
		carregarCombo();
		iniciarTela();
	}

	public IncluirMateriaPrimaManufaturaAction(Shell shell, ItemEntrada item) {
		super(shell);
		this.item = item;
		carregarCombo();
		carregarDados(item);
		iniciarTela();
	}

	private void iniciarTela() {
		adicionarEventosButton();
		combo.forceFocus();
		shell.open();
		Interface.manterJanelaModal(shell);
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
		try {
			item = new ItemEntrada();
			MateriaPrima materia = produtos.get(combo.getSelectionIndex());
			item.setMateriaPrima(materia);
			item.setQuantidade(BigDecimalConverter.stringToBigDecimal(text.getText()));
		} catch (Exception e) {
			Uteis.mostrarErro(e);
		}
	}

	private void carregarCombo() {
		//TODO: fazer
		/*HibernateUtil.begin();
		try {
			produtos = new ProdutoDao().getProdutosMateriaPrimaAtivos();
			for (Produto p : produtos) {
				combo.add(p.getNome());
			}
			HibernateUtil.commit();
		} catch (Exception e) {
			HibernateUtil.rollback();
			KlojaUteis.exibirErro(e, combo);
			KlojaUteis.gravarLog(e);
		}*/	
	}

	private void carregarDados(ItemEntrada item) {
		combo.setText(item.getMateriaPrima().getNome());
		combo.setEnabled(false);
		text.setText(BigDecimalConverter.bigDecimalToString(item.getQuantidade(), 4));
	}

	public ItemEntrada getMateriaPrima() {
		return item;
	}

	public boolean isConfirmou() {
		return confirmou;
	}
}
