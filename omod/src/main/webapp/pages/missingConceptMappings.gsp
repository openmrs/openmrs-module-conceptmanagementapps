<%
    
    ui.decorateWith("appui", "standardEmrPage");
    ui.includeCss("uicommons", "styleguide/index.css");
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
        ${ui.message("conceptmanagementapps.title")}
    </h2>
    
    <h3>
        ${ui.message("conceptmanagementapps.mainpage.missingconceptmappings.title")}
    </h3>
     <fieldset>
      	<legend>
       	  ${ui.message("conceptmanagementapps.downloadpage.title")}
    	 </legend>
     	<p>
        	<a href="${ui.pageLink("conceptmanagementapps", "downloadSpreadsheet")}">${ui.message("conceptmanagementapps.mainpage.downloadpage.title")}</a>
     	</p>
     </fieldset>
     
    <fieldset>
     	<legend>
       	  ${ui.message("conceptmanagementapps.uploadpage.title")}
     	</legend>
         <p>
         	<a href="${ui.pageLink("conceptmanagementapps", "uploadSpreadsheet")}">${ui.message("conceptmanagementapps.mainpage.uploadpage.title")}</a>
         </p>
     </fieldset>

    
    