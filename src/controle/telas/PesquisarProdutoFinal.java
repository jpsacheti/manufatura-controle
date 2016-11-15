package controle.telas;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import controle.dao.ProdutoFinalDao;
import controle.modelos.ProdutoFinal;

public class PesquisarProdutoFinal implements PesquisaEntidade<ProdutoFinal> {

	@Override
	public String getTitulo() {
		return "Pesquisar produto final";
	}

	@Override
	public boolean alterar(Shell shell, ProdutoFinal entidade) throws Exception {
		CadastrarProdutoFinalAction janela = new CadastrarProdutoFinalAction(shell, entidade);
		return janela.isConfirmou();
	}

	@Override
	public void excluir(ProdutoFinal entidade) throws Exception {
		new ProdutoFinalDao().excluir(entidade);		
	}

	@Override
	public List<ProdutoFinal> pesquisar(String argumento, Table tabela) throws Exception {
		ProdutoFinalDao dao = new ProdutoFinalDao();
		List<ProdutoFinal> lista = argumento.isEmpty() ? dao.listarTodas() : dao.pesquisar("nome", argumento);
		lista.forEach(pf ->{
			TableItem ti = new TableItem(tabela, SWT.NONE);
			ti.setText(0, pf.getNome());
			ti.setText(1, pf.getUnidade().name());
			ti.setText(2, pf.getPreco().toString());
		});
		return lista;
	}

}
