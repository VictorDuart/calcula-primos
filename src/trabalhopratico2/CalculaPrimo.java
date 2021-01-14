package trabalhopratico2;

import java.util.LinkedList;
import java.util.List;

//classe manipulada pela thread que encontra os numeros primos dado um intervalo
public class CalculaPrimo extends Thread{
    
    private int inicial;
    private int termino;
    //lista para os numeros primos
    private List primos = new LinkedList();

    //a classe recebe os limites do intervalo 
    public CalculaPrimo(int inicio, int fim){
        this.inicial = inicio;
        this.termino = fim;
    }
    
    //método run que cada thread executa
    public void run(){
        retornaPrimos();
    } 
    
    
    //método que lista todos os valores primos dentro de um intervalo
    public void retornaPrimos(){
        for(int i = this.inicial; i <= this.termino; i++){
            if(ehPrimo(i) == true){
                primos.add(i);
            }
        }
    }
    
    //método que verifica se um numero é primo
    private boolean ehPrimo(int numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0)
                return false;   
        }      
        return true;
    }
    
    public List getPrimos() {
        return primos;
    }

    public void setPrimos(List primos) {
        this.primos = primos;
    }
    
}
