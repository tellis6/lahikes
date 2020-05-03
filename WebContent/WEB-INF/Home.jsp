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

h1#app-name {
    font-size:84px;
}

h2#page-header {
    padding-top:20px;
    padding-bottom:20px;
}

h3#welcome {
	padding-top:20px;
    padding-bottom:20px;
    font-size:54px;
    font-style: italic;
}

body #page-container {
    margin-top: 20px;
    text-align: center;
    margin-bottom: 20px;
}
</style>

<title>Home</title>
</head>
<body>
<div class="container" id="page-container">
<h1 id="app-name"><i class="fas fa-hiking"></i><span class="glyphicon glyphicon-hiker"></span> LA HIKES</h1>
<div>
<h2 id="page-header">
<a href="${log}"> ${log} </a>
 | <a href="Search"> Search </a>
 | <a href="Favorites"> Favorites </a>
 | <a href="AddTrails"> Add Trails </a>
 | <a href="Forums"> Forum </a>
</h2>
</div>
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
        
     <!DOCTYPE html> <!-- This whole thing is for the slideshow on home page -->
<html>
  <head>
    <title>Slider</title>
    <style>
      .slider-container {
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 1200px;
        height: 350px;
        position: relative;
        overflow: hidden;
        text-align: center;
      }
      .slide {
      width: 1200px;
      height: 400px;
      position: absolute;
      top: 0;
      left: 100%;
      z-index: 10;
      padding: 1em 1em 0;
      background-size: cover;
      background-position: 50% 50%;
      transition: left 0s 0.75s;
      }
      .menu {
      position: absolute;
      left: 0;
      z-index: 900;
      width: 100%;
      bottom: 30;
      }
      .menu label {
      cursor: pointer;
      display: inline-block;
      width: 16px;
      height: 16px;
      background: #fff;
      border-radius: 50px;
      margin: 0 0.2em 1em;
      margin-top:25px
      }
      [id^="slide"]:checked + .slide {
      left: 0;
      z-index: 100;
      transition: left 0.65s ease-out;
      }
      .slide-1 {
      background-image: url('hollywood-hiking.jpg');
      }
      .slide-2 {
      background-image: url('malibu-hiking.jpg');
      }
      .slide-3 {
      background-image: url('palos-verdes-hiking.jpg');
      }
      .slide-4{
        background-image: url('terranea-hiking.jpg');
      }
    </style>
  </head>
  <body>
    <div class="slider-container">
      <div class="menu">
        <label for="slide-dot-1"></label>
        <label for="slide-dot-2"></label>
        <label for="slide-dot-3"></label>
        <label for="slide-dot-4"></label>
      </div>
      <input id="slide-dot-1" type="radio" name="slides" checked>
      <div class="slide slide-1"></div>
      <input id="slide-dot-2" type="radio" name="slides">
      <div class="slide slide-2"></div>
      <input id="slide-dot-3" type="radio" name="slides">
      <div class="slide slide-3"></div>
      <input id="slide-dot-4" type="radio" name="slides">
      <div class="slide slide-4"></div>
    </div>
    

    
  </body>
</html>
 
        
</body>
</html>