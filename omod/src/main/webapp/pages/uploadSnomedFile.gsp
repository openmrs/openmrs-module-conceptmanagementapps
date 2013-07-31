<%
    
    ui.decorateWith("appui", "standardEmrPage");

    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200);

    
%>

${ ui.includeFragment("uicommons", "validationMessages")}
<script type="text/javascript" src="jquery-1.2.6.min.js"></script>
<script type="text/javascript">
    jQuery(function() {
        KeyboardController();
    }
</script>

 <script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("conceptmanagementapps.uploadsnomedfile.title") }", link: "${ ui.pageLink("conceptmanagementapps", "uploadSpreadsheet") }" }
    ];
 </script>
 <script type="text/javascript">

function validateForm() {  
	var fileToUploadErrText = document.getElementById("showHideFileUploadValidationError");
	var error=0;
    fileToUploadErrText.style.display = "none";
    if(document.getElementsByName("snomedFile")[0].value.length==0){
        fileToUploadErrText.style.display = "block";
        error=1;
    }
    if(error==1){
    	return false;
    }
    else 
    { 
        document.uploadsnomedfileform.submit();
    }
}
</script>


<div>

    <h2>
        ${ui.message("conceptmanagementapps.uploadsnomedfile.title")}
    </h2>
    <h3>
        ${ui.message("conceptmanagementapps.uploadsnomedfile.subtitle")}
    </h3>

<form method="post" name="uploadsnomedfileform">
    	 <div id="showHideFileUploadValidationError" style="display: none">
     		<p  style="color:red" class="required">${ ui.message("emr.formValidation.messages.requiredField") }</p>
     	</div>
     	<b>${ui.message("conceptmanagementapps.uploadsnomedfile.upload.file.label")} <input type="text" id="fileupload" name="snomedFile"/></b>
    	<div id="submit">
        	<p style="display: inline"><input type="button" class="confirm" id="uploadSnomedFile" value="Upload" onclick="javascript:validateForm();"/></p>
        </div>

</form>
</div>