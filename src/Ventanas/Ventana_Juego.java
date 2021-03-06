/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Manager.LevelManager;
import Threads.BasicMove;
import Threads.Left;
import Threads.Right;
import Threads.Shoot;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *Clase encargada del de desarrollo de ventanas de juego.
 * @author Daniel Camacho
 */
public class Ventana_Juego extends JFrame
{
    //Canvas en el que se desarrolla el juego 
    private  Canvas canv;
    
    
    //Marcador del juego 
    private int marc;
    
    
    // Datos escritos que se proyectan en el marcador
    private String marcs;
    
    
    //Label que proyecta el marcador 
    private JLabel Punt;
    private JLabel Punt2;
    private JLabel Punt4;
    private JLabel Punt5;
    private JLabel Punt7;
   
    //KeyListeners encargados de recibir los inputs del usuario
    private final Teclado0 tec0;
    private final Teclado2 tec2;
    
    //Atributos de la ventana
    private final String title;
    private final Font fuentet;
    private final Image icono;
    private final Color color;
    private final Font fuentem;
    private final Gestor2 gest;
    //Thread que permite el movimiento continuo de los enemigos.
    private Thread mover;
    private BasicMove move;
    
    private boolean cond;
    
    private LevelManager LManager;
    private Thread r;
    private Right right;
    private Thread l;
    private Left left;
    private boolean condr;
    private boolean condl;
    /**
     * Cosntructor de la clase Ventana_Juego
     * @param title String titulo
     * @param FuenteT Font fuente
     * @param Icono Icon icono
     * @param Btn Color color de los botones de la ventana.
     * @param FuenteM Font fuente del marcador
     * @param gest Gestor2 gestor
     * @param lvl int nivel
     */
    Ventana_Juego(String title,Font FuenteT,Image Icono, Color Btn,Font FuenteM,Gestor2 gest,LevelManager lvl)
    {
       this.LManager=lvl;
       this.tec0=new Teclado0();
       this.tec2= new Teclado2();
       this.title=title;
       this.fuentem=FuenteM;
       this.fuentet=FuenteT;
       this.icono=Icono;
       this.color=Btn;
       this.gest=gest;
       this.marc=0;
       this.marcs=String.format("%013d",marc);
       this.Punt= new JLabel(marcs);
       this.Punt2= new JLabel();
       this.Punt4= new JLabel();
       this.Punt5= new JLabel();
       this.Punt7= new JLabel();

       
       this.cond=false;
       this.move=LManager.getCurrent().getMove();
       this.right=new Right(gest);
       this.left= new Left(gest);
       this.condr= true;
       this.condl=true;
       Init();
    }
    /**
     * Metodo que retorna el manager de niveles de la ventana.
     * @return LManager manager
     */
  public LevelManager getLManager()
  {
      return LManager;
  }/**
   * Metodo que retorna el KeyListener teclado2
   * @return KeyListener teclado2
   */
  public Teclado2 getTec()
  {
      return tec2;
  }
  /**
   * Metodo para agregar puntos al marcador 
   * @param x cantidad entera de puntos a agregar 
   */
  public void addMarc(int x)
  {
      marc+=x;
  }
  /**
   * Metodo para actualizar el marcador.
   */
  public void updateMarcs()
    {
        marcs=String.format("%013d",marc);
        Punt.setText(marcs);
    }
  /**
   * Metodo para obtener el canvas de la ventana
   * @return objeto de tipo canvas 
   */
  public Canvas getCanvas()
    {
        return canv;
    }
  /**
   * Metodo para remover el teclado2.
   */
  public void rem()
  {
      this.removeKeyListener(tec2);
  }
  /**
   * Metodo para reponer el teclado2.
   */
  public void adder()
  {
      this.addKeyListener(tec2);
  }/**
   * Metodo que inicializa la dinamica del juego.
   */
  public void gameStarter()
  {
      r= new Thread((Runnable) right);
      r.setUncaughtExceptionHandler(
        new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
                }
            }
      );
      r.start();
      
      l= new Thread((Runnable) left);
      
      l.setUncaughtExceptionHandler(
        new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
                }
            }
      );
      l.start();
      
      mover= new Thread((Runnable)move);
      
      mover.setUncaughtExceptionHandler(
      new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
                }
            }
      );
      mover.start();
  }
  /**
   * Metodo que retorna el movimiento del juego.
   * @return BasicMove movimiento.
   */
  public Thread getMover()
  {
      return mover;
  }
  /**
   * Metodo que retorna la fuente del marcador.
   * @return Font fuentem
   */
  public Font getFontm()
  {
      return this.fuentem;
  }
  /**
   * Metodo que actualiza el marcador.
   */
  public void updateScreen()
  {
      if(LManager.getCurrent()==null)
      {
          LManager.lvlUP();
          Punt4.setText("CURRENT "+ LManager.getCurrent().getType());
          Punt5.setText("NEXT "+ LManager.getCurrent().getNext().getType());
          Punt7.setText("LEVEL "+LManager.getLeveln());
          Punt2.setIcon(new ImageIcon(LManager.getCurrent().getCurrent()));
      }
      else if(LManager.getCurrent().getNext()==null)
      {
      Punt4.setText("CURRENT "+ LManager.getCurrent().getType());
      Punt5.setText("NEXT LEVEL");
      Punt7.setText("LEVEL "+LManager.getLeveln());
      Punt2.setIcon(new ImageIcon(LManager.getCurrent().getCurrent()));
      }
      else
      {
      Punt4.setText("CURRENT "+ LManager.getCurrent().getType());
      Punt5.setText("NEXT "+ LManager.getCurrent().getNext().getType());
      Punt7.setText("LEVEL "+LManager.getLeveln());
      Punt2.setIcon(new ImageIcon(LManager.getCurrent().getCurrent()));
      }
  }
  /**
   * Metodo que cambia la condicion cond.
   */
   public void chanCond()
    {
        cond=!cond;
    }
   /**
    * Metodo que retorna la condicion cond.
    * @return boolean cond
    */
    public boolean getCond()
    {
        return cond; 
    }
    /**
     * Metodo que retorna el marcador Puntaje de la ventana.
     * @return Puntaje marcador
     */
    public int getMarc()
    {
        return this.marc;
    }
    /**
     * metodo que detien los Threads del juego.
     */
    public void stopExcecution()
    {
        try 
        {
            right.chnBool();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        try 
        {
            left.chnBool();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        try 
        {
          this.move.chnBool();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        gest.getDatos().getSet().stop();
    }
  /**
   * Metodo que inicializa la ventana
   */
  public void Init()
  {
        canv=new Canvas();
        canv.setPreferredSize(new Dimension(985,720));
        canv.setBackground(Color.DARK_GRAY);
        setTitle(title);
        setSize(1300,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(8,8,8,8, Color.BLACK));
        setResizable(false);
        setIconImage(icono);
        setFocusable(true);
        addKeyListener(tec0);
        addKeyListener(tec2);

        JPanel fondo=new JPanel();
        fondo.setBackground(Color.DARK_GRAY);
        fondo.setPreferredSize(new Dimension(985,670));
        fondo.add(canv);
        getContentPane().add(canv,BorderLayout.EAST);

        JPanel Panel1= new JPanel();
        Panel1.setPreferredSize(new Dimension(300,1000));
        Panel1.setBackground(Color.BLACK);
        Panel1.setBorder(BorderFactory.createMatteBorder(4,0,0,8,Color.BLACK));
        add(Panel1,BorderLayout.WEST);

        Punt.setBackground(color);
        Punt.setOpaque(true);
        Punt.setBorder(BorderFactory.createMatteBorder(4,0,8,0, Color.BLACK));
        Punt.setBounds(0,0,292,75);
        Punt.setForeground(Color.GREEN);
        Punt.setFont(fuentem.deriveFont(Font.PLAIN,20));
        Panel1.setLayout(null);
        Panel1.add(Punt);

        Punt2.setBackground(Color.BLACK);
        Punt2.setIcon(new ImageIcon(LManager.getCurrent().getCurrent()));
        Punt2.setOpaque(true);
        Punt2.setBounds(12,75,266,250);
        Punt2.setForeground(Color.GREEN);
        Panel1.add(Punt2);

        JLabel Punt3= new JLabel();
        Punt3.setBackground(Color.BLACK);
        Punt3.setOpaque(true);
        Punt3.setBounds(0,325,292,8);
        Panel1.add(Punt3);

        Punt4.setText("CURRENT "+ LManager.getCurrent().getType());
        Punt4.setBounds(5,210,292,292);
        Punt4.setForeground(Color.GREEN);
        Punt4.setFont(fuentem.deriveFont(Font.PLAIN,15));
        Panel1.add(Punt4);

        Punt5.setText("NEXT "+ LManager.getCurrent().getNext().getType());
        Punt5.setBounds(5,280,292,292);
        Punt5.setForeground(Color.GREEN);
        Punt5.setFont(fuentem.deriveFont(Font.PLAIN,15));
        Panel1.add(Punt5);
        //Boton que permite salirse del juego
        JButton Abort= new JButton("Abort");
        Abort.setForeground(Color.GREEN);
        Abort.setBackground(color);
        Abort.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Abort.setFont(fuentet.deriveFont(Font.PLAIN,20));
        Abort.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        Abort.setFocusPainted(false);
        Abort.setBounds(48,550, 200, 70);
        Abort.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }


        });
        Panel1.add(Abort);

        Punt7.setText("LEVEL "+LManager.getLeveln());
        Punt7.setBounds(5,350,292,292);
        Punt7.setForeground(Color.GREEN);
        Punt7.setFont(fuentem.deriveFont(Font.PLAIN,15));
        Panel1.add(Punt7);

        JLabel Punt6= new JLabel();
        Punt6.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Backgrounds/Back2.png"))));
        Punt6.setBounds(0,330,292,450);
        Panel1.add(Punt6);
        
  }
  //KeyListaner para mover la nave
  private class Teclado0 implements KeyListener
  {
        @Override
        public void keyTyped(KeyEvent e) 
        {
      
        }
        @Override
        public void keyPressed(KeyEvent e) 
        {
            int code= e.getKeyCode();
            if(code==KeyEvent.VK_RIGHT)
            {
             if(condr==true)
             {
             right.chnCond();
             condr=false;
             }
            }
            else if(code==KeyEvent.VK_LEFT)
            {
             if(condl==true)
             {
             left.chnCond();
             condl=false;
            }
        }
        }
        @Override
        public void keyReleased(KeyEvent e) 
        {
            int code= e.getKeyCode();
            if(code==KeyEvent.VK_RIGHT)
            {
             if(condr==false)
             {
             right.chnCond();
             condr=true;
             }
            }
            else if(code==KeyEvent.VK_LEFT)
            {
             if(condl==false)
             {
             left.chnCond();
             condl=true;
             }
            }
        }
  }
 
  //KeyListener para disparar
  private class Teclado2 implements KeyListener
  {
        @Override
        public void keyTyped(KeyEvent e) 
        {
      
        }
        @Override
        public void keyPressed(KeyEvent e) 
        {
            int code= e.getKeyCode();
            if(code==KeyEvent.VK_F)
            {
              
              Thread s= new Thread(new Shoot(gest));
              s.start();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) 
        {
       
        }
      
  
  }
}

