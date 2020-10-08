package Models.CatProdutos;

import java.io.Serializable;
import java.util.Set;

/**
 * Interface do Catálogo de Produtos
 */
public interface ICatProdutos extends Serializable
{
    /**
     * Função que dá um get a um Set de Produtos
     * @return  Set de Interfaces IProduto
     */
    public Set<IProduto> getCatProdutos();

    /**
     * Função que insere um IProduto
     * @param produto  Interface IProduto que pretendemos inserir
     */
    public void insere(IProduto produto);

    /**
     * Função que verifica se um IProduto se encontra num catálogo
     * @param produto           Interface IProduto a verificar se existe no Catálogo
     * @return                  Booleano que mostra se Produto pertence ao catálogo ou não
     */
    public boolean procura(IProduto produto);

    /**
     * Função que dá clone ao catálogo
     * @return           Cópia do ICatProdutos
     */
    public ICatProdutos clone();

}
