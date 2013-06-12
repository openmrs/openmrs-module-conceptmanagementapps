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
<script>
function captureId(id)document.getElementById("sourceId").value=id
</script>
<div>
    <h2>
        temp Concept Management app label
    </h2>

	<form method="post">
  		<fieldset>
    		<legend>
      			<i class="icon-class-if-needed"></i>
 				Download Missing Concept Mappings for Selected Source
    		</legend>
    		<p class="input-position-class">
				<div>
				<b>Select a source to find missing mappings:</b> 
				<input type="hidden" name="sourceId" id="sourceId" value="test"/>
					<select id="sourceSelection" onchange="javascript:captureId(this.value)">
						<option>Select a Source</option>
						<% sourceList.each { source -> %> 
							<option value=${source.id}>${source.name}</option>
						<%}%> 
					</select>
				</div>
				<div></br></br></br>
					<b>Select classes to include:</b></br>
					<% classList.each { classlist -> %> 
						${classlist.name}<input type="checkbox" value=${classlist.id}/></br>
					<%}%> 
				</div>
	    		<div id="confirmation">
            		<div id="submit">
              			<p style="display: inline"><input type="submit" class="confirm" value="Submit" /></p>
            		</div>
    			</div>
    		</p>
		</fieldset>
	</form>
</div>