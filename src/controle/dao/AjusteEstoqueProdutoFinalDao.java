package controle.dao;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.query.Query;

import controle.modelos.AjusteEstoqueProdutoFinal;
import controle.modelos.ProdutoFinal;

public class AjusteEstoqueProdutoFinalDao extends AbstractDao<AjusteEstoqueProdutoFinal>{

	public AjusteEstoqueProdutoFinalDao() {
		super(AjusteEstoqueProdutoFinal.class);
	}

	public BigDecimal apurarQuantidade(ProdutoFinal produtoFinal) {
		Query<AjusteEstoqueProdutoFinal> query = MorphiaHelper.getDatastore().createQuery(AjusteEstoqueProdutoFinal.class);
		query.field("produtoFinal").equal(produtoFinal);
		List<AjusteEstoqueProdutoFinal> ajustes = query.asList();
		BigDecimal adicoes = ajustes.stream().filter(a -> a.isEntrada()).map(a -> a.getQuantidade()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
		BigDecimal retiradas = ajustes.stream().filter(a -> !a.isEntrada()).map(a ->a.getQuantidade()).reduce(BigDecimal.ZERO, (a,b)->a.add(b));
		return adicoes.subtract(retiradas);
	}
}
