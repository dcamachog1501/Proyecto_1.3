/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Ventanas.Gestor2;
import java.io.IOException;

/**
 *
 * @author dcama
 */
public class Main 
{
    
    
    public static void main(String[] args) throws IOException 
    {
       Gestor2 g = new Gestor2();
       g.Init();
       g.gestInicial();
    }
}
    
