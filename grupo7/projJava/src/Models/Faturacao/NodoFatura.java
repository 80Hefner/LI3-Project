package Models.Faturacao;

import Structs.*;
import Tests.GestVendasTest_Set;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe NodoFatura que implementa o seu Comparable
 */
public class NodoFatura implements Comparable<NodoFatura>, Serializable
{
    String productID;
    List<ProdutosFilial> produtosFilialN;
    List<ProdutosFilial> produtosFilialP;

    /**
     * Função que comparação Natural a ser usada pelo NodoFatura sempre
     * @param nodoFatura    NodoFatura ao qual queremos comparar o this
     * @return              Inteiro que vai servir de comparação
     */
    public int compareTo(NodoFatura nodoFatura)
    {
        return this.productID.compareTo(nodoFatura.getProductID());
    }

    /**
     * Construtor parametrizado do NodoFatura
     * @param nr_filiais    Numero de Filiais do Projeto
     */
    public NodoFatura(int nr_filiais)
    {
        this.productID = "";
        this.produtosFilialN = new ArrayList<>(nr_filiais);
        for (int i = 0; i < nr_filiais; i++) this.produtosFilialN.add(new ProdutosFilial());
        this.produtosFilialP = new ArrayList<>(nr_filiais);
        for (int i = 0; i < nr_filiais; i++) this.produtosFilialP.add(new ProdutosFilial());
    }

    /**
     * Construtor parametrizado do NodoFatura
     * @param productID     Código de Produto
     * @param nr_filiais    Numero de Filiais do Projeto
     */
    public NodoFatura(String productID, int nr_filiais)
    {
        this.productID = productID;
        this.produtosFilialN = new ArrayList<>(nr_filiais);
        for (int i = 0; i < nr_filiais; i++) this.produtosFilialN.add(new ProdutosFilial());
        this.produtosFilialP = new ArrayList<>(nr_filiais);
        for (int i = 0; i < nr_filiais; i++) this.produtosFilialP.add(new ProdutosFilial());
    }

    /**
     * Construtor parametrizado do NodoFatura
     * @param productID         Código de Produto
     * @param produtosFilialN   Lista de dados de compras normais
     * @param produtosFilialP   Lista de dados de compras em Desconto
     */
    public NodoFatura(String productID, List<ProdutosFilial> produtosFilialN, List<ProdutosFilial> produtosFilialP)
    {
        this.productID = productID;
        this.produtosFilialN = produtosFilialN.stream().map(ProdutosFilial::clone).collect(Collectors.toCollection(ArrayList::new));
        this.produtosFilialP = produtosFilialP.stream().map(ProdutosFilial::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Construtor parametrizado do NodoFatura, especificando o tipo concreto das List
     * @param productID     Código de Produto
     * @param nr_filiais    Numero de Filiais do Projeto
     * @param listInt       Tipo concreto das List a ser criadas
     */
    public NodoFatura(String productID, int nr_filiais, int listInt)
    {
        this.productID = "";

        switch (listInt) {
            case GestVendasTest_Set.VECTOR:
                this.produtosFilialN = new Vector<>(nr_filiais);
                this.produtosFilialP = new Vector<>(nr_filiais);
                break;
            case GestVendasTest_Set.ARRAY_LIST:
                this.produtosFilialN = new ArrayList<>(nr_filiais);
                this.produtosFilialP = new ArrayList<>(nr_filiais);
                break;
        }

        for (int i = 0; i < nr_filiais; i++) this.produtosFilialN.add(new ProdutosFilial());
        for (int i = 0; i < nr_filiais; i++) this.produtosFilialP.add(new ProdutosFilial());
    }

    /**
     * Construtor de cópia do NodoFatura
     * @param nf           NodoFatura a copiar
     */
    public NodoFatura(NodoFatura nf)
    {
        this.productID = nf.getProductID();
        this.produtosFilialN = nf.getProdutosFilialN();
        this.produtosFilialP = nf.getProdutosFilialP();
    }

    /**
     * Getter da String do Produto
     * @return           String com um código de Produto
     */
    public String getProductID()
    {
        return productID;
    }

    /**
     * Setter da String do Produto
     * @param productID     String com um código de Produto
     */
    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    /**
     * Getter da Lista de Dados das Compras Normais para este produto
     * @return           Lista de Dados das Compras Normais deste produto
     */
    public List<ProdutosFilial> getProdutosFilialN()
    {
        return produtosFilialN.stream().map(ProdutosFilial::clone).collect(Collectors.toList());
    }

    /**
     * Setter da Lista de Dados das Compras Normais para este produto
     * @param produtosFilialN       Lista de Dados das Compras Normais deste produto
     */
    public void setProdutosFilialN(List<ProdutosFilial> produtosFilialN)
    {
        this.produtosFilialN = produtosFilialN.stream().map(ProdutosFilial::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Getter da Lista de Dados das Compras em Desconto para este produto
     * @return           Lista de Dados das Compras em Desconto deste produto
     */
    public List<ProdutosFilial> getProdutosFilialP()
    {
        return produtosFilialP.stream().map(ProdutosFilial::clone).collect(Collectors.toList());
    }

    /**
     * Setter da Lista de Dados das Compras em Desconto para este produto
     * @param produtosFilialP       Lista de Dados das Compras em Desconto deste produto
     */
    public void setProdutosFilialP(List<ProdutosFilial> produtosFilialP)
    {
        this.produtosFilialP = produtosFilialP.stream().map(ProdutosFilial::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Função de equals do NodoFatura
     * @param o           Objeto ao qual queremos comparar o NodoFatura
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        NodoFatura nf = (NodoFatura) o;

        return this.productID.equals(nf.getProductID()) &&
                this.produtosFilialN.equals(nf.getProdutosFilialN()) &&
                this.produtosFilialP.equals(nf.getProdutosFilialP());
    }

    /**
     * Função que transforma o NodoFatura numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("PRODUCT_ID:").append(this.productID);
        for (int i = 0; i < this.produtosFilialN.size(); i++)
            sb.append("\nPRODUTOS_FILIAL_").append(i+1).append(": ").append(this.produtosFilialN.get(i).toString());
        for (int i = 0; i < this.produtosFilialP.size(); i++)
            sb.append("\nPRODUTOS_FILIAL_").append(i+1).append(": ").append(this.produtosFilialP.get(i).toString());

        return sb.toString();
    }

    /**
     * Função que dá clone ao NodoFatura
     * @return           Cópia do NodoFatura
     */
    public NodoFatura clone()
    {
        return new NodoFatura(this);
    }

    /**
     * Função que formula um HashCode de cada NodoFatura
     * @return Inteiro que é o código Hash do NodoFatura
     */
    public int hashCode()
    {
        int hash = 7;

        hash = 37 * hash + this.productID.hashCode();
        hash = 37 * hash + this.produtosFilialN.hashCode();
        hash = 37 * hash + this.produtosFilialP.hashCode();

        return hash;
    }

    /**
     * Funcao que insere uma venda no NodoFatura
     * @param preco         Preco unitario da compra
     * @param quantidade    Quantidade da compra
     * @param modo          Modo da compra (normal ou desconto)
     * @param mes           Mês da Compra
     * @param filial        Filial em que a compra foi feita
     */
    public void insereVenda(double preco, int quantidade, char modo, int mes, int filial)
    {
        if (modo == 'N')
            this.produtosFilialN.get(filial-1).insereVenda(preco, quantidade, mes);
        else
            this.produtosFilialP.get(filial-1).insereVenda(preco, quantidade, mes);
    }

    /**
     * Funçao que verifica se produto do NodoFatura em estudo já foi comprado
     * @return      Booleano que mostra se produto já foi comprado ou não
     */
    public boolean foiComprado()
    {
        return (this.produtosFilialN.stream().map(ProdutosFilial::temCompras).filter(b -> b).count() +
                this.produtosFilialP.stream().map(ProdutosFilial::temCompras).filter(b -> b).count()) != 0;
    }

    /***** QUERY 10 *****/
/*
    public void faturacaoTotalTodosProdutos (Map<String, List<List<Double>> > mapa) {
        int nr_filiais = this.produtosFilialN.size();
        List<List<Double>> total = new ArrayList<>(nr_filiais);
        for (int fil = 0; fil < nr_filiais; fil++) {
            List<Double> meses = new ArrayList<>(Collections.nCopies(12, 0.0));

            total.add(fil, meses);
        }
        for (int fil = 0; fil < nr_filiais; fil++) {
            this.produtosFilialN.get(fil).faturacaoTotalTodosProdutos(total.get(fil));
            this.produtosFilialP.get(fil).faturacaoTotalTodosProdutos(total.get(fil));
        }
        mapa.putIfAbsent(this.getProductID(), total);
    }
*/
    /**
     * Função auxiliar da Query10
     * @param meses     Uma Lista de uma Lista de uma Lista de Pares String Double a preencher
     */
    public void faturacaoTotalTodosProdutos(List<List<List<ParStringDouble>>> meses)
    {
        for (int fil = 0; fil < this.produtosFilialN.size(); fil++) {
            ProdutosFilial auxN = this.produtosFilialN.get(fil);
            ProdutosFilial auxP = this.produtosFilialP.get(fil);

            for (int mes = 0; mes < 12; mes++) {
                double total_faturado = auxN.getTotalFaturado(mes+1,mes+1) + auxP.getTotalFaturado(mes+1,mes+1);
                meses.get(mes).get(fil).add(new ParStringDouble(this.productID, total_faturado));
            }
        }
    }

    /***** QUERY 1E *****/
    /**
     * Funçao auxiliar da Query2.1 Estatística
     * @param lista Lista de inteiros que iremos preencher
     */
    public void getTotalComprasPorMes(List<Integer> lista)
    {
        this.produtosFilialN.forEach(prod -> prod.preencheListaComprasMes(lista));
        this.produtosFilialP.forEach(prod -> prod.preencheListaComprasMes(lista));
    }

    /***** QUERY 2E *****/
    /**
     * Função auxiliar da Query2.2 Estatística
     * @param lista     Uma lista de listas de doubles a preencher
     */
    public void getTotalFaturadoPorMes(List<List<Double>> lista)
    {
        for (int fil = 0; fil<this.produtosFilialN.size(); fil++) {
            this.produtosFilialN.get(fil).preencheListaFaturadoMes(lista.get(fil));
            this.produtosFilialP.get(fil).preencheListaFaturadoMes(lista.get(fil));
        }
    }

    /***** QUERY 6 *****/
    /**
     * Função auxiliar da Query6
     * @param mapa  Mapa com keys que saõ os códigos de Produto e os values Pares String Integer que vamos preencher
     */
    public void getProdsMaisCompradosTotal (Map<String, ParStringInteger> mapa)
    {
        String produto = this.getProductID();
        if (!mapa.containsKey(produto)) {
            mapa.put(produto,new ParStringInteger(produto));
        }
        this.produtosFilialN.forEach(dados -> dados.getProdsMaisCompradosTotal(mapa, produto));
        this.produtosFilialP.forEach(dados -> dados.getProdsMaisCompradosTotal(mapa, produto));
    }
}
