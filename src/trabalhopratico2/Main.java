package trabalhopratico2;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class TrabalhoPratico2 {


    public static void main(String[] args){
        
        Scanner ler = new Scanner(System.in);
        
        //numero de threads e limite do intervalo [1, NUM]
        int numThread;
        int NUM;
        
        //lista de numeros primos à serem escritos
        List listaPrimos = new LinkedList();
        
        System.out.println("Insira o limite do intervalo de verificação de números primos: ");
        NUM = ler.nextInt();
        
        System.out.println("Insira a quantidade de threads que executarão o calculo: ");
        numThread = ler.nextInt();
        
        //variáveis que realizam a divisão dos subintervalos
        int divideCalculo = NUM/numThread;
        int modDivisao = NUM%numThread;
        
        //instancia uma lista de objetos CalculaPrimo, que será a quantidade de threads
        //que operam sobre o intervalo de números
        CalculaPrimo[] calcula = new CalculaPrimo[numThread];

        //variáveis auxiliares que ajudam na divisão dos subintervalos entre as threads
        int aux = 1;
        int aux2 = divideCalculo;
  
        int j = 1;
        
        //laço para instanciar cada objeto calcula
        for(int i = 0; i < numThread; i++){
            if(j == numThread){
                //ultima thread conterá sua parte + os valores que sobraram caso 
                //a divisão NUM/numThread nao seja um número inteiro
                calcula[i] = new CalculaPrimo(aux, (aux2 + modDivisao));
            }else{
                //threads que antecedem a ultima que contém as partes iguais
                calcula[i] = new CalculaPrimo(aux, aux2);
            }
            aux = aux + divideCalculo;
            aux2= aux2 + divideCalculo;
            j++;
        }
        
        for(int i = 0; i < numThread; i++){
            //inicialização das threads
            calcula[i].start();
        }     
        
        for(int i = 0; i < numThread; i++){
            try {
                //método join utilizado para que as threads que encontram os numeros primos
                //executem todas antes de escrever no arquivo
                calcula[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrabalhoPratico2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(int i = 0; i < numThread; i++){
            //é necessário reunir em uma lista (listaPrimos) todas as listas com numeros 
            //primos encontrados nas threads
            listaPrimos.add(calcula[i].getPrimos());
        }
        
        //thread que realiza a gravação da lista de numeros primos no arquivo txt
        EscrevePrimos escreve = new EscrevePrimos(listaPrimos);
        
        escreve.start();
 
    }
    
}
