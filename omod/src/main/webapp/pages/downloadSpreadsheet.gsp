<%
    
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeCss("uicommons", "styleguide/index.css")
    ui.includeCss("uicommons", "styleguide/jquery.toastmessage.css")
    ui.includeCss("uicommons", "styleguide/jquery-ui-1.9.2.custom.min.css")

    ui.includeJavascript("uicommons", "jquery-1.8.3.min.js");
    ui.includeJavascript("uicommons", "bootstrap-scrollspy.js");
    ui.includeJavascript("uicommons", "typeahead.js");
    ui.includeJavascript("uicommons", "script.js");
    ui.includeJavascript("uicommons", "navigator/validators.js", Integer.MAX_VALUE - 19)
    ui.includeJavascript("uicommons", "navigator/navigator.js", Integer.MAX_VALUE - 20)
    ui.includeJavascript("uicommons", "navigator/navigatorHandlers.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/navigatorModels.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/exitHandlers.js", Integer.MAX_VALUE - 22);
    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200)
    
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
    }
 </script> 
 <script type="text/javascript">
     var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("conceptmanagementapps.downloadpage.label") }", link: "${ ui.pageLink("conceptmanagementapps", "downloadSpreadsheet") }" }
    ];
 </script>  
 <script type="text/javascript">
    function getCheckedItems(){
    	hiddenClassList = document.getElementById("classes");
    	for(var i = 0; i < document.getElementsByName("checkboxes").length; i++){
    		if(document.getElementsByName("checkboxes")[i].checked){
    		
    			if(hiddenClassList.value.length>0){
    				hiddenClassList.value=hiddenClassList.value+" or concept.conceptClass.conceptClassId = "+document.getElementsByName("checkboxes")[i].value;
    			}
   				else{
   					hiddenClassList.value=" concept.conceptClass.conceptClassId = "+document.getElementsByName("checkboxes")[i].value;
   				}
   			}
   		}
	}
	function getSourceId(){
		document.getElementById("sourceId").value = document.getElementsByName("sourceList")[0].value;
	}
</script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/referenceapplication/styles/referenceapplication.css" type="text/css"/>

    <h2>
        ${ui.message("conceptmanagementapps.app.label")}
    </h2>
    <h3>
        ${ui.message("conceptmanagementapps.downloadpage.label")}
    </h3>
      
	<form method="post">
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
				<p>
				<ul class="select">
				<label  class="required"></label>
						<% classList.each { classlist -> %> 
							<li>
								<label>${ui.message(classlist.name)}</label>
								<input type="checkbox" value=${classlist.id} name="checkboxes" id="${classlist.id}" "/>
							</li>
						<%}%> 
						<input name="classes" id="classes" type="hidden"/>
						<input name="sourceId" id="sourceId" type="hidden"/>
				</ul></p>
				<div class="before-dataCanvas"></div>
            <div id="dataCanvas"></div>
            <div class="after-data-canvas"></div>
            	<div id="submit">
              		<p style="display: inline"><input type="submit" class="confirm" value="Download" onclick="javascript:getSourceId();getCheckedItems();"/></p>
            	</div>
    		
			</fieldset>

	</form>

