/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caldeiraVapor;

/**
 *
 * @author eliezer
 */
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
     vasaoValvulaCaldeira = 500; // L/seg
     valvulaCaldeiraFlag = false;
   }
}
