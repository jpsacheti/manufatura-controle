package controle.modelos;

import java.math.BigDecimal;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
@Entity("ajusteProduto")
public class AjusteEstoqueProdutoFinal {
	@Id
	private ObjectId codigo;
	private ProdutoFinal produtoFinal;
	private BigDecimal quantidade;
	public AjusteEstoqueProdutoFinal(ProdutoFinal produtoFinal, BigDecimal quantidade) {
		this.produtoFinal = Objects.requireNonNull(produtoFinal);
		this.quantidade = Objects.requireNonNull(quantidade);
	}
	public ObjectId getCodigo() {
		return codigo;
	}
	public void setCodigo(ObjectId codigo) {
		this.codigo = codigo;
	}
	public ProdutoFinal getProdutoFinal() {
		return produtoFinal;
	}
	public void setProdutoFinal(ProdutoFinal produtoFinal) {
		this.produtoFinal = produtoFinal;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public String toString() {
		return "AjusteEstoqueProdutoFinal [codigo=" + codigo + ", produtoFinal=" + produtoFinal + ", quantidade="
				+ quantidade + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((produtoFinal == null) ? 0 : produtoFinal.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		AjusteEstoqueProdutoFinal other = (AjusteEstoqueProdutoFinal) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (produtoFinal == null) {
			if (other.produtoFinal != null)
				return false;
		} else if (!produtoFinal.equals(other.produtoFinal))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
	
}
