package Comparators;

import Structs.ParStringDouble;

import java.util.Comparator;

/**
 * Classe com Comparator de Pares String Double
 */
public class ComparatorStringDouble implements Comparator<ParStringDouble> {

    /**
     * @brief              Função que compara dois Pares de String Double.
     * @param e1           ParStringDouble
     * @param e2           ParStringDouble
     * @return             Inteiro que vai servir de comparação, como nas funções gerais de compare
     */
    @Override
    public int compare(ParStringDouble e1, ParStringDouble e2) {
        double compara = e2.getInteiro() - e1.getInteiro();
        if (compara == 0) {
            return e1.getStringKey().compareTo(e2.getStringKey());
        }
        else if (compara > 0) return 1;
        else return -1;
    }
}
