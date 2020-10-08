package Utils;

import static java.lang.System.nanoTime;

/**
 * Classe Crono utilizada para medição de tempos
 */
public class Crono {

    private static long inicio = 0L;
    private static long fim = 0L;

    /**
     * Função que inicia a contagem de tempo
     */
    public static void start() {
        fim = 0L;
        inicio = nanoTime();
    }

    /**
     * Função que para a contagem de tempo e devolve o tempo decorrido
     * @return      Tempo decorrido desde o start até para a contagem de tempo
     */
    public static double stop() {
        fim = nanoTime();
        long elapsedTime = fim - inicio;

        return elapsedTime / 1.0E09; // segundos
    }

    /**
     * Função que para a contagem de tempo e devolve uma String com esse tempo
     * @return      String com o tempo deccorida desde o start até para a contagem de tempo
     */
    public static String getTime() {
        return "" + stop();
    }


    /**
     * Função que para a contagem de tempo e devolve uma String com esse tempo iniciada com Elapsed Time e que escreve um s ao fim do tempo para mostrar que são segundos
     * @return      String com esse tempo decorrido e iniciada com Elapsed Time, que escreve um s ao fim do tempo para mostrar que são segundos
     */
    public static String getTImeString() {
        return "Elapsed Time: " + getTime() + " s";
    }
}
