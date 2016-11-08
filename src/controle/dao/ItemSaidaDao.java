package controle.dao;

import java.math.BigDecimal;

import org.mongodb.morphia.query.Query;

import controle.modelos.ItemSaida;
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
}
