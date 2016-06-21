package caldeiraVapor.planta;

public class Bomba {
    
    private int vazao; // L/seg
    private boolean bombaAberta; // true se a bomba esta aberta, false se fechada
    private boolean estragada;

    Bomba() {
        vazao = 5; // L/seg
        bombaAberta = true;
        estragada = false;
    }
      
    public void abre() {
        if (!estragada)
            bombaAberta = true;
    }
    
    public void setAberta(Boolean entrada){
        bombaAberta = entrada;
    }

    public void fecha() {
        if (!estragada)
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
    
    public void estragaBomba() {
        estragada = true;
    }
    
    public void consertaBomba() {
        estragada = false;
    }
  
}
