<%
    
    ui.decorateWith("appui", "standardEmrPage");
    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200);
    
def sourceListMap=[]
	sourceList.each { sourcelist ->
		sourceListMap2 = [label: sourcelist.name, value: sourcelist.id]
		sourceListMap << sourceListMap2

	}

def classListMap=[]
	classList.each { classlist ->
		classListMap2 = [label: classlist.name, value: classlist.id]
		classListMap << classListMap2

	}
    
%>

${ ui.includeFragment("uicommons", "validationMessages")}

<script type="text/javascript">
    jQuery(function() {
        KeyboardController();
    });
</script>

<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message('conceptmanagementapps.downloadspreadsheet.label') }", link: "${ ui.pageLink('conceptmanagementapps', 'downloadSpreadsheet') }" }
    ];
 </script>
 <script type="text/javascript">

function validateForm() {  
	var conceptClassErrText = document.getElementById("showHideConceptClassValidationError");
	var sourceIdErrText = document.getElementById("showHideSourceIdValidationError");
	var error=0;
    conceptClassErrText.style.display = "none";
    sourceIdErrText.style.display = "none";
    var conceptClassFields = jQuery("input[name='conceptClass']").serializeArray(); 
    if (conceptClassFields.length == 0) 
    { 
    	conceptClassErrText.style.display = "block";
    	error=1;
    } 
    if(document.getElementsByName("sourceList")[0].value.length==0){
        sourceIdErrText.style.display = "block";
        error=1;
    }
    if(error==1){
    	return false;
    }
    else 
    { 
        document.downloadForm.submit();
    }
}
</script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/referenceapplication/styles/referenceapplication.css" type="text/css"/>

    <h2>
        ${ui.message("conceptmanagementapps.app.label")}
    </h2>
    <h3>
        ${ui.message("conceptmanagementapps.downloadpage.label")}
    </h3>
      
<form class="simple-form-ui" name="downloadForm" method="post">
            <div id="showHideSourceIdValidationError" style="display: none">
            	<span  class="required">(${ ui.message("emr.formValidation.messages.requiredField") })</span>
            </div>
            <fieldset>        
                ${ ui.includeFragment("uicommons", "field/dropDown", [
                label: ui.message("conceptmanagementapps.source.label"),
                formFieldName: "sourceList",
                options: sourceListMap,
                classes: ["required"],
                maximumSize: 1,
               	left: true]
               	)}
        	</fieldset>

            <fieldset>
       
            
            	<legend>${ui.message("conceptmanagementapps.class.label")}</legend>
            	<div id="showHideConceptClassValidationError" style="display: none">
            		<span  class="required">(${ ui.message("emr.formValidation.messages.requiredField") })</span>
            	</div>
            	
				<p>
				<ul class="select">
				<label  class="required"></label>
						<% classList.each { classlist -> %> 
							<li>
								<label>${ui.message(classlist.name)}</label>
								<input type="checkbox" value=${classlist.id} name="conceptClass" id="${classlist.id}"/>
							</li>
						<%}%>
				</ul>
						
				
				
				</p>
			</fieldset>
            <div id="submit">
            <p style="display: inline"><input type="button" class="confirm" value="Download" onclick="javascript:validateForm();"/></p>
			</div>
	</form>
</div>