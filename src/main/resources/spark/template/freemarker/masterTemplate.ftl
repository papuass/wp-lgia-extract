<#macro masterTemplate title="Welcome">
<!doctype html>
<html lang="lv">
	<head>
	    <meta charset="utf-8">
	    <title>${title}</title>	    
	    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  		<link rel="stylesheet" type="text/css" href="css/style.css">	    
	</head>
    <body>
	  	<div class="container">
	  		<#nested />
	  	</div>
    </body>
</html>
</#macro>