package Models.CatProdutos;

import java.io.Serializable;

/**
 * Interface do Produto
 */
public interface IProduto extends Comparable<IProduto>, Serializable
{
    /**
     * Getter da String de um Produto
     * @return           String com um código de Produto
     */
    public String getProductID();

    /**
     * Função que dá clone ao IProduto
     * @return           Cópia do IProduto
     */
    public IProduto clone();
}
