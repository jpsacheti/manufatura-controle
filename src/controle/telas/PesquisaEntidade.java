package controle.telas;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public interface PesquisaEntidade<E> {
	public String getTitulo();
	public boolean alterar(Shell shell, E entidade) throws Exception;
	public void excluir(E entidade) throws Exception;
	public List<E> pesquisar(String argumento, Table tabela) throws Exception;
	public void ajustarEstoque(E entidade, Shell pai);
}
