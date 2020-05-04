<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
        crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Result</title>

<style>
html { 
    background: rgb(2,0,36);
	background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(38,38,138,1) 0%, rgba(52,74,156,1) 12%, rgba(0,212,255,1) 100%, rgba(0,212,255,1) 100%);
}

body {
    color: white;
    background-color: transparent;
}

p {
	color: white;
    float: left;
    font-size: 20px;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

h1 {
	color: white;
	float: center;
	font-size: 40px;
	font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

body #page-container {
    margin-top: 150px;
    text-align: center;
    margin-bottom: 40px;
}

a:link {
    color: white;
}

a:visited {
  color: white;
}

a:hover {
    color: hotpink;
}

.search input[type=text] 
  {
    float: center;
    padding: 12px;
    border: steelblue;
    margin-top: 20px;
    margin-left: 500px;
    font-size: 17px;
    border-style: ridge;
  }
  
h2
  {
    color: white;
    font-size: 40px;
    text-align: center;
  }
  
img
  {
    display: block;
    margin-left: 20px;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
  }
  
.infos
  {
    color: white;
    float: left;
    width: 1100px;
    margin-bottom: 15px;
    line-height: 20px;
    font-size: 20px;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  }
  
h2#page-header {
    padding-top:20px;
    padding-bottom:20px;
}

</style>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
   <script>
        async function myFunction() 
        {
            let response = await fetch(`https://www.hikingproject.com/data/get-trails-by-id?ids=${id}&key=200686226-9f6ad20bc50f64a2ef9c71adf7a43bf8`);
            let data = await response.json();
            var placeholder = document.getElementById("results");
            var myHTML = '';
            myHTML = myHTML + 
            `
            <div class="container">
            	<div class="header mr-2">
            		<h1>\${data.trails[0].name}</h1>
            	</div>
            	<br />
            	<div class="row">    
            		<div class="col-12 col-md-6">
            				<img class="img-fluid" src = \${data.trails[0].imgMedium}></img>
            		</div>
            		<br />
            			<div class="col-12 col-md-6">
            				<div class= "mt-3 mr-2">
            					<p>Location: \${data.trails[0].location}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
            					<p>Miles: \${data.trails[0].length}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
            					<p>Summary: \${data.trails[0].summary}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
        						<p>Difficulty: \${data.trails[0].difficulty}</p>
        						<br />
        					</div>
            				<div class= "mt-3 mr-2">
            					<p>Stars: \${data.trails[0].stars}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
        						<p>Votes: \${data.trails[0].starVotes}</p>
        						<br />
        					</div>
        					<div class= "mt-3 mr-2">
        						<p>Ascent: \${data.trails[0].ascent}</p>
        						<br />
        					</div>
        					<div class= "mt-3 mr-2">
        						<p>Descent: \${data.trails[0].descent}</p>
        						<br />
        					</div>
            				<div class= "mt-3 mr-2">
            					<p>Current Condition Status: \${data.trails[0].conditionStatus}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
        						<p>Current Condition Details: \${data.trails[0].conditionDetails}</p>
        						<br />
        					</div>
            			</div>
            	</div>
            </div>
            `;      
            
            placeholder.innerHTML = myHTML;
        }       
        </script>
</head>

<body onload="myFunction()" class="container">

    <nav class="navbar navbar-expand-md navbar-dark">
        <a class="navbar-brand" href="Home"><i class="fas fa-hiking"></i> LA HIKES </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${log}">${log}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Search">Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Favorites">Favorites</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AddTrails">Add Trails</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Forums">Forum</a>
                </li>
            </ul>
        </div>
    </nav>      
<br />   
     <div>
        <h2><span id="results"></span></h2>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"
        integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em"
        crossorigin="anonymous"></script>
</body>
</html>