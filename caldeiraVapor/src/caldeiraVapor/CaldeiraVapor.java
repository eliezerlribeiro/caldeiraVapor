
package caldeiraVapor;

public class CaldeiraVapor {
    Bomba[] arrayBombas;
    BotaoEmergencia botao;
    SensorNivelAgua sensorAgua;
    SensorVapor sensorVapor;
    ValvulaSaidaCaldeira valvula;
    int capacidade;
    CaldeiraVapor(){
        arrayBombas = new Bomba[4];
        capacidade = 1000; //Litros
    }
    CaldeiraVapor(int qntBomba){
        arrayBombas = new Bomba[qntBomba];
        capacidade = 1000; //Litros
    }
}
