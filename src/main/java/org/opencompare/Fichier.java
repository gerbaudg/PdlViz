package org.opencompare;
import java.io.*;
public class Fichier{
	
	//private int nbFeature = 4;
	private String htmlPath = "src/Graph.html";
	private String jsPath = "src/js/newChart.js";
	private String jsonPath = "json/voiture.json";
	private String idChart ="myChart";

	
	public void createHtml() throws IOException{
		String head,body;
		head = "<!doctype html><html><head><meta charset=\"utf-8\">"
				+"<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">"
				+"<TITLE>Votre comparateur</TITLE><script src=\"https://code.jquery.com/jquery-3.1.1.min.js\">"
				+"</script></head>"
				;
		body = "<body><div style=\"width : 400px\">"
				+"<h1>Projet OPENCOMPARE - VIZ</h1>" 
				+"<button onclick=\"alert('Baton')\";><img src=\"img/bars-chart.png\" width=\"32px\" height=\"32px\" class=\"coinphoto\"></button>"
				+"<button onclick=\"alert('Camembert')\";><img src=\"img/pie-chart.png\" width=\"32px\" height=\"32px\" class=\"coinphoto\"></button>"
				+"<canvas id=\""+idChart+"\" height=\"400\" width=\"400\"></canvas>"
				+"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>"
				+"<script src=\"js/newChart.js\" ></script>"
				+"</div></body></html>"
				;
		File file = new File(htmlPath);
		// creates the file
		file.createNewFile();
		
    
    	// creates a FileWriter Object
    	FileWriter writer = new FileWriter(file);
    	
    	// Writes the content to the file
    	writer.write(head+body);
    			
    	writer.flush();
    	writer.close();
 }
	
	public void createJS() throws IOException {
		String global,functions,chartFunction,execFunction;
		global = "Chart.defaults.scale.ticks.beginAtZero = true;Chart.defaults.global.elements.line.borderWidth = 1;";
		functions = "function getRandomColor() {var letters = '0123456789ABCDEF';var color = '#';for (var i = 0; i < 6; i++ ) {color += letters[Math.floor(Math.random() * 16)];}return color;}";
		chartFunction = "function radarChart() {var jsonData = $.ajax({url: '"+jsonPath+"',dataType: 'json',}).done(function (results) {var feature = \"nb de porte\";\r\n" + 
				"		var dataset = [];\r\n" + 
				"		var labels = [];\r\n" + 
				"		var data = [];\r\n" + 
				"		var dataS = [];\r\n" + 
				"		var donneeEtEffectif ={};\r\n" + 
				"		var color = '';\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		$.each(results,function(key,val) {\r\n" + 
				"			var donneeTemp = val[feature];\r\n" + 
				"			if (!(donneeTemp in donneeEtEffectif)){\r\n" + 
				"				donneeEtEffectif[donneeTemp] = 1;\r\n" + 
				"			}\r\n" + 
				"			else{\r\n" + 
				"				donneeEtEffectif[donneeTemp] ++;\r\n" + 
				"			}	\r\n" + 
				"		});\r\n" + 
				"		\r\n" + 
				"		Object.keys(donneeEtEffectif).forEach(function (key) {\r\n" + 
				"			labels.push(key);\r\n" + 
				"			data.push(donneeEtEffectif[key]);\r\n" + 
				"		});\r\n" + 
				"		\r\n" + 
				"		dataset.label = feature;\r\n" + 
				"		dataset.data = data;\r\n" + 
				"		var nombreLabels = labels.length;\r\n" + 
				"		var bColor = [];\r\n" + 
				"		var bderColor =[];\r\n" + 
				"		for (i=1; i<= nombreLabels;i++){\r\n" + 
				"			color=getRandomColor();\r\n" + 
				"			bColor.push(color);\r\n" + 
				"			bderColor.push(color);\r\n" + 
				"		}\r\n" + 
				"		dataset.backgroundColor = bColor;\r\n" + 
				"		dataset.borderColor = bderColor;\r\n" + 
				"		dataS.push(dataset);\r\n" + 
				"		\r\n" + 
				"	//Variable data du constructeur\r\n" + 
				"	var tempData = {\r\n" + 
				"			labels : labels,\r\n" + 
				"			datasets : dataS\r\n" + 
				"	};\r\n" + 
				"	console.log(tempData);\r\n" + 
				"\r\n" + 
				"      //Chart constructeur\r\n" + 
				"	 var ctx = document.getElementById(\""+idChart+"\").getContext(\"2d\");\r\n" + 
				"     var myLineChart =new Chart(ctx, {\r\n" + 
				"		type: 'radar',\r\n" + 
				"		data: tempData,\r\n" + 
				"	});\r\n" + 
				"	\r\n" + 
				"  });\r\n" +
				"}"
				;
				execFunction ="radarChart();";
				
				File file = new File(jsPath);
				// creates the file
				file.createNewFile();
				
		    
		    	// creates a FileWriter Object
		    	FileWriter writer = new FileWriter(file);
		    	
		    	// Writes the content to the file
		    	writer.write(global+functions+chartFunction+execFunction);
		    			
		    	writer.flush();
		    	writer.close();
				
				;
	}


   public static void main(String args[])throws IOException {
	   Fichier file = new Fichier();
	   file.createHtml();
	   System.out.println("Le fichier html a été généré.");
	   file.createJS();
	   System.out.println("Le fichier js a été généré.");
}
   }