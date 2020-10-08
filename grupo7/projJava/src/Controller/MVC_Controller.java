package Controller;

import Models.*;
import NewExceptions.*;
import Structs.*;
import Utils.*;
import View.*;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Classe que possui Controlador
 */
public class MVC_Controller
{
    private IGestVendas model;
    private MVC_View_MainMenu view;

    /**
     * Setter do Model do MVC (Modelo Vista Controlador)
     * @param model            Modelo que queremos implementar
     */
    public void setModel(IGestVendas model)
    {
        this.model = model;
    }

    /**
     * Setter da View do MVC (Modelo Vista Controlador)
     * @param view            View que queremos implementar
     */
    public void setView(MVC_View_MainMenu view)
    {
        this.view = view;
    }

    /**
     * Funçao que atribui as funções ás diferentes teclas
     */
    public void start()
    {
        view.getLoadButton().addActionListener(e -> loadFromFiles());
        view.getExitButton().addActionListener(e -> exitProgram());
        view.getQueryE1Button().addActionListener(e -> queryE1());
        view.getQueryE2Button().addActionListener(e -> queryE2());
        view.getQuery1Button().addActionListener(e -> query1());
        view.getQuery2Button().addActionListener(e -> query2());
        view.getQuery3Button().addActionListener(e -> query3());
        view.getQuery4Button().addActionListener(e -> query4());
        view.getQuery5Button().addActionListener(e -> query5());
        view.getQuery6Button().addActionListener(e -> query6());
        view.getQuery7Button().addActionListener(e -> query7());
        view.getQuery8Button().addActionListener(e -> query8());
        view.getQuery9Button().addActionListener(e -> query9());
        view.getQuery10Button().addActionListener(e -> query10());
        view.getLoadFromDiskButton().addActionListener(e -> loadFromDisk());
        view.getSaveToDiskButton().addActionListener(e -> saveToDisk());

        view.setQueryButtonsEnable(false);
        view.getSaveToDiskButton().setEnabled(false);
    }

    /**
     * Controlador responsável pela Query Estatística 1
     */
    public void queryE1()
    {
        double elapsed_time;

        Crono.start();
        Map.Entry<String, List<Integer>> resultado = model.querieEstatistica1();
        elapsed_time = Crono.stop();

        view.printQueryE1(resultado);
        view.print("Tempo para executar a Query Estatística 1: " + elapsed_time + " seg\n");
    }

    /**
     * Controlador responsável pela Query Estatística 2
     */
    public void queryE2()
    {
        double elapsed_time;

        Crono.start();
        List<Integer> resultado1 = model.getTotalComprasPorMes();
        List<List<Double>> resultado2 = model.getTotalFaturadoPorMes();
        List<List<Integer>> resultado3 = model.getTotalCompradoresPorMes();
        elapsed_time = Crono.stop();

        view.printQueryE2(resultado1, resultado2, resultado3);
        view.print("Tempo para executar a Query Estatistica 2: " + elapsed_time + " seg\n");
    }

    /**
     * Controlador responsável pela Query Interativa 1
     */
    public void query1()
    {
        double elapsed_time;

        Crono.start();
        List<String> resultado = model.getProdutosNuncaComprados();
        resultado.sort(null);
        elapsed_time = Crono.stop();
        view.printQuery1(resultado);
        view.print("Tempo para executar a Query 1: " + elapsed_time + " seg\n");
    }

    /**
     * Controlador responsável pela Query Interativa 2
     */
    public void query2()
    {
        double elapsed_time;

        try {
            int mes = Integer.parseInt(JOptionPane.showInputDialog("Mês que pretende estudar"));
            Crono.start();
            List<List<Integer>> resultado = model.numeroVendasEClientesMes(mes);
            elapsed_time = Crono.stop();
            view.printQuery2(resultado);
            view.print("Tempo para executar a Query 2: " + elapsed_time + " seg\n");
        } catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }

    /**
     * Controlador responsável pela Query Interativa 3
     */
    public void query3()
    {
        double elapsed_time;

        try {
            String cliente = JOptionPane.showInputDialog("Cliente que pretende estudar").toUpperCase();
            Crono.start();
            List<List<Double>> resultado = model.getComprasProdsGastoMes(cliente);
            elapsed_time = Crono.stop();
            view.printQuery3(resultado, cliente);
            view.print("Tempo para executar a Query 3: " + elapsed_time + " seg\n");
        } catch (ClienteInexistenteException e){
            view.printError(e.toString());
        }
    }

    /**
     * Controlador responsável pela Query Interativa 4
     */
    public void query4()
    {
        double elapsed_time;

        try {
            String produto = JOptionPane.showInputDialog("Produto que pretende estudar").toUpperCase();
            Crono.start();
            List<List<Double>> resultado = model.getComprasClientesGastoMes(produto);
            elapsed_time = Crono.stop();
            view.printQuery4(resultado, produto);
            view.print("Tempo para executar a Query 4: " + elapsed_time + " seg\n");
        } catch (ProdutoInexistenteException e){
            view.printError(e.toString());
        }
    }

    /**
     * Controlador responsável pela Query Interativa 5
     */
    public void query5()
    {
        double elapsed_time;

        try {
            String cliente = JOptionPane.showInputDialog("Cliente que pretende estudar").toUpperCase();
            Crono.start();
            List<ParStringInteger> resultado = model.getProdsClienteMaisComprados(cliente);
            elapsed_time = Crono.stop();
            view.printQuery5(resultado, cliente);
            view.print("Tempo para executar a Query 5: " + elapsed_time + " seg\n");
        } catch (ClienteInexistenteException e){
            view.printError(e.toString());
        }
    }

    /**
     * Controlador responsável pela Query Interativa 6
     */
    public void query6()
    {
        double elapsed_time;

        try {
            int limite = Integer.parseInt(JOptionPane.showInputDialog("Limite de Produtos que pretende estudar"));
            Crono.start();
            List<Map.Entry<ParStringInteger, Integer>> resultado = model.getProdsMaisCompradosTotal(limite);
            elapsed_time = Crono.stop();
            view.printQuery6(resultado, limite);
            view.print("Tempo para executar a Query 6: " + elapsed_time + " seg\n");
        } catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }

    /**
     * Controlador responsável pela Query Interativa 7
     */
    public void query7()
    {
        double elapsed_time;

        Crono.start();
        List<List<ParStringDouble>> resultado = model.tresMaioresCompradoresPorFilial();
        elapsed_time = Crono.stop();
        view.printQuery7(resultado);
        view.print("Tempo para executar a Query 7: " + elapsed_time + " seg\n");
    }

    /**
     * Controlador responsável pela Query Interativa 8
     */
    public void query8()
    {
        double elapsed_time;

        try {
            int limite = Integer.parseInt(JOptionPane.showInputDialog("Limite de Produtos que pretende estudar"));
            Crono.start();
            List<ParStringInteger> resultado = model.getClientesCompraramMaisProdutos(limite);
            elapsed_time = Crono.stop();
            view.printQuery8(resultado, limite);
            view.print("Tempo para executar a Query 8: " + elapsed_time + " seg\n");
        } catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }

    /**
     * Controlador responsável pela Query Interativa 9
     */
    public void query9()
    {
        double elapsed_time;

        try {
            int limite = Integer.parseInt(JOptionPane.showInputDialog("Limite de Produtos que pretende estudar"));
            String produto = JOptionPane.showInputDialog("Produto que pretende estudar").toUpperCase();
            Crono.start();
            List<ParStringDouble> resultado = model.getClientesMaisCompraramProduto(produto,limite);
            elapsed_time = Crono.stop();
            view.printQuery9(resultado, produto, limite);
            view.print("Tempo para executar a Query 8: " + elapsed_time + " seg\n");
        } catch (NumberFormatException e){
            view.printError("Valor inválido");
        } catch (ProdutoInexistenteException e){
            view.printError(e.toString());
        }
    }

    /**
     * Controlador responsável pela Query Interativa 10
     */
    public void query10()
    {
        double elapsed_time;

        Crono.start();
        //Map<String, List<List<Double>>> resultado = model.faturacaoTotalTodosProdutos();
        List<List<List<ParStringDouble>>> resultado = model.faturacaoTotalTodosProdutos();
        elapsed_time = Crono.stop();
        view.printQuery10(resultado);
        view.print("Tempo para executar a Query 10: " + elapsed_time + " seg\n");
    }

    /**
     * Controlador responsável pelo load de dados dos ficheiros para as estruturas de Dados
     */
    private void loadFromFiles()
    {
        double produtos_elapsed_time, clientes_elapsed_time, vendas_elapsed_time;

        StreamReader sr_produtos = new StreamReader(model.getProdutos_src());
        Crono.start();
        sr_produtos.parseProdutos(model);
        produtos_elapsed_time = Crono.stop();

        StreamReader sr_clientes = new StreamReader(model.getClientes_src());
        Crono.start();
        sr_clientes.parseClientes(model);
        clientes_elapsed_time = Crono.stop();

        StreamReader sr_vendas = new StreamReader(model.getVendas_src());
        Crono.start();
        sr_vendas.parseVendas(model);
        vendas_elapsed_time = Crono.stop();

        view.print("Tempo para guardar os produtos: " + produtos_elapsed_time + " seg\n"
                         + "Tempo para guardar os clientes: " + clientes_elapsed_time + " seg\n"
                         + "Tempo para guardar as vendas: " + vendas_elapsed_time + " seg\n"
                         + "\nProdutos válidos: " + model.getNr_produtos()
                         + "\nClientes válidos: " + model.getNr_clientes()
                         + "\nVendas válidas: " + model.getNr_vendas());

        view.setQueryButtonsEnable(true);
        view.setLoadedStatus(true);
    }

    private void loadFromDisk()
    {
        IGestVendas new_gestVendas = null;
        String filename = JOptionPane.showInputDialog("Nome do ficheiro de onde quer ler (*.dat)");

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".dat"));
            new_gestVendas = (IGestVendas) in.readObject();
            this.model = new_gestVendas;
            view.setQueryButtonsEnable(true);
            view.setLoadedStatus(true);
            view.print("Model carregado com sucesso");
        } catch (IOException e) {
            view.printError("Impossível carregar ficheiro");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToDisk()
    {
        String filename = JOptionPane.showInputDialog("Nome do ficheiro onde quer guardar (*.dat)");

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + ".dat"));
            out.writeObject(this.model);
            view.print("Model guardado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Controlador responsável por sair do programa
     */
    private void exitProgram()
    {
        System.exit(0);
    }
}
