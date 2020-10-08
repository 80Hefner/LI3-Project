package Models.CatProdutos;

import Tests.GestVendasTest_Set;

import java.util.*;

/**
 * Classe Catalogo dos Produtos que implementa a interface ICatProdutos
 */
public class CatProdutos implements ICatProdutos
{
    /**
     * Set de interfaces IProduto
     */
    private Set<IProduto> catalogo;

    /**
     * Construtor por omissão do Catálogo de Produtos
     */
    public CatProdutos()
    {
        this.catalogo = new HashSet<>();
    }

    /**
     * Construtor parametrizado do Catálogo de Produtos
     * @param catalogo           Set de interfaces IProduto
     */
    public CatProdutos(Set<IProduto> catalogo)
    {
        this.catalogo = new HashSet<>(catalogo);
    }

    /**
     * Construtor de cópia do Catálogo de Produtos
     * @param catalogo           Catálogo de Produtos a copiar
     */
    public CatProdutos(ICatProdutos catalogo)
    {
        this.catalogo = catalogo.getCatProdutos();
    }

    /**
     * Construtor que apenas define a estrutura do Catálogo de Produtos
     */
    public CatProdutos(int setInt)
    {
        switch (setInt) {
            case GestVendasTest_Set.HASH_SET:
                this.catalogo = new HashSet<>();
                break;
            case GestVendasTest_Set.LINKED_HASH_SET:
                this.catalogo = new LinkedHashSet<>();
                break;
            case GestVendasTest_Set.TREE_SET:
                this.catalogo = new TreeSet<>();
                break;
        }
    }

    /**
     * Getter do Set do catálogo de Produtos
     * @return           Set de interfaces IProduto
     */
    public Set<IProduto> getCatProdutos()
    {
        return new HashSet<>(this.catalogo);
    }

    /**
     * Setter do Set do catálogo de Produtos
     * @param catalogo           Set de interfaces IProduto
     */
    public void setCatProdutos(Set<IProduto> catalogo)
    {
        this.catalogo = new HashSet<>(catalogo);
    }

    /**
     * Função de equals do catálogo de Produtos
     * @param o           Objeto ao qual queremos comparar o catálogo
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        CatProdutos c = (CatProdutos) o;

        return this.catalogo.equals(c.getCatProdutos());
    }

    /**
     * Função que transforma o catálogo numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        this.catalogo.forEach(e -> sb.append(e).append(":"));

        return sb.toString();
    }

    /**
     * Função que dá clone ao catálogo
     * @return           Cópia do Catálogo de Produtos
     */
    public CatProdutos clone()
    {
        return new CatProdutos(this);
    }

    /**
     * Função que insere um IProduto no catálogo
     * @param produto           Interface IProduto a adicionar ao Catálogo
     */
    public void insere(IProduto produto)
    {
        this.catalogo.add(produto.clone());
    }

    /**
     * Função que verifica se um IProduto se encontra no catálogo
     * @param produto           Interface IProduto a verificar se existe no Catálogo
     * @return                  Booleano que mostra se Produto pertence ao catálogo ou não
     */
    public boolean procura(IProduto produto)
    {
        return this.catalogo.contains(produto);
    }
}
