package caldeiraVapor.planta;

public class BotaoEmergencia {
    
    private Boolean botaoEmerg;
    
    BotaoEmergencia () {
        botaoEmerg = false;
    }
    
    public void push() {
        System.out.println("BOTAO PARADA DE EMERGENCIA");
        botaoEmerg = true;
    }
    
    public Boolean getBotaoEmerg() {
        return botaoEmerg;
    }
}
