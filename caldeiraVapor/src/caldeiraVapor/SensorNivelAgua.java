package caldeiraVapor;

public class SensorNivelAgua {
    int nivel;
    Boolean funcionando;
    SensorNivelAgua(){
        nivel=0;
        funcionando=true;
    }
    public void setNivel(int val){
        nivel = val;
    }
    public int getNivel(){
        return nivel;
    }
    public void setFuncionando(Boolean val){
        funcionando = val;
    }
    public Boolean getFuncionando(){
        return funcionando;
    }
}
