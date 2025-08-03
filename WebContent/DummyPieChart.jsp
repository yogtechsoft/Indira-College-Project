<!DOCTYPE html>
<html>
<head>
<title>jQuery Pie Chart</title> 
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script> 
<script type="text/javascript" src="https://cdn.canvasjs.com/jquery.canvasjs.min.js"></script> 
<script type="text/javascript"> 
window.onload = function() { 
	$("#chartContainer").CanvasJSChart({ 
		title: { 
			text: "Worldwide Smartphone sales by brand - 2012",
			fontSize: 24
		}, 
		axisY: { 
			title: "Products in %" 
		}, 
		legend :{ 
			verticalAlign: "center", 
			horizontalAlign: "right" 
		}, 
		data: [ 
			{ 
				type: "pie", 
				showInLegend: true, 
				toolTipContent: "{label} <br/> {y} %", 
				indexLabel: "{y} %", 
				dataPoints: [ 
					{ label: "Samsung",  y: 30.3, legendText: "Samsung"}, 
					{ label: "Apple",    y: 19.1, legendText: "Apple"  }, 
					{ label: "Huawei",   y: 4.0,  legendText: "Huawei" }, 
					{ label: "LG",       y: 3.8,  legendText: "LG Electronics"}, 
					{ label: "Lenovo",   y: 3.2,  legendText: "Lenovo" }, 
					{ label: "Others",   y: 39.6, legendText: "Others" } 
				] 
			} 
			] 
	}); 
} 
</script> 
</head> 
<body> 
<div id="chartContainer" style="width: 100%; height: 300px"></div> 
</body> 
</html>