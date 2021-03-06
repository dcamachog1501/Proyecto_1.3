/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;
import Componentes_Jugador.Player;
import Manager.LevelManager;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase encargada de generar un gestor de ventanas.
 * @author Daniel Camacho
 */
public class Gestor2
{
    
  //Elementos necesarios para el desrrollo de ventanas
  private  final String Titulo="Invaders";
  private  final Image Icono=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Iconos/Icono.png"));
  private  Font FuenteTitulo;
  private  Font FuenteMarc;
  private final  Color Btn=new Color(75,0,130);
  private final Image Back=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Backgrounds/Background.jpg"));
  private Image Back2= Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Backgrounds/Back2.png"));
  
  //Creacion de las ventanas de la interfaz 
  private Ventana_Inicial VentanaInicial;
  private Ventana_Estadisticas VentanaStatics;
  private  Ventana_Datos VentanaDatos;
  private  Ventana_Juego VentanaJuego;
  private Ventana_Final VentanaFinal;
  private LevelManager LManager;
  /**
   * Cosntructor de la clase Gestor2
   */
    public Gestor2()
    {
      try
      {
          //Fuente principal de la interfaz
          InputStream is = getClass().getResourceAsStream("/Resources/Fuentes/Furore.ttf");
          FuenteTitulo= Font.createFont(Font.TRUETYPE_FONT,is);
      }
      catch(Exception e)
      {
          FuenteTitulo=null;
      }
        
      try
      {
          //Fuente del marcador
        InputStream is = getClass().getResourceAsStream("/Resources/Fuentes/Marcador.ttf");
        FuenteMarc= Font.createFont(Font.TRUETYPE_FONT,is);  
      }
      catch(Exception e)
      {
         FuenteMarc=null; 
      }
    }
    /**
     * Método encargado de desplegar la ventana inicial en panatalla.
     */
    public void gestInicial()
    {
      VentanaInicial.setVisible(true);
    }
    /**
     * Método que permite desplegar la ventana de estadisticas
     */
    public void gestStatics()
    {
        VentanaStatics.setVisible(true);
    }
    /**
     * Metodo que permite la proyeccion de la ventana de toma de datos.
     */
    public void gestDatos()
    {
       VentanaDatos.setVisible(true);
    }
    /**
     * Metodo que permite desplegar la ventana del juego.
     */
    public void gestJuego()
    {
       VentanaJuego.setVisible(true);
    }
    /**
     * Metodo que permite desplegar la ventana final.
     */
    public void gestFinal()
    {
        VentanaFinal.setVisible(true);
    }
    /**
     * Metodo que inicializa la ventana.
     */
    public void Init()
    {
        this.LManager=new LevelManager(this);
        VentanaInicial=new Ventana_Inicial(Titulo,FuenteTitulo,Back,Icono,Btn, this);
      try {
          VentanaStatics=new Ventana_Estadisticas(Titulo,FuenteTitulo,Back,Icono,Btn,Back2,this);
      } catch (IOException ex) {
          Logger.getLogger(Gestor2.class.getName()).log(Level.SEVERE, null, ex);
      }
        VentanaDatos=new Ventana_Datos(Titulo,FuenteTitulo,Back,Icono,Btn,this);
        VentanaJuego=new Ventana_Juego(Titulo,FuenteTitulo,Icono,Btn,FuenteMarc,this,LManager);
        VentanaFinal= new Ventana_Final(Titulo,FuenteTitulo,Back,Icono,Btn, this);
    }
    /**
     * Metodo para obtener la instancia de VentanaJuego
     * @return objeto de tipo Ventana_Juego
     */
    public Ventana_Juego getGame()
    {
        return VentanaJuego;
    }
    /**
     * Metodo para obtener la instancia de VentanaDatos
     * @return onjeto de tipo Ventana_Datos
     */
    public Ventana_Datos getDatos()
    {
        return VentanaDatos;
    }
    /**
     * Metodo que retorna el manager de niveles de la ventana.
     * @return LManager manager
     */
    public LevelManager getLManager()
    {
        return LManager;
    }
    /**
     * Metodo que finaliza el juego.
     */
    public void endGame()
    {
     Player p= new Player();
     p.Init(VentanaDatos.getPlayerNam(),VentanaJuego.getMarc());
     VentanaStatics.saveMarc(p);
      try {
          VentanaStatics.update();
      } 
      catch (Exception e) 
      {
          
      }
     VentanaFinal.setMarc(VentanaJuego.getMarc());
     VentanaJuego.stopExcecution();
     VentanaJuego.dispose();
     VentanaDatos=null;
     VentanaJuego=null;
     gestFinal();
     System.out.println("------------------GAME_OVER------------------");
    }
}
