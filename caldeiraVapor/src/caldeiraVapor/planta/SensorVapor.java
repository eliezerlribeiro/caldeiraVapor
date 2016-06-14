package caldeiraVapor.planta;

public class SensorVapor {
    int fluxo;
    Boolean funcionando;
    SensorVapor(){
        fluxo=13;
        funcionando=true;
    }
    void setFluxo(int val){
        fluxo = val;
    }
    int getFluxo(){
        return fluxo;
    }
    void setFuncionando(Boolean val){
        funcionando = val;
    }
    Boolean getFuncionando(){
        return funcionando;
    }
}
