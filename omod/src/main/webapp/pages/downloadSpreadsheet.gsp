
<html>
	<head>
		<title>GSP</title>
<script>
function captureId(id)document.getElementById("sourceId").value=id
</script>
	</head>
	<body>

<form method="post">
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
	<div></br></br></br><b>Select classes to include:</b></br>
		<% classList.each { classlist -> %> 
			${classlist.name}<input type="checkbox" value=${classlist.id}/></br>
		<%}%> 
		</div>
	    <div id="confirmation">
            <div id="submit">
              	<p style="display: inline"><input type="submit" class="confirm" value="Submit" /></p>
            </div>
    </div>
</form>
	</body>
</html>