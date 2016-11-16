package controle.telas;

import java.time.LocalDateTime;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import controle.dao.AjusteEstoqueProdutoFinalDao;
import controle.dao.ProdutoFinalDao;
import controle.modelos.AjusteEstoqueProdutoFinal;
import controle.modelos.ProdutoFinal;
import controle.modelos.Unidade;
import controle.uteis.Interface;
import controle.uteis.Uteis;

public class CadastrarProdutoFinalAction extends CadastrarProdutoFinal{

	private boolean edicao;
	private ProdutoFinal produto;
	private boolean confirmou;
	
	public CadastrarProdutoFinalAction(Shell pai) {
		super(pai);
		produto = new ProdutoFinal();
		carregarCombo();
		iniciarTela();
	}
	
	public CadastrarProdutoFinalAction(Shell pai, ProdutoFinal produto){
		super(pai);
		this.produto = produto;
		carregarCombo();
		popularCampos();
		iniciarTela();
	}

	private void carregarCombo() {
		Unidade.asList().forEach(u -> comboUnid.add(u.name()));
	}

	private void popularCampos() {
		comboUnid.setText(produto.getUnidade().name());
		adaptarCombo();
		txtNome.setText(produto.getNome());
		spinnerPreco.setSelection(Uteis.getBigDecimalAsSpinnerValue(produto.getPreco(), spinnerPreco.getDigits()));
		spinnerEstoque.setEnabled(false);
	}

	private void adaptarCombo() {
		if(comboUnid.getText().equals(Unidade.KILOS.name())){
			spinnerEstoque.setDigits(3);
		} else{
			spinnerEstoque.setDigits(0);
		}
	}

	private void iniciarTela() {
		adicionarEventosCombo();
		adicionarEventosButton();
		txtNome.forceFocus();
		shell.open();
		Interface.manterJanelaModal(shell);
		
	}

	private void adicionarEventosCombo() {
		comboUnid.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				adaptarCombo();
			}
		});
	}

	private void adicionarEventosButton() {
		btnCancelar.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnGravar.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Interface.verificarPreenchimentoCampos(shell)) {
					criarBean();
					ProdutoFinalDao dao = new ProdutoFinalDao();
					if(edicao){
						dao.atualizar(produto);
					} else{
						dao.cadastrar(produto);
						criarAjusteProdutoFinal();
					}
					confirmou = true;
					shell.close();
				}
			}

			
		});
		
	}
	private void criarAjusteProdutoFinal() {
		AjusteEstoqueProdutoFinal ajuste = new AjusteEstoqueProdutoFinal();
		ajuste.setDataHora(LocalDateTime.now());
		ajuste.setEntrada(true);
		ajuste.setProdutoFinal(produto);
		ajuste.setQuantidade(Uteis.getSpinnerValueAsBigDecimal(spinnerEstoque));
		new AjusteEstoqueProdutoFinalDao().cadastrar(ajuste);
	}

	private void criarBean() {
		produto.setNome(txtNome.getText());
		produto.setUnidade(Unidade.asList().get(comboUnid.getSelectionIndex()));
		produto.setPreco(Uteis.getSpinnerValueAsBigDecimal(spinnerPreco));
	}

	public boolean isConfirmou() {
		return confirmou;
	}

}
