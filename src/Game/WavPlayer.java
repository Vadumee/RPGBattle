package Game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WavPlayer {    
	 
    private Clip clip = null;
    private AudioInputStream audioStream = null;
    
    public WavPlayer(File f) {
        try {
			audioStream = AudioSystem.getAudioInputStream(f);
			//recuperation d'un stream de type audo sur le fichier
	        AudioFormat audioFormat = audioStream.getFormat();//recuperation du format de son
	        //recuperation du son que l'on va stoquer dans un oblet de type clip
	        DataLine.Info info = new DataLine.Info(
	                Clip.class, audioStream.getFormat(),
	                ((int) audioStream.getFrameLength() * audioFormat.getFrameSize()));
	        //recuperation d'une instance de type Clip
	        clip = (Clip) AudioSystem.getLine(info);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /**
     * Ouverture du flux audio
     * @return On retourne <code>false
si il y a eu une erreure 
    */ 
   public boolean open(){ 
       if(clip != null && !clip.isOpen())//teste pour ne pas le faire dans le vent 
           try { 
               clip.open(audioStream); 
           } catch (Exception e) { 
               e.printStackTrace();//pour le debugage 
               return false; 
           } 
       return true; 
   } 
    
   /** 
    * Fermeture du flux audio 
    */ 
   public void close(){ 
       if(clip != null && clip.isOpen())//teste pour ne pas le faire dans le vent 
           clip.close(); 
   } 
    
   /** 
    * On joue le son 
    */ 
   public void play(){ 
       if(clip != null && clip.isOpen()) 
           clip.start(); 
   } 
    
   /** 
    * On arrete le son 
    */ 
   public void stop(){ 
       if(clip != null && clip.isOpen()) 
           clip.stop(); 
   } 
    
} 