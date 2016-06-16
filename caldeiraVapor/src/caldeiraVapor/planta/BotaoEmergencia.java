package caldeiraVapor.planta;

public class BotaoEmergencia {
    Boolean botaoEmerg;
    BotaoEmergencia (){
        botaoEmerg=false;
    }
    public void push(){
        System.out.println("BOTAO PARADA DE EMERGENCIA");
        botaoEmerg = true;
    }
    Boolean getBotaoEmerg( ){
        return botaoEmerg ;
    }
}
