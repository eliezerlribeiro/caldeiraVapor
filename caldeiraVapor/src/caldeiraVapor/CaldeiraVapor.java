
package caldeiraVapor;

public class CaldeiraVapor {
    Bomba[] arrayBombas;
    int vaporCaldeira;      
    int nivelCaldeira;
    int vasaoValvulaCaldeira; 
    Boolean valvulaCaldeiraFlag;
    CaldeiraVapor(){
        arrayBombas = new Bomba[4];
        vaporCaldeira= 0;
        nivelCaldeira=0;
        vasaoValvulaCaldeira = 15; // L/seg
        valvulaCaldeiraFlag = false;
    }
    CaldeiraVapor(int qntBomba, int vasaoValvCald){
        arrayBombas = new Bomba[qntBomba];
        vaporCaldeira= 0;
        nivelCaldeira=0;
        vasaoValvulaCaldeira = vasaoValvCald; // L/seg
        valvulaCaldeiraFlag = false;
    }
   void setValvulaCaldeira(Boolean val){
        valvulaCaldeiraFlag = val;   
    }
    Boolean getValvulaCaldeira(){
        return valvulaCaldeiraFlag;   
    }
    
    int getNivelCaldeira(){
        return nivelCaldeira;   
    }
    int getVasaoValvulaCaldeira(){
        return vasaoValvulaCaldeira;   
    }    
}
