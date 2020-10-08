package Models.Filial;

import Models.CatClientes.*;
import Models.Venda;
import Structs.*;
import Tests.GestVendasTest_Set;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe Filail que implementa a interface IFilial
 */
public class Filial implements IFilial{

    /**
     * Map com keys que saõ os codigos de Produtos e os values os seus dados
     */
    private Map<String, NodoFilial> mapFilial;

    /**
     * Construtor por omissão da Filial
     */
    public Filial ()
    {
        this.mapFilial = new HashMap<>();
    }

    /**
     * Construtor parametrizado da Filial
     * @param catClientes           Catalogo com todos os Clientes a introduzir
     */
    public Filial (ICatClientes catClientes)
    {
        this.mapFilial = catClientes.getCatClientes().stream().collect(Collectors.toMap(ICliente::getClientID, s -> new NodoFilial(s.getClientID())));
    }

    /**
     * Construtor parametrizado da Filial
     * @param mapFilial       Mapa com os dados da Filial
     */
    public Filial (Map<String,NodoFilial> mapFilial)
    {
        setFilial(mapFilial);
    }

    /**
     * Construtor de cópia da Filial
     * @param f           Filial a copiar
     */
    public Filial(Filial f)
    {
        this.mapFilial = f.getFilial();
    }


    /**
     * Função que dá clone à Filial
     * @return           Cópia da Filial
     */
    public Filial clone()
    {
        return new Filial(this);
    }


    /**
     * Getter do Map da Filial
     * @return           Map com os dados da Filial
     */
    public Map<String,NodoFilial> getFilial()
    {
        HashMap<String,NodoFilial> novo = new HashMap<>();
        mapFilial.forEach((key,value) -> novo.put(key, value.clone()));
        return novo;
    }

    /**
     * Setter do Map da Filial
     * @param mapFilial           Map que queremos pôr na Filial
     */
    public void setFilial(Map<String,NodoFilial> mapFilial)
    {
        this.mapFilial = new HashMap<>();
        mapFilial.forEach((key,value) -> this.mapFilial.put(key,value.clone()));
    }

    /**
     * Função de equals da Filial
     * @param o           Objeto ao qual queremos comparar a Filial
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Filial f = (Filial) o;

        return this.mapFilial.equals(f.getFilial());
    }

    /**
     * Função que inicia a Filial com todos os Clientes e todos os valores a 0
     * @param catClientes   Catalogo com todos os Clientes a introduzir
     */
    public void initFilial (ICatClientes catClientes)
    {
        this.mapFilial = catClientes.getCatClientes().stream().collect(Collectors.toMap(ICliente::getClientID, s -> new NodoFilial(s.getClientID())));
    }

    /**
     * Função que inicia a Filial com todos os Clientes e todos os valores a 0
     * @param catClientes   Catalogo com todos os Clientes a introduzir
     * @param mapInt        Identificador dos maps a ser criados
     */
    public void initFilial (ICatClientes catClientes, int mapInt)
    {
        Map<String, NodoFilial> auxMap = catClientes.getCatClientes().stream().collect(Collectors.toMap(ICliente::getClientID, s -> new NodoFilial(s.getClientID(), mapInt)));

        switch (mapInt) {
            case GestVendasTest_Set.HASH_MAP:
                this.mapFilial = new HashMap<>(auxMap);
                break;
            case GestVendasTest_Set.TREE_MAP:
                this.mapFilial = new TreeMap<>(auxMap);
                break;
        }

        this.mapFilial = catClientes.getCatClientes().stream().collect(Collectors.toMap(ICliente::getClientID, s -> new NodoFilial(s.getClientID(), mapInt)));
    }

    /**
     * Função que transforma a Filial numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("CLIENTES E RELAÇÕES COM OS PRODUTOS QUE COMPROU");
        this.mapFilial.forEach((key,value) -> sb.append(value.toString()).append("\n"));

        return sb.toString();
    }

    /**
     * Função que insere uma venda na Filial
     * @param venda         Venda com os dados que queremos introduzir na faturacao
     */
    public void insereFilial(Venda venda)
    {
        String productID = venda.getProductID();
        String clientID = venda.getClientID();
        double preco = venda.getPreco();
        int quantidade = venda.getQuantidade();
        char modo = venda.getModo();
        int mes = venda.getMes();

        if (this.mapFilial.containsKey(clientID)) {
            this.mapFilial.get(clientID).insereVendaFilial(productID, preco, quantidade, modo, mes);
        } else {
            NodoFilial novo = new NodoFilial();
            novo.setClientID(clientID);
            novo.insereVendaFilial(productID, preco, quantidade, modo, mes);
            this.mapFilial.put(clientID, novo);
        }
    }

    /**
     * Função que insere uma venda na Filial
     * @param venda         Venda com os dados que queremos introduzir na faturacao
     * @param mapInt        Identificador dos maps a serem criados
     */
    public void insereFilial(Venda venda, int mapInt)
    {
        String productID = venda.getProductID();
        String clientID = venda.getClientID();
        double preco = venda.getPreco();
        int quantidade = venda.getQuantidade();
        char modo = venda.getModo();
        int mes = venda.getMes();

        if (this.mapFilial.containsKey(clientID)) {
            this.mapFilial.get(clientID).insereVendaFilial(productID, preco, quantidade, modo, mes);
        } else {
            NodoFilial novo = new NodoFilial(clientID, mapInt);
            novo.insereVendaFilial(productID, preco, quantidade, modo, mes);
            this.mapFilial.put(clientID, novo);
        }
    }

    /*** QUERIES ESTATITTICAS ***/
    /**
     * Funcao auxiliar da Query1 Estatística
     * @param listaClientesCompraram    Set de Strings que vamos preencher com String de códigos de clientes da filial em estudo que compraram
     */
    public void getClientesCompraram(Set<String> listaClientesCompraram)
    {
        this.mapFilial.values().forEach(fil -> fil.getClientesCompraram(listaClientesCompraram));
    }


    /***** QUERY 1E *****/
    /**
     * Funcao auxiliar da Query2.3 Estatística
     * @param listClientesDistintos    Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getTotalCompradoresPorMes (List<Set<String>> listClientesDistintos)
    {
        this.mapFilial.values().forEach(cli -> cli.getTotalCompradoresPorMes(listClientesDistintos));
    }

    /***** QUERY 2 *****/
    /**
     * Funcao auxiliar da Query2
     * @param listClientesDistintos     Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     * @param mes                       Mês que pretendemos estudar
     */
    public int numeroVendasEClientesMes (Set<String> listClientesDistintos, int mes)
    {
        return (this.mapFilial.values().stream().mapToInt(rel -> rel.numeroVendasEClientesMes(listClientesDistintos, mes)).sum());
    }


    /***** QUERY 3 *****/
    /**
     * Funcao auxiliar da Query3
     * @param lista                     Lista de Lista de doubles onde vamos guardar os resultados pretendidos pela querie
     * @param clientID                  Código de Cliente que pretendemos estudar
     * @param listaProdsNaoRepetidos    Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getComprasProdsGastoMes (List< List<Double> > lista, String clientID, List<Set<String>> listaProdsNaoRepetidos)
    {
        if (this.mapFilial.containsKey(clientID)) {
            this.mapFilial.get(clientID).getComprasProdsGastoMes(lista, listaProdsNaoRepetidos);
        }
    }

    /***** QUERY 4 *****/
    /**
     * Funcao auxiliar da Query4
     * @param lista                     Lista de Lista de doubles onde vamos guardar os resultados pretendidos pela querie
     * @param productID                 Código de Produto que pretendemos estudar
     * @param listaProdsNaoRepetidos    Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getComprasClientesGastoMes (List< List<Double> > lista, String productID, List<Set<String>> listaProdsNaoRepetidos)
    {
        this.mapFilial.values().forEach(nodo -> nodo.getComprasClientesGastoMes(lista, listaProdsNaoRepetidos, productID));
    }

    /***** QUERY 5 *****/
    /**
     * Funcao auxiliar da Query5
     * @param mapa                     Lista de Pares String Integer onde vmaos colocando os resultados pretendidos pela querie
     * @param clienteID                Código de Cliente que pretendemos estudar
     */
    public void getProdsClienteMaisComprados (List<ParStringInteger> mapa, String clienteID)
    {
        if (this.mapFilial.containsKey(clienteID)) {
            this.mapFilial.get(clienteID).getProdsClienteMaisComprados(mapa);
        }
    }

    /***** QUERY 6 *****/
    /**
     * Funcao auxiliar da Query6
     * @param lista                    Lista de Pares String Integer onde vmaos colocando os resultados pretendidos pela querie
     * @param listaProdsNaoRepetidos     Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getProdsMaisCompradosTotal (List<ParStringInteger> lista, List<Set<String>> listaProdsNaoRepetidos)
    {
        this.mapFilial.values().forEach(nodo -> nodo.getProdsMaisCompradosTotal(lista, listaProdsNaoRepetidos));
    }

    /***** QUERY 7 *****/
    //public void tresMaioresCompradoresPorFilial (Map<ParStringDouble, String> mapa)
    /**
     * Funcao auxiliar da Query7
     * @param mapa                    Set de Pares String Double que vamos preencher á medida que vamos percorrendo a filial com os resultados pretendidos
     */
    public void tresMaioresCompradoresPorFilial (Set<ParStringDouble> mapa)
    {
        this.mapFilial.values().forEach(cliente -> cliente.getTresMaioresCompradoresPorFilial(mapa) );
    }

    /***** QUERY 8 *****/
    /**
     * Funcao auxiliar da Query8
     * @param mapaClientesESeusProdutos     Map onde as keys sao os códigos de Clietnes e os values são sets onde vamos introduzindo codigos de Produtos que foram comprados pelo clietne da key
     */
    public void getClientesCompraramMaisProdutos (Map<String, Set<String>> mapaClientesESeusProdutos)
    {
        this.mapFilial.values().forEach(cliente -> cliente.getClientesCompraramMaisProdutos(mapaClientesESeusProdutos));
    }

    /***** QUERY 9 *****/
    /**
     * Funcao auxiliar da Query9
     * @param produtoID     String com o Código do Produto que pretendemos estudar
     * @param mapa          Map onde as keys sao os códigos de Clietnes e os values são sets Pares String Integer
     */
    public void getClientesMaisCompraramProduto (String produtoID, Map<String, ParStringInteger> mapa)
    {
        this.mapFilial.values().forEach(cliente -> cliente.getClientesMaisCompraramProduto(produtoID, mapa) );
    }

    /**
     * Funcao auxiliar da Query9
     * @param productID     String com o Código do Produto que pretendemos estudar
     * @param lista         List de Pares String Double onde vamos incrementando os resultados á medida que percorremos a filial
     */
    public void getFaturadoPorClientes (List<ParStringDouble> lista, String productID)
    {
        lista.forEach(par -> this.mapFilial.get(par.getStringKey()).getFaturadoPorUmDadoCliente(par, productID));
    }
}
