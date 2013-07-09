<%
    
    ui.decorateWith("appui", "standardEmrPage");
    ui.includeCss("uicommons", "styleguide/index.css");
    ui.includeCss("uicommons", "styleguide/jquery.toastmessage.css");
    ui.includeCss("uicommons", "styleguide/jquery-ui-1.9.2.custom.min.css");
    ui.includeJavascript("uicommons", "jquery-1.8.3.min.js");
    ui.includeJavascript("uicommons", "script.js");
    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200);
      
%>

<script type="text/javascript">
    jQuery(function() {
        KeyboardController();
    });
</script>
 <script type="text/javascript">
     var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("conceptmanagementapps.missingconceptmappings.label") }", link: "${ ui.pageLink("conceptmanagementapps", "missingConceptMappings") }" }
    ];
 </script>
 <link rel="stylesheet" href="/openmrs/ms/uiframework/resource/referenceapplication/styles/referenceapplication.css" type="text/css"/>

    <h2>
        ${ui.message("conceptmanagementapps.app.label")}
    </h2>
    <h3>
        ${ui.message("conceptmanagementapps.missingconceptmappings.label")}
    </h3>
    <fieldset>
     	<legend>
       	  ${ui.message("conceptmanagementapps.uploadspreadsheet.label")}
     	</legend>
         <p>
         	<a href="${ui.pageLink("conceptmanagementapps", "uploadSpreadsheet")}">${ui.message("conceptmanagementapps.uploadpage.label")}</a>
         </p>
     </fieldset>
     <fieldset>
      	<legend>
       	  ${ui.message("conceptmanagementapps.downloadspreadsheet.label")}
    	 </legend>
     	<p>
        	<a href="${ui.pageLink("conceptmanagementapps", "downloadSpreadsheet")}">${ui.message("conceptmanagementapps.downloadpage.label")}</a>
     	</p>
     </fieldset>
    
    