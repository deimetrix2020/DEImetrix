<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<!--[if lt IE 9]>
    <script src="resources/js/html5shiv.js"></script>
    <script src="resources/js/respond.min.js"></script>
  <![endif]-->
 <html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <link rel="icon" type="image/png" href="resources/img/fav_icon.png" />
  <!--- Bootstrap CSS File-------->
  <link rel="stylesheet" type="text/css" href="resources/web-plugins/jquery/daterangepicker.css" />
  <link rel="stylesheet" type="text/css" href="resources/css/menu_styles.css" />
  <link rel="stylesheet" type="text/css" href="resources/web-plugins/Validation_Engine/validationEngine.jquery.css"/>
  <link rel="stylesheet" type="text/css" href="resources/web-plugins/bootstrap/tags/bootstrap-tagsinput.css">
  <link rel="stylesheet" type="text/css" href="resources/web-plugins/bootstrap/css/bootstrap.min.css">
  <!------ Fonts CSS File--------->
  <link rel="stylesheet" type="text/css" href="resources/web-plugins/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <!------ Login CSS File--------->
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">
  <!---- Datatable css file------>
   <link rel="stylesheet" type="text/css" href="resources/web-plugins/datatable/datatable.css">
   <link rel="stylesheet" type="text/css" href="resources/web-plugins/datatable/responsive.datatable.css">
   <!----- Alert box design ----->
	<link rel="stylesheet" type="text/css" href="resources/web-plugins/alert/alert.css">
	<link rel="stylesheet" type="text/css" href="resources/web-plugins/loader/css/preloader.css">
</head>
<body>
	<!--- Jquery File-------->
	<script type="text/javascript" src="resources/web-plugins/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="resources/web-plugins/jquery/moment.min.js"></script>
	<script type="text/javascript" src="resources/web-plugins/jquery/daterangepicker.min.js"></script>
	<script type="text/javascript" src="resources/js/all.min.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="resources/js/menu_scripts.js"></script>
	<!---- Bootstrap Js File ------->
	<script type="text/javascript" src="resources/web-plugins/Validation_Engine/languages/jquery.validationEngine-en.js"></script>
	<script type="text/javascript" src="resources/web-plugins/Validation_Engine/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="resources/web-plugins/bootstrap/js/popper.min.js"></script>
	<script type="text/javascript" src="resources/web-plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/web-plugins/bootstrap/js/bootstrap-filestyle.min.js"></script>
	<!---- Datatable------>
	<script type="text/javascript" src="resources/web-plugins/datatable/datatable.js"></script>
	<script type="text/javascript" src="resources/web-plugins/datatable/bootsrap.datatable.js"></script>
	<script type="text/javascript" src="resources/web-plugins/datatable/responsive.datatable.js"></script>
	<!---- Alert jS file ------>
	<script type="text/javascript" src="resources/web-plugins/alert/alert.js"></script>
	<script type="text/javascript" src="resources/web-plugins/loader/js/jquery.preloader.min.js"></script>
	<script type="text/javascript" src="resources/web-plugins/bootstrap/tags/bootstrap-tagsinput.js"></script>
	<script type="text/javascript">
    	var idleTime = 0;
        $(document).ready(function () {
            //Increment the idle time counter every minute.
            var idleInterval = setInterval(timerIncrement, 60000); // 1 minute

            //Zero the idle timer on mouse movement.
            $(this).mousemove(function (e) {
                idleTime = 0;
            });
            $(this).keypress(function (e) {
                idleTime = 0;
            });
        });

        function timerIncrement() {
            idleTime = idleTime + 1; 
            if (idleTime > 19) { // 20 minutes
                window.location.reload();
            }
        }

     	// right click disable 
    	/* document.onmousedown=disableclick;
    	status="Right Click Disabled";
    	function disableclick(e)
    	{
    	  if(event.button==2)
    	   {
    	     return false;
    	   }
    	}

    	$(document).bind("contextmenu",function(e) {
    		 e.preventDefault();
    		});

    	$(document).keydown(function(e){
    	    if(e.which === 123){
    	       return false;
    	    }
    	}); */
    </script>
</body>
</html>