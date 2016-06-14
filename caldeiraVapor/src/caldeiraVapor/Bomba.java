
package caldeiraVapor;

public class Bomba {
    int vasao; // L/seg
    Boolean flagAbetFech;
    Bomba(){
        vasao = 5; // L/seg
        flagAbetFech = true;
    }
      
    Bomba(int vasao){
        this.vasao = vasao;
        flagAbetFech = true;
    }
    void setFlag(Boolean flag){
        flagAbetFech = flag;
    }
    
    Boolean getFlag(Boolean flag){
        return flagAbetFech;
    }
   
    void setVasao(int vasao){
        this.vasao = vasao;
    }
    
    int getFlag(){
        return vasao;
    }
}
