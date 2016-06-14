package caldeiraVapor;

public class BotaoEmergencia {
    Boolean botaoEmerg;
    BotaoEmergencia (){
        botaoEmerg=false;
    }
    void setBotaoEmerg(Boolean val){
        botaoEmerg = val;
    }
    Boolean getBotaoEmerg( ){
        return botaoEmerg ;
    }
}
