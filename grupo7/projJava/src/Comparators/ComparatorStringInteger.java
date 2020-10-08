package Comparators;

import Structs.ParStringInteger;

import java.util.Comparator;

/**
 * Classe com Comparator de Pares String Integer
 */
public class ComparatorStringInteger implements Comparator<ParStringInteger> {

    /**
     * @brief              Função que compara dois Pares de String Integer.
     * @param e1           ParStringInteger
     * @param e2           ParStringInteger
     * @return             Inteiro que vai servir de comparação, como nas funções gerais de compare
     */
    @Override
    public int compare(ParStringInteger e1, ParStringInteger e2) {
        int compara = e2.getInteiro() - e1.getInteiro();
        if (compara == 0) {
            return e1.getStringKey().compareTo(e2.getStringKey());
        }
        else return compara;
    }
}
