
package caldeiraVapor;

public class SensorVapor {
    int fluxo;
    Boolean funcionando;
    SensorVapor(){
        fluxo=0;
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