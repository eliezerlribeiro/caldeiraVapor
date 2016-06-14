
package caldeiraVapor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CaldeiraVapor extends Thread{
    Bomba[] arrayBombas;
    BotaoEmergencia botao;
    SensorNivelAgua sensorAgua;
    SensorVapor sensorVapor;
    ValvulaSaidaCaldeira valvula;
    int totalBombas;
    int temperatura;
    int capacidade;
    CaldeiraVapor(){
        totalBombas = 4;
        arrayBombas = new Bomba[totalBombas];
        for(int i =0 ; i < totalBombas; i++ ){
            arrayBombas[i] = new Bomba();
        }
        capacidade = 10000; //Litros
        temperatura = 100; //celsius
        botao = new BotaoEmergencia();
        sensorAgua = new SensorNivelAgua();
        sensorVapor = new SensorVapor();
        valvula = new ValvulaSaidaCaldeira();
    }
    
    public  void enchdendoCaldeira(){
        int somatorio=0;
       for(int i =0 ; i < totalBombas; i++ ){
            if(arrayBombas[i].getFlag()){
                somatorio += arrayBombas[i].getVasao();
            }
        }
        int b = sensorAgua.getNivel();
        System.out.println("Litros"+(somatorio+b));
        sensorAgua.setNivel(somatorio+b);
        CaldeiraVaporGui.alteraGui(somatorio+b);
    }
    
    public void run() {
        while(true){
            System.out.println("Enchendo");
            enchdendoCaldeira(); 
            System.out.println("enchi");
            if(sensorAgua.getNivel()>=capacidade){
                System.out.println("CaldeiraCheia>>" + sensorAgua.getNivel());
                break;
            }
            try {
                System.out.println("ThreadSLEEP");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("ThreadSLEEP");
            }
        }
    }
    
    
}
