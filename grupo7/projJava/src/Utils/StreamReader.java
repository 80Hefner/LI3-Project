package Utils;

import Models.CatClientes.Cliente;
import Models.CatProdutos.Produto;
import Models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe StreamReader responsável pela leitura dos ficheiros
 */
public class StreamReader
{
    private String file_path;

    /**
     * Construtor por omissão do StreamReader
     */
    public StreamReader()
    {
        this.file_path = "";
    }

    /**
     * Construtor parametrizado do StreamReader
     * @param path      Path onde se encontram os ficheiros
     */
    public StreamReader(String path)
    {
        this.file_path = path;
    }

    /**
     * Construtor de cópia do StreamReader
     * @param sr    StreamReader a copiar
     */
    public StreamReader(StreamReader sr)
    {
        this.file_path = sr.getFilePath();
    }

    /**
     * Função que dá o path dos ficheiros
     * @return      String com o path dos files
     */
    public String getFilePath()
    {
        return file_path;
    }

    /**
     * Função que dá set ao path dos ficheiros
     * @param path       String com o path dos files a introduzir
     */
    public void setFilePath(String path)
    {
        this.file_path = path;
    }

    /**
     * Função de equals da StreamReader
     * @param o           Objeto ao qual queremos comparar a StreamReader
     */
    public boolean equals (Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        StreamReader sr = (StreamReader) o;

        return this.file_path.equals(sr.getFilePath());
    }

    /**
     * Função que transforma o StreamReader numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("File path: ").append(this.file_path);

        return sb.toString();
    }

    /**
     * Função que dá clone ao StreamReader
     * @return           Cópia do StreamReader
     */
    public StreamReader clone()
    {
        return new StreamReader(this);
    }

    /**
     * Função que dá parse do file do path e introduz os dados na GestVendas
     * @param gestVendas        GestVendas no qual se introduz os dados
     */
    public void parseConfig(IGestVendas gestVendas)
    {
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            line = br.readLine();
            line = line.split(":")[1];
            if (line.equals("DEFAULT")) gestVendas.setProdutos_src(System.getProperty("user.dir") + "/Dados/Produtos.txt");
            else gestVendas.setProdutos_src(line);

            line = br.readLine();
            line = line.split(":")[1];
            if (line.equals("DEFAULT")) gestVendas.setClientes_src(System.getProperty("user.dir") + "/Dados/Clientes.txt");
            else gestVendas.setClientes_src(line);

            line = br.readLine();
            line = line.split(":")[1];
            if (line.equals("DEFAULT")) gestVendas.setVendas_src(System.getProperty("user.dir") + "/Dados/Vendas_1M.txt");
            else gestVendas.setVendas_src(line);

            line = br.readLine();
            line = line.split(":")[1];
            gestVendas.setNr_filiais(Integer.parseInt(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Função que dá parse do file do path dos Produtos e introduz os dados na GestVendas
     * @param gestVendas        GestVendas no qual se vão buscar e introduzir dados
     */
    public void parseProdutos(IGestVendas gestVendas)
    {
        String line;
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            while ((line = br.readLine()) != null) {
                if (parseProduto(line, gestVendas))
                    count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gestVendas.setNr_produtos(gestVendas.getCatProdutos().getCatProdutos().size());
    }

    /**
     * Função que dá parse a um dado Produto e introduz os dados na GestVendas
     * @param produto           String com o código do Produto ao qual estamos a dar Parsing
     * @param gestVendas        GestVendas no qual se vão buscar e introduzir dados
     * @return                  Booleano que indica se Produto era válido ou inválido
     */
    private boolean parseProduto(String produto, IGestVendas gestVendas)
    {
        char letra1 = produto.charAt(0);
        char letra2 = produto.charAt(1);
        int codigo = 0;

        try {
            codigo = Integer.parseInt(produto.substring(2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (letra1 >= 'A' && letra1 <= 'Z' &&
                letra2 >= 'A' && letra2 <= 'Z' &&
                codigo >= 1000 && codigo <= 9999)
        {
            gestVendas.insereProduto(new Produto(produto));
            return true;
        }

        return false;
    }

    /**
     * Função que dá parse do file do path dos Clientes e introduz os dados na GestVendas
     * @param gestVendas        GestVendas no qual se vão buscar e introduzir dados
     */
    public void parseClientes(IGestVendas gestVendas)
    {
        String line;
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            while ((line = br.readLine()) != null) {
                if (parseCliente(line, gestVendas))
                    count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gestVendas.setNr_clientes(gestVendas.getCatClientes().getCatClientes().size());
    }

    /**
     * Função que dá parse a um dado Cliente e introduz os dados na GestVendas
     * @param cliente           String com o código do Clietne ao qual estamos a dar Parsing
     * @param gestVendas        GestVendas no qual se vão buscar e introduzir dados
     * @return                  Booleano que indica se Cliente era válido ou inválido
     */
    private boolean parseCliente(String cliente, IGestVendas gestVendas)
    {
        char letra = cliente.charAt(0);
        int codigo = 0;

        try {
            codigo = Integer.parseInt(cliente.substring(1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (letra >= 'A' && letra <= 'Z' && codigo >= 1000 && codigo <= 9999) {
            gestVendas.insereCliente(new Cliente(cliente));
            return true;
        }

        return false;
    }

    /**
     * Função que dá parse do file do path das Vendas e introduz os dados na GestVendas
     * @param gestVendas        GestVendas no qual se vão buscar e introduzir dados
     */
    public void parseVendas(IGestVendas gestVendas)
    {
        String line;
        int count = 0;
        int countTotal = 0;
        gestVendas.initFaturacao(gestVendas.getNr_filiais());
        gestVendas.initFilial();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            while ((line = br.readLine()) != null) {
                if (parseVenda(line, gestVendas))
                    count++;
                countTotal++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gestVendas.setNr_vendas(gestVendas.getNr_vendas() + count);
        gestVendas.setNr_vendas_totais(gestVendas.getNr_vendas_totais() + countTotal);
    }

    /**
     * Função que dá parse a uma dada Venda e introduz os dados na GestVendas
     * @param venda_str         String com o os dados de uma dada Venda á qual estamos a dar Parsing
     * @param gestVendas        GestVendas no qual se vão buscar e introduzir dados
     * @return                  Booleano que indica se Venda era válida ou inválida
     */
    private boolean parseVenda(String venda_str, IGestVendas gestVendas)
    {
        String[] tokens = venda_str.split(" ");
        Venda venda = new Venda(tokens);

        if (venda.isValid(gestVendas)) {
            gestVendas.insereFaturacao(venda);
            gestVendas.insereNaFilial(venda);

            if (venda.getPreco() == 0.00) {
                gestVendas.setNr_vendas_valor0(gestVendas.getNr_vendas_valor0() + 1);
            }

            return true;
        }

        return false;
    }

    public void parseVendas(IGestVendas gestVendas, int mapInt, int listInt)
    {
        String line;
        int count = 0;
        int countTotal = 0;
        gestVendas.initFaturacao(gestVendas.getNr_filiais(), mapInt, listInt);
        gestVendas.initFilial(mapInt);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            while ((line = br.readLine()) != null) {
                if (parseVenda(line, gestVendas, mapInt, listInt))
                    count++;
                countTotal++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gestVendas.setNr_vendas(gestVendas.getNr_vendas() + count);
        gestVendas.setNr_vendas_totais(gestVendas.getNr_vendas_totais() + countTotal);
    }

    private boolean parseVenda(String venda_str, IGestVendas gestVendas, int mapInt, int listInt)
    {
        String[] tokens = venda_str.split(" ");
        Venda venda = new Venda(tokens);

        if (venda.isValid(gestVendas)) {
            gestVendas.insereFaturacao(venda, listInt);
            gestVendas.insereNaFilial(venda, mapInt);

            if (venda.getPreco() == 0.00) {
                gestVendas.setNr_vendas_valor0(gestVendas.getNr_vendas_valor0() + 1);
            }

            return true;
        }

        return false;
    }

//-------------------------------TEST-------------------------------//

    public static int MODO_BUFFERED_READER = 0, MODO_FILES = 1;

    /**
     * Função auxiliar para os testes de performance
     * @param leitura       Booleano que mostra se função deve fazer leitura ou não
     * @param parsing       Booleano que mostra se função deve fazer parsing ou não
     * @param validacao     Booleano que mostra se função deve fazer validaçao de dados ou não
     * @param gestVendas    GestVendas no qual se vão buscar e introduzir dados
     * @param modo          Modo que queremos que sejam lidos os ficheiros
     * @return              Tempo decorrido durante o teste
     */
    public double test(boolean leitura, boolean parsing, boolean validacao, IGestVendas gestVendas, int modo)
    {
        List<String> lines = new ArrayList<>();
        List<Venda> parsed_vendas;
        List<Venda> validated_vendas;
        String line;
        double elapsed_time;

        Crono.start();
        if (leitura) {
            if (modo == 0) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file_path));
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (modo == 1){
                try {
                    lines = Files.readAllLines(Paths.get(file_path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (parsing) {
                parsed_vendas = lines.stream()
                                 .map(l -> new Venda(l.split(" ")))
                                 .collect(Collectors.toList());

                if (validacao) {
                    validated_vendas = parsed_vendas.stream()
                                        .filter(v -> v.isValid(gestVendas))
                                        .collect(Collectors.toList());
                }
            }
        }
        elapsed_time = Crono.stop();

        return elapsed_time;
    }


}
