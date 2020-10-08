package Structs;

import java.util.Objects;

/**
 * Classe ParStringDouble
 */
public class ParStringDouble {
    private String stringKey;
    private double inteiro;

    /**
     * Construtor por omissão do ParStringDouble
     */
    public ParStringDouble() {
        this.stringKey = "";
        this.inteiro = 0.0;
    }

    /**
     * Construtor de cópia do ParStringDouble
     * @param par           ParStringDouble a copiar
     */
    public ParStringDouble(ParStringDouble par) {
        this.stringKey = par.getStringKey();
        this.inteiro = par.getInteiro();
    }

    /**
     * Construtor parametrizado do ParStringDouble
     * @param stringKey     String que pretendemos colocar
     * @param inteiro       Double que pretendemos colocar
     */
    public ParStringDouble(String stringKey, double inteiro) {
        this.stringKey = stringKey;
        this.inteiro = inteiro;
    }

    /**
     * Construtor parametrizado do NodoFatura
     * @param stringKey     String que pretendemos colocar
     */
    public ParStringDouble(String stringKey) {
        this.stringKey = stringKey;
        this.inteiro = 0.0;
    }

    /**
     * Getter da String do ParStringDouble
     * @return           String obtida
     */
    public String getStringKey() {
        return stringKey;
    }

    /**
     * Getter do Integer do ParStringDouble
     * @return           Double obtido
     */
    public double getInteiro() {
        return inteiro;
    }

    /**
     * Setter da String do ParStringDouble
     * @param stringKey     String que pretendemos colocar no ParStringDouble
     */
    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    /**
     * Setter do Double do ParStringDouble
     * @param inteiro     Double que pretendemos colocar no ParStringDouble
     */
    public void setInteiro(double inteiro) {
        this.inteiro = inteiro;
    }

    /**
     * Função que encrementa ao Double do Par o inteiro
     * @param inteiro     Double que pretendemos encrementar no ParStringDouble
     */
    public void setInteiroSomaComAntigo(double inteiro) {
        this.inteiro = this.inteiro + inteiro;
    }

    /**
     * Função que dá clone ao ParStringDouble
     * @return           Cópia do ParStringDouble
     */
    public ParStringDouble clone()
    {
        return new ParStringDouble(this);
    }


    /**
     * Função de equals do ParStringDouble
     * @param o           Objeto ao qual queremos comparar o ParStringDouble
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParStringDouble that = (ParStringDouble) o;
        return //inteiro == that.inteiro &&
                Objects.equals(stringKey, that.stringKey);
    }

    /**
     * Função que transforma o ParStringDouble numa String
     * @return           String resultante da função
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ParStringDouble {");
        sb.append(" String = ").append(stringKey);
        sb.append(", Inteiro = ").append(inteiro);
        sb.append(" }");
        return sb.toString();
    }
}
