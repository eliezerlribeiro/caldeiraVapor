package caldeiraVapor.planta;

public class Bomba {
    int vazao; // L/seg
    Boolean bombaAberta; // true se a bomba esta aberta, false se fechada

    Bomba() {
        vazao = 2; // L/seg
        bombaAberta = true;
    }
      
    public void abre() {
        bombaAberta = true;
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
