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
 
 function getStartIndex () {

     var startIndex = document.getElementById('startIndex').value;
     if(startIndex.length<1){
    	document.getElementById('startIndex').value=0;
    	startIndex=0;
    }
    return startIndex;
 }
function getNumOfRefTermsToRetrieve () {

    var numOfRefTermsToRetrieve = document.getElementById('numOfRefTermsToRetrieve').value;
    if(numOfRefTermsToRetrieve.length<1){
     	document.getElementById('numOfRefTermsToRetrieve').value=200;
     	numOfRefTermsToRetrieve=200;
    }
    return numOfRefTermsToRetrieve;
 }
 function getRefTermQuery () {

 var refTermQuery = document.getElementById('refTermQuery').value;
    if(refTermQuery.length<=0){
     	document.getElementById('refTermQuery').value="";
     	refTermQuery="";
    }
    return refTermQuery;
 }
 
 function blankRefQueryText(){
 document.getElementById('refTermQuery').value="";
 }

 </script>
 
 <script type="text/javascript">
jQuery(function() {  
jq.getJSON('${ ui.actionLink("conceptmanagementapps", "browseReferenceTermsTable", "getInitialDataTablePage") }',
                    {
                        'startIndex': getStartIndex(),
                        'sourceId': getSourceId(),
                        'numOfRefTermsToRetrieve': getNumOfRefTermsToRetrieve()
                        
                    })
                    .success(function(data) {
 						jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    					jQuery('#example').dataTable( {
    						"sPaginationType": "four_button",
        					"aaData": data,
        					"aoColumns": [
            				{ "sTitle": "source" },
            				{ "sTitle": "code" },
            				{ "sTitle": "name" },
           					{ "sTitle": "description"}
        					]
   					 	} );   
                   	})
                    .error(function(xhr, status, err) {
                        alert('Reference Term AJAX error' + err);
                    });
 });
</script>

<script type="text/javascript">
 jQuery(function() {
 
  jQuery('[name=sourceList]').change(function () {
           if (document.getElementById('refTermQuery').value.length > 1){
          	jQuery('#refTermQuery').keyup(); 
          	return;
          }
          
    jq.getJSON('${ ui.actionLink("conceptmanagementapps", "browseReferenceTermsTable", "retrieveNewPages") }',
                    {
                        'startIndex': getStartIndex(),
                        'sourceId': getSourceId(),
                        'numOfRefTermsToRetrieve': getNumOfRefTermsToRetrieve()
                    })
                    .success(function(data) {
 
 						jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    					jQuery('#example').dataTable( {
    						"sPaginationType": "four_button",
        					"aaData": data,
        					"aoColumns": [
            				{ "sTitle": "source" },
            				{ "sTitle": "code" },
            				{ "sTitle": "name" },
           					{ "sTitle": "description"}
        					]
   					 	} );   
                   	})
                    .error(function(xhr, status, err) {
                        alert('Reference Term AJAX error' + err);
                    });
             });
             
jQuery("#retrieveNewPages").click(function () {
          if (document.getElementById('refTermQuery').value.length > 1){
          	jQuery('#refTermQuery').keyup(); 
          	return;
          }
          
    jq.getJSON('${ ui.actionLink("conceptmanagementapps", "browseReferenceTermsTable", "retrieveNewPages") }',
                    {
                        'startIndex': getStartIndex(),
                        'sourceId': getSourceId(),
                        'numOfRefTermsToRetrieve': getNumOfRefTermsToRetrieve()
                    })
                    .success(function(data) {
 
 						jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    					jQuery('#example').dataTable( {
    						"sPaginationType": "four_button",
        					"aaData": data,
        					"aoColumns": [
            				{ "sTitle": "source" },
            				{ "sTitle": "code" },
            				{ "sTitle": "name" },
           					{ "sTitle": "description"}
        					]
   					 	} );   
                   	})
                    .error(function(xhr, status, err) {
                        alert('Reference Term AJAX error' + err);
                    });
             });

jQuery('#refTermQuery')
    .unbind('keypress keyup')
    .bind('keypress keyup', function(e){
          if (jQuery(this).val().length < 1 && e.keyCode != 13){
          	jQuery('#retrieveNewPages').trigger('click'); 
          	return;
          }
            jq.getJSON('${ ui.actionLink("conceptmanagementapps", "browseReferenceTermsTable", "searchForReferenceTerms") }',
                    {
                        'refTermQuery': getRefTermQuery(),
                        'startIndex': getStartIndex(),
                        'sourceId': getSourceId(),
                        'numOfRefTermsToRetrieve': getNumOfRefTermsToRetrieve()
                        
                    })
                    .success(function(data) {
                    jQuery('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
    					jQuery('#example').dataTable( {
    						"sPaginationType": "four_button",
        					"aaData": data,
        					"aoColumns": [
            				{ "sTitle": "source" },
            				{ "sTitle": "code" },
            				{ "sTitle": "name" },
           					{ "sTitle": "description"}
        					]
   					 	} );   
                   	})
                    .error(function(xhr, status, err) {
                        alert('Reference term search AJAX error' + err);
                    });        
    });
});
</script>

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

<fieldset>
	<p><label>${ui.message("conceptmanagementapps.browsereferenceterms.startindex.label")}</label>
		<input class="required" type="text" id="startIndex" size="4" value="0"/>
	</p>
	<p>
		<label>${ui.message("conceptmanagementapps.browsereferenceterms.numrefterms.label")}</label>
		<input class="required" type="text" id="numOfRefTermsToRetrieve" size="4" value="200"/>
	</p>
	<input type="button" id="retrieveNewPages" value="Reload"/>
</fieldset>

<fieldset>
	<p>
		<label>${ui.message("conceptmanagementapps.browsereferenceterms.reftermquery.label")}</label>
		<input type="text" id="refTermQuery" size="25" />
	</p>
</fieldset>



