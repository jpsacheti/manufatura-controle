package controle.dao;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.query.Query;

import controle.modelos.ItemSaida;
import controle.modelos.Manufatura;
import controle.modelos.ProdutoFinal;

public class ItemSaidaDao extends AbstractDao<ItemSaida> {
	public ItemSaidaDao() {
		super(ItemSaida.class);
	}
	
	public BigDecimal getTotalUtilizado(ProdutoFinal produto){
		Query<ItemSaida> query = MorphiaHelper.getDatastore().createQuery(ItemSaida.class);
		return query.field("produtoFinal")
				    .equal(produto)
				    .asList()
				    .stream().map(ItemSaida::getQuantidade)
				    .reduce(BigDecimal.ZERO, (a, s)-> a.add(s));
	}

	public List<ItemSaida> getItensManufatura(Manufatura manufatura) {
		Query<ItemSaida> query = MorphiaHelper.getDatastore().createQuery(ItemSaida.class);
		return query.field("manufatura")
				    .equal(manufatura)
				    .asList();
	}
}
