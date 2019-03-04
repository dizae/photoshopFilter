import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.io.DataInput;
 import java.io.FileNotFoundException;
import java.io.IOException;
 import java.io.RandomAccessFile;
import javax.imageio.stream.FileImageInputStream; 
import javax.imageio.stream.FileImageOutputStream; 




public class ImageFilter  extends JPanel implements Runnable, MouseListener
{
boolean ingame = true;
private Dimension d;
int BOARD_WIDTH=800;
int BOARD_HEIGHT=500;
BufferedImage img;
boolean start = true;
boolean imgReady = false;
 private Thread animator;
 int count = 0;
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
String tempS = "z";
BufferedImage img2;
boolean iImgReady = false;
ExamineImage ei = new ExamineImage();
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
           int x = 100;
        int y = 200;
         
 
 
    public ImageFilter()
    {
          addKeyListener(new TAdapter());
         addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
       
     
           
            if (animator == null || !ingame) {
               animator = new Thread(this);
               animator.start();
            }
                    
  
        setDoubleBuffered(true);
    }
    
     public void selectFile() {
 
        //Handle open button action.
       
       
            ChooseFile.starter();
           
        }
    
  
    
    
    public void imageSelect(){
    if(ChooseFile.sourceFolder.equals("z")){
        //don't do anything
    }else{
        setImages();
    }
    
}

   public void setImages(){
      
       
        try {
           
              img =   ImageIO.read(new FileImageInputStream(new File(ChooseFile.sourceFolder)));
              //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
               tempS = ChooseFile.sourceFolder;
              ei.rgb(ChooseFile.sourceFolder);
              //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
               
                imgReady=true;
            } catch (IOException e) {
            System.out.println("Image could not be read");
            // System.exit(1);
            }
            
       
    }
    
    
    
     public void image2Ready(){ //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
       //ChooseFile.theFile
       if(!(ExamineImage.iImgName.equals("z"))){
          System.out.println("Image Inverted");
        try {
            //    img = ImageIO.read(this.getClass().getResource(ChooseFile.sourceFolder));
              img2 =   ImageIO.read(new FileImageInputStream(new File(ExamineImage.iImgName)));
               iImgReady=true;
            } catch (IOException e) {
            System.out.println("Image could not be read");
            // System.exit(1);
            }
        } 
       
    }//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    
    public void paint(Graphics g)
{
super.paint(g);

   g.setColor(Color.white);
   g.fillRect(0, 0, d.width, d.height);
   
        
   
    if (ingame) {
       //button for selecting file
      g.setColor(Color.gray);
      g.fillRect(50, d.height-75, 100, 25);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Select File", 55, d.height-60);
        //select file
        
       
        
      if(imgReady==true){
        // System.out.println("TRUE");&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        g.drawImage(img,0,0,400,400 ,null);
        if( tempS != ChooseFile.sourceFolder){
            setImages();
            iImgReady = false;
        }
        
         //button for inverting image
         g.setColor(Color.gray);
         g.fillRect(250, d.height-75, 100, 25);
         g.setColor(Color.white);
        g.drawString("Invert Image", 255, d.height-60);
        //invert image&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        
        //button for switching image colors
         g.setColor(Color.gray);
         g.fillRect(500, d.height-75, 100, 25);
         g.setColor(Color.white);
        g.drawString("Switch Colors", 505, d.height-60);
        //invert image&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        
        if(iImgReady == true){//invert image&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        
                  g.drawImage(img2,420,0,400,400 ,null);
              
            }else{
                // ei.image(ei.rr,ei.gg,ei.bb,ei.lumm);
                 image2Ready(); 
           
        }//invert image&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        
    }else{
        
        imageSelect();
    }
      
        
   
    }
      Toolkit.getDefaultToolkit().sync();
       g.dispose();
}



private class TAdapter extends KeyAdapter {

    
    
public void keyReleased(KeyEvent e) {
// player.keyReleased(e);
 int key = e.getKeyCode();
 
      if(key==39){
         
        }
        
         if(key==37){
          
        }
        
    
     
}

public void keyPressed(KeyEvent e) {
System.out.println( e.getKeyCode());
   // message = "Key Pressed: " + e.getKeyCode();
    int key = e.getKeyCode();
    
        if(key==39){
          
        }
        
        
        if(key==37){
         
        }
        
        
          if(key==38){
            
            }
        
      // if(key

}

}



public void mousePressed(MouseEvent e) {
    int mx = e.getX();
     int my = e.getY();
    // g.fillRect(50, d.height-75, 100, 25);
    if(mx > 50 && mx < 150 && my > d.height-75 && my < d.height-75 + 25){
        selectFile();
    }
   
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
      if(mx > 250 && mx < 350 && my > d.height-75 && my < d.height-75 + 25){
        System.out.println("Invert");
        ei.image(ei.rr,ei.gg,ei.bb,ei.lumm, "invert");
                 image2Ready(); 
    }
   //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
   
   //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& THIS IS THE NEW ONE
      if(mx > 500 && mx < 550 && my > d.height-75 && my < d.height-75 + 25){
        System.out.println("Filter");
        ei.image(ei.rr,ei.gg,ei.bb,ei.lumm, "filter");
                 image2Ready(); 
    }
   //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
         //  ei.lum(sourceFolder);

}

public void mouseReleased(MouseEvent e) {

}

public void mouseEntered(MouseEvent e) {

}

public void mouseExited(MouseEvent e) {

}

public void mouseClicked(MouseEvent e) {

}

public void run() {

long beforeTime, timeDiff, sleep;

beforeTime = System.currentTimeMillis();
 int animationDelay = 10;
 long time = 
            System.currentTimeMillis();
    while (true) {//infinite loop
     // spriteManager.update();
      repaint();
      try {
         time += animationDelay;
          Thread.sleep(Math.max(0,time - 
          System.currentTimeMillis()));
      }catch (InterruptedException e) {
        System.out.println(e);
      }//end catch
    }//end while loop

    


}//end of run

  

}//end of class