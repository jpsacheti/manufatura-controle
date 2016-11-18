package controle.dao;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.query.Query;

import controle.modelos.ItemEntrada;
import controle.modelos.Manufatura;
import controle.modelos.MateriaPrima;

public class ItemEntradaDao extends AbstractDao<ItemEntrada> {
	public ItemEntradaDao() {
		super(ItemEntrada.class);
	}

	public BigDecimal getTotalUtilizado(MateriaPrima materiaPrima) {
		Query<ItemEntrada> query = MorphiaHelper.getDatastore().createQuery(ItemEntrada.class);
		return query.field("materiaPrima")
				    .equal(materiaPrima)
				    .asList()
				    .stream().map(ItemEntrada::getQuantidade)
				    .reduce(BigDecimal.ZERO, (a, s)-> a.add(s));
	}

	public List<ItemEntrada> getItensManufatura(Manufatura manufatura) {
		Query<ItemEntrada> query = MorphiaHelper.getDatastore().createQuery(ItemEntrada.class);
		return query.field("manufatura")
				    .equal(manufatura)
				    .asList();
	}
}
