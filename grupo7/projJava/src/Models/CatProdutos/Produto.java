package Models.CatProdutos;

import Models.CatClientes.ICliente;

/**
 * Classe Produto que implementa a interface IProduto
 */
public class Produto implements IProduto
{
    /**
     * String que representa um Produto (o seu código)
     */
    private String productID;

    /**
     * Construtor por omissão do Produto
     */
    public Produto()
    {
        this.productID = "";
    }

    /**
     * Construtor parametrizado do Produto
     * @param productID           String com o código de Produto
     */
    public Produto(String productID)
    {
        this.productID = productID;
    }

    /**
     * Construtor de cópia do Produto
     * @param produto           Produto a copiar
     */
    public Produto(IProduto produto)
    {
        this.productID = produto.getProductID();
    }

    /**
     * Função que transforma o Produto numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.productID);

        return sb.toString();
    }

    /**
     * Função de equals do Produto
     * @param o           Objeto ao qual queremos comparar o Produto
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Produto p = (Produto) o;

        return this.productID.equals(p.getProductID());
    }

    /**
     * Função que formula um HashCode de cada Produto
     * @return Inteiro que é o código Hash do Produto
     */
    public int hashCode()
    {
        int hash = 7;

        hash = 37 * hash + this.productID.hashCode();

        return hash;
    }

    /**
     * Getter da String do Produto
     * @return           String com um código de Produto
     */
    @Override
    public String getProductID()
    {
        return this.productID;
    }

    /**
     * Função que dá clone ao Produto
     * @return           Cópia do Produto
     */
    @Override
    public IProduto clone()
    {
        return new Produto(this);
    }

    /**
     * Método que compara duas instâncias da classe Produto
     * @param produto   Produto a ser comparado
     * @return          Resultado da comparação de 'this' com produto
     */
    @Override
    public int compareTo(IProduto produto)
    {
        return this.productID.compareTo(produto.getProductID());
    }

}
