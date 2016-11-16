package controle.telas;

import java.time.LocalDateTime;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import controle.dao.AjusteEstoqueMateriaPrimaDao;
import controle.dao.MateriaPrimaDao;
import controle.modelos.AjusteEstoqueMateriaPrima;
import controle.modelos.MateriaPrima;
import controle.modelos.Unidade;
import controle.uteis.Interface;
import controle.uteis.Uteis;

public class CadastrarMateriaPrimaAction extends CadastrarMateriaPrima {

	private boolean edicao;
	private boolean confirmou = false;
	private MateriaPrima materiaPrima;
	
	
	public CadastrarMateriaPrimaAction(Shell pai) {
		super(pai);
		materiaPrima = new MateriaPrima();
		carregarCombo();
		iniciarTela();
	}
	public CadastrarMateriaPrimaAction(Shell pai, MateriaPrima materiaPrima) {
		super(pai);
		this.materiaPrima = materiaPrima;
		this.edicao = true;
		carregarCombo();
		popularCampos();
		iniciarTela();
	}


	private void popularCampos() {
		comboUnid.setText(materiaPrima.getUnidade().name());
		adaptarCombo();
		txtNome.setText(materiaPrima.getNome());
		spinnerPreco.setSelection(Uteis.getBigDecimalAsSpinnerValue(materiaPrima.getPreco(), spinnerPreco.getDigits()));
		spinnerQuantidadeInicial.setEnabled(false);
		
	}
	private void carregarCombo() {
		Unidade.asList().forEach(u -> comboUnid.add(u.name()));	
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
					MateriaPrimaDao dao = new MateriaPrimaDao();
					if(edicao){
						dao.atualizar(materiaPrima);
					} else{
						dao.cadastrar(materiaPrima);
						criarAjusteMateriaPrima();
					}
					confirmou = true;
					shell.close();
				}
			}
			
		});
	}

	private void criarBean() {
		materiaPrima.setNome(txtNome.getText());
		materiaPrima.setUnidade(Unidade.asList().get(comboUnid.getSelectionIndex()));
		materiaPrima.setPreco(Uteis.getSpinnerValueAsBigDecimal(spinnerPreco));
	}
	private void adaptarCombo() {
		if(comboUnid.getText().equals(Unidade.KILOS.name())){
			spinnerQuantidadeInicial.setDigits(3);
		} else{
			spinnerQuantidadeInicial.setDigits(0);
		}
	}
	private void criarAjusteMateriaPrima() {
		AjusteEstoqueMateriaPrima ajuste = new AjusteEstoqueMateriaPrima();
		ajuste.setDataHora(LocalDateTime.now());
		ajuste.setEntrada(true);
		ajuste.setMateriaPrima(materiaPrima);
		ajuste.setQuantidade(Uteis.getSpinnerValueAsBigDecimal(spinnerQuantidadeInicial));
		new AjusteEstoqueMateriaPrimaDao().cadastrar(ajuste);
	}
	
	public boolean isConfirmou() {
		return confirmou;
	}

}
