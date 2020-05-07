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
</style>
    <title>Display Topic</title>
</head>
<body class="container">

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
    
<!--     This is the heading of the forum showing the name of the forum and topic that the user is in -->
	<div><h2><a href="<c:url value='/Topics?id=${fId}' />">${fName}</a> > ${tName}</h2></div>
	<br />
	<div>
	
<!-- 	This is a table displaying the topic and reply object information -->
	<table class="table table-bordered">
		<thead>
			<tr><th>Author</th><th>Content</th><th>Posted On</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${topics}" var="topic" >
				<c:if test= "${topic.id == param.id}">
					<tr><td>${topic.author}</td><td>${topic.content}</td><td>${topic.date}</td></tr>
				</c:if>
			</c:forEach>
			<c:forEach items="${replies}" var="reply" >
				<c:if test= "${reply.tid == param.id}">
					<tr><td>${reply.name}</td><td>${reply.message }</td><td>${reply.date}</td></tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table></div><br />
	
<!-- 	This is the form to get the reply from the user and send to the post method of the java servlet -->
	<form method='post'>
	<div class="form-group">
		<div class="form-group row">
			<label for="message" class="col-sm-2 col-form-label">Reply</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="message" name="message" required></textarea>
			</div>
		</div>
	</div>
		<button type="submit" class="btn btn-secondary">Post</button>
	</form>
	
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