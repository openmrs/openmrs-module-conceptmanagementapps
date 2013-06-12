<%
    
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("uicommons", "navigator/validators.js", Integer.MAX_VALUE - 19)
    ui.includeJavascript("uicommons", "navigator/navigator.js", Integer.MAX_VALUE - 20)
    ui.includeJavascript("uicommons", "navigator/navigatorHandlers.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/navigatorModels.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/exitHandlers.js", Integer.MAX_VALUE - 22);
    ui.includeCss("uicommons", "emr/simpleFormUi.css", -200)
%>
${ ui.includeFragment("uicommons", "validationMessages")}

<script type="text/javascript">
    jQuery(function() {
        KeyboardController();
    });
</script>



<div>
    <h2>
        temp Concept Management app label
    </h2>

	<form method="post" encType="multipart/form-data">
  		<fieldset>
   			<legend>
      			<i class="icon-class-if-needed"></i>
 					Upload Missing Concept Mappings
    		</legend>
    		<p class="input-position-class">
    			<b>File to upload jennTest: <input type="file" name="spreadsheet"/></b>
    			<div id="confirmation">
            		<div id="submit">
              			<p style="display: inline"><input type="submit" class="confirm" value="Submit" /></p>
            		</div>
    			</div>
    		</p>
		</fieldset>
	</form>
	</div>
    


	