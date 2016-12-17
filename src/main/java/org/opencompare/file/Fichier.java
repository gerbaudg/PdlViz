package org.opencompare.file;
import java.io.*;
public class Fichier{
	
	private int nbFeature; // nombre feature
	private String htmlPath = "Graph.html";
	private String jsPath = "src/js/newChart.js";
	private String jsPathRelative = "js/newChart.js";
	private String jsonPath = "json/voiture.json";
	private String idChart ="myChart";
	private String execFunction ="thisChart()";
	private String pcmPath="";
	
	public void setpcmPath(String pcm){
		pcmPath=pcm;
	}
	
	public void createHtml(int nbFeatures) throws IOException{
		String head,body,a;
		a="";
		head = "<!doctype html><html><head><meta charset=\"utf-8\">"
				+"<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\">"
				+"<TITLE>Votre comparateur</TITLE>"
				+ "<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\">"
				+"</script></head>"
				;
		for(int i =0; i<nbFeatures; i++){
			a = a+ "<button onclick=\""
				+ execFunction
				+ "\";><img src=\"img/bars-chart.png\" width=\"32px\" height=\"32px\" class=\"coinphoto\"></button>"
				;
		}
		
		body = "<body>"
				+"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>"
				+"<script src=\""
				+ jsPathRelative
				+ "\" ></script>"
				+a
				+"<button onclick=\""
				+ execFunction
				+ "\";><img src=\"img/bars-chart.png\" width=\"32px\" height=\"32px\" class=\"coinphoto\"></button>"
				+"<button onclick=\""
				+ execFunction
				+ "\";><img src=\"img/pie-chart.png\" width=\"32px\" height=\"32px\" class=\"coinphoto\"></button>"
				+ "<div style=\"width : 400px\">"
				+"<canvas id=\""
				+idChart
				+"\" height=\"400\" width=\"400\"></canvas>"

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
		String global,functions,chartFunction;
		global = "Chart.defaults.scale.ticks.beginAtZero = true;Chart.defaults.global.elements.line.borderWidth = 1;";
		functions = "function getRandomColor() {var letters = '0123456789ABCDEF';var color = '#';for (var i = 0; i < 6; i++ ) {color += letters[Math.floor(Math.random() * 16)];}return color;}";
		chartFunction = "function "
				+ execFunction
				+ " {var jsonData = $.ajax({url: '"+jsonPath+"',dataType: 'json',}).done(function (results) {var feature = \"nb de porte\";\r\n" + 
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
				//execFunction ="thisChart();";
				
				File file = new File(jsPath);
				// creates the file
				file.createNewFile();
				
		    
		    	// creates a FileWriter Object
		    	FileWriter writer = new FileWriter(file);
		    	
		    	// Writes the content to the file
		    	writer.write(global+functions+chartFunction);
		    			
		    	writer.flush();
		    	writer.close();
				
				;
	}


	public void createRadarJS (){
		//Récuperer une seule fois les features
		// POur chaque product
	}
   }