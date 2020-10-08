package Models;

import Models.Faturacao.*;
import Models.Filial.*;
import Models.CatClientes.*;
import Models.CatProdutos.*;
import NewExceptions.*;
import Structs.*;
import Comparators.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe GestVendas que implementa a interface IGestVendas
 */
public class GestVendas implements IGestVendas
{
    private ICatProdutos catProdutos;
    private ICatClientes catClientes;
    private IFaturacao faturacao;
    private List<IFilial> filiais;
    private String produtos_src;
    private String clientes_src;
    private String vendas_src;
    private int nr_filiais;
    private int nr_produtos;
    private int nr_clientes;
    private int nr_vendas;
    private int nr_vendas_totais;
    private int nr_vendas_valor0;

    /**
     * Construtor por omissão da GestVendas
     */
    public GestVendas()
    {
        this.catProdutos = new CatProdutos();
        this.catClientes = new CatClientes();
        this.faturacao = new Faturacao();
        this.filiais = null;
        this.produtos_src = "";
        this.clientes_src = "";
        this.vendas_src = "";
        this.nr_filiais = 0;
        this.nr_produtos = 0;
        this.nr_clientes = 0;
        this.nr_vendas = 0;
        this.nr_vendas_totais = 0;
        this.nr_vendas_valor0 = 0;
    }

    /**
     * Construtor parametrizado da GestVendas
     * @param catProdutos           Catálogo de Produtos
     * @param catClientes           Catálogo de Clientes
     * @param faturacao             Fáturacao
     * @param filiais               Lista com estruturas Filial
     * @param produtos_src          String com path do ficheiro dos Produtos
     * @param clientes_src          String com path do ficheiro dos Clientes
     * @param vendas_src            String com path do ficheiro das Vendas
     * @param nr_filiais            Inteiro com o número de filiais
     * @param nr_produtos           Inteiro com o numero de Produtos
     * @param nr_clientes           Inteiro com o numero de Clientes
     * @param nr_vendas             Número de Vendas Válidas
     * @param nr_vendas_totais      Número de Vendas Totais
     * @param nr_vendas_valor0      Número de Vendas com Valor 0
     */
    public GestVendas(ICatProdutos catProdutos, ICatClientes catClientes, IFaturacao faturacao, List<IFilial> filiais, String produtos_src, String clientes_src, String vendas_src, int nr_filiais, int nr_produtos, int nr_clientes, int nr_vendas, int nr_vendas_totais, int nr_vendas_valor0)
    {
        this.catProdutos = catProdutos.clone();
        this.catClientes = catClientes.clone();
        this.faturacao = faturacao.clone();
        setFiliais(filiais);
        this.produtos_src = produtos_src;
        this.clientes_src = clientes_src;
        this.vendas_src = vendas_src;
        this.nr_filiais = nr_filiais;
        this.nr_produtos = nr_produtos;
        this.nr_clientes = nr_clientes;
        this.nr_vendas = nr_vendas;
        this.nr_vendas_totais = nr_vendas_totais;
        this.nr_vendas_valor0 = nr_vendas_valor0;
    }

    /**
     * Construtor de cópia da GestVendas
     * @param gestVendas           GestVendas a copiar
     */
    public GestVendas(GestVendas gestVendas)
    {
        this.catProdutos = gestVendas.getCatProdutos();
        this.catClientes = gestVendas.getCatClientes();
        this.faturacao = gestVendas.getFaturacao();
        this.filiais = new ArrayList<>(gestVendas.getFiliais());
        this.produtos_src = gestVendas.getProdutos_src();
        this.clientes_src = gestVendas.getClientes_src();
        this.vendas_src = gestVendas.getVendas_src();
        this.nr_filiais = gestVendas.getNr_filiais();
        this.nr_produtos = gestVendas.getNr_produtos();
        this.nr_clientes = gestVendas.getNr_clientes();
        this.nr_vendas = gestVendas.getNr_vendas();
        this.nr_vendas_totais = gestVendas.getNr_vendas_totais();
        this.nr_vendas_valor0 = gestVendas.getNr_vendas_valor0();
    }

    /**
     * Construtor parametrizado da GestVendas
     * @param setInt        Identificador do tipo de Set a criar (HashSet, TreeSet, etc.)
     */
    public GestVendas(int setInt)
    {
        this.catProdutos = new CatProdutos(setInt);
        this.catClientes = new CatClientes(setInt);
        this.faturacao = new Faturacao();
        this.filiais = null;
        this.produtos_src = "";
        this.clientes_src = "";
        this.vendas_src = "";
        this.nr_filiais = 0;
        this.nr_produtos = 0;
        this.nr_clientes = 0;
        this.nr_vendas = 0;
        this.nr_vendas_totais = 0;
        this.nr_vendas_valor0 = 0;
    }

    /**
     * Getter do Catálogo de Produtos
     * @return           Catálogo de Produtos
     */
    public ICatProdutos getCatProdutos()
    {
        return catProdutos.clone();
    }

    /**
     * Setter do Catálogo de Produtos
     * @param catProdutos           Catálogo de Produtos
     */
    public void setCatProdutos(ICatProdutos catProdutos)
    {
        this.catProdutos = catProdutos.clone();
    }

    /**
     * Getter do Catálogo de Clientes
     * @return           Catálogo de Clientes
     */
    public ICatClientes getCatClientes()
    {
        return catClientes.clone();
    }

    /**
     * Setter do Catálogo de Clientes
     * @param catClientes           Catálogo de Clientes
     */
    public void setCatClientes(ICatClientes catClientes)
    {
        this.catClientes = catClientes.clone();
    }

    /**
     * Getter da Faturaçao
     * @return           Faturação
     */
    public IFaturacao getFaturacao()
    {
        return faturacao.clone();
    }

    /**
     * Setter da Faturacao
     * @param faturacao           Faturacao
     */
    public void setFaturacao(IFaturacao faturacao)
    {
        this.faturacao = faturacao.clone();
    }

    /**
     * Getter da Lista de Filiais
     * @return           List de Filiais
     */
    public List<IFilial> getFiliais()
        {
        return filiais.stream().map(IFilial::clone).collect(Collectors.toList());
    }

    /**
     * Setter da List de Filiais
     * @param filiais           List de Filiais
     */
    public void setFiliais(List<IFilial> filiais) {
        this.filiais = filiais.stream().map(IFilial::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Getter da String com o Path do ficheiro dos Produtos
     * @return           String com o Path do ficheiro dos Produtos
     */
    public String getProdutos_src()
    {
        return produtos_src;
    }

    /**
     * Setter da String com o Path do ficheiro dos Produtos
     * @param produtos_src           String com o Path do ficheiro dos Produtos
     */
    public void setProdutos_src(String produtos_src)
    {
        this.produtos_src = produtos_src;
    }

    /**
     * Getter da String com o Path do ficheiro dos Clientes
     * @return           String com o Path do ficheiro dos Clientes
     */
    public String getClientes_src()
    {
        return clientes_src;
    }

    /**
     * Setter da String com o Path do ficheiro dos Clientes
     * @param clientes_src           String com o Path do ficheiro dos Clientes
     */
    public void setClientes_src(String clientes_src)
    {
        this.clientes_src = clientes_src;
    }

    /**
     * Getter da String com o Path do ficheiro das Vendas
     * @return           String com o Path do ficheiro das Vendas
     */
    public String getVendas_src()
    {
        return vendas_src;
    }

    /**
     * Setter da String com o Path do ficheiro das Vendas
     * @param vendas_src           String com o Path do ficheiro das Vendas
     */
    public void setVendas_src(String vendas_src)
    {
        this.vendas_src = vendas_src;
    }

    /**
     * Getter do número de filiais do projeto
     * @return           Integer com o número de filiais
     */
    public int getNr_filiais()
    {
        return nr_filiais;
    }

    /**
     * Setter do número de filiais do prjeto
     * @param nr_filiais           Integer com o número de filiais
     */
    public void setNr_filiais(int nr_filiais)
    {
        this.nr_filiais = nr_filiais;
    }

    /**
     * Getter do número de produtos do projeto
     * @return           Integer com o número de produtos
     */
    public int getNr_produtos()
    {
        return nr_produtos;
    }

    /**
     * Setter do número de produtos do prjeto
     * @param nr_produtos           Integer com o número de produtos
     */
    public void setNr_produtos(int nr_produtos)
    {
        this.nr_produtos = nr_produtos;
    }

    /**
     * Getter do número de Clientes do projeto
     * @return           Integer com o número de Clientes
     */
    public int getNr_clientes()
    {
        return nr_clientes;
    }

    /**
     * Setter do número de Clientes do prjeto
     * @param nr_clientes           Integer com o número de Clientes
     */
    public void setNr_clientes(int nr_clientes)
    {
        this.nr_clientes = nr_clientes;
    }

    /**
     * Getter do número de Vendas Válidas do Projeto
     * @return           Integer com o número de Vendas Válidas do Projeto
     */
    public int getNr_vendas()
    {
        return nr_vendas;
    }

    /**
     * Setter do número de Vendas Válidas do Projeto
     * @param nr_vendas           Integer com o número de Vendas Válidas do Projeto
     */
    public void setNr_vendas(int nr_vendas)
    {
        this.nr_vendas = nr_vendas;
    }

    /**
     * Getter do número de Vendas Totais do Projeto
     * @return           Integer com o número de Vendas Totais do Projeto
     */
    public int getNr_vendas_totais() {
        return nr_vendas_totais;
    }

    /**
     * Setter do número de Vendas Totais do Projeto
     * @param nr_vendas_totais           Integer com o número de Vendas Totais do Projeto
     */
    public void setNr_vendas_totais(int nr_vendas_totais) {
        this.nr_vendas_totais = nr_vendas_totais;
    }

    /**
     * Getter do número de Vendas de valor 0 do Projeto
     * @return           Integer com o número de Vendas de valor 0 do Projeto
     */
    public int getNr_vendas_valor0() {
        return nr_vendas_valor0;
    }

    /**
     * Setter do número de Vendas de valor 0 do Projeto
     * @param nr_vendas_valor0           Integer com o número de Vendas de valor 0 do Projeto
     */
    public void setNr_vendas_valor0(int nr_vendas_valor0) {
        this.nr_vendas_valor0 = nr_vendas_valor0;
    }

    /**
     * Getter de uma Filial da Lista de Filiais
     * @param filial    Número de filial que pretendemos obter
     * @return          Filial
     */
    public IFilial getFilial(int filial) {
        return this.filiais.get(filial-1).clone();
    }


    /**
     * Função de equals da GestVendas
     * @param o           Objeto ao qual queremos comparar a GestVendas
     */
    public boolean equals (Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        GestVendas gv = (GestVendas) o;

        return this.catProdutos.equals(gv.getCatProdutos()) &&
                this.catClientes.equals(gv.getCatClientes()) &&
                this.faturacao.equals(gv.getFaturacao()) &&
                this.filiais.equals(gv.getFiliais()) &&
                this.produtos_src.equals(gv.getProdutos_src()) &&
                this.clientes_src.equals(gv.getClientes_src()) &&
                this.vendas_src.equals(gv.getVendas_src()) &&
                this.nr_filiais == gv.getNr_filiais() &&
                this.nr_produtos == gv.getNr_produtos() &&
                this.nr_clientes == gv.getNr_clientes() &&
                this.nr_vendas == gv.getNr_vendas();
    }

    /**
     * Função que transforma a GestVendas numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.catProdutos.toString()).append("\n");
        sb.append(this.catClientes.toString()).append("\n");
        sb.append(this.faturacao.toString()).append("\n");
        for (int i = 0; i < this.filiais.size(); i++)
            sb.append("FILIAL ").append(i+1).append(": ").append(this.filiais.get(i).toString()).append("\n");

        return sb.toString();
    }

    /**
     * Função que dá clone à GestVendas
     * @return           Cópia da GestVendas
     */
    public GestVendas clone()
    {
        return new GestVendas(this);
    }

    /**
     * Função que insere produto no catálogo de produtos
     * @param produto       Produto que queremos inserir
     */
    public void insereProduto(IProduto produto)
    {
        this.catProdutos.insere(produto);
    }

    /**
     * Função que insere cliente no catálogo de clientes
     * @param cliente       Cliente que queremos inserir
     */
    public void insereCliente(ICliente cliente)
    {
        this.catClientes.insere(cliente);
    }

    /**
     * Função que insere uma venda na Faturacao
     * @param venda       Venda que queremos inserir
     */
    public void insereFaturacao(Venda venda)
    {
        this.faturacao.insereFatura(venda, this.nr_filiais);
    }

    /**
     * Função que insere uma venda na Faturacao
     * @param venda       Venda que queremos inserir
     * @param listInt     Tipo de List a criar (ArrayList, LinkedList, etc.)
     */
    public void insereFaturacao(Venda venda, int listInt)
    {
        this.faturacao.insereFatura(venda, this.nr_filiais, listInt);
    }

    /**
     * Função que insere uma venda na Lista de Filiais
     * @param venda       Venda que queremos inserir
     */
    public void insereNaFilial (Venda venda) {
        this.filiais.get(venda.getFilial()-1).insereFilial(venda);
    }

    /**
     * Função que insere uma venda na Filial
     * @param venda       Venda que queremos inserir
     * @param mapInt      Tipo de Map a criar (HashMap, TreeMap, etc.)
     */
    public void insereNaFilial (Venda venda, int mapInt) {
        this.filiais.get(venda.getFilial()-1).insereFilial(venda, mapInt);
    }

    /**
     * Função que inicia a Faturacao com todos os Produtos e todos os valores a 0
     * @param nr_filiais    Número de filiais do Projeto
     */
    public void initFaturacao(int nr_filiais)
    {
        this.faturacao.initFaturacao(catProdutos, nr_filiais);
    }

    /**
     * Função que inicia a Faturacao com todos os Produtos e todos os valores a 0
     * @param nr_filiais      Número de Filiais do Projeto
     * @param mapInt          Tipo de Map a criar (HashMap, TreeMap, etc.)
     * @param listInt         Tipo de List a criar (ArrayList, LinkedList, etc.)
     */
    public void initFaturacao(int nr_filiais, int mapInt, int listInt)
    {
        this.faturacao.initFaturacao(catProdutos, nr_filiais, mapInt, listInt);
    }

    /**
     * Função que inicia a List de Filiais com n Filial (sendo N o número de filiais) e inicia todos os parametros a 0
     */
    public void initFilial()
    {
        this.filiais = new ArrayList<>();
        for (int i = 0; i < this.nr_filiais; i++)
            this.filiais.add(new Filial());

        for (IFilial filial : this.filiais)
            filial.initFilial(catClientes);
    }

    /**
     * Função que inicia a List de Filiais com n Filial (sendo N o número de filiais) e inicia todos os parametros a 0
     * @param mapInt          Tipo de Map a criar (HashMap, TreeMap, etc.)
     */
    public void initFilial(int mapInt)
    {
        this.filiais = new ArrayList<>();
        for (int i = 0; i < this.nr_filiais; i++)
            this.filiais.add(new Filial());

        for (IFilial filial : this.filiais)
            filial.initFilial(catClientes, mapInt);
    }

    /**
     * Função que verifica se um IProduto se encontra no catálogo
     * @param produto           Interface IProduto a verificar se existe no Catálogo
     * @return                  Booleano que mostra se Produto pertence ao catálogo ou não
     */
    public boolean searchProduto(IProduto produto)
    {
        return this.catProdutos.procura(produto);
    }

    /**
     * Função que verifica se um ICliente se encontra no catálogo
     * @param cliente           Interface ICliente a verificar se existe no Catálogo
     * @return                  Booleano que mostra se Cliente pertence ao catálogo ou não
     */
    public boolean searchCliente(ICliente cliente)
    {
        return this.catClientes.procura(cliente);
    }


    /* ################### QUERIES ESTATISTICAS ################### */

    /*#*#*#*#*#*#*#*#*# Para a Querie1 estatistica #*#*#*#*#*#*#*#*#*/

    /**
     * Função que dá o numero de vendas inválidas lidas
     * @return      Integer com o numero de vendas inválidas lidas
     */
    public int totalDeVendasErradas () {
        return (this.getNr_vendas_totais()-this.getNr_vendas());
    }

    /**
     * Função que dá um Set com todos os clientes que compraram pelo menos um produto
     * @return      Set com Strings de códigos de Clientes
     */
    public Set<String> getClientesCompraram()
    {
        Set<String> clientesCompraram = new TreeSet<String>();
        this.filiais.forEach(fil -> fil.getClientesCompraram(clientesCompraram));
        return clientesCompraram;
    }

    /**
     * Query que apresenta os dados referentes ao último ficheiro de vendas lido
     * @return      Tipo de Estrutura onde estão todos os dados necessários para responder á queries estatística
     */
    public Map.Entry< String, List<Integer> > querieEstatistica1 ()
    {
        List<Integer> listaResultados = new ArrayList<Integer>(10);

        listaResultados.add(0, this.getNr_vendas_totais());
        listaResultados.add(1, this.getNr_vendas());
        listaResultados.add(2, this.totalDeVendasErradas());
        listaResultados.add(3, this.getNr_vendas_valor0());

        int nrProdutosNaoComprados = this.getProdutosNuncaComprados().size();
        listaResultados.add(4, this.getNr_produtos());
        listaResultados.add(5, (this.getNr_produtos() - nrProdutosNaoComprados));
        listaResultados.add(6, nrProdutosNaoComprados);

        int nrClientesCompraram = this.getClientesCompraram().size();
        listaResultados.add(7, this.getNr_clientes());
        listaResultados.add(8, nrClientesCompraram);
        listaResultados.add(9, (this.getNr_clientes() - nrClientesCompraram));

        Map.Entry< String, List<Integer> > resultado = new AbstractMap.SimpleEntry<String, List<Integer>>(this.getVendas_src(), listaResultados);
        return resultado;
    }


    /******************QUERY 1E**********************/
    /**
     * Funçao da Query2.1 Estatística - Número total de compras por mês
     * @return      Lista de inteiros com resultados necessários para responder á querie
     */
    public List<Integer> getTotalComprasPorMes ()
    {
        List<Integer> resultado = new ArrayList<>(Collections.nCopies(12, 0));
        this.faturacao.getTotalComprasPorMes(resultado);
        return resultado;
    }

    /******************QUERY 2E**********************/
    /**
     * Funçao da Query2.2 Estatística - Faturação total por mês para cada filial e o valor total global;
     * @return      Lista de Lista de Doubles com resultados necessários para responder á querie
     */
    public List<List<Double>> getTotalFaturadoPorMes ()
    {
        List<List<Double>> resultado = new ArrayList<>(4);
        for (int fil = 0; fil < this.nr_filiais+1; fil++) {
            resultado.add(fil, new ArrayList<>(Collections.nCopies(12, 0.0)));
        }

        this.faturacao.getTotalFaturadoPorMes(resultado);

        for (int mes = 0; mes < 12; mes++) {
            double faturadoTotalMes = 0;
            for (int fil = 0; fil < this.nr_filiais; fil++) {
                faturadoTotalMes += resultado.get(fil).get(mes);
            }
            resultado.get(this.nr_filiais).set(mes, faturadoTotalMes);
        }

        return resultado;
    }

    /******************QUERY 3E**********************/
    /**
     * Funçao da Query2.3 Estatística - Número de distintos clientes que compraram em cada mês
     * @return      Lista de Lista de Inteiros com resultados necessários para responder á querie
     */
    public List<List<Integer>> getTotalCompradoresPorMes ()
    {
        List<List<Set<String>>> clientesDeCadaMes = new ArrayList<>(this.nr_filiais);
        for (int fil = 0; fil < this.nr_filiais; fil++) {
            clientesDeCadaMes.add(new ArrayList<>(12));
            for (int mes = 0; mes < 12; mes++) {
                clientesDeCadaMes.get(fil).add(mes, new TreeSet<String>());
            }
            this.filiais.get(fil).getTotalCompradoresPorMes( clientesDeCadaMes.get(fil) );
        }
        List<List<Integer>> resultado = new ArrayList<>(this.nr_filiais);
        for (int fil = 0; fil < this.nr_filiais; fil++) {
            resultado.add(fil, new ArrayList<>(Collections.nCopies(12, 0)));
            for (int mes = 0; mes < 12; mes++) {
                resultado.get(fil).set(mes, clientesDeCadaMes.get(fil).get(mes).size());
            }
        }

        return resultado;
    }


    /* ################### QUERIES INTERATIVAS ################### */
    /******************QUERY 1**********************/
    /**
     * Funçao da Query1 - Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respetivo total
     * @return      Lista de Strings com resultados necessários para responder á query
     */
    public List<String> getProdutosNuncaComprados()
    {
        return this.faturacao.getProdutosNuncaComprados();
    }


    /******************QUERY 2**********************/
    /**
     * Funçao da Query2 - Dado um mês válido, determinar o número total global de vendas realizadas e  o número total de clientes distintos que as fizeram; Fazer o mesmo mas para cada uma das filiais;
     * @param mes   Mês que pretendemos estudar
     * @return      Lista de Lista de Integers com resultados necessários para responder á query
     */
    public List<List<Integer>> numeroVendasEClientesMes (int mes)
    {
        List<List<Integer>> res = new ArrayList<>(this.nr_filiais);
        List<Set<String>> listClientesDistintos = new ArrayList<>(this.nr_filiais);
        for (int fil = 0; fil < this.nr_filiais; fil++) {
            listClientesDistintos.add(fil, new TreeSet<>());
        }

        for (int fil = 0; fil < this.nr_filiais; fil++) {
            res.add(new ArrayList<Integer>(2));
            int num = this.filiais.get(fil).numeroVendasEClientesMes(listClientesDistintos.get(fil), mes);

            res.get(fil).add(0, num);
            res.get(fil).add(1, listClientesDistintos.get(fil).size());
        }

        return res;
    }

    /******************QUERY 3**********************/
    /**
     * Funçao da Query3 - Dado  um  código  de  cliente,  determinar,  para  cada  mês,  quantas  compras  fez, quantos produtos distintos comprou e quanto gastou no total.
     * @param clienteID     Código de Cliente a estudar
     * @return              Lista de Lista de Doubles com resultados necessários para responder á query
     */
    public List<List<Double>> getComprasProdsGastoMes (String clienteID) throws ClienteInexistenteException
    {
        if (!this.catClientes.procura(new Cliente(clienteID))) throw new ClienteInexistenteException();

        List<List<Double>> res = new ArrayList<>(this.nr_filiais);
        List<Set<String> > listaProdsNaoRepetidos = new ArrayList<>(12);
            for (int i=0; i<12; i++) {
                listaProdsNaoRepetidos.add(i, new TreeSet<>());
            }

        for (int fil = 0; fil < this.nr_filiais; fil++) {
            List<Double> meses = new ArrayList<>(Collections.nCopies(12, 0.0));
            res.add(fil, meses);
        }

        this.filiais.forEach(fil -> fil.getComprasProdsGastoMes(res, clienteID, listaProdsNaoRepetidos));

        return res;
    }


    /******************QUERY 4**********************/
    /**
     * Funçao da Query4 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi comprado, por quantos clientes diferentes e o total faturado
     * @param produtoID     Código de Produto a estudar
     * @return              Lista de Lista de Doubles com resultados necessários para responder á query
     */
    public List<List<Double>> getComprasClientesGastoMes (String produtoID) throws ProdutoInexistenteException
    {
        if (!this.catProdutos.procura(new Produto(produtoID))) throw new ProdutoInexistenteException();

        List<List<Double>> res = new ArrayList<>(this.nr_filiais);
        List<Set<String> > listaClientesNaoRepetidos = new ArrayList<>(12);
            for (int i=0; i<12; i++) {
                listaClientesNaoRepetidos.add(i, new TreeSet<>());
            }

        for (int fil = 0; fil < this.nr_filiais; fil++) {
            List<Double> meses = new ArrayList<>(Collections.nCopies(12, 0.0));
            res.add(fil, meses);
        }

        this.filiais.forEach(fil -> fil.getComprasClientesGastoMes(res, produtoID, listaClientesNaoRepetidos));

        return res;
    }


    /******************QUERY 5**********************/
    /**
     * Funçao da Query5 - Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou  (e  quantos),  ordenada  por ordem  decrescente  de  quantidade  e, para quantidades iguais, por ordem alfabética dos códigos
     * @param clienteID     Código de Cliente a estudar
     * @return              Lista de Pares de String e Integer com os resultados necessários para responder á query
     */
    public List<ParStringInteger> getProdsClienteMaisComprados (String clienteID) throws ClienteInexistenteException
    {
        if (!this.catClientes.procura(new Cliente(clienteID))) throw new ClienteInexistenteException();

        List<ParStringInteger> res = new ArrayList<>();
        this.filiais.forEach(fil -> fil.getProdsClienteMaisComprados(res, clienteID));
        res.sort(new ComparatorStringInteger());
        return res;
    }


    /******************QUERY 6**********************/
    /**
     * Funçao da Query6 - Determinar o conjunto dos X produtos mais vendidos na totalidade do ano (em número de unidades  vendidas)  indicando  o  número  total  de  distintos  clientes  que  o compraram (X é um inteiro dado pelo utilizador)
     * @param limite     Limite de Produtos a estudar
     * @return           Lista Map Entrys em que as keys são Pares String Integer e os values são Strings, com os resultados necessários para responder á query
     */
    public List<Map.Entry<ParStringInteger, Integer>> getProdsMaisCompradosTotal (int limite)
    {
        Map<String, ParStringInteger> res = new HashMap<>();
        this.faturacao.getProdsMaisCompradosTotal(res);

        List<ParStringInteger> resultado = res.values().stream().sorted(new ComparatorStringInteger()).limit(limite).collect(Collectors.toList());

        List<Set<String> > listaClientesDistintos = new ArrayList<>(resultado.size());
        for (int i=0; i<resultado.size(); i++) {
            listaClientesDistintos.add(i, new TreeSet<>());
        }

        this.filiais.forEach(fil -> fil.getProdsMaisCompradosTotal(resultado, listaClientesDistintos));

        List<Map.Entry<ParStringInteger, Integer>> resultadoFinal = new ArrayList<>(resultado.size());
        for (int i=0; i<resultado.size(); i++) {
            resultadoFinal.add(new AbstractMap.SimpleEntry<>(resultado.get(i), listaClientesDistintos.get(i).size()));
        }

        return resultadoFinal;
    }


    /******************QUERY 7**********************/
    /**
     * Funçao da Query7 - Determinar,  para  cada  filial, a  lista dos três  maiores  compradores em  termos de dinheiro faturado
     * @return        Lista de uma Lista de Pares de String e Doubles com os resultados necessários para responder á query
     */
    public List< List<ParStringDouble> > tresMaioresCompradoresPorFilial ()
    {
        //List< Map<ParStringDouble, String> > listaInicial = new ArrayList<>(this.nr_filiais);
        List< Set<ParStringDouble> > listaInicial = new ArrayList<>(this.nr_filiais);
        for (int i=0; i < this.nr_filiais; i++) {
            /*listaInicial.add(i, new TreeMap<>(new ComparatorStringDouble()));*/
            listaInicial.add(i, new TreeSet<ParStringDouble>(new ComparatorStringDouble()));
        }
        for (int nrFilial = 0; nrFilial < this.nr_filiais; nrFilial++) {
            this.filiais.get(nrFilial).tresMaioresCompradoresPorFilial(listaInicial.get(nrFilial));
        }

        List< List<ParStringDouble> > resultadoFinal = new ArrayList<>(this.nr_filiais);
        for (int nrFilial = 0; nrFilial < this.nr_filiais; nrFilial++) {
            //Map<ParStringDouble, String> arvore = listaInicial.get(nrFilial);
            Set<ParStringDouble> arvore = listaInicial.get(nrFilial);

            //resultadoFinal.add(nrFilial, arvore.keySet().stream().limit(3).collect(Collectors.toList()));
            resultadoFinal.add(nrFilial, arvore.stream().limit(3).collect(Collectors.toList()));
        }

        return resultadoFinal;
    }


    /******************QUERY 8**********************/
    /**
     * Funçao da Query8 - Determinar os códigos dos X clientes (sendo X dado pelo utilizador) que compraram mais produtos diferentes(não  interessa  a  quantidade  nem  o  valor), indicando quantos,  sendo  o  critério  de  ordenação  a  ordem  decrescente  do  número  de produtos;
     * @param limite    Limite de Clientes a estudar
     * @return          Lista de Pares de String e Integer com os resultados necessários para responder á query
     */
    public List<ParStringInteger> getClientesCompraramMaisProdutos (int limite)
    {
        Map<String, Set<String>> mapaClientesESeusProdutos = new TreeMap<String, Set<String>>();
        this.filiais.forEach(fil -> fil.getClientesCompraramMaisProdutos(mapaClientesESeusProdutos));

        List<ParStringInteger> listaIntermedia = new ArrayList<ParStringInteger>();
                mapaClientesESeusProdutos.entrySet().forEach(par -> listaIntermedia.add(new ParStringInteger(par.getKey(), par.getValue().size())) );
        List<ParStringInteger> resultado = listaIntermedia.stream().sorted(new ComparatorStringInteger()).limit(limite).collect(Collectors.toList());

        return resultado;
    }


    /******************QUERY 9**********************/
    /**
     * Funçao da Query9 - Dado o código de um produto que deve existir, determinar o conjunto dos X clientes que mais o compraram e, para cada um, qual o valor gasto (ordenação cf. 5);
     * @param produtoID     Código de Produto a estudar
     * @param limite        Limite de Clientes a estudar
     * @return              Lista de Pares de String e Doubles com os resultados necessários para responder á query
     */
    public List<ParStringDouble> getClientesMaisCompraramProduto (String produtoID, int limite) throws ProdutoInexistenteException
    {
        if (!this.catProdutos.procura(new Produto(produtoID))) throw new ProdutoInexistenteException();

        //Map<ParStringInteger, String> mapaClientes = new TreeMap<ParStringInteger, String>(new ComparatorStringInteger());
        Map<String, ParStringInteger> mapaClientes = new TreeMap<String, ParStringInteger>();
        this.filiais.forEach(fil -> fil.getClientesMaisCompraramProduto(produtoID, mapaClientes) );

        List<ParStringInteger> listaClientesQuantidade = mapaClientes.values().stream().sorted(new ComparatorStringInteger()).limit(limite).collect(Collectors.toList());
        List<ParStringDouble> listaClientesFaturado = new ArrayList<>(listaClientesQuantidade.size());
                listaClientesQuantidade.stream().forEach(par -> listaClientesFaturado.add(new ParStringDouble(par.getStringKey())));
        this.filiais.forEach(fil -> fil.getFaturadoPorClientes(listaClientesFaturado, produtoID));

        return listaClientesFaturado;
    }


    /******************QUERY 10*********************/
/*
    public Map<String, List<List<Double>> > faturacaoTotalTodosProdutos () {
        Map <String, List<List<Double>> > res = new HashMap<>();
        this.faturacao.faturacaoTotalTodosProdutos(res);
        return res;
    }
*/
    /**
     * Funçao da Query10 - Determinar mês a mês, e para cada mês filial a filial, a faturação total com cada produto
     * @return              Lista de Pares de String e Doubles com os resultados necessários para responder á query
     */
    public List<List<List<ParStringDouble>>> faturacaoTotalTodosProdutos()
    {
        List<List<List<ParStringDouble>>> meses = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            meses.add(new ArrayList<>(this.nr_filiais));
            for (int j = 0; j < this.nr_filiais; j++)
                meses.get(i).add(new ArrayList<>());
        }

        this.faturacao.faturacaoTotalTodosProdutos(meses);

        return meses;
    }

}