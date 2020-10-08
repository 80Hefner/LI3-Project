package Tests;

import Models.*;
import NewExceptions.*;
import Utils.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.DecimalFormat;

public class GestVendasTest_List
{
    public static final int HASH_MAP = 1, TREE_MAP = 2;
    public static final int HASH_SET = 3, LINKED_HASH_SET = 4, TREE_SET = 5;
    public static final int VECTOR = 6, ARRAY_LIST = 7;
    private static final int REPS = 5;
    private static final int QUERY1 = 0, QUERY2 = 1, QUERY3 = 2, QUERY4 = 3, QUERY5 = 4, QUERY6 = 5, QUERY7 = 6, QUERY8 = 7, QUERY9 = 8, QUERY10 = 9, QUERY_E1 = 10, QUERY_E2 = 11;
    private static final int LOAD_PRODUTOS = 0, LOAD_CLIENTES = 1, LOAD_VENDAS = 2;
    private static final String PRODUCT_ID_TEST = "AF1184", CLIENT_ID_TEST = "Z5000";
    private static final int LIMIT_TEST = 100, MES_TEST = 1;
    private static final long SLEEP_TIME = 2000;
    private static BufferedWriter writer;

    public static void main(String[] args)
    {
        //----------------TESTES RELATIVOS AS LISTS-----------------""

        //----------CRIAÇÃO DOS GESTVENDAS----------//
        IGestVendas gestVendas_Vector = new GestVendas();
        IGestVendas gestVendas_ArrayList = new GestVendas();
        System.out.println(("GEST VENDAS VECTOR: " + gestVendas_Vector.hashCode()));
        System.out.println(("GEST VENDAS ARRAY_LIST: " + gestVendas_ArrayList.hashCode()));
        System.out.println(("------------------------------------------\n"));


        //----------ARRAYS DE RESULTADOS----------//
        List<Double> tempos_load_Vector = new ArrayList<>(3);
        List<Double> tempos_load_ArrayList = new ArrayList<>(3);
        List<List<Double>> tempos_queries_Vector = new ArrayList<>(12);
        List<List<Double>> tempos_queries_ArrayList = new ArrayList<>(12);
        List<Double> tempos_finais_queries_Vector = new ArrayList<>(12);
        List<Double> tempos_finais_queries_ArrayList = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            tempos_queries_Vector.add(new ArrayList<>(REPS));
            tempos_queries_ArrayList.add(new ArrayList<>(REPS));
        }
        System.out.println("[DEBUG] arrays created");

        //-----------PARSE CONFIG------------//
        Path cfg_path = Paths.get(System.getProperty("user.dir") + "/config.cfg");
        if (!Files.isReadable(cfg_path)) System.exit(0);
        StreamReader sr_config = new StreamReader(cfg_path.toString());
        sr_config.parseConfig(gestVendas_Vector);
        sr_config.parseConfig(gestVendas_ArrayList);
        System.out.println("[DEBUG] configs parsed");

        //----------STREAM READERS----------//
        StreamReader sr_produtos = new StreamReader(gestVendas_Vector.getProdutos_src());
        StreamReader sr_clientes = new StreamReader(gestVendas_Vector.getClientes_src());
        StreamReader sr_vendas = new StreamReader(gestVendas_Vector.getVendas_src());

        //----------LOADS----------//
        try {
            runLoad(tempos_load_Vector, gestVendas_Vector, sr_produtos, sr_clientes, sr_vendas, VECTOR);
            runLoad(tempos_load_ArrayList, gestVendas_ArrayList, sr_produtos, sr_clientes, sr_vendas, ARRAY_LIST);
            System.out.println("[DEBUG] loads done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //----------QUERIES----------//
        for (int i = 0; i < REPS; i++) {
            try {
                runQueries(tempos_queries_Vector, gestVendas_Vector);
                System.out.println("[DEBUG] ran queries for " + gestVendas_Vector.hashCode());
                runQueries(tempos_queries_ArrayList, gestVendas_ArrayList);
                System.out.println("[DEBUG] ran queries for " + gestVendas_ArrayList.hashCode());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //----------MEDIA DOS RESULTADOS DAS QUERIES----------//
        for (int i = 0; i < 12; i++) {
            tempos_finais_queries_Vector.add(tempos_queries_Vector.get(i).stream().reduce(0.0, Double::sum) / REPS);
            tempos_finais_queries_ArrayList.add(tempos_queries_ArrayList.get(i).stream().reduce(0.0, Double::sum) / REPS);
        }

        //----------PRINTS----------//
        try {
            GestVendasTest_List.writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/gestVendasTest_result_List.txt"));
            writer.write("--------RESULTADOS---------\n\n");
            printResultadosLoads(tempos_load_Vector, tempos_load_ArrayList);
            printResultadosQueries(tempos_finais_queries_Vector, tempos_finais_queries_ArrayList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------PRINTS----------------------------//

    public static void printResultadosLoads(List<Double> resultados_Vector, List<Double> resultados_ArrayList) throws IOException
    {
        for (int i = 0; i < 3; i++)
            printResultadosLoad(resultados_Vector, resultados_ArrayList, i);
    }

    public static void printResultadosLoad(List<Double> resultados_Vector, List<Double> resultados_ArrayList, int load) throws IOException
    {
        DecimalFormat fmt = new DecimalFormat("0.000");

        switch(load) {
            case LOAD_PRODUTOS: writer.write("LOAD PRODUTOS\n"); break;
            case LOAD_CLIENTES: writer.write("LOAD CLIENTES\n"); break;
            case LOAD_VENDAS: writer.write("LOAD VENDAS\n"); break;
        }

        writer.write("\t" + "VECTOR  " + fmt.format(resultados_Vector.get(load)*1000) + " mseg\n");
        writer.write("\t" + "ARRAY_LIST  " + fmt.format(resultados_ArrayList.get(load)*1000) + " mseg\n\n");
    }

    public static void printResultadosQueries(List<Double> resultados_Vector, List<Double> resultados_ArrayList) throws IOException
    {
        for (int i = 0; i < 12; i++)
            printResultadosQuery(resultados_Vector, resultados_ArrayList, i);
    }

    public static void printResultadosQuery(List<Double> resultados_Vector, List<Double> resultados_ArrayList, int query) throws IOException
    {
        DecimalFormat fmt = new DecimalFormat("0.00000");

        switch (query) {
            case QUERY1: writer.write("QUERY 1\n"); break;
            case QUERY2: writer.write("QUERY 2\n"); break;
            case QUERY3: writer.write("QUERY 3\n"); break;
            case QUERY4: writer.write("QUERY 4\n"); break;
            case QUERY5: writer.write("QUERY 5\n"); break;
            case QUERY6: writer.write("QUERY 6\n"); break;
            case QUERY7: writer.write("QUERY 7\n"); break;
            case QUERY8: writer.write("QUERY 8\n"); break;
            case QUERY9: writer.write("QUERY 9\n"); break;
            case QUERY10: writer.write("QUERY 10\n"); break;
            case QUERY_E1: writer.write("QUERY E1\n"); break;
            case QUERY_E2: writer.write("QUERY E2\n"); break;
        }

        writer.write("\t" + "VECTOR  " + fmt.format(resultados_Vector.get(query)*1000) + " mseg\n");
        writer.write("\t" + "ARRAY_LIST  " + fmt.format(resultados_ArrayList.get(query)*1000) + " mseg\n\n");
    }

    //----------------------------RUNS----------------------------//

    public static void runQueries(List<List<Double>> resultados, IGestVendas gestVendas) throws InterruptedException
    {
        double elapsed_time;

        Crono.start();
        gestVendas.getProdutosNuncaComprados();
        elapsed_time = Crono.stop();
        resultados.get(QUERY1).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.numeroVendasEClientesMes(MES_TEST);
        elapsed_time = Crono.stop();
        resultados.get(QUERY2).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        try {
            gestVendas.getComprasProdsGastoMes(CLIENT_ID_TEST);
        } catch (ClienteInexistenteException e) {
            e.printStackTrace();
        }
        elapsed_time = Crono.stop();
        resultados.get(QUERY3).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        try {
            gestVendas.getComprasClientesGastoMes(PRODUCT_ID_TEST);
        } catch (ProdutoInexistenteException e) {
            e.printStackTrace();
        }
        elapsed_time = Crono.stop();
        resultados.get(QUERY4).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        try {
            gestVendas.getProdsClienteMaisComprados(CLIENT_ID_TEST);
        } catch (ClienteInexistenteException e) {
            e.printStackTrace();
        }
        elapsed_time = Crono.stop();
        resultados.get(QUERY5).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.getProdsMaisCompradosTotal(LIMIT_TEST);
        elapsed_time = Crono.stop();
        resultados.get(QUERY6).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.tresMaioresCompradoresPorFilial();
        elapsed_time = Crono.stop();
        resultados.get(QUERY7).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.getClientesCompraramMaisProdutos(LIMIT_TEST);
        elapsed_time = Crono.stop();
        resultados.get(QUERY8).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        try {
            gestVendas.getClientesMaisCompraramProduto(PRODUCT_ID_TEST, LIMIT_TEST);
        } catch (ProdutoInexistenteException e) {
            e.printStackTrace();
        }
        elapsed_time = Crono.stop();
        resultados.get(QUERY9).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.faturacaoTotalTodosProdutos();
        elapsed_time = Crono.stop();
        resultados.get(QUERY10).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.querieEstatistica1();
        elapsed_time = Crono.stop();
        resultados.get(QUERY_E1).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        gestVendas.getTotalComprasPorMes();
        gestVendas.getTotalFaturadoPorMes();
        gestVendas.getTotalCompradoresPorMes();
        elapsed_time = Crono.stop();
        resultados.get(QUERY_E2).add(elapsed_time);
        Thread.sleep(SLEEP_TIME);
    }

    public static void runLoad(List<Double> resultados, IGestVendas gestVendas, StreamReader sr_produtos, StreamReader sr_clientes, StreamReader sr_vendas, int listInt) throws InterruptedException
    {
        Crono.start();
        sr_produtos.parseProdutos(gestVendas);
        resultados.add(Crono.stop());
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        sr_clientes.parseClientes(gestVendas);
        resultados.add(Crono.stop());
        Thread.sleep(SLEEP_TIME);

        Crono.start();
        sr_vendas.parseVendas(gestVendas, HASH_MAP, listInt);
        resultados.add(Crono.stop());
        Thread.sleep(SLEEP_TIME);
    }
}