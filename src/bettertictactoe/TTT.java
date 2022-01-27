package bettertictactoe;

import java.util.Scanner;
import bettertictactoe.Gegner.Move;

public class TTT {

	public TTT() {
	}

	public static void main(String[] args) {
		
		char [] [] spielbrett = new char[3][3]; 
	    char [] spieler = {'X','O'};
	    int dran;
	    int runde =0;
	    boolean playagain = true;
	    boolean beendet = false;
	    boolean gewonnen = false;
	    System.out.print("Tippe 1 um gegen ein Ki zu spielen");
	    Scanner sc2 = new Scanner(System.in);
		String einzelspieler= sc2.next();
		
		int y = Integer.parseUnsignedInt(einzelspieler.replace(" ", ""));
		
	    fuelleSpielbrett(spielbrett);
	    while (playagain == true)
	    {
	         while(beendet != true)
	          {
	           	draw(spielbrett, spieler, runde, 0);
	           	if(y == 1)
	    		{
	           		gegner(spielbrett,spieler);
	    		}
	    		else
	    		{
	    			draw(spielbrett, spieler, runde, 1);
	    		}
	           	
	            checkende(runde, checkwin(spielbrett, gewonnen) , beendet, spieler, runde);	
	    	    runde ++;
	    	    if(checkwin(spielbrett, gewonnen) == true)
	    	    {
	    		   beendet = true;
	    	       playAgain(gewonnen);
	    	      
	     	    }
	          } 
	    }
		
	}

	public static void gegner(char[][] spielbrett, char[] spieler)
	{
		Gegner gegner = new Gegner();
		Move bestMove = gegner.findBestMove(spielbrett);
		System.out.printf("The Optimal Move is :\n");
	    System.out.printf("ROW: %d COL: %d\n\n", 
	               bestMove.row, bestMove.col );
	    spielbrett [bestMove.row] [bestMove.col] = spieler [1];

	 // spielbrett anzeigen
	 		for (int i=0; i<spielbrett.length; i++)
	 		{
	 			System.out.print("|");
	 			for (int k =0; k<spielbrett[0].length; k++)
	 			{
	 			   	System.out.print(spielbrett[i] [k]+ "|");
	 			}
	 			System.out.println();
	 		}	  
	    
	    
	}
	
	public static void playAgain(boolean playagain)
	{
		System.out.println();
		System.out.print("Nochmal?");
		System.out.println();
		System.out.print("Tippe 1 um nochmal zu spielen oder tippe 2 um aufzuhören");
		Scanner sc2 = new Scanner(System.in);
		String eingabe2= sc2.next();
		int y = Integer.parseUnsignedInt(eingabe2.replace(" ", ""));
		if(y == 1)
		{
			playagain = true;
			main(null);
		}
		else
		{
			playagain = false;
		}
	}
	
	
	
	public static void fuelleSpielbrett(char[][] spielbrett)
	{
		for (int z=0; z< spielbrett.length; z++)
		{
			for (int s=0; s< spielbrett[z].length; s++)
			{
				
				spielbrett [z][s] = '_';
			}
		}
	}
	
	
	
	public static void draw(char[] [] spielbrett, char[] spieler, int runde, int dran)
	{

		 System.out.println();   	
		 System.out.println( "Spieler " + spieler[ dran] + " ist dran!");
		        
		Scanner sc = new Scanner(System.in);
		System.out.print("Zeile  (0<=z<=2), z = ");
		String eingabe= sc.next();
		int z1 = Integer.parseUnsignedInt(eingabe.replace(" ", "")) - 1;
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Spalte  (0<=z<=2), s = ");
		String eingabe1= sc1.next();
		int s = Integer.parseInt(eingabe1.replace(" ", "")) - 1;
				
		//stelle brett [z] und [s] = spieler X ode O
		spielbrett [z1] [s] = spieler [dran];
		// spielbrett anzeigen
		for (int i=0; i<spielbrett.length; i++)
		{
			System.out.print("|");
			for (int k =0; k<spielbrett[0].length; k++)
			{
			   	System.out.print(spielbrett[i] [k]+ "|");
			}
			System.out.println();
		}	    	   
	}
	
	
	
	public static boolean checkwin(char[][] spielbrett, boolean gewonnen) {
	  
	    //Überprüfen-> wenn für z anstelle [z][0] nicht gleich 0 ist und dann an die stllen Z=Z und s =1,2 das gleiche ist dann spiel gewonnen
	
	   for (int zeile = 0; zeile<spielbrett.length; zeile++)
	   {
	     if (spielbrett [zeile] [0] != '_' &&
	         spielbrett [zeile] [0] == spielbrett [zeile] [1] &&
	         spielbrett [zeile] [0] == spielbrett [zeile] [2])
	         {
	 	       gewonnen = true;  
	         }
	   }
	   
	   // Z= 0,1,2 und s=s gewonnen=true
	   for(int s1 =0; s1< spielbrett[0].length; s1++)
	   {
	     if (spielbrett [0] [s1] != '_' &&
		     spielbrett [0][s1] == spielbrett [1] [s1] &&
		     spielbrett [0][s1] == spielbrett [2] [s1])
		     {
			  gewonnen = true;
		     }
	   }
	   
	   //Stelle 0,0 ; 1,1; 2,2 gleich dann gewonnwn =true
	   if (spielbrett [0] [0] != '_' &&
	       spielbrett [0] [0] == spielbrett [1] [1] &&
	       spielbrett [0] [0] == spielbrett [2] [2])
		   {
			  gewonnen = true;
		   }
	   
	   //Stelle 2,0; 1,1; 0,2 gleich dann gewonnen = true
	   if (spielbrett [2] [0] != '_' &&
	       spielbrett [2] [0] == spielbrett [1] [1] &&
	       spielbrett [2] [0] == spielbrett [0] [2])
		   {
			  gewonnen = true;			
		   }
	   
		return gewonnen;

	}
	
	
	
	public static void checkende(int runde, boolean gewonnen, boolean beendet, char[] spieler, int dran)
	{
		 /*if(runde%2 == 0)
		 {
			 dran =0;
		 }
		 else
		 {
			 dran =1;
		 }*/
		    	
		  if( runde == 9 || gewonnen == true)
		     {
			   beendet = true;
		     }
		   
		  runde++;
	    
		  if(beendet == true)
		  {
		     //siegerauswertung
		     if ( gewonnen == true)
		     {
		    	 System.out.println("Sieger: Spieler" + spieler[dran]);
		     }
		     else
		     {
		         System.out.println( "Unentschieden!");
		     }
		     
          //spiel beenden
	         System.out.println( "Spiel beendet");
		   }
		
	}
	
}
