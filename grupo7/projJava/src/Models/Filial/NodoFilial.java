package Models.Filial;

import Structs.*;
import Tests.GestVendasTest_Set;

import java.io.Serializable;
import java.util.*;

/**
 * Classe NodoFilial que implementa o seu Comparable
 */
public class NodoFilial implements Comparable<NodoFilial>, Serializable
{

    private String clientID;
    private Map<String, ProdutosCliente> produtosClienteN;
    private Map<String, ProdutosCliente> produtosClienteP;

    /**
     * Construtor por omissão do NodoFilial
     */
    public NodoFilial()
    {
        this.clientID = "";
        this.produtosClienteN = new HashMap<>();
        this.produtosClienteP = new HashMap<>();
    }

    /**
     * Construtor parametrizado do NodoFilial
     * @param clientID     Código de Cliente
     */
    public NodoFilial(String clientID)
    {
        this.clientID = clientID;
        this.produtosClienteN = new HashMap<>(); //Fazer aquilo de passar já o parametro adicionado em baixo aqui?
        this.produtosClienteP = new HashMap<>();
    }

    /**
     * Construtor parametrizado do NodoFilial
     * @param clientID           Código de Cliente
     * @param produtosClienteN   Map com os dados de compras normais
     * @param produtosClienteP   Map com os dados de compras em Desconto
     */
    public NodoFilial(String clientID, Map<String, ProdutosCliente> produtosClienteN, Map<String, ProdutosCliente> produtosClienteP)
    {
        this.clientID = clientID;
        setProdutosClienteN(produtosClienteN);
        setProdutosClienteN(produtosClienteP);
    }

    /**
     * Construtor de cópia do NodoFilial
     * @param nf           NodoFilial a copiar
     */
    public NodoFilial(NodoFilial nf)
    {
        this.clientID = nf.getClientID();
        this.produtosClienteN = nf.getProdutosClienteN();
        this.produtosClienteP = nf.getProdutosClienteP();
    }

    /**
     * Construtor parametrizado do NodoFilial
     * @param clientID      Código de Cliente
     * @param mapInt        Identificador dos Maps a ser criados
     */
    public NodoFilial(String clientID, int mapInt)
    {
        this.clientID = clientID;

        switch (mapInt) {
            case GestVendasTest_Set.HASH_MAP:
                this.produtosClienteN = new HashMap<>();
                this.produtosClienteP = new HashMap<>();
                break;
            case GestVendasTest_Set.TREE_MAP:
                this.produtosClienteN = new TreeMap<>();
                this.produtosClienteP = new TreeMap<>();
                break;
        }
    }

    /**
     * Função que comparação Natural a ser usada pelo NodoFilial sempre
     * @param nodoFilial    NodoFilial ao qual queremos comparar o this
     * @return              Inteiro que vai servir de comparação
     */
    public int compareTo(NodoFilial nodoFilial)
    {
        return this.clientID.compareTo(nodoFilial.getClientID());
    }

    /**
     * Função que dá clone ao NodoFilial
     * @return           Cópia do NodoFilial
     */
    public NodoFilial clone()
    {
        return new NodoFilial(this);
    }

    /**
     * Getter da String do Cliente
     * @return       String com um código de Clietne
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Setter da String do Cliente
     * @param clientID     String com um código de Cliente
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Getter do Map com os Dados das Compras Normais para este Cliente
     * @return           Map com os Dados das Compras Normais deste Cliente
     */
    public Map<String, ProdutosCliente> getProdutosClienteN()
    {
        HashMap<String,ProdutosCliente> novo = new HashMap<>();
        produtosClienteN.forEach((key,value) -> novo.put(key, value.clone()));
        return novo;
    }

    /**
     * Setter do Map com os Dados das Compras Normais para este Cliente
     * @param produtosClienteN       Map com os Dados das Compras Normais deste cliente
     */
    public void setProdutosClienteN(Map<String, ProdutosCliente> produtosClienteN)
    {
        this.produtosClienteN = new HashMap<>();
        produtosClienteN.forEach((key,value) -> this.produtosClienteN.put(key,value.clone()));
    }

    /**
     * Getter do Map com os Dados das Compras com Desconto para este Cliente
     * @return           Map com os Dados das Compras com Desconto deste Cliente
     */
    public Map<String, ProdutosCliente> getProdutosClienteP()
    {
        HashMap<String,ProdutosCliente> novo = new HashMap<>();
        produtosClienteP.forEach((key,value) -> novo.put(key, value.clone()));
        return novo;
    }

    /**
     * Setter do Map com os Dados das Compras com Desconto para este Cliente
     * @param produtosClienteP       Map com os Dados das Compras com Desconto deste cliente
     */
    public void setProdutosClienteP(Map<String, ProdutosCliente> produtosClienteP)
    {
        this.produtosClienteP = new HashMap<>();
        produtosClienteP.forEach((key,value) -> this.produtosClienteP.put(key,value.clone()));
    }

    /**
     * Função de equals do NodoFilial
     * @param o           Objeto ao qual queremos comparar o NodoFilial
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        NodoFilial nf = (NodoFilial) o;

        return this.clientID.equals(nf.getClientID()) &&
                this.produtosClienteN.equals(nf.getProdutosClienteN()) &&
                this.produtosClienteP.equals(nf.getProdutosClienteP());
    }

    /**
     * Função que transforma o NodoFilial numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("CLIENT_ID:").append(this.clientID);
        sb.append("\nPRODUTOS COMPRADOS POR CLIENTE EM MODO NORMAL:   ").append(this.produtosClienteN.toString());
        sb.append("\nPRODUTOS COMPRADOS POR CLIENTE EM MODO DESCONTO: ").append(this.produtosClienteP.toString());

        return sb.toString();
    }

    /**
     * Função que formula um HashCode de cada NodoFilial
     * @return Inteiro que é o código Hash do NodoFilial
     */
    public int hashCode()
    {
        int hash = 7;

        hash = 37 * hash + this.clientID.hashCode();
        hash = 37 * hash + this.produtosClienteN.hashCode();
        hash = 37 * hash + this.produtosClienteP.hashCode();

        return hash;
    }

    /**
     * Funcao que insere uma venda no NodoFilial
     * @param productID     Código de Produto da Compra
     * @param preco         Preco unitario da compra
     * @param quantidade    Quantidade da compra
     * @param modo          Modo da compra (normal ou desconto)
     * @param mes           Mês da Compra
     */
    public void insereVendaFilial(String productID, double preco, int quantidade, char modo, int mes)
    {
        if (modo == 'N') {
            if (this.produtosClienteN.containsKey(productID)) {
                this.produtosClienteN.get(productID).insereVenda(preco, quantidade, mes);
            }
            else {
                ProdutosCliente novo = new ProdutosCliente();
                novo.setProductID(productID);
                novo.insereVenda(preco, quantidade, mes);
                this.produtosClienteN.put(productID, novo);
            }
        }

        else {
            if (this.produtosClienteP.containsKey(productID)) {
                this.produtosClienteP.get(productID).insereVenda(preco, quantidade, mes);
            }
            else {
                ProdutosCliente novo = new ProdutosCliente();
                novo.setProductID(productID);
                novo.insereVenda(preco, quantidade, mes);
                this.produtosClienteP.put(productID, novo);
            }
        }

    }

    /*** QUERIES ESTATISTICA ***/
    /**
     * Funcao auxiliar da Query1 Estatística
     * @param listaClientesCompraram    Set de Strings que vamos preencher com String de códigos de clientes da filial em estudo que compraram
     */
    public void getClientesCompraram(Set<String> listaClientesCompraram)
    {
        if ((this.produtosClienteN.size() > 0 || this.produtosClienteP.size() > 0)) {
            listaClientesCompraram.add(this.getClientID());
        }
    }

    /***** QUERY 1E *****/
    /**
     * Funcao auxiliar da Query2.3 Estatística
     * @param listClientesDistintos    Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getTotalCompradoresPorMes (List<Set<String>> listClientesDistintos)
    {
        for (int mes = 0; mes < 12; mes++) {
            int finalMes = mes;
            if (this.produtosClienteN.values().stream().anyMatch(rel -> rel.produtoFoiCompradoMes(finalMes+1))) {
                listClientesDistintos.get(mes).add(this.getClientID());
            } else if (this.produtosClienteP.values().stream().anyMatch(rel -> rel.produtoFoiCompradoMes(finalMes+1))) {
                listClientesDistintos.get(mes).add(this.getClientID());
            }
        }
    }

    /***** QUERY 2 *****/
    /**
     * Funcao auxiliar da Query2
     * @param listClientesDistintos     Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     * @param mes                       Mês que pretendemos estudar
     */
    public int numeroVendasEClientesMes (Set<String> listClientesDistintos, int mes)
    {
        int nrVendasTotais = this.getVendasTotaisMes(mes);
        if (nrVendasTotais > 0) {
            listClientesDistintos.add(this.getClientID());
        }
        return  nrVendasTotais;
    }

    /**
     * Funcao auxiliar da Query2
     * @param mes       Mês que pretendemos estudar
     * @return          Total faturado pelo cliente em estudo em ambos os modos de compra no dado mês
     */
    public int getVendasTotaisMes(int mes) {
        int nrVendas = 0;
        nrVendas += this.produtosClienteN.values().stream().mapToInt(rel -> rel.getTotalVendasMes(mes)).sum();
        nrVendas += this.produtosClienteP.values().stream().mapToInt(rel -> rel.getTotalVendasMes(mes)).sum();
        return nrVendas;
    }

    /***** QUERY 3 *****/
    /**
     * Funcao auxiliar da Query3
     * @param lista                     Lista de Lista de doubles onde vamos guardar os resultados pretendidos pela querie
     * @param listaProdsNaoRepetidos    Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getComprasProdsGastoMes(List<List<Double>> lista, List<Set<String>> listaProdsNaoRepetidos)
    {
        for(ProdutosCliente rel : this.produtosClienteN.values()) {
            rel.getComprasProdsGastoMes(lista, listaProdsNaoRepetidos);
        }
        for(ProdutosCliente rel : this.produtosClienteP.values()) {
            rel.getComprasProdsGastoMes(lista, listaProdsNaoRepetidos);
        }
    }

    /***** QUERY 4 *****/
    /**
     * Funcao auxiliar da Query4
     * @param lista                     Lista de Lista de doubles onde vamos guardar os resultados pretendidos pela querie
     * @param productID                 Código de Produto que pretendemos estudar
     * @param listaProdsNaoRepetidos    Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getComprasClientesGastoMes(List<List<Double>> lista, List<Set<String>> listaProdsNaoRepetidos, String productID)
    {
        if (this.produtosClienteN.containsKey(productID)) {
            this.produtosClienteN.get(productID).getComprasClientesGastoMes(lista, listaProdsNaoRepetidos, this.getClientID());
        }
        if (this.produtosClienteP.containsKey(productID)) {
            this.produtosClienteP.get(productID).getComprasClientesGastoMes(lista, listaProdsNaoRepetidos, this.getClientID());
        }
    }

    /***** QUERY 5 *****/
    /**
     * Funcao auxiliar da Query5
     * @param mapa                     Lista de Pares String Integer onde vmaos colocando os resultados pretendidos pela querie
     */
    public void getProdsClienteMaisComprados(List<ParStringInteger> mapa)
    {
        this.produtosClienteN.values().forEach(rel -> rel.getProdsClienteMaisComprados(mapa));
        this.produtosClienteP.values().forEach(rel -> rel.getProdsClienteMaisComprados(mapa));
    }

    /***** QUERY 6 *****/
    /**
     * Funcao auxiliar da Query6
     * @param lista                    Lista de Pares String Integer onde vmaos colocando os resultados pretendidos pela querie
     * @param listaProdsNaoRepetidos     Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getProdsMaisCompradosTotal(List<ParStringInteger> lista, List<Set<String>> listaProdsNaoRepetidos)
    {
        int index = 0;
        for (ParStringInteger par : lista) {
            if (this.produtosClienteN.containsKey(par.getStringKey()) || this.produtosClienteP.containsKey(par.getStringKey()) ) {
                Set<String> compara = listaProdsNaoRepetidos.get(index);
                if (!compara.contains(this.getClientID())) {
                    compara.add(this.getClientID());
                }
            }
            index++;
        }
    }

    /***** QUERY 7 *****/
    //public void getTresMaioresCompradoresPorFilial(Map<ParStringDouble, String> mapa)
    /**
     * Funcao auxiliar da Query7
     * @param mapa                    Set de Pares String Double que vamos preencher á medida que vamos percorrendo a filial com os resultados pretendidos
     */
    public void getTresMaioresCompradoresPorFilial(Set<ParStringDouble> mapa)
    {
        double valorTotalFaturado = this.produtosClienteN.values().stream().mapToDouble(rel -> rel.getTotalFaturadoAno()).sum() +
                                    this.produtosClienteP.values().stream().mapToDouble(rel -> rel.getTotalFaturadoAno()).sum() ;

        //mapa.put(new ParStringDouble(this.getClientID(), valorTotalFaturado), this.getClientID());
        mapa.add(new ParStringDouble(this.getClientID(), valorTotalFaturado));
    }

    /***** QUERY 8 *****/
    /**
     * Funcao auxiliar da Query8
     * @param mapaClientesESeusProdutos     Map onde as keys sao os códigos de Clietnes e os values são sets onde vamos introduzindo codigos de Produtos que foram comprados pelo clietne da key
     */
    public void getClientesCompraramMaisProdutos(Map<String, Set<String>> mapaClientesESeusProdutos)
    {
        String cliente = this.getClientID();
        mapaClientesESeusProdutos.putIfAbsent(cliente, new TreeSet<String>());
        this.produtosClienteN.values().forEach(rel -> rel.getClientesCompraramMaisProdutos(mapaClientesESeusProdutos.get(cliente) ));
        this.produtosClienteP.values().forEach(rel -> rel.getClientesCompraramMaisProdutos(mapaClientesESeusProdutos.get(cliente) ));
    }

    /***** QUERY 9 *****/
    /**
     * Funcao auxiliar da Query9
     * @param produtoID     String com o Código do Produto que pretendemos estudar
     * @param mapa          Map onde as keys sao os códigos de Clietnes e os values são sets Pares String Integer
     */
    public void getClientesMaisCompraramProduto(String produtoID,  Map<String, ParStringInteger> mapa)
    {
        int nrUnidadesCompradas = 0;
        if (this.produtosClienteN.containsKey(produtoID) ){
            nrUnidadesCompradas += this.produtosClienteN.get(produtoID).getTotalUnidadesAno();
        }
        if (this.produtosClienteP.containsKey(produtoID) ){
            nrUnidadesCompradas += this.produtosClienteP.get(produtoID).getTotalUnidadesAno();
        }
        if (nrUnidadesCompradas > 0) {
            if (!mapa.containsKey(this.getClientID())) {
                mapa.put(this.getClientID(), new ParStringInteger(this.getClientID()));
            }
            mapa.get(this.getClientID()).setInteiroSomaComAntigo(nrUnidadesCompradas);
        }
    }

    /**
     * Funcao auxiliar da Query9
     * @param produtoID     String com o Código do Produto que pretendemos estudar
     * @param par           Par String Double onde vamos incrementar os resultados que obtemos sobre o dado Produto
     */
    public void getFaturadoPorUmDadoCliente (ParStringDouble par, String produtoID)
    {
        double valorTotalFaturado = 0;
        if (this.produtosClienteN.containsKey(produtoID)) {
            valorTotalFaturado += this.produtosClienteN.get(produtoID).getTotalFaturadoAno();
        }
        if (this.produtosClienteP.containsKey(produtoID)) {
            valorTotalFaturado += this.produtosClienteP.get(produtoID).getTotalFaturadoAno();
        }
        par.setInteiroSomaComAntigo(valorTotalFaturado);
    }
}
