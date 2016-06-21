package caldeiraVapor.planta;

public class Bomba {
    
    private int vazao; // L/seg
    private Boolean bombaAberta; // true se a bomba esta aberta, false se fechada

    Bomba() {
        vazao = 5; // L/seg
        bombaAberta = true;
    }
      
    public void abre() {
        bombaAberta = true;
    }
    
    public void setAberta(Boolean entrada){
        bombaAberta = entrada;
    }

    public void fecha() {
            bombaAberta = false;
    }
    
    public Boolean isBombaAberta() {
        return bombaAberta;
    }
   
    public void setVazao(int vazao) {
        this.vazao = vazao;
    }
    
    public int getVazao() {
        return vazao;
    }
    
  
}
