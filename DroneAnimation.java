package application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DroneAnimation {
	ImageView droneImage;
	
	public DroneAnimation(ImageView droneImage) { 
		this.droneImage = droneImage; 
	}
	
	public void visitDroneAnimation(int xCord,int yCord, int droneX, int droneY)throws IOException {
		TranslateTransition trans1 = new TranslateTransition(); 
		trans1.setToX(0); 
		trans1.setToY(0);
		trans1.setDuration(Duration.millis(1000)); 
		trans1.setNode(droneImage);		
		RotateTransition rotate1 = new RotateTransition(); 
		rotate1.setByAngle(360); 
		rotate1.setDuration(Duration.millis(2000));
		rotate1.setNode(droneImage);		
		TranslateTransition trans2 = new TranslateTransition(); 
		trans2.setToX(xCord-droneX); 
		trans2.setToY(yCord-droneY);
		trans2.setDuration(Duration.millis(1000)); 
		trans2.setNode(droneImage);		
		PauseTransition pause1 = new PauseTransition(); 
		pause1.setDuration(Duration.millis(1000)); 
						
		SequentialTransition st = new SequentialTransition(trans1, rotate1, pause1, trans2); 
		st.play();
	}
	
	public void scanWholeFarm() throws IOException { 
//		650-400
		TranslateTransition s1 = new TranslateTransition();
		s1.setToX(0);
		s1.setToY(0);
		s1.setDuration(Duration.millis(1000)); 
		RotateTransition rotate0 = new RotateTransition();
	  	rotate0.setByAngle(90);
	  	rotate0.setDuration(Duration.millis(500));
	  	rotate0.setNode(droneImage);
	  	
	  	s1.setNode(droneImage); TranslateTransition s2 = new TranslateTransition(); 
	  	s2.setToX(520);
	  	s2.setToY(40);
	  	s2.setDuration(Duration.millis(1000)); 	  		  	
	  	s2.setNode(droneImage);
	  	
	  	RotateTransition rotate1 = new RotateTransition();
	  	rotate1.setByAngle(90);
	  	rotate1.setDuration(Duration.millis(500));
	  	rotate1.setNode(droneImage);
	  		    
	    TranslateTransition s7 = new TranslateTransition(); 
	    s7.setToX(520); 
	    s7.setToY(190); 
	    s7.setDuration(Duration.millis(500)); 
	  	s7.setNode(droneImage);	  		  	
	    
	  	RotateTransition rotate6 = new RotateTransition();
	  	rotate6.setByAngle(90);
	  	rotate6.setDuration(Duration.millis(500));
	  	rotate6.setNode(droneImage);
	    
	  	TranslateTransition s8 = new TranslateTransition(); 
	  	s8.setToX(-380); 
	  	s8.setToY(190); 
	  	s8.setDuration(Duration.millis(1000)); 
	  	s8.setNode(droneImage);	  		  	
	    
	  	RotateTransition rotate7 = new RotateTransition(); 
	  	rotate7.setByAngle(-90); 
	  	rotate7.setDuration(Duration.millis(500)); 
	  	rotate7.setNode(droneImage);
	    
	  	TranslateTransition s9 = new TranslateTransition(); 
	  	s9.setToX(-380); 
	  	s9.setToY(300); 
	  	s9.setDuration(Duration.millis(500)); 
	  	s9.setNode(droneImage);	
	  	
	  	RotateTransition rotate8 = new RotateTransition();
	  	rotate8.setByAngle(-90);
	  	rotate8.setDuration(Duration.millis(500)); 
	  	rotate8.setNode(droneImage);
	  	
	    TranslateTransition s10 = new TranslateTransition();
	    s10.setToX(520);
	    s10.setToY(300);
	    s10.setDuration(Duration.millis(1000)); 
	  	s10.setNode(droneImage);
	  	
	    RotateTransition rotate9 = new RotateTransition();
	    rotate9.setByAngle(90); 
	    rotate9.setDuration(Duration.millis(500)); 
	    rotate9.setNode(droneImage);
	    
	    TranslateTransition s11 = new TranslateTransition();
	    s11.setToX(520);
	    s11.setToY(350); s11.setDuration(Duration.millis(500)); 
	  	s11.setNode(droneImage);	  		  
	  	
	  	RotateTransition rotate10 = new RotateTransition(); 
	  	rotate10.setByAngle(90);
	  	rotate10.setDuration(Duration.millis(500)); 
	  	rotate10.setNode(droneImage);
	  	
	    TranslateTransition s12 = new TranslateTransition(); 
	    s12.setToX(-380); 
	    s12.setToY(350); 
	    s12.setDuration(Duration.millis(1000)); 
	  	s12.setNode(droneImage);
	  	
	    RotateTransition rotate15 = new RotateTransition(); 
	    rotate15.setByAngle(90); 
	    rotate15.setDuration(Duration.millis(500)); 
	    rotate15.setNode(droneImage);
	    
	    TranslateTransition s17 = new TranslateTransition();
	    s17.setToX(0); 
	    s17.setToY(0); 
	    s17.setDuration(Duration.millis(1500)); 
	  	s17.setNode(droneImage);	  
	  	
	    
	  	SequentialTransition s = new SequentialTransition(
	  			s1, rotate0,s2, rotate1 ,s7, rotate6, s8, rotate7 ,s9, rotate8, s10, 
	  			rotate9 ,s11, rotate10, s12, rotate15, s17); 
	  	
	  	s.play(); 
	}

}
