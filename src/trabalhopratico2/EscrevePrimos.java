package trabalhopratico2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//classe que realizará a escrita no arquivo
public class EscrevePrimos extends Thread{
    private List lista_primos = new LinkedList();
    
    //recebe a lista de numeros primos à ser escrita no arquivo
    public EscrevePrimos(List primos){
        this.lista_primos = primos;
    }
    
    public void escreve() throws IOException{
        //abre o arquivo de texto pra escrita
        FileWriter arq = new FileWriter("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\testes\\src\\trabalhopratico2\\primos.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        //realiza a gravação da lista de numeros primos no arquivo
        gravarArq.print(lista_primos);
        
        arq.close();
        
    }
    
    public void run(){
        try {
            escreve();
            //System.out.println(this.lista_primos);
        } catch (IOException ex) {
            Logger.getLogger(EscrevePrimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
