<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- This is to load the "Bootstrap" Framwork for styling the page and changing the site based on computer/phone display -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
        crossorigin="anonymous">
        
<!--  Everything under the style here is css for adding specifications to classes, ids, background, etc... -->
<style>
html { 
    background: rgb(2,0,36);
	background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(38,38,138,1) 0%, rgba(52,74,156,1) 12%, rgba(0,212,255,1) 100%, rgba(0,212,255,1) 100%);
}

body {
    color: white;
    background-color: transparent;
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
    display: block;
    margin-left: 20px;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
  }
  
.infos
  {
    color: white;
    font-size: 30px;
  }
  
h2#page-header {
    padding-top:20px;
    padding-bottom:20px;
}

.file-field.medium .file-path-wrapper {
height: 3rem; }
.file-field.medium .file-path-wrapper .file-path {
height: 2.8rem; }

.file-field.big-2 .file-path-wrapper {
height: 3.7rem; }
.file-field.big-2 .file-path-wrapper .file-path {
height: 3.5rem; }

</style>
<title>Add Trails</title>
</head>
<body class="container">
	<!-- This is the navbar at the top -->
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
    
<!--     This will begin the form to send all input back to the post method of the java servlet, there we should add the data to a database table -->
    <h2 id="page-header">Add a Trail</h2>
    <form class="md-form" action='AddTrails' method='post' enctype='multipart/form-data'>
  <div class="form-row">
    <div class="col-md-4 mb-3">
      <label for="validationDefault01">Trail Name</label>
      <input type="text" class="form-control" id="validationDefault01" name="trailname" value ="trailname" placeholder="i.e. Boulder Skyline Traverse" required>
    </div>
  </div>
  <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationDefault02">Trail Summary</label>
      <textarea class="form-control" id="validationDefault02" name="summary" value="summary" placeholder="i.e. The classic long mountain route in Boulder." required></textarea>
    </div>
  </div>
  <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationDefault03">Longitude of Trailhead</label>
      <input type="text" class="form-control" id="validationDefault03" name="long" value="long" placeholder="Longitude" required>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationDefault04">Latitude of Trailhead</label>
      <input type="text" class="form-control" id="validationDefault04" name="lat" value="lat" placeholder="Latitude" required>
    </div>
  </div>
  <div class="form-row">
  	<div class="col-md-3 mb-3">
  		<label for="validationDefault05">Trail Length</label>
  		<input type="text" class="form-control" id="validationDefault05" name="miles" value="miles" placeholder="Length in miles" required>
  	</div>
  </div>
  <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationDefault06">Trail Conditions</label>
      <textarea class="form-control" id="validationDefault06" name="condition" value="condition" placeholder="i.e. Dry, Snowy, Mostly Dry, Some Mud, Icy - Spikes n poles helpful but not required" required></textarea>
    </div>
  </div>
  <div class="form-row">
  	<div class="file-field">
    	<div class="btn btn-pink btn-rounded btn-sm float-left">
      		<span><i class="fas fa-upload mr-2" aria-hidden="true"></i>Choose file</span>
      		<input type="file">
    	</div>
  	</div>
  </div>
  <div class="form-row">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
      <label class="form-check-label" for="invalidCheck2">
        Agree to terms and conditions
      </label>
    </div>
  </div>
  <button class="btn btn-secondary" type="submit">Submit form</button>
</form>
    
</body>
</html>