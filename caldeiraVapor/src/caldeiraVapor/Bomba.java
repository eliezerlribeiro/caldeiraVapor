/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caldeiraVapor;

/**
 *
 * @author Eliezer
 */
public class Bomba {
    int vasao; // L/seg
    Boolean flagAbetFech;
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
