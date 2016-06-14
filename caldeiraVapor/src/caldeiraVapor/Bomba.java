
package caldeiraVapor;

public class Bomba extends Thread{
    int vasao; // L/seg
    Boolean flagAbetFech;
    Bomba(){
        vasao = 5; // L/seg
        flagAbetFech = true;
    }
      
    public void setFlag(Boolean flag){
        flagAbetFech = flag;
    }
    
    public Boolean getFlag(){
        return flagAbetFech;
    }
   
    public void setVasao(int vasao){
        this.vasao = vasao;
    }
    
    public int getVasao(){
        return vasao;
    }
    
  
    public void run() {
     
    }
}
