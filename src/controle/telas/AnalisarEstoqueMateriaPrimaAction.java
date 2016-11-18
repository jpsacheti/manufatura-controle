package controle.telas;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.AjusteEstoqueMateriaPrimaDao;
import controle.modelos.AjusteEstoqueMateriaPrima;
import controle.modelos.MateriaPrima;
import controle.modelos.Unidade;
import controle.uteis.BigDecimalConverter;
import controle.uteis.Interface;

public class AnalisarEstoqueMateriaPrimaAction extends AnalisarEstoqueMateriaPrima {

	private MateriaPrima materia;
	private AjusteEstoqueMateriaPrimaDao daoAjuste = new AjusteEstoqueMateriaPrimaDao();
	private List<AjusteEstoqueMateriaPrima> listAjustes = new ArrayList<>();

	public AnalisarEstoqueMateriaPrimaAction(Shell pai, MateriaPrima materia) {
		super(pai);
		this.materia = Objects.requireNonNull(materia);
		adicionarEventosButton();
		popularDados();
		shell.open();
		Interface.manterJanelaModal(shell);
	}

	private void popularDados() {
		SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		lblItem.setText(materia.getNome());
		BigDecimal total = daoAjuste.apurarQuantidade(materia);
		lblTotal.setText(total.toPlainString());
		listAjustes = daoAjuste.pesquisar(materia);
		listAjustes.forEach(ae -> {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(0, formatoDataHora.format(ae.getDataHora()));
			ti.setText(1, formatarQuantidade(ae));
			ti.setText(2, ae.isEntrada() ? "E" : "S");
		});
	}
	
	private void adicionarEventosButton(){
		btnFechar.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
	}

	private String formatarQuantidade(AjusteEstoqueMateriaPrima ae) {
		StringBuilder sb = new StringBuilder();
		sb.append(ae.isEntrada() ? "" : "-");
		boolean isDecimal = ae.getMateriaPrima().getUnidade().equals(Unidade.KILOS);
		sb.append(BigDecimalConverter.bigDecimalToString(ae.getQuantidade(), isDecimal ? 4 : 0));
		return sb.toString();
	}
}
