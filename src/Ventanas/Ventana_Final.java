/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *Clase encargada de instanciar la ventana final 
 * @author Daniel Camacho
 */
public class Ventana_Final extends JFrame
{
    private final String title;
    private final Image back;
    private final Image icono;
    private final Color color;
    private final Font fuentem;
    private final Gestor2 gestor;
    private Image sider;
    private final Image medal;
    private JLabel FinalS;
    /**
     * Constructor de la clase Ventana_Final
     * @param title String titulo.
     * @param Fuente Font fuente.
     * @param back Image background.
     * @param Icono Icon icono.
     * @param Btn Color color de los botones de la ventana.
     * @param gest Gestor2 gestor.
     */
    public Ventana_Final(String title,Font Fuente,Image back,Image Icono, Color Btn, Gestor2 gest)
    {
        this.gestor = gest;
        this.title=title;
        this.fuentem=Fuente;
        this.icono=Icono;
        this.color=Btn;
        this.back=back;
        this.medal=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Iconos/medal.png"));
        Init();
    }/**
     * Metodo encargado de inicializar la ventana.
     */
    public void Init()
    {
      setTitle(title);
      setResizable(false);
      setUndecorated(true);
      setIconImage(icono);
      setSize(800,300);
      setLocationRelativeTo(null);
      getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4,Color.BLACK));
      setLayout(null);
      
      JLabel Uno= new JLabel(new ImageIcon(back));
      Uno.setBounds(0,0,800,300);
      add(Uno);
      
      JLabel medal= new JLabel(new ImageIcon(this.medal));
      medal.setBounds(230,0,800,250);
      Uno.add(medal);
      
      JLabel Title= new JLabel("Game Over!");
      Title.setBounds(280,0,500,50);
      Title.setFont(fuentem.deriveFont(Font.PLAIN,35));
      Title.setForeground(Color.GREEN);
      Uno.add(Title);
      
      FinalS= new JLabel("Final Score: ");
      FinalS.setBounds(0,85,500,100);
      FinalS.setFont(fuentem.deriveFont(Font.PLAIN,35));
      FinalS.setForeground(Color.GREEN);
      Uno.add(FinalS);
      
      JButton menu= new JButton("MAIN MENU");
      menu.setFont(fuentem.deriveFont(Font.PLAIN,20));
      menu.setForeground(color);
      menu.setBackground(Color.GREEN);
      menu.setBounds(0,231,250,50);
      menu.setFocusPainted(false);
      menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
      menu.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
      menu.addActionListener(new ActionListener()
        {
       @Override
       public void actionPerformed(ActionEvent e) 
       {
        dispose();
        gestor.gestInicial();
        gestor.Init();
       }
        });
      Uno.add(menu);
      
      JButton restart= new JButton("PLAY AGAIN");
      restart.setFont(fuentem.deriveFont(Font.PLAIN,20));
      restart.setForeground(Color.GREEN);
      restart.setBackground(color);
      restart.setBounds(260,231,250,50);
      restart.setFocusPainted(false);
      restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
      restart.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
      restart.addActionListener(new ActionListener()
        {
       @Override
       public void actionPerformed(ActionEvent e) 
       {
        dispose();
        gestor.Init();
        gestor.gestDatos();
       }
        });
      Uno.add(restart);
      
      JButton abort= new JButton("ABORT");
      abort.setFont(fuentem.deriveFont(Font.PLAIN,20));
      abort.setForeground(color);
      abort.setBackground(Color.GREEN);
      abort.setBounds(520,231,250,50);
      abort.setFocusPainted(false);
      abort.setCursor(new Cursor(Cursor.HAND_CURSOR));
      abort.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
      abort.addActionListener(new ActionListener()
        {
       @Override
       public void actionPerformed(ActionEvent e) 
       {
        System.exit(0);
       }
        });
      Uno.add(abort);
    }
    /**
     * Metodo que proyecta el marcador final en la ventana.
     * @param marc Puntaje marcador.
     */
    public void setMarc(int marc)
    {
        this.FinalS.setText("Final Score: "+marc);
    }
}
