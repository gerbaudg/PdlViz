package org.opencompare;
import java.io.*;
public class Fichier{
	
	public Fichier() throws IOException{
	
	File file = new File("C:/Users/Glenn/Hello1.html");
    
    // creates the file
    file.createNewFile();
    int nbFeatures=4;
    String bab = "var ctx = document.getElementById(\"myChart\");var myChart = new Chart(ctx, {"
 + "type: 'bar',"
  +"data: {"
   +"   labels: [\"Red\", \"Blue\", \"Yellow\", \"Green\", \"Purple\", \"Orange\"],"
      +"datasets: [{"
        +"  label: '# of Votes',"
         +" data: [12, 19, 3, 5, 2, 3],"
         +" backgroundColor: ["
           +"   'rgba(255, 99, 132, 0.2)',"
             +" 'rgba(54, 162, 235, 0.2)',"
             +" 'rgba(255, 206, 86, 0.2)',"
             +" 'rgba(75, 192, 192, 0.2)',"
             +" 'rgba(153, 102, 255, 0.2)',"
             +" 'rgba(255, 159, 64, 0.2)'"
       +"   ],"
          +"borderColor: ["
              +"'rgba(255,99,132,1)',"
             +" 'rgba(54, 162, 235, 1)',"
             +" 'rgba(255, 206, 86, 1)',"
             +" 'rgba(75, 192, 192, 1)',"
             +" 'rgba(153, 102, 255, 1)',"
             +" 'rgba(255, 159, 64, 1)'"
         +" ],"
         +" borderWidth: 1"
     +"}]"
  +"},"
  +"options: {"
      +"scales: {"
         +" yAxes: [{"
             +" ticks: {"
                +"  beginAtZero:true"
            +"  }"
         +" }]"
      +"}"
 +"}" 
+"});";
    
    // creates a FileWriter Object
    FileWriter writer = new FileWriter(file); 
    
    // Writes the content to the file
    writer.write("<html>"
  		  +"<head> <script src=\"Chart.js\"></script> <TITLE>Votre comparateur</TITLE></head>"
  		 +"<body>"
  		  
  		  +"<div style=\"width : 80%\">"
    		+ "<meta chartset=\"utf-8\" /> </head><h2><b> Choisissez </b> ce que vous voulez comparer</h2>"
  		  +nbFeatures+"features"
    		+"<canvas id=\"myChart\" width=\"400\" height=\"400\"> Votre navigateur ne supporte pas canvas.</canvas>"
    		+"<script>"+bab+"</script>"
    		+"</div>"
    		+ " blabla"
    		+ "fin du graf"
    		+"</body>"); 
    writer.flush();
    writer.close();

    // Creates a FileReader Object
    FileReader fr = new FileReader(file); 
    char [] a = new char[50];
    fr.read(a);   // reads the content to the array
    
 
       System.out.print("Le fichier a été généré");   
    fr.close();
 }


   public static void main(String args[])throws IOException {
      new Fichier();
}
   }