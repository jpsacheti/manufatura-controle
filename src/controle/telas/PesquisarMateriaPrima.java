package controle.telas;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.MateriaPrimaDao;
import controle.modelos.MateriaPrima;

public class PesquisarMateriaPrima implements PesquisaEntidade<MateriaPrima> {

	@Override
	public String getTitulo() {
		return "Pesquisa de Matéria prima";
	}

	@Override
	public boolean alterar(Shell shell, MateriaPrima entidade) throws Exception {
		CadastrarMateriaPrimaAction janela = new CadastrarMateriaPrimaAction(shell, entidade);
		return janela.isConfirmou();
	}

	@Override
	public void excluir(MateriaPrima entidade) throws Exception {
		new MateriaPrimaDao().excluir(entidade);
	}

	@Override
	public List<MateriaPrima> pesquisar(String argumento, Table tabela) throws Exception {
		MateriaPrimaDao dao = new MateriaPrimaDao();
		List<MateriaPrima> todas = argumento.isEmpty() ? dao.listarTodas() : dao.pesquisar("nome", argumento);

		todas.forEach(mp -> {
			TableItem ti = new TableItem(tabela, SWT.NONE);
			ti.setText(0, mp.getNome());
			ti.setText(1, mp.getUnidade().name());
			ti.setText(2, mp.getPreco().toString());
		});
		return todas;
	}

}
