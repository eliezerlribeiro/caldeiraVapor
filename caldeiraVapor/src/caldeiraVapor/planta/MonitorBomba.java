package caldeiraVapor.planta;

public class MonitorBomba {  
    
    private Boolean funcionando;
    
    MonitorBomba() {
        funcionando = true;
    }
    
    void setFuncionando(Boolean val) {
        funcionando = val;
    }
    
    Boolean getFuncionando() {
        return funcionando;
    }
}
