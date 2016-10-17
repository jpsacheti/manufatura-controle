package controle.modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
@Entity("ajusteProduto")
public class AjusteEstoqueProdutoFinal {
	@Id
	private ObjectId codigo;
	@Reference
	private ProdutoFinal produtoFinal;
	private BigDecimal quantidade;
	private boolean entrada;
	private LocalDateTime dataHora;
	
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
	public boolean isEntrada() {
		return entrada;
	}
	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	@Override
	public String toString() {
		return "AjusteEstoqueProdutoFinal [codigo=" + codigo + ", produtoFinal=" + produtoFinal + ", quantidade=" + quantidade + ", entrada=" + entrada + ", dataHora=" + dataHora + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + (entrada ? 1231 : 1237);
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
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (entrada != other.entrada)
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
