<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- This is a tag so we can use<c:for <c:if and other tags --%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
     <!-- This is to load the "Bootstrap" Framwork for styling the page and changing the site based on computer/phone display -->
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
        crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Result</title>

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

<!-- Javascript to call the hiking project api with the trail id and get the trail info from the api -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
   <script>
        async function myFunction() 
        {
            let response = await fetch(`https://www.hikingproject.com/data/get-trails-by-id?ids=${id}&key=200686226-9f6ad20bc50f64a2ef9c71adf7a43bf8`);
            let data = await response.json();
            var i = 100;
            var inputLocation
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
            					<p>Summary: \${data.trails[0].summary}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">         					
            					<p>Length: \${data.trails[0].length} miles  |  Ascent: \${data.trails[0].ascent} ft.  |  Descent: \${data.trails[0].descent} ft.</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
        						<p>Difficulty: \${data.trails[0].difficulty}  |  Rating: \${data.trails[0].stars} stars  |  Vote Total: \${data.trails[0].starVotes} votes</p>
        						<br />
        					</div>
            				<div class= "mt-3 mr-2">
            					<p>Current Condition Status: \${data.trails[0].conditionStatus}  |  Details: \${data.trails[0].conditionDetails}</p>
            					<br />
            				</div>
            				<div class= "mt-3 mr-2">
        					<p><span style="text-decoration:underline"><b><a href=\${data.trails[0].url}>Check out the Hiking Project for more information</a></b></span></p>
        					<br />
        					</div>
        					<div class= "mt-3 mr-2">
        					<p><span style="text-allign:center"><input onclick="addFavClicked(\${i})" id='\${i}' type="button" value ="Add to Favorites"></input></span></p>
        					<br />
        				</div>
            			</div>
            	</div>
            </div>
            `;      
            
            placeholder.innerHTML = myHTML;
        }
        
        function addFavClicked(i)
        {
        	document.getElementById(i).value  = 'Added';
            current = JSON.parse(localStorage.getItem('username'));

            if(current != null) //current = localstorage elemts
            {
                var obj = current + ":" + i + ":";
            }
            else
            {
                var obj = inputLocation + ":" + i + ":";
            }
            
            localStorage.setItem('username', JSON.stringify(obj)); //add trail to localstorage called username
            
            var result = JSON.parse(localStorage.getItem('username'));
            console.log(result);
            //localStorage.clear();          
        }
        
 /*        This javascript function allows user to toggle the create topic form on and off by clicking the add topic link */
        function showTopicForm(){
        	var tf = document.getElementById("topicform");
            if (tf.style.display === "none") {
            	tf.style.display = "block";
              } else {
            	  tf.style.display = "none";
              }
            }
        
/*  This function does the same for the create reply link */
        function showReplyForm(){
            var rf = document.getElementById("replyform");
            if (rf.style.display === "none") {
            	rf.style.display = "block";
              } else {
            	  rf.style.display = "none";
              }
            }   
        </script>
</head>

<body onload="myFunction()" class="container"> <!-- load the function to get hike info upon loading of the web page -->

<!-- This is the navbar with the little image "i" giphy from the fontAwesome website along with the nav setup-->
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
     <!-- This is where the trail info will show up -->
        <h2><span id="results"></span></h2>
    </div>
<br />
<!-- This will display all the topics objects from the java servlet -->
<c:forEach items="${topics}" var="topic" >
	<table class="table table-bordered">
		<thead>
			<tr><th>Author</th><th>Subject</th><th>Message</th><th>Posted On</th></tr>
		</thead>
		<tbody>
			<c:if test= "${topic.fid == param.id}">
				<tr><td>${topic.author}</td><td>${topic.subject}</td><td>${topic.content}</td><td>${topic.date}</td></tr>
			</c:if>
			<c:forEach items="${replies}" var="reply" >
				<c:if test= "${reply.tid == topic.id}">
					<tr><td>${reply.name}</td><td></td><td>${reply.message }</td><td>${reply.date}</td></tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
<br />	
<br />
<!-- This link calls the javascript function to toggle the form on and off -->
	<a href="#" id="replylink" onclick="showReplyForm();">Post Reply</a>
	<div id="replyform" style="display:none">
	<form method='post'>
    		<div class="form-group">
    			<div class="form-group row">
    				<label for="message" class="col-sm-2 col-form-label">Reply</label>
    					<div class="col-sm-10">
    						<textarea class="form-control" id="message" name="message" required></textarea>
    					</div>
    			</div>
    		</div>			
    			<input type='hidden' name='tid' value='${topic.id}' /> <!-- Hidden field here so we can reference the id in the java servlet -->
    			<button type="submit" class="btn btn-secondary">Post</button>
    	</form>	
    </div>
<br />
<br />
</c:forEach>
<br />
<br />

<!-- This link calls the javascript function to toggle the form on and off -->
	<a href="#" id="topiclink" onclick="showTopicForm();">Post Topic</a>
	<div id="topicform" style="display:none">
	<form method='post'>
        	<div class="form-group">
        		<div class="form-group row">
        			<label for="subject" class="col-sm-2 col-form-label">Subject</label>
        			<div class="col-sm-10">
        				<input type="text" class="form-control" id="subject" name="subject" required/>
        			</div>
        		</div>
        		<div class="form-group row">
        			<label for="content" class="col-sm-2 col-form-label">Message</label>
        			<div class="col-sm-10">
        				<textarea class="form-control" id="content" name="content" rows="4" required></textarea>
        			</div>
        		</div>
        	</div>
        		<input type='hidden' name='id' value='${param.id}' /> <!-- Hidden field here so we can reference the id in the java servlet -->
        		<button type="submit" class="btn btn-secondary">Post</button>
        	</form>
	</div>
	
	    <!-- 	these scripts are for bootstrap as well -->
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