/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fabrica_Enemigos;

import Enemigos.Type_A;
import Enemigos.Enemy;
import Ventanas.Gestor2;

/**
 *Clase encargada de instanciar fabricas deenemigos tipo A.
 * @author Daniel Camacho
 */
public class A_Creator implements Enemy_Creator
{
    @Override
    public Enemy createEnemy(int x, int y, int sup,int dir,Gestor2 gest, int lvl, int h,int s ) 
    {
        Type_A enm= new Type_A();
        enm.Init(x,y,sup,dir,gest,lvl,h,s);
        return enm;
    } 
}
