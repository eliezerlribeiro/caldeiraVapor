package caldeiraVapor.planta;

public class ValvulaSaidaCaldeira {
    int fluxo;
    Boolean aberta;
    ValvulaSaidaCaldeira(){
        fluxo=30;
        aberta=false;
    }
    public void setFluxo(int val){
        fluxo = val;
    }
    public int getFluxo(){
        return fluxo;
    }
    public void changeValve(){
        aberta = !aberta;
    }
    public void setAberta(Boolean val){
        aberta = val;
    }
    public Boolean getAberta(){
        return aberta;
    }
}
