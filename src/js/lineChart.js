function lineChart() {

	//EXTRACTION JSON
    var jsonData = $.ajax({
      url: 'json/data.json',
      dataType: 'json',
    }).done(function (results) {
	
		  var labels = [], data=[];
		  //PARCOURS JSON
		  results.forEach(function(packet) {
			labels.push(packet.timestamp);
			data.push(packet.quoted);
		  });
			
		//Variable data du constructeur
		  var tempData = {
			labels : labels,
			datasets : [{
				label: "Combien de quoted",
				backgroundColor: "rgba(75,192,192,0.4)",
				fillColor             : "rgba(75,0,75,0.2)",
				strokeColor           : "rgba(75,75,75,0.2)",
				pointColor            : "rgba(75,75,75,0.2)",
				pointStrokeColor      : "rgba(75,75,75,0.2)",
				pointHighlightFill    : "rgba(75,75,75,0.2)",
				pointHighlightStroke  : "rgba(75,75,75,0.2)",
				data                  : data
			}]
		  };

      //Chart constructeur
	  var ctx = document.getElementById("lineChart").getContext("2d");
      var myLineChart =new Chart(ctx, {
		type: 'line',
		data: tempData,
		  });
    });
	
  }

lineChart();