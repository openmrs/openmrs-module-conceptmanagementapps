<%
    def sourceListMap=[]
    sourceListMap2 = [label: "Browse All", value: 0]
    sourceListMap << sourceListMap2
	sourceList.each { sourcelist ->
		sourceListMap2 = [label: sourcelist.name, value: sourcelist.id]
		sourceListMap << sourceListMap2

	}

%>

 <script type="text/javascript">
 function getSourceId() {
    var sourceId=document.getElementsByName("sourceList")[0].value;
    if(sourceId.length<1){
        document.getElementsByName("sourceList")[0].value=0;
    	sourceId=0;
    }
    return sourceId;
 }
 function getNumResultsToRetrieve() {
    var numResults=document.getElementById('numResults').value;
    if(numResults.length<1){
        document.getElementById("numResults").value=200;
    	numResults=200;
    }
    return numResults;
 }       
 
</script>

<script type="text/javascript"> 
jQuery(function() {
jQuery('[name=sourceList]').change(function () {
	var actionUrl='${ ui.actionLink("conceptmanagementapps", "browseTableOfReferenceTerms", "retrieveTableData") }';
	jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    jQuery('#example').dataTable( {
    	"iDisplayLength": 25,
    	"bProcessing": true,
       	"bServerSide": true,
    	"sPaginationType": "four_button",
    	"sAjaxSource": actionUrl,
    	"aoColumns": [
        	{ "sTitle": "source"},
            { "sTitle": "code" },
            { "sTitle": "name" },
           	{ "sTitle": "description"}
        	],
        "fnServerParams": function ( aoData ) {
      		aoData.push({ "name": "sourceId", "value": getSourceId()});
      		aoData.push({ "name": "numResultsToRetrieve", "value": getNumResultsToRetrieve()});
    		},
		"fnServerData": function ( sSource, aoData, fnCallback ) {
			jQuery.getJSON( sSource, aoData, function (json) {
            	var parsedJSON = jQuery.parseJSON(json);
            	fnCallback(parsedJSON)
                		
            });
         }
	});        
});   		   
jQuery('#updateRowNum').click(function () {
	var actionUrl='${ ui.actionLink("conceptmanagementapps", "browseTableOfReferenceTerms", "retrieveTableData") }';
	jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    jQuery('#example').dataTable( {
    	"iDisplayLength": 25,
    	"bProcessing": true,
       	"bServerSide": true,
    	"sPaginationType": "four_button",
    	"sAjaxSource": actionUrl,
    	"aoColumns": [
        	{ "sTitle": "source"},
            { "sTitle": "code" },
            { "sTitle": "name" },
           	{ "sTitle": "description"}
        	],
        "fnServerParams": function ( aoData ) {
      		aoData.push({ "name": "sourceId", "value": getSourceId()});
      		aoData.push({ "name": "numResultsToRetrieve", "value": getNumResultsToRetrieve()});
    		},
		"fnServerData": function ( sSource, aoData, fnCallback ) {
			jQuery.getJSON( sSource, aoData, function (json) {
            	var parsedJSON = jQuery.parseJSON(json);
            	fnCallback(parsedJSON)
                		
            });
         }
    });           
});
}); 
</script>
<script type="text/javascript">
jQuery(function() {

	var actionUrl='${ ui.actionLink("conceptmanagementapps", "browseTableOfReferenceTerms", "retrieveTableData") }';
	jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    jQuery('#example').dataTable( {
    	"iDisplayLength": 25,
    	"bProcessing": true,
       	"bServerSide": true,
    	"sPaginationType": "four_button",
    	"sAjaxSource": actionUrl,
    	"aoColumns": [
        	{ "sTitle": "source"},
            { "sTitle": "code" },
            { "sTitle": "name" },
           	{ "sTitle": "description"}
        	],

        "fnServerParams": function ( aoData ) {
      		aoData.push({ "name": "sourceId", "value": getSourceId()});
      		aoData.push({ "name": "numResultsToRetrieve", "value": getNumResultsToRetrieve()});
    		},
		"fnServerData": function ( sSource, aoData, fnCallback ) {
			jQuery.getJSON( sSource, aoData, function (json) {
            	var parsedJSON = jQuery.parseJSON(json);
            	fnCallback(parsedJSON)
                		
            });
         }
               
	});  
});
</script>
<fieldset> 
<p>
<label>Max Number Of Results To Return:</label><input type="text" id="numResults" size="4" value="200"/>
</p>
<input type="button" id="updateRowNum" value="Reload"/>
</fieldset> 
<fieldset>        
	${ ui.includeFragment("uicommons", "field/dropDown", [
		label: ui.message("conceptmanagementapps.browsereferenceterms.select.source.label"),
		formFieldName: "sourceList",
		options: sourceListMap,
		maximumSize: 1,
		left: true,
		initialValue: 0
		]
	)}
</fieldset>
</p>


