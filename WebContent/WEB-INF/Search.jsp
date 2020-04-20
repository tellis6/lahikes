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

<style>
html { 
    background: rgb(2,0,36);
	background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(38,38,138,1) 0%, rgba(52,74,156,1) 12%, rgba(0,212,255,1) 100%, rgba(0,212,255,1) 100%);
}

body {
    color: white;
    background-color: transparent;
    font-family: monospace;
}

body #page-container {
    margin-top: 150px;
    text-align: center;
    margin-bottom: 40px;
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
    float: left;
    width: 250px; 
    display: block;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 4px;
   
  }
  
.infos
  {
    color: white;
    float: left;
    width: 800px;
    margin-bottom: 15px;
    line-height: 20px;
    font-size: 18px;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  }
  
h2#page-header {
    padding-top:20px;
    padding-bottom:20px;
}

</style>

    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
    var inputLocation;
    function myFunction() 
    {
        console.log('in myFunction');
        inputLocation = document.getElementById("inputLocation").value;
        getMapBoxAPI(inputLocation);
    }
    /*  Sends location to Map API and fetches long & lat, then sends to Hiking API
        Need to restrict user input to LA area
    */
    async function getMapBoxAPI(inputLocation) 
    {
        console.log('in mapBox '+ inputLocation);
        const map_url = 'https://api.mapbox.com/geocoding/v5/mapbox.places/' + inputLocation + '.json?access_token=pk.eyJ1IjoidnNhdSIsImEiOiJjazZ5bDN0MGgweXloM2VzNDhvbDdtMGo0In0.bXnlG8XVJ7kvSYR0zQPuRg';
        const response = await fetch(map_url);
        const data = await response.json();
        var lon = data.features[0].center[0];
        var lat = data.features[0].center[1];
        console.log(data.features[0].center[1]);
        console.log(data.features[0].center[0]);
        getHikingProjectAPI(lon, lat);
    }

    /*  Gets long & lat from Map API, send to Hiking API, store the fetched data
        Currently only limited to 10 results
    */
    async function getHikingProjectAPI(lon, lat) 
    {
        console.log('in getHikes');
        const hike_url = 'https://www.hikingproject.com/data/get-trails?lat='+lat+'&lon='+lon+'&maxDistance=10&key=200690853-3205a9c34469dd802461d0957a657bc9';
        const response = await fetch(hike_url);
        const data = await response.json();
        console.log(data);
        showContent(data);
    }

    //  Outputs the Hiking data, images to webpage.
    async function showContent(data) 
    {
        console.log('in showContent');
        cap = inputLocation;
        var cap = cap.toLowerCase().split(" ");
          for(var i = 0; i< cap.length; i++)
          {
             cap[i] = cap[i][0].toUpperCase() + cap[i].slice(1);
          }
            inputLocation = (cap.join(" "));
        
        var wrapper = document.getElementById("results");
        var myHTML = '';
        if(data.trails.length==0)
        {
            myHTML = myHTML + `No Results for \${inputLocation}<br>`;
        }
        else{
            myHTML = `Trails in \${inputLocation} <br><br>`;
        }
        
        
        for (var i = 0; i < data.trails.length; i++) //if show error, can ignore
        {
            myHTML = myHTML + 
            `
         <div class=infos>
        <p align="left">
        <a href="add.html">
        <img src = \${data.trails[i].imgSmall} onerror="this.onerror=null; this.src='noimage.jpg'" alt="No Image Yet" width="250" height="180"  hspace="20">
        </a>
        <br> Trail: \${data.trails[i].name}
      	<br>Location: \${data.trails[i].location}
      	<br>Miles: \${data.trails[i].length}
      	<br>Summary: \${data.trails[i].summary}
      	<br>Stars: \${data.trails[i].stars} 
      	<br>Current Condition: \${data.trails[i].conditionStatus}
        </p>
        </div>
            `
        }
        wrapper.innerHTML = myHTML
    }
        
    </script>
</head>

<body class="container">

    <nav class="navbar navbar-expand-md navbar-dark">
        <a class="navbar-brand" href="Home"><i class="fas fa-hiking"></i> LA HIKES </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="Login">Login</a>
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
    
      <h2 id="page-header">Search For a Trail</h2>
    
    <div class="input-group mb-3">
  <input type="text" class="form-control" id="inputLocation" placeholder="Enter a Neighborhood..." aria-label="Enter a Neighborhood..." aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-secondary" onclick="myFunction()" type="submit">Search</button>
  </div>
</div>
    
    <div id ="displayResults">
        <h2 id="results"></h2>
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