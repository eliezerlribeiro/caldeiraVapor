package caldeiraVapor;

public class SensorNivelAgua {
    int nivel;
    Boolean funcionando;
    SensorNivelAgua(){
        nivel=0;
        funcionando=true;
    }
    void setNivel(int val){
        nivel = val;
    }
    int getNivel(){
        return nivel;
    }
    void setFuncionando(Boolean val){
        funcionando = val;
    }
    Boolean getFuncionando(){
        return funcionando;
    }
}
