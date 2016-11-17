package controle.telas;

import java.util.List;

import org.eclipse.swt.widgets.Combo;

import controle.dao.ProdutoFinalDao;
import controle.modelos.ProdutoFinal;

public class ProdutoSelecionavel implements Selecionavel<ProdutoFinal> {

	private ProdutoFinalDao dao = new ProdutoFinalDao();
	private List<ProdutoFinal> list = dao.listarTodas();

	@Override
	public void popularCombo(Combo combo) {
		list.forEach(p -> combo.add(p.getNome()));
		
	}

	@Override
	public List<ProdutoFinal> getLista() {
		return list;
	}

}
