package Models;

import Models.CatClientes.*;
import Models.CatProdutos.*;

/**
 * Classe Venda
 */
public class Venda
{
    private String productID;
    private double preco;
    private int quantidade;
    private char modo;
    private String clientID;
    private int mes;
    private int filial;

    /**
     * Construtor por omissão da Venda
     */
    public Venda()
    {
        this.productID = "";
        this.preco = 0.0;
        this.quantidade = 0;
        this.modo = 0;
        this.clientID = "";
        this.mes = 0;
        this.filial = 0;
    }

    /**
     * Construtor parametrizado da Venda
     * @param productID     String com o código de Produto
     * @param preco         Preco unitário do Produto
     * @param quantidade    Quantidade comprada
     * @param modo          Modo de compra
     * @param clientID      String com o código de Cliente
     * @param mes           Mes da Venda
     * @param filial        Filial em que venda foi feita
     */
    public Venda(String productID, double preco, int quantidade, char modo, String clientID, int mes, int filial)
    {
        this.productID = productID;
        this.preco = preco;
        this.quantidade = quantidade;
        this.modo = modo;
        this.clientID = clientID;
        this.mes = mes;
        this.filial = filial;
    }

    /**
     * Construtor de cópia do Venda
     * @param venda      Venda a copiar
     */
    public Venda(Venda venda)
    {
        this.productID = venda.getProductID();
        this.preco = venda.getPreco();
        this.quantidade = venda.getQuantidade();
        this.modo = venda.getModo();
        this.clientID = venda.getClientID();
        this.mes = venda.getMes();
        this.filial = venda.getFilial();
    }

    /**
     * Construtor parametrizado da Venda
     * @param tokens    Um array de Strings onde cada posição contem um tipo de dados da Venda
     */
    public Venda(String[] tokens)
    {
        this.productID = tokens[0];
        this.preco = Double.parseDouble(tokens[1]);
        this.quantidade = Integer.parseInt(tokens[2]);
        this.modo = tokens[3].charAt(0);
        this.clientID = tokens[4];
        this.mes = Integer.parseInt(tokens[5]);
        this.filial = Integer.parseInt(tokens[6]);
    }

    /**
     * Getter da String com o código de Produto
     * @return           String com o código de Produto
     */
    public String getProductID()
    {
        return productID;
    }

    /**
     * Setter da String com o código de Produto
     * @param productID     String com o código de Produto
     */
    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    /**
     * Getter do Preco unitário do Produto
     * @return           Double com o Preco unitário do Produto
     */
    public double getPreco()
    {
        return preco;
    }

    /**
     * Setter do Preco unitário do Produto
     * @param preco     Double com o Preco unitário do Produto
     */
    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    /**
     * Getter da quantidade comprada da Venda
     * @return           Integer com a quantidade comprada da Venda
     */
    public int getQuantidade()
    {
        return quantidade;
    }

    /**
     * Setter da quantidade comprada da Venda
     * @param quantidade     Integer com a quantidade comprada da Venda
     */
    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    /**
     * Getter do Modo de compra
     * @return      Char que determina o Modo de compra
     */
    public char getModo()
    {
        return modo;
    }

    /**
     * Setter do Modo de compra
     * @param modo     Char que determina o Modo de compra
     */
    public void setModo(char modo)
    {
        this.modo = modo;
    }

    /**
     * Getter da String com o código de Produto
     * @return           String com o código de Produto
     */
    public String getClientID()
    {
        return clientID;
    }

    /**
     * Setter da String com o código de Cliente
     * @param clientID     String com o código de Cliente
     */
    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }

    /**
     * Getter do Mes que Venda foi efetuada
     * @return           Integer com o mês da venda
     */
    public int getMes()
    {
        return mes;
    }

    /**
     * Setter do Mes que Venda foi efetuada
     * @param mes     Integer com o mês da venda
     */
    public void setMes(int mes)
    {
        this.mes = mes;
    }

    /**
     * Getter da filial em que venda foi feita
     * @return           Integer que determina em que filial venda foi feita
     */
    public int getFilial()
    {
        return filial;
    }

    /**
     * Setter da filial em que venda foi feita
     * @param filial     Integer que determina em que filial venda foi feita
     */
    public void setFilial(int filial)
    {
        this.filial = filial;
    }

    /**
     * Função de equals da Venda
     * @param o           Objeto ao qual queremos comparar a Venda
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Venda v = (Venda) o;

        return this.productID.equals(v.getProductID()) &&
                this.preco == v.getPreco() &&
                this.quantidade == v.getQuantidade() &&
                this.modo == v.getModo() &&
                this.clientID.equals(v.getClientID()) &&
                this.mes == v.getMes() &&
                this.filial == v.getFilial();
    }

    /**
     * Função que transforma a Venda numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Produto: ").append(this.productID);
        sb.append("\nPreço: ").append(this.preco);
        sb.append("\nQuantidade: ").append(this.quantidade);
        sb.append("\nModo: ").append(this.modo);
        sb.append("\nCliente: ").append(this.clientID);
        sb.append("\nMês: ").append(this.mes);
        sb.append("\nFilial: ").append(this.filial);

        return sb.toString();
    }

    /**
     * Função que dá clone á Venda
     * @return           Cópia da Venda
     */
    public Venda clone()
    {
        return new Venda(this);
    }

    /**
     * Funçao que verifica se uma venda é valida ou não
     * @param gestVendas    GestVendas onde podemos ir buscar todos os recursos para verificação da Venda
     * @return              Booleano que determina se venda é valida ou não
     */
    public boolean isValid(IGestVendas gestVendas)
    {
        return (gestVendas.searchProduto(new Produto(productID)) &&
                preco >= 0 && preco < 1000 &&
                quantidade >= 1 && quantidade <= 200 &&
                (modo == 'N' || modo == 'P') &&
                gestVendas.searchCliente(new Cliente(clientID)) &&
                mes >= 1 && mes <= 12 &&
                filial >= 1 && filial <= gestVendas.getNr_filiais());
    }
}
