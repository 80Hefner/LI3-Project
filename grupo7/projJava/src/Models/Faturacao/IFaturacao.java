package Models.Faturacao;

import Models.CatProdutos.ICatProdutos;
import Models.Venda;
import Structs.*;

import java.io.Serializable;
import java.util.*;

/**
 * Interface da Faturação
 */
public interface IFaturacao extends Serializable
{
    /**
     * Getter do Map da Faturacao
     * @return           Map com os dados da faturacao
     */
    public Map<String,NodoFatura> getFaturas();

    /**
     * Função que inicia a Faturacao com todos os Produtos e todos os valores a 0
     * @param catProdutos   Catalogo com todos os Produtos a introduzir
     * @param nr_filiais    Número de filiais do Projeto
     */
    public void initFaturacao (ICatProdutos catProdutos, int nr_filiais);

    /**
     * Função que inicia a Faturacao com todos os Produtos e todos os valores a 0 e com uma estrutura especificada
     * @param catProdutos   Catalogo com todos os Produtos a introduzir
     * @param nr_filiais    Número de filiais do Projeto
     * @param mapInt        Identificador do tipo de Maps a serem criados
     * @param listInt       Identificador do tipo de Lists a serem criados
     */
    public void initFaturacao (ICatProdutos catProdutos, int nr_filiais, int mapInt, int listInt);

    /**
     * Função que insere uma venda na Faturacao
     * @param venda         Vendas com os dados que queremos introduzir na faturacao
     * @param nr_filiais    Número de Filiais do projeto
     */
    public void insereFatura(Venda venda, int nr_filiais);

    /**
     * Função que insere uma venda na Faturacao, especificando o tip odas estruturas
     * @param venda         Vendas com os dados que queremos introduzir na faturacao
     * @param nr_filiais    Número de Filiais do projeto
     * @param listInt       Identificador do tipo de Lists a serem criados
     */
    public void insereFatura(Venda venda, int nr_filiais, int listInt);

    /**
     * Funçao auxiliar da Query2.1 Estatística
     * @param lista Lista de inteiros que iremos preencher
     */
    public void getTotalComprasPorMes(List<Integer> lista);

    /**
     * Função auxiliar da Query2.2 Estatística
     * @param lista     Uma lista de listas de doubles a preencher
     */
    public void getTotalFaturadoPorMes(List<List<Double>> lista);

    /**
     * Função auxiliar da Query1
     * @return  List de String com os produtos nunca comprados
     */
    public List<String> getProdutosNuncaComprados();

    /**
     * Função auxiliar da Query6
     * @param mapa  Mapa com keys que saõ os códigos de Produto e os values Pares String Integer que vamos preencher
     */
    public void getProdsMaisCompradosTotal (Map<String, ParStringInteger> mapa);

    /**
     * Função auxiliar da Query10
     * @param meses     Uma Lista de uma Lista de uma Lista de Pares String Double a preencher
     */
    public void faturacaoTotalTodosProdutos(List<List<List<ParStringDouble>>> meses);

    /**
     * Função que dá clone à Faturacao
     * @return           Cópia da Faturacao
     */
    public IFaturacao clone();
}
