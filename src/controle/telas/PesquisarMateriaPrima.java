package controle.telas;

import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.AjusteEstoqueMateriaPrimaDao;
import controle.dao.MateriaPrimaDao;
import controle.modelos.AjusteEstoqueMateriaPrima;
import controle.modelos.MateriaPrima;
import controle.modelos.Unidade;

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
			ti.setText(2, mp.getQuantidade().toString());
		});
		return todas;
	}

	@Override
	public void ajustarEstoque(MateriaPrima entidade, Shell pai) {
		AjusteEstoque ajuste = new AjusteEstoque(pai, entidade.getNome(), entidade.getUnidade().equals(Unidade.KILOS) ? 4 : 0);
		if (ajuste.isFinalizou()) {
			AjusteEstoqueMateriaPrima ajusteProduto = new AjusteEstoqueMateriaPrima();
			ajusteProduto.setDataHora(new Date());
			ajusteProduto.setEntrada(ajuste.isEntrada());
			ajusteProduto.setQuantidade(ajuste.getQuantidade());
			ajusteProduto.setMateriaPrima(entidade);
			new AjusteEstoqueMateriaPrimaDao().cadastrar(ajusteProduto);
		}

	}

}
