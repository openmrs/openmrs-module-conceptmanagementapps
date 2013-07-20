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
        { label: "${ ui.message("conceptmanagementapps.referenceterms.label") }", link: "${ ui.pageLink("conceptmanagementapps", "referenceTerms") }" }
    ];
 </script>
 <link rel="stylesheet" href="/openmrs/ms/uiframework/resource/referenceapplication/styles/referenceapplication.css" type="text/css"/>

    <h2>
        ${ui.message("conceptmanagementapps.title")}
    </h2>
        <h3>
        ${ui.message("conceptmanagementapps.mainpage.referenceTerms.title")}
    </h3>
     <fieldset>
      	<legend>
       	  ${ui.message("conceptmanagementapps.addterm.title")}
    	 </legend>
     	<p>
        	<a href="${ui.pageLink("conceptmanagementapps", "addTerm")}">${ui.message("conceptmanagementapps.mainpage.addterm.title")}</a>
     	</p>
     </fieldset>
     
    <fieldset>
     	<legend>
       	  ${ui.message("conceptmanagementapps.browsereferenceterms.title")}
     	</legend>
         <p>
         	<a href="${ui.pageLink("conceptmanagementapps", "browseReferenceTermsTable")}">${ui.message("conceptmanagementapps.mainpage.browsereferenceterms.title")}</a>
         </p>
     </fieldset>
     
      <fieldset>
     	<legend>
       	  ${ui.message("conceptmanagementapps.uploadsnomedfile.title")}
     	</legend>
         <p>
         	<a href="${ui.pageLink("conceptmanagementapps", "uploadSnomedFile")}">${ui.message("conceptmanagementapps.mainpage.uploadsnomedfile.title")}</a>
         </p>
     </fieldset>

    
    