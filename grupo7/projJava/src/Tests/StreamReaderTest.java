package Tests;

import Models.GestVendas;
import Models.IGestVendas;
import Utils.StreamReader;

import java.text.DecimalFormat;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StreamReaderTest
{
    private static final int REPS = 5;
    private static final long SLEEP_TIME = 2000;
    private static BufferedWriter writer;

    public static void main(String[] args)
    {
        IGestVendas gestVendas = new GestVendas();
        List<Double> tempos_leitura_buffered_reader = new ArrayList<>();
        List<Double> tempos_leitura_files = new ArrayList<>();
        List<Double> tempos_parsing_buffered_reader = new ArrayList<>();
        List<Double> tempos_parsing_files = new ArrayList<>();
        List<Double> tempos_validacao_buffered_reader = new ArrayList<>();
        List<Double> tempos_validacao_files = new ArrayList<>();
        double tempo_leitura_buffered_reader;
        double tempo_leitura_files;
        double tempo_parsing_buffered_reader;
        double tempo_parsing_files;
        double tempo_validacao_buffered_reader;
        double tempo_validacao_files;
        System.out.println("[DEBUG] arrays created");

        //-----------PARSE CONFIG------------//
        Path cfg_path = Paths.get(System.getProperty("user.dir") + "/config.cfg");
        if (!Files.isReadable(cfg_path)) System.exit(0);
        StreamReader sr_config = new StreamReader(cfg_path.toString());
        sr_config.parseConfig(gestVendas);
        System.out.println("[DEBUG] config parsed");

        //----------STREAM READERS----------//
        StreamReader sr_produtos = new StreamReader(gestVendas.getProdutos_src());
        sr_produtos.parseProdutos(gestVendas);
        StreamReader sr_clientes = new StreamReader(gestVendas.getClientes_src());
        sr_clientes.parseClientes(gestVendas);
        StreamReader sr_vendas = new StreamReader(gestVendas.getVendas_src());

        //----------STREAM READS----------//
        for (int i = 0; i < REPS; i++) {
            try {
                tempos_leitura_buffered_reader.add(sr_vendas.test(true, false, false, gestVendas, StreamReader.MODO_BUFFERED_READER));
                Thread.sleep(SLEEP_TIME);
                tempos_leitura_files.add(sr_vendas.test(true, false, false, gestVendas, StreamReader.MODO_FILES));
                Thread.sleep(SLEEP_TIME);
                tempos_parsing_buffered_reader.add(sr_vendas.test(true, true, false, gestVendas, StreamReader.MODO_BUFFERED_READER));
                Thread.sleep(SLEEP_TIME);
                tempos_parsing_files.add(sr_vendas.test(true, true, false, gestVendas, StreamReader.MODO_FILES));
                Thread.sleep(SLEEP_TIME);

                tempos_validacao_buffered_reader.add(sr_vendas.test(true, true, true, gestVendas, StreamReader.MODO_BUFFERED_READER));
                Thread.sleep(SLEEP_TIME);
                tempos_validacao_files.add(sr_vendas.test(true, true, true, gestVendas, StreamReader.MODO_FILES));
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[DEBUG] doing work...");
        }

        //----------MEDIA DOS RESULTADOS----------//
        tempo_leitura_buffered_reader = tempos_leitura_buffered_reader.stream().reduce(0.0, Double::sum) / REPS;
        tempo_leitura_files = tempos_leitura_files.stream().reduce(0.0, Double::sum) / REPS;
        tempo_parsing_buffered_reader = tempos_parsing_buffered_reader.stream().reduce(0.0, Double::sum) / REPS;
        tempo_parsing_files = tempos_parsing_files.stream().reduce(0.0, Double::sum) / REPS;
        tempo_validacao_buffered_reader = tempos_validacao_buffered_reader.stream().reduce(0.0, Double::sum) / REPS;
        tempo_validacao_files = tempos_validacao_files.stream().reduce(0.0, Double::sum) / REPS;

        //----------PRINTS----------//
        try {
            StreamReaderTest.writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/StreamReaderTest_result.txt"));
            DecimalFormat fmt = new DecimalFormat("0.000");

            writer.write("----------------RESULTADOS----------------");
            writer.write("\nLEITURA\n");
            writer.write("\tBUFFERED READER: " + fmt.format(tempo_leitura_buffered_reader*1000) + " mseg\n");
            writer.write("\tFILES: " + fmt.format(tempo_leitura_files*1000) + " mseg\n");

            writer.write("\nLEITURA + PARSING\n");
            writer.write("\tBUFFERED READER: " + fmt.format(tempo_parsing_buffered_reader*1000) + " mseg\n");
            writer.write("\tFILES: " + fmt.format(tempo_parsing_files*1000) + " mseg\n");

            writer.write("\nLEITURA + PARSING + VALIDAÇÃO\n");
            writer.write("\tBUFFERED READER: " + fmt.format(tempo_validacao_buffered_reader*1000) + " mseg\n");
            writer.write("\tFILES: " + fmt.format(tempo_validacao_files*1000) + " mseg\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
