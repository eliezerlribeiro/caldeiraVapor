package caldeiraVapor.planta;

public class Bomba {
    int vazao; // L/seg
    Boolean flagAbetFech;
    Bomba(){
        vazao = 5; // L/seg
        flagAbetFech = true;
    }
      
    public void setFlag(Boolean flag){
        flagAbetFech = flag;
    }
    
    public Boolean getFlag(){
        return flagAbetFech;
    }
   
    public void setVazao(int vazao){
        this.vazao = vazao;
    }
    
    public int getVazao(){
        return vazao;
    }
    
  
}
