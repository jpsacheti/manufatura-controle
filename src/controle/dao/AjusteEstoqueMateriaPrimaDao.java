package controle.dao;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.query.Query;

import controle.modelos.AjusteEstoqueMateriaPrima;
import controle.modelos.MateriaPrima;

public class AjusteEstoqueMateriaPrimaDao extends AbstractDao<AjusteEstoqueMateriaPrima> {

	public AjusteEstoqueMateriaPrimaDao() {
		super(AjusteEstoqueMateriaPrima.class);
	}

	public BigDecimal apurarQuantidade(MateriaPrima materiaPrima) {
		Query<AjusteEstoqueMateriaPrima> query = MorphiaHelper.getDatastore().createQuery(AjusteEstoqueMateriaPrima.class);
		query.field("materiaPrima").equal(materiaPrima);
		List<AjusteEstoqueMateriaPrima> ajustes = query.asList();
		BigDecimal adicoes = ajustes.stream().filter(a -> a.isEntrada()).map(a -> a.getQuantidade()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
		BigDecimal producoes = new ItemEntradaDao().getTotalUtilizado(materiaPrima);
		BigDecimal retiradas = ajustes.stream().filter(a -> !a.isEntrada()).map(a ->a.getQuantidade()).reduce(BigDecimal.ZERO, (a,b)->a.add(b));
		return adicoes.subtract(producoes).subtract(retiradas);
	}

}
