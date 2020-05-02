<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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

a:link {
    color: white;
}

a:visited {
  color: white;
}

a:hover {
    color: hotpink;
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
    width: 1100px;
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
<title>Favorites</title>
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
    
    <h2 id="favs"></h2>
    
    <div id ="favorites">
        <h2 id="favs"></h2>
    </div>
    <script>
    
    
    var count1 = 0;
    var count2 = 0;
    var favIndex=0;
    var arrayCount;
    var glob ='';
    userFav = new Array();
    city =  new Array();                   
    index = new Array();
    //var trailsArray = {"trails":[]};
    var myHTML = '';
 	var wrapper = document.getElementById("favs");
    start();
    
    function start()
    {
    	results = JSON.parse(localStorage.getItem('username')); //getting user's fav from local storage
    	if(results ==null) //header output added to myHTML. will add hike info below
        {
            myHTML = myHTML + `No Current Favorites <br>`;
            wrapper.innerHTML = myHTML;
        }
        else
        {
            myHTML = myHTML + `Your Current Favorites <br><br>`;
        }
    	splitResults = results.split(":");                                            //process of splitting results and putting index, name in sep arrays            
    	splitResults = splitResults.filter(splitResults => splitResults != "");                   
    	console.log(splitResults);
    
    for(var i=0; i<splitResults.length; i++) //put index and city into different arrays. Will access by same index
    {
        if(i%2==0)
        {
            city[count1] = splitResults[i];
            count1=count1+1;
        }
        else
        {
            index[count2] = splitResults[i];
            count2 = count2+1;
        }
    }

    for(var i=0; i<city.length; i++) //send each city and index to fetch hike info from mapbox, hiking API.
    {
    	getMapBoxAPI(city[i], index[i]);
    }
    }
    
    async function getMapBoxAPI(inputLocation, i) //same function as search.jsp. Did not use those function due to async function not returning until later on
    {
        console.log('in mapBox '+ inputLocation);
        const map_url = 'https://api.mapbox.com/geocoding/v5/mapbox.places/' + inputLocation + '.json?access_token=pk.eyJ1IjoidnNhdSIsImEiOiJjazZ5bDN0MGgweXloM2VzNDhvbDdtMGo0In0.bXnlG8XVJ7kvSYR0zQPuRg';
        const response = await fetch(map_url);
        const data = await response.json();
        var long = data.features[0].center[0];
        var lat = data.features[0].center[1];
        getHikingProjectAPI(long, lat, i);
    }
    async function getHikingProjectAPI(long, lat, i) 
    {
        console.log('in getHikes');
        const hike_url = 'https://www.hikingproject.com/data/get-trails?lat='+lat+'&lon='+long+'&maxDistance=10&key=200690853-3205a9c34469dd802461d0957a657bc9';
        const response = await fetch(hike_url);
        const data = await response.json();
        //console.log(data);
        showFavs(data, i);
    }
    function showFavs(data, x) //outputs all user favs
    {
    	//trailsArray['trails'].push(data.trails[x]);
    	//console.log("trail array in show " + trailsArray);
  
        myHTML = myHTML + 
         `
        <div class=infos>
        <p align="left">
        <img src = \${data.trails[x].imgSmall} onerror="this.onerror=null; this.src='noimage.jpg'" alt="No Image Yet" width="250" height="180"  hspace="20">
        </a>
        <br> Trail: \${data.trails[x].name}
      	<br>Location: \${data.trails[x].location}
      	<br>Miles: \${data.trails[x].length}
      	<br>Summary: \${data.trails[x].summary}
      	<br>Stars: \${data.trails[x].stars} 
      	<br>Current Condition: \${data.trails[x].conditionStatus}
      	<br><input onclick="removeFav(\${favIndex})" id='\${favIndex}'  type="button" value ="Remove From Favorites"></input>
        </p>
        </div>
            `    
        favIndex = favIndex+1;
        wrapper.innerHTML = myHTML
    }
    async function removeFav(favIndex) //onclick, change button to remove, make new array to hold remaining favs, refresh page as user removes
    {
    	document.getElementById(favIndex).value  = 'Removed';
    	console.log("fav index " + favIndex);
    	newFavArray = new Array();
    	newCount = 0;
		localStorage.clear();   
    	for(var i=0; i<city.length; i++)
    	{
    		if(i != favIndex) //include all favs except the removed one
    		{
    			city[newCount] = city[i];	//update glob arrays
    			index[newCount] = index[i];
    			
    			current = JSON.parse(localStorage.getItem('username'));
    			if(current != null) 	//place remaining favs back into the local storage
        		{
           		 var obj = current + city[i] + ":" + i + ":";
      		 	}
        		else
        		{
            	var obj = city[i] + ":" + i + ":";
        		}
        
        		localStorage.setItem('username', JSON.stringify(obj)); //add trail to localstorage called username
    		}
    	}
    	
    	console.log(localStorage.getItem('username'));
    	myHTML = '';		//reset html wrapper and do start() again
		location.reload();
	
    }
  
    </script>
    
    
</body>
</html>