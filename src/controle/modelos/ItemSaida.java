package controle.modelos;

import java.math.BigDecimal;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("itemSaida")
public class ItemSaida {
	@Id
	private ObjectId codigo;
	private ProdutoFinal produtoFinal;
	@Property("quantidade")
	private BigDecimal quantidade;
	@Reference(lazy=false)
	private Manufatura manufatura; 

	public ItemSaida() {
	}

	public ItemSaida(ProdutoFinal produto, BigDecimal quantidade) {
		this.quantidade = Objects.requireNonNull(quantidade);
		this.produtoFinal = Objects.requireNonNull(produto);
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
		this.produtoFinal = Objects.requireNonNull(produtoFinal);
	}

	public BigDecimal getQuantidade() {
		return Objects.requireNonNull(quantidade);
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
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
		ItemSaida other = (ItemSaida) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Manufatura getManufatura() {
		return manufatura;
	}

	public void setManufatura(Manufatura manufatura) {
		this.manufatura = manufatura;
	}

}
