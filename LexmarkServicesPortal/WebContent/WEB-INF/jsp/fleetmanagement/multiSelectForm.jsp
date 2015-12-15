<script id="list-multiSelect-template" type="text/x-handlebars-template">

        {{#assets}}
            		<div>
                        <input type="hidden" name="assetDetailsList['{{@index}}'].assetId" id="{{id}}_assetId"  value="{{id}}"/>
 						<input type="hidden" name="assetDetailsList['{{@index}}'].serialNumber" id="{{id}}_serialNumber" value="{{serialNumber}}" /> 
						<input type="hidden" name="assetDetailsList['{{@index}}'].productTLI" id="{{id}}_productTLI" value="{{modelNumber}}" /> 						
						<input type="hidden" name="assetDetailsList['{{@index}}'].deviceName" id="{{id}}_deviceName" value="{{productName}}" />
 						<input type="hidden" name="assetDetailsList['{{@index}}'].ipAddress" id="{{id}}_ipAddress"  value="{{ipAddress}}"/>
						<input type="hidden" name="assetDetailsList['{{@index}}'].deviceTag" id="{{id}}_deviceTag"  value="{{customerDeviceTag}}"/>
						<input type="hidden" name="assetDetailsList['{{@index}}'].account.accountId" id="{{id}}_accountId"  value="{{accountId}}"/>
				   </div> 
                  
        {{/assets}}					
</script>
<script type="text/javascript">

var multiSelectTemplate;
function createMultiSelectTemplate()
	{
	
	YUI().use('handlebars','node-base', function (Y) {
		
		if(Y.one('#list-multiSelect-template')!=null){
			
			multiSelectTemplate = Y.Handlebars.compile(Y.one('#list-multiSelect-template').getHTML());		
		}
	});
	}
</script>
