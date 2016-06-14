package caldeiraVapor.planta;

public class ValvulaSaidaCaldeira {
    int fluxo;
    Boolean aberta;
    ValvulaSaidaCaldeira(){
        fluxo=0;
        aberta=false;
    }
    void setFluxo(int val){
        fluxo = val;
    }
    int getFluxo(){
        return fluxo;
    }
    void setAberta(Boolean val){
        aberta = val;
    }
    Boolean getAberta(){
        return aberta;
    }
}
