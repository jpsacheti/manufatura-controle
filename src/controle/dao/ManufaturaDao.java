package controle.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.Query;

import controle.modelos.ItemEntrada;
import controle.modelos.Manufatura;
import controle.modelos.MateriaPrima;
import controle.modelos.ProdutoFinal;

public class ManufaturaDao extends AbstractDao<Manufatura> {
	public ManufaturaDao(){
		super(Manufatura.class);
	}
	
	public List<Manufatura> pesquisarPorData(LocalDate dataBase, LocalDate dataTeto){
		Query<Manufatura> query = MorphiaHelper.getDatastore().createQuery(Manufatura.class);
		query.field("date").greaterThan(dataBase).field("date").lessThan(dataTeto);
		return query.asList();
	}

	public BigDecimal getTotalUtilizado(MateriaPrima materiaPrima) {	
		Iterator<BigDecimal> agregacao = MorphiaHelper.getDatastore()
				.createAggregation(Manufatura.class)
				.unwind("listEntrada")
				.match(MorphiaHelper.getDatastore().createQuery(ItemEntrada.class).field("materiaPrima").equal(materiaPrima))
				.project(Projection.projection("quantidade")).out(BigDecimal.class);
		BigDecimal soma = BigDecimal.ZERO;
		while (agregacao.hasNext()) {
			BigDecimal bigDecimal = agregacao.next();
			soma = soma.add(bigDecimal);
		}
		return soma;
	}

	public BigDecimal getTotalUtilizado(ProdutoFinal produtoFinal) {
		Iterator<BigDecimal> agregacao = MorphiaHelper.getDatastore()
				.createAggregation(Manufatura.class)
				.unwind("listSaida")
				.match(MorphiaHelper.getDatastore().createQuery(ItemEntrada.class).field("produtoFinal").equal(produtoFinal))
				.project(Projection.projection("quantidade")).out(BigDecimal.class);
		BigDecimal soma = BigDecimal.ZERO;
		while (agregacao.hasNext()) {
			BigDecimal bigDecimal = agregacao.next();
			soma = soma.add(bigDecimal);
		}
		return soma;
	}
}
