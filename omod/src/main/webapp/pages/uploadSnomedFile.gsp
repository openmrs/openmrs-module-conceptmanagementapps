<%
    
    ui.decorateWith("appui", "standardEmrPage");

    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200);
    
    ui.includeJavascript("conceptmanagementapps", "jquery.form.js");
	ui.includeCss("conceptmanagementapps","../css/jquery.ui.all.css");
	ui.includeJavascript("conceptmanagementapps","jquery.ui.core.js");
	ui.includeJavascript("conceptmanagementapps","jquery.ui.widget.js");
	ui.includeJavascript("conceptmanagementapps","jquery.ui.progressbar.js");
	ui.includeCss("conceptmanagementapps","../css/progressBar.css");
    
%>

${ ui.includeFragment("uicommons", "validationMessages")}


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
    
}
</script>

<script type="text/javascript">
jQuery(function() {
var percent = document.getElementById('percent');
jQuery('form').ajaxForm({
    beforeSend: function() {
    	if(validateForm()==false){
    		jQuery('form').preventDefault();
    	} 
    	jQuery( "#progressbar" ).progressbar({
			value: .01
		});
		jQuery("#progressbar .ui-progressbar-value").addClass("ui-corner-right");
        var percentVal = '0%';
    },
    uploadProgress: function(event, position, total, percentComplete) {
        var percentVal = percentComplete + '%';
  		jQuery("#progressbar .ui-progressbar-value").animate(
		{
			width: percentVal
		},  {queue: false});
		percent.innerHTML=percentVal;
    },
    complete: function(xhr) {
  		jQuery("#progressbar .ui-progressbar-value").animate(
		{
			width: "100%"
		}, {queue: false});
		percent.innerHTML="100%";
    }
    }); 
}); 

</script>

<style type="text/css">	

</style>
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



<div>
	<p>
		<p style="color:red">${errorMessage}</p>
	</p>
    <h2>
        ${ui.message("conceptmanagementapps.uploadsnomedfile.title")}
    </h2>

<form method="post" name="uploadsnomedfileform" encType="multipart/form-data">        
        <div id="showHideFileUploadValidationError" style="display: none">
     		<p  style="color:red" class="required">${ ui.message("emr.formValidation.messages.requiredField") }</p>
     	</div>
     	<b>${ui.message("conceptmanagementapps.uploadsnomedfile.upload.file.label")} 
     	<input type="file" id="fileupload" name="snomedFile"/></b>
    	<div id="submit">
        	<p style="display: inline"><input class="confirm" type="submit" value="Upload File"></p>
        </div>
    </form>
</br>    
<div id="progressbarWrapper" style="height:10px; " class="ui-widget-default">
	<div id="progressbar" style="height:100%;"></div>
</div>
    <div id="percent" class="percent"></div>
<div id="status"></div>

