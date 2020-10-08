package Models.Filial;

import Structs.*;

import java.io.Serializable;
import java.util.*;

/**
 * Classe ProdutosCliente que contem os dados das vendas e que que implementa o seu Comparable
 */
public class ProdutosCliente implements Comparable<ProdutosCliente>, Serializable
{

    String productID;
    int totalUnidades[];
    double totalFaturado[];
    int totalVendas[];

    /**
     * Construtor por omissão do ProdutosCliente
     */
    public ProdutosCliente ()
    {
        this.productID     = "";
        this.totalUnidades = new int[12];
        this.totalFaturado = new double[12];
        this.totalVendas = new int[12];
    }

    /**
     * Construtor parametrizado do ProdutosCliente
     * @param productID         String com Código de Produto
     * @param totalUnidades     Array com o total de unidades por mês
     * @param totalFaturado     Array com o total faturado por mês
     * @param totalVendas       Array com o total de vendas por mês
     */
    public ProdutosCliente (String productID, int[] totalUnidades, double[] totalFaturado, int totalVendas[])
    {
        this.productID     = productID;
        this.totalUnidades = Arrays.copyOf(totalUnidades, totalUnidades.length);
        this.totalFaturado = Arrays.copyOf(totalFaturado, totalFaturado.length);
        this.totalVendas   = Arrays.copyOf(totalVendas,   totalVendas.length);
    }

    /**
     * Construtor de cópia do ProdutosCliente
     * @param produtosCliente       ProdutosCliente a copiar
     */
    public ProdutosCliente (ProdutosCliente produtosCliente)
    {
        this.productID     = produtosCliente.getProductID();
        this.totalUnidades = produtosCliente.getArrayTotalUnidades();
        this.totalFaturado = produtosCliente.getArrayTotalFaturado();
        this.totalVendas   = produtosCliente.getArrayTotalVendas();
    }

    /**
     * Função que comparação Natural a ser usada pelo ProdutosCliente sempre
     * @param produtosCliente   ProdutosCliente ao qual queremos comparar o this
     * @return                  Inteiro que vai servir de comparação
     */
    public int compareTo(ProdutosCliente produtosCliente)
    {
        return this.productID.compareTo(produtosCliente.getProductID());
    }

    /**
     * Função que dá clone ao ProdutosCliente
     * @return           Cópia do ProdutosCliente
     */
    public ProdutosCliente clone ()
    {
        return new ProdutosCliente(this);
    }

    /**
     * Getter da String do Produto
     * @return       String com um código de Produto
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
     * Getter do array de total de unidades por mês
     * @return  Array de inteiros com total de unidades por mês
     */
    public int[] getArrayTotalUnidades()
    {
        return Arrays.copyOf(this.totalUnidades, this.totalUnidades.length);
    }

    /**
     * Setter do array de total de unidades por mês
     * @param totalUnidades     Array de inteiros com total de unidades por mês
     */
    public void setArrayTotalUnidades(int[] totalUnidades)
    {
        this.totalUnidades = Arrays.copyOf(totalUnidades, totalUnidades.length);
    }

    /**
     * Getter do total de unidades num dado mês
     * @param mes  Mês que pretendemos obter o numero de unidades
     * @return     Inteiro com o total de unidades compradas no mês pedido
     */
    public int getTotalUnidadesoMes (int mes) { return this.totalUnidades[mes-1]; }

    /**
     * Getter do total de unidades da totalidade do ano
     * @return  Inteiro com o total de unidades compradas no ano inteiro
     */
    public int getTotalUnidadesAno () {
        int total = 0;
        for (int mes = 0; mes < 12; mes ++) {
            total += this.totalUnidades[mes];
        }
        return total;
    }

    /**
     * Getter do array de total faturado por mês
     * @return  Array de Doubles com total faturado por mês
     */
    public double[] getArrayTotalFaturado()
    {
        return Arrays.copyOf(this.totalFaturado, this.totalFaturado.length);
    }

    /**
     * Setter do array de total faturado por mês
     * @param totalFaturado     Array de Doubles com total faturado por mês
     */
    public void setTotalFaturado(double[] totalFaturado)
    {
        this.totalFaturado = Arrays.copyOf(totalFaturado, totalFaturado.length);
    }

    /**
     * Getter do total de faturado num dado mês
     * @param mes  Mês que pretendemos obter o faturado
     * @return     Double com o total faturado no mês pedido
     */
    public double getTotalFaturadoMes (int mes) { return this.totalFaturado[mes-1]; }

    /**
     * Getter do total faturado na totalidade do ano
     * @return  Double com o total faturado no ano inteiro
     */
    public double getTotalFaturadoAno ()
    {
        double total = 0;
        for (int mes = 0; mes < 12; mes++) {
            total += this.totalFaturado[mes];
        }
        return total;
    }

    /**
     * Getter do array de total de vendas por mês
     * @return  Array de inteiros com total de vendas por mês
     */
    public int[] getArrayTotalVendas()
    {
        return Arrays.copyOf(this.totalVendas, this.totalVendas.length);
    }

    /**
     * Setter do array de total de vendas por mês
     * @param totalVendas     Array de inteiros com total de vendas por mês
     */
    public void setArrayTotalVendas(int[] totalVendas)
    {
        this.totalVendas = Arrays.copyOf(totalVendas, totalVendas.length);
    }

    /**
     * Getter do total de vendas num dado mês
     * @param mes  Mês que pretendemos obter o numero de Vendas
     * @return     Inteiro com o total de vendas no mês pedido
     */
    public int getTotalVendasMes (int mes) { return this.totalVendas[mes-1]; }


    /**
     * Função de equals do ProdutosCliente
     * @param o           Objeto ao qual queremos comparar o ProdutosCliente
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutosCliente pc = (ProdutosCliente) o;

        return this.productID.equals(pc.getProductID()) &&
                Arrays.equals(totalUnidades, pc.getArrayTotalUnidades() ) &&
                Arrays.equals(totalFaturado, pc.getArrayTotalFaturado() ) &&
                Arrays.equals(totalVendas,   pc.getArrayTotalVendas() );
    }

    /**
     * Função que transforma o ProdutosCliente numa String
     * @return           String resultante da função
     */
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        sb.append("ID de Produto='").append(this.productID).append('\'');

        sb.append("UNIDADES:");
        for (int i : this.totalUnidades)
            sb.append(i).append(":");

        sb.append("\nFATURADO:");
        for (double i : this.totalUnidades)
            sb.append(i).append(":");

        sb.append("\nNUMERO VENDAS:");
        for (int i : this.totalUnidades)
            sb.append(i).append(":");

        return sb.toString();
    }

    /**
     * Função que formula um HashCode de cada ProdutosCliente
     * @return Inteiro que é o código Hash do ProdutosCliente
     */
    public int hashCode()
    {
        int hash = 7;
        long aux;

        hash = 37 * hash + this.productID.hashCode();
        for (int x : this.totalUnidades)
            hash = 37 * hash + x;
        for (double x : this.totalFaturado) {
            aux = Double.doubleToLongBits(x);
            hash = 37 * hash + (int)(aux ^ (aux >>> 32));
        }
        for (int x : this.totalVendas)
            hash = 37 * hash + x;

        return hash;
    }


    /**
     * Função que dá o total de unidades no ano inteiro
     * @return  Inteiro com o total de Unidades
     */
    public int getTotalUnidades()
    {
        return Arrays.stream(this.totalUnidades).sum();
    }

    /**
     * Função que dá o total faturado no ano inteiro
     * @return  Double com o total faturado
     */
    public double getTotalFaturado()
    {
        return Arrays.stream(this.totalFaturado).sum();
    }


    /**
     * Funcao que insere uma venda no ProdutosCliente
     * @param preco         Preco unitario da compra
     * @param quantidade    Quantidade da compra
     * @param mes           Mês da Compra
     */
    public void insereVenda(double preco, int quantidade, int mes)
    {
        this.totalUnidades[mes-1] += quantidade;
        this.totalFaturado[mes-1] += preco * quantidade;
        this.totalVendas[mes-1] ++;
    }

    /**
     * Funçao que verifica se o Produto em estudo foi comprado num dado mes
     * @param mes   Mês a estudar
     * @return      Booleano que mostra se produto foi comprado pelo cliente no mês dado ou não
     */
    public boolean produtoFoiCompradoMes (int mes) {
        return (this.getTotalVendasMes(mes) > 0);
    }

    /***** QUERY 3 *****/
    /**
     * Funcao auxiliar da Query3
     * @param lista                     Lista de Lista de doubles onde vamos guardar os resultados pretendidos pela querie
     * @param listaProdsNaoRepetidos    Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getComprasProdsGastoMes (List<List<Double>> lista, List<Set<String>> listaProdsNaoRepetidos) {
        for (int mes = 0; mes < 12 ; mes++) {
            if (this.getTotalVendasMes(mes+1) > 0) {

                Set<String> compara = listaProdsNaoRepetidos.get(mes);
                if (!compara.contains(this.getProductID())) {
                    compara.add(this.getProductID());
                    lista.get(0).set(mes, (lista.get(0).get(mes) + 1));
                }

                lista.get(1).set(mes, (lista.get(1).get(mes) + this.getTotalVendasMes(mes+1)));
                lista.get(2).set(mes, (lista.get(2).get(mes) + this.getTotalFaturadoMes(mes+1)));
            }
        }
    }

    /***** QUERY 4 *****/
    /**
     * Funcao auxiliar da Query4
     * @param lista                     Lista de Lista de doubles onde vamos guardar os resultados pretendidos pela querie
     * @param clientID                  Código de Clietne que já comprou o Produto em estudo
     * @param listaProdsNaoRepetidos    Lista com Set de Strings que vamos preencher com String de códigos de Clientes da filial em estudo que compraram
     */
    public void getComprasClientesGastoMes (List<List<Double>> lista, List<Set<String>> listaProdsNaoRepetidos, String clientID) {
        for (int mes = 0; mes < 12 ; mes++) {
            if (this.getTotalVendasMes(mes+1) > 0) {

                Set<String> compara = listaProdsNaoRepetidos.get(mes);
                if (!compara.contains(clientID)) {
                    compara.add(clientID);
                    lista.get(0).set(mes, (lista.get(0).get(mes) + 1));
                }

                lista.get(1).set(mes, (lista.get(1).get(mes) + this.getTotalVendasMes(mes+1)));
                lista.get(2).set(mes, (lista.get(2).get(mes) + this.getTotalFaturadoMes(mes+1)));

            }
        }
    }

    /***** QUERY 5 *****/
    /**
     * Funcao auxiliar da Query5
     * @param mapa                     Lista de Pares String Integer onde vmaos colocando os resultados pretendidos pela querie
     */
    public void getProdsClienteMaisComprados (List<ParStringInteger> mapa) {
        if (!mapa.contains(this.getProductID()) ) {
            mapa.add(new ParStringInteger(this.getProductID()));
        }
        int index = indexOfElement(mapa, this.getProductID());
        //int index = mapa.indexOf(this.getProductID());
        mapa.get(index).setInteiroSomaComAntigo(this.getTotalUnidadesAno());
    }

    /**
     * Funçao auxiliar que permite receber o index do par de uma Lista de Par String Integer que contenha a String como código de produto dado
     * @param lista     Lista de Par String Integer a estudar
     * @param produto   Código d eproduto que pertendemos estudar
     * @return          Index do produto na lista de pares, caso não encontre, retorna -1
     */
    public int indexOfElement (List<ParStringInteger> lista, String produto)
    {
        int pos = 0;
        for (ParStringInteger ele : lista) {
            if (produto.equals(ele.getStringKey())) {
                return pos;
            }
            pos ++;
        }
        return -1;
    }

    /***** QUERY 8 *****/
    /**
     * Funcao auxiliar da Query8
     * @param produtosCompradosPorCliente     Sets de Strings onde são colocados os códigos dos Produtos já comprados por um Cliente
     */
    public void getClientesCompraramMaisProdutos(Set<String> produtosCompradosPorCliente)
    {
        produtosCompradosPorCliente.add(this.getProductID());
    }

}