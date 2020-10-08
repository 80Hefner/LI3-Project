package Models.Faturacao;

import Models.CatProdutos.*;
import Models.Venda;
import Structs.ParStringDouble;
import Structs.ParStringInteger;
import Tests.GestVendasTest_Set;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe Faturacao que implementa a interface IFaturacao
 */
public class Faturacao implements IFaturacao
{
    /**
     * Map com keys que saõ os codigos de Produtos e os values os seus dados
     */
    private Map<String, NodoFatura> faturas;

    /**
     * Construtor por omissão da Faturação
     */
    public Faturacao()
    {
        this.faturas = new HashMap<>();
    }

    /**
     * Construtor parametrizado da Faturacao
     * @param faturas           Map com as faturas dos produtos
     */
    public Faturacao(Map<String,NodoFatura> faturas)
    {
        this.faturas = faturas.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    /**
     * Construtor de cópia da Faturacao
     * @param f           Faturacao a copiar
     */
    public Faturacao(IFaturacao f)
    {
        this.faturas = f.getFaturas();
    }

    /**
     * Getter do Map da Faturacao
     * @return           Map co os dados da faturacao
     */
    public Map<String,NodoFatura> getFaturas()
    {
        return this.faturas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    /**
     * Setter do Map da Faturacao
     * @param faturas           Map que queremos pôr na Faturacao
     */
    public void setFaturas(Map<String,NodoFatura> faturas)
    {
        this.faturas = new HashMap<>();
        faturas.forEach((key,value) -> this.faturas.put(key,value.clone()));
    }

    /**
     * Função que inicia a Faturacao com todos os Produtos e todos os valores a 0
     * @param catProdutos   Catalogo com todos os Produtos a introduzir
     * @param nr_filiais    Número de filiais do Projeto
     */
    public void initFaturacao (ICatProdutos catProdutos, int nr_filiais)
    {
        this.faturas = catProdutos.getCatProdutos().stream().collect(Collectors.toMap(IProduto::getProductID, s -> new NodoFatura(s.getProductID(), nr_filiais)));
    }

    /**
     * Função que inicia a Faturacao com todos os Produtos e todos os valores a 0 e com uma estrutura especificada
     * @param catProdutos   Catalogo com todos os Produtos a introduzir
     * @param nr_filiais    Número de filiais do Projeto
     * @param mapInt        Identificador do tipo de Maps a serem criados
     * @param listInt       Identificador do tipo de Lists a serem criados
     */
    public void initFaturacao (ICatProdutos catProdutos, int nr_filiais, int mapInt, int listInt)
    {
        Map<String, NodoFatura> auxMap = catProdutos.getCatProdutos().stream().collect(Collectors.toMap(IProduto::getProductID, s -> new NodoFatura(s.getProductID(), nr_filiais, listInt)));

        switch (mapInt) {
            case GestVendasTest_Set.HASH_MAP:
                this.faturas = new HashMap<>(auxMap);
                break;
            case GestVendasTest_Set.TREE_MAP:
                this.faturas = new TreeMap<>(auxMap);
                break;
        }
    }

    /**
     * Função de equals da Faturacao
     * @param o           Objeto ao qual queremos comparar a Faturacao
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Faturacao f = (Faturacao) o;

        return this.faturas.equals(f.getFaturas());
    }

    /**
     * Função que transforma a Faturacao numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("FATURAS:");
        this.faturas.forEach((key,value) -> sb.append(value.toString()).append("\n"));

        return sb.toString();
    }

    /**
     * Função que dá clone à Faturacao
     * @return           Cópia da Faturacao
     */
    public Faturacao clone()
    {
        return new Faturacao(this);
    }

    /**
     * Função que insere uma venda na Faturacao
     * @param venda         Vendas com os dados que queremos introduzir na faturacao
     * @param nr_filiais    Número de Filiais do projeto
     */
    public void insereFatura(Venda venda, int nr_filiais)
    {
        String productID = venda.getProductID();
        double preco = venda.getPreco();
        int quantidade = venda.getQuantidade();
        char modo = venda.getModo();
        int mes = venda.getMes();
        int filial = venda.getFilial();

        if (this.faturas.containsKey(productID)) {
            this.faturas.get(productID).insereVenda(preco, quantidade, modo, mes, filial);
        } else {
            NodoFatura novo = new NodoFatura(nr_filiais);
            novo.setProductID(productID);
            novo.insereVenda(preco, quantidade, modo, mes, filial);
            this.faturas.put(productID, novo);
        }
    }

    /**
     * Função que insere uma venda na Faturacao, especificando o tip odas estruturas
     * @param venda         Vendas com os dados que queremos introduzir na faturacao
     * @param nr_filiais    Número de Filiais do projeto
     * @param listInt       Identificador do tipo de Lists a serem criados
     */
    public void insereFatura(Venda venda, int nr_filiais, int listInt)
    {
        String productID = venda.getProductID();
        double preco = venda.getPreco();
        int quantidade = venda.getQuantidade();
        char modo = venda.getModo();
        int mes = venda.getMes();
        int filial = venda.getFilial();

        if (this.faturas.containsKey(productID)) {
            this.faturas.get(productID).insereVenda(preco, quantidade, modo, mes, filial);
        } else {
            NodoFatura novo = new NodoFatura(productID, nr_filiais, listInt);
            novo.insereVenda(preco, quantidade, modo, mes, filial);
            this.faturas.put(productID, novo);
        }
    }

    /***** QUERY 1E *****/
    /**
     * Funçao auxiliar da Query2.1 Estatística
     * @param lista Lista de inteiros que iremos preencher
     */
    public void getTotalComprasPorMes(List<Integer> lista)
    {
        this.faturas.values().forEach(prod -> prod.getTotalComprasPorMes(lista));
    }

    /***** QUERY 2E *****/
    /**
     * Função auxiliar da Query2.2 Estatística
     * @param lista     Uma lista de listas de doubles a preencher
     */
    public void getTotalFaturadoPorMes(List<List<Double>> lista)
    {
        this.faturas.values().forEach(prod -> prod.getTotalFaturadoPorMes(lista));
    }

    /***** QUERY 1 *****/
    /**
     * Função auxiliar da Query1
     * @return  List de String com os produtos nunca comprados
     */
    public List<String> getProdutosNuncaComprados()
    {
        return this.faturas
                .values()
                .stream()
                .filter(p -> !p.foiComprado())
                .map(NodoFatura::getProductID)
                .collect(Collectors.toList());
    }

    /***** QUERY 6 *****/
    /**
     * Função auxiliar da Query6
     * @param mapa  Mapa com keys que saõ os códigos de Produto e os values Pares String Integer que vamos preencher
     */
    public void getProdsMaisCompradosTotal (Map<String, ParStringInteger> mapa)
    {
        this.faturas.values().forEach(nodo -> nodo.getProdsMaisCompradosTotal(mapa));
    }

    /***** QUERY 10 *****/
/*
    public void faturacaoTotalTodosProdutos (Map<String, List<List<Double>> > mapa) {
        this.faturas.values().forEach(prod -> prod.faturacaoTotalTodosProdutos(mapa));
    }
*/

    /**
     * Função auxiliar da Query10
     * @param meses     Uma Lista de uma Lista de uma Lista de Pares String Double a preencher
     */
    public void faturacaoTotalTodosProdutos(List<List<List<ParStringDouble>>> meses)
    {
        this.faturas.values().forEach(prod -> prod.faturacaoTotalTodosProdutos(meses));
    }
}
