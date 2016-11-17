package controle.telas;

import java.util.List;

import org.eclipse.swt.widgets.Combo;

public interface Estocavel<E> {
	public void popularCombo(Combo combo);
	public List<E> getLista();
}
