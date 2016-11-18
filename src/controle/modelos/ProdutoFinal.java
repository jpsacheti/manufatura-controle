package controle.modelos;

import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import controle.dao.AjusteEstoqueProdutoFinalDao;
@Entity("produtoFinal")
public class ProdutoFinal {
	@Id
	private ObjectId codigo;
	private String nome;
	@Property("preco")
	private BigDecimal preco;
	private Unidade unidade;

	public ObjectId getCodigo() {
		return codigo;
	}

	public void setCodigo(ObjectId codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String toString() {
		return nome;
	}
	
	public BigDecimal getQuantidade(){
		return new AjusteEstoqueProdutoFinalDao().apurarQuantidade(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoFinal other = (ProdutoFinal) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
