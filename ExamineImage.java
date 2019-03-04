import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ExamineImage{
    
    
  double[][] lumm;
     int[][] aa ;
                       int[][] rr;
                        int[][] gg ;
                         int[][] bb ;
         //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
             public static String iImgName = "z";
          //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

public void rgb(String ffile) {
    BufferedImage img=null;
   
    File myFile = new File(ffile);
      //  File[] listOfFiles = myFolder.listFiles();
   
try{

              if (myFile.isFile()) {
              
            
               
                img = ImageIO.read(myFile);
                int width=0;
                int height=0;

                int count=0;
                double avg=0;
                            if(img!=null){
                               width  = img.getWidth();
                               height  = img.getHeight();
                            }
                             System.out.println("Width " + width + " " +  height);
                             /* */
                       aa = new int[width][height];
                        rr = new int[width][height];
                         gg = new int[width][height];
                         bb = new int[width][height];
                         lumm = new double[width][height];
                
                for(int x=0; x< width; x++){
                    for (int y=0;y< height;y++){
                                   
                                 
                                     int pixelCol = img.getRGB(x, y);
                                        int a = (pixelCol >>> 24) & 0xff;
                                        int r = (pixelCol >>> 16) & 0xff;
                                        int g = (pixelCol >>> 8) & 0xff;
                                        int b = pixelCol & 0xff;
                                     /* */
                                        aa[x][y] = a;
                                        rr[x][y] = r;
                                        gg[x][y] = g;
                                        bb[x][y] = b;
                                       
                                       
                                    lumm[x][y]=   (0.2126*r) + (0.7152*g) + (0.0722*b);
                                 
                                     
                           }}  
                           
                        
                 
                // image(rr,gg,bb,lumm);
            }

}catch(Exception e){

System.out.println("THERE WAS A PROBLEM " + e);
}

}//end of lumanance method






//this creates a new image

 public void image(int[][] red, int[][] green, int[][] blue, double[][] lum, String type)
    {
       //make some changes to the pixels
      
      int[][] invertRed = new int[red.length][red.length];
      int[][] invertGreen = new int[green.length][green.length];
      int[][] invertBlue = new int[blue.length][blue.length];
        
        if(type == "invert"){
             for(int x=0; x< red.length-1; x++){
                    for (int y=0;y < red[0].length-1;y++){
                       //if user clicks "invert," each color value is inverted and displayed
                       
                       invertRed[x][y] = 255 - red[x][y];
                       invertGreen[x][y] = 255 - green[x][y];
                       invertBlue[x][y] = 255 - blue[x][y];
                        
                        
                    }}
           
        }
        else if(type == "filter"){
                for(int x=0; x< red.length-1; x++){
                    for (int y=0;y < red[0].length-1;y++){
                       //If user clicks "switch colors," each color becomes that color value - a different color value and is displayed
                       
                       invertRed[x][y] = Math.abs(red[x][y] - green[x][y]);
                       invertGreen[x][y] = Math.abs(green[x][y] - blue[x][y]);
                       invertBlue[x][y] = Math.abs(blue[x][y] - red[x][y]);
                        
                        
                    }}
      
        }
      
               
      //make changes above
       
       
       
       //create new image using new values


      BufferedImage img = new BufferedImage(red.length, red[0].length, BufferedImage.TYPE_INT_RGB);
      
  
      for (int x = 0; x< red.length; x++){
          for (int y = 0; y< red[0].length; y++){
              
                    int r = invertRed[x][y];
                    int g = invertGreen[x][y];
                    int b = invertBlue[x][y];
                    int col = (r << 16) | (g << 8) | b;
                    img.setRGB(x, y, col);
          
            
        }}
         
        File f = new File("inverted.jpg");
            
            
            try{
                ImageIO.write(img, "JPEG", f);
                  //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                    iImgName = "inverted.jpg";
                  //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
            } catch(Exception e){
                
            }
            
           
    }






}//end of class

