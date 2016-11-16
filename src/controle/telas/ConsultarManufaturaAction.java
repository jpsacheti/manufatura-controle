package controle.telas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.ManufaturaDao;
import controle.modelos.Manufatura;
import controle.uteis.Interface;
import controle.uteis.MascaraDatas;
import controle.uteis.Uteis;

public class ConsultarManufaturaAction extends ConsultarManufatura {

	private List<Manufatura> listManufatura;
	private ManufaturaDao dao = new ManufaturaDao();
	private static final DateTimeFormatter padrao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public ConsultarManufaturaAction(Shell pai) {
		super(pai);
		adicionarEventosButton();
		adicionarEventosText();
		adicionarEventosTable();
		preencherDatas();
		shell.open();
		Interface.manterJanelaModal(shell);
	}

	private void adicionarEventosButton() {
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Interface.verificarPreenchimentoCampos(shell)) {
					mostrarDados();
				}
			}
		});
	}
	
	private void preencherDatas(){
		LocalDate primeiroDia = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate ultimoDia = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		txtDataBase.setText(primeiroDia.format(padrao));
		txtDataTeto.setText(ultimoDia.format(padrao));
	}

	private void adicionarEventosText() {
		txtDataBase.addFocusListener(MascaraDatas.getFormatarDatas());
		txtDataTeto.addFocusListener(MascaraDatas.getFormatarDatas());
	}

	private void adicionarEventosTable() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int index = table.getSelectionIndex();
				if(index >= 0){
					new ManufaturarProdutoFinalAction(shell, listManufatura.get(index));
				} else{
					Uteis.exibirMensagem(shell, "Selecione um registro!");
				}
			}
		});
	}

	private void mostrarDados() {
		LocalDate dataBase = LocalDate.parse(txtDataBase.getText(), padrao);
		LocalDate dataTeto = LocalDate.parse(txtDataTeto.getText(), padrao);
		listManufatura = dao.pesquisarPorData(dataBase, dataTeto);
		listManufatura.forEach(m -> {
			TableItem ti = new TableItem(table, SWT.None);
			ti.setText(0, m.getDate().format(padrao));
			ti.setText(1,m.getTotalMateriaPrima().toString());
			ti.setText(2, m.getTotalProdutoFinal().toString());
		});
	}
}
