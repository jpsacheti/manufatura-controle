package controle.telas;

import java.util.List;

import org.eclipse.swt.widgets.Combo;

import controle.dao.MateriaPrimaDao;
import controle.modelos.MateriaPrima;

public class MateriaEstocavel implements Estocavel<MateriaPrima> {

	private MateriaPrimaDao dao = new MateriaPrimaDao();
	private List<MateriaPrima> list = dao.listarTodas();
	
	@Override
	public void popularCombo(Combo combo) {
		list.forEach(m -> combo.add(m.getNome()));
	}

	@Override
	public List<MateriaPrima> getLista() {
		return list;
	}



}
