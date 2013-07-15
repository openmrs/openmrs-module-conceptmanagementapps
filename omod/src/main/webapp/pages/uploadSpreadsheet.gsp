<%
    
    ui.decorateWith("appui", "standardEmrPage");
    ui.includeJavascript("uicommons", "navigator/validators.js", Integer.MAX_VALUE - 19);
    ui.includeJavascript("uicommons", "navigator/navigator.js", Integer.MAX_VALUE - 20);
    ui.includeJavascript("uicommons", "navigator/navigatorHandlers.js", Integer.MAX_VALUE - 21);
    ui.includeJavascript("uicommons", "navigator/navigatorModels.js", Integer.MAX_VALUE - 21);
    ui.includeJavascript("uicommons", "navigator/exitHandlers.js", Integer.MAX_VALUE - 22);
    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200);
    
%>
${ ui.includeFragment("uicommons", "validationMessages")}

<script type="text/javascript">
    jQuery(function() {
        KeyboardController();
    }
</script>

 <script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("conceptmanagementapps.uploadpage.title") }", link: "${ ui.pageLink("conceptmanagementapps", "uploadSpreadsheet") }" }
    ];
 </script>

<div>
	<p>
		<p style="color:red">${errorMessage}</p>
	</p>
    <h2>
        ${ui.message("conceptmanagementapps.uploadpage.title")}
    </h2>
    <h3>
        ${ui.message("conceptmanagementapps.uploadpage.subtitle")}
    </h3>

<form method="post" encType="multipart/form-data">


     <p class="input-position-class">
     	<b>${ui.message("conceptmanagementapps.uploadpage.upload.file.label")} <input type="file" name="spreadsheet"/></b>
     </p>
	<p>
    	<div id="submit">
        	<p style="display: inline"><input type="submit" class="confirm" value="Upload" /></p>
        </div>

   	</p>
</form>
</div>