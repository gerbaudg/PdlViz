Chart.defaults.scale.ticks.beginAtZero = true;Chart.defaults.global.elements.line.borderWidth = 1;function getRandomColor() {var letters = '0123456789ABCDEF';var color = '#';for (var i = 0; i < 6; i++ ) {color += letters[Math.floor(Math.random() * 16)];}return color;}function thisChart() {var jsonData = $.ajax({url: 'json/voiture.json',dataType: 'json',}).done(function (results) {var feature = "nb de porte";
		var dataset = [];
		var labels = [];
		var data = [];
		var dataS = [];
		var donneeEtEffectif ={};
		var color = '';
		
		
		$.each(results,function(key,val) {
			var donneeTemp = val[feature];
			if (!(donneeTemp in donneeEtEffectif)){
				donneeEtEffectif[donneeTemp] = 1;
			}
			else{
				donneeEtEffectif[donneeTemp] ++;
			}	
		});
		
		Object.keys(donneeEtEffectif).forEach(function (key) {
			labels.push(key);
			data.push(donneeEtEffectif[key]);
		});
		
		dataset.label = feature;
		dataset.data = data;
		var nombreLabels = labels.length;
		var bColor = [];
		var bderColor =[];
		for (i=1; i<= nombreLabels;i++){
			color=getRandomColor();
			bColor.push(color);
			bderColor.push(color);
		}
		dataset.backgroundColor = bColor;
		dataset.borderColor = bderColor;
		dataS.push(dataset);
		
	//Variable data du constructeur
	var tempData = {
			labels : labels,
			datasets : dataS
	};
	console.log(tempData);

      //Chart constructeur
	 var ctx = document.getElementById("myChart").getContext("2d");
     var myLineChart =new Chart(ctx, {
		type: 'radar',
		data: tempData,
	});
	
  });
}
// appel 2ième fonction linechart()

function lineChart() {var jsonData = $.ajax({url: 'json/voiture.json',dataType: 'json',}).done(function (results) {var feature = "nb de porte";
		var dataset = [];
		var labels = [];
		var data = [];
		var dataS = [];
		var donneeEtEffectif ={};
		var color = '';
		
		
		$.each(results,function(key,val) {
			var donneeTemp = val[feature];
			if (!(donneeTemp in donneeEtEffectif)){
				donneeEtEffectif[donneeTemp] = 1;
			}
			else{
				donneeEtEffectif[donneeTemp] ++;
			}	
		});
		
		Object.keys(donneeEtEffectif).forEach(function (key) {
			labels.push(key);
			data.push(donneeEtEffectif[key]);
		});
		
		dataset.label = feature;
		dataset.data = data;
		var nombreLabels = labels.length;
		var bColor = [];
		var bderColor =[];
		for (i=1; i<= nombreLabels;i++){
			color=getRandomColor();
			bColor.push(color);
			bderColor.push(color);
		}
		dataset.backgroundColor = bColor;
		dataset.borderColor = bderColor;
		dataS.push(dataset);
		
	//Variable data du constructeur
	var tempData = {
			labels : labels,
			datasets : dataS
	};
	console.log(tempData);

      //Chart constructeur
	 var ctx = document.getElementById("lineChart").getContext("2d");
     var myLineChart =new Chart(ctx, {
		type: 'line',
		data: tempData,
	});
	
  });
}lineChart();

// appel 3ième fonction barchart()
function barChart() {var jsonData = $.ajax({url: 'json/voiture.json',dataType: 'json',}).done(function (results) {var feature = "nb de porte";
		var dataset = [];
		var labels = [];
		var data = [];
		var dataS = [];
		var donneeEtEffectif ={};
		var color = '';
		
		
		$.each(results,function(key,val) {
			var donneeTemp = val[feature];
			if (!(donneeTemp in donneeEtEffectif)){
				donneeEtEffectif[donneeTemp] = 1;
			}
			else{
				donneeEtEffectif[donneeTemp] ++;
			}	
		});
		
		Object.keys(donneeEtEffectif).forEach(function (key) {
			labels.push(key);
			data.push(donneeEtEffectif[key]);
		});
		
		dataset.label = feature;
		dataset.data = data;
		var nombreLabels = labels.length;
		var bColor = [];
		var bderColor =[];
		for (i=1; i<= nombreLabels;i++){
			color=getRandomColor();
			bColor.push(color);
			bderColor.push(color);
		}
		dataset.backgroundColor = bColor;
		dataset.borderColor = bderColor;
		dataS.push(dataset);
		
	//Variable data du constructeur
	var tempData = {
			labels : labels,
			datasets : dataS
	};
	console.log(tempData);

      //Chart constructeur
	 var ctx = document.getElementById("barChart").getContext("2d");
     var myBarChart =new Chart(ctx, {
		type: 'bar',
		data: tempData,
	});
	
  });
}barChart();