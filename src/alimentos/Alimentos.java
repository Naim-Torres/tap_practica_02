package alimentos;
public class Alimentos {

    private String alimento; //Nombre del alimento
    private int tipo; // identifiacion del tipo de alimento

    public Alimentos (String alimento, int tipo){

        this.alimento = alimento;
        this.tipo = tipo;
    }
    public int tipo(){
        return tipo;
    }
    public String alimento(){
        return alimento;
    }
    @Override
    public String toString(){
        return alimento;
    }
    public boolean equals(Alimentos ali){
        return alimento.equals(ali.alimento) && tipo == ali.tipo;
    }
}