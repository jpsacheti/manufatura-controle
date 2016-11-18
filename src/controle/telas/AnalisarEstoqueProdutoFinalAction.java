package controle.telas;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.AjusteEstoqueProdutoFinalDao;
import controle.modelos.AjusteEstoqueProdutoFinal;
import controle.modelos.ProdutoFinal;
import controle.modelos.Unidade;
import controle.uteis.BigDecimalConverter;
import controle.uteis.Interface;

public class AnalisarEstoqueProdutoFinalAction extends AnalisarEstoqueProdutoFinal{

	private ProdutoFinal produto;
	private AjusteEstoqueProdutoFinalDao daoAjuste = new AjusteEstoqueProdutoFinalDao();
	private List<AjusteEstoqueProdutoFinal> list = new ArrayList<>();
	
	public AnalisarEstoqueProdutoFinalAction(Shell pai, ProdutoFinal produto) {
		super(pai);
		this.produto = Objects.requireNonNull(produto);
		adicionarEventosButton();
		popularDados();
		shell.open();
		Interface.manterJanelaModal(shell);
	}
	
	private void adicionarEventosButton(){
		btnFechar.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
	}
	
	private void popularDados(){
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		lblItem.setText(produto.getNome());
		BigDecimal total = daoAjuste.apurarQuantidade(produto);
		lblTotal.setText(total.toPlainString());
		list = daoAjuste.pesquisar(produto);
		list.forEach(ae -> {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(0, formatoDataHora.format(ae.getDataHora()));
			ti.setText(1, formatarQuantidade(ae));
			ti.setText(2, ae.isEntrada() ? "E" : "S");
		});
	}
	
	private String formatarQuantidade(AjusteEstoqueProdutoFinal ae) {
		StringBuilder sb = new StringBuilder();
		sb.append(ae.isEntrada() ? "" : "-");
		boolean isDecimal = ae.getProdutoFinal().getUnidade().equals(Unidade.KILOS);
		sb.append(BigDecimalConverter.bigDecimalToString(ae.getQuantidade(), isDecimal ? 4 : 0));
		return sb.toString();
	}

}
