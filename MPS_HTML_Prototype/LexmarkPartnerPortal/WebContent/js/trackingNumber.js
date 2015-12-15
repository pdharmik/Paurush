/**
 * This is a tracking logic connect to express companies.
 * 
 * receive company name and tracking number;
 * 
 */
var webPage;
function onTrackingNumberClick(carrier, trackingNumber) {
	callOmnitureAction('Ordered Parts / Returned Parts', 'Tracking Number Click');
	if (carrier == "Federal Express") {
		webPage = window.open("","Child",config = "height=300,width=700,toolbar=1,scrollbars=1,location=1,status=1,resizable=1");
		webPage.document.write("<HTML><HEAD>");
		webPage.document.write("<\/HEAD>");
		webPage.document.write("<BODY>");
		webPage.document.write(" FedEx Shipment Status page is Loading...");
		webPage.document.write("...please Wait");
		webPage.document
				.write("<FORM name='frm1' method='post' action='http://www.fedex.com/Tracking'>");
		webPage.document
				.write("<input type='hidden' name='ascend_header' value= '1'>");
		webPage.document
				.write("<input type='hidden' name='clienttype' value= 'dotcom'>");
		webPage.document
				.write("<input type='hidden' name='cntry_code' value= 'us'>");
		webPage.document
				.write("<input type='hidden' name='language' value= 'english'>");
		webPage.document
				.write("<input type='hidden' name='tracknumbers' value= '" + trackingNumber + "'>");
		webPage.document.write("<\/FORM>");
		webPage.document.write("<\/BODY><\/HTML>");
		webPage.document.frm1.submit();
		return ("CancelOperation");
	}
	if (carrier == "UPS") {
		webPage = window.open("","Child",config = "height=300,width=700,toolbar=1,scrollbars=1,location=1,status=1,resizable=1");
		webPage.document.write("<HTML><HEAD>");
		webPage.document.write("<\/HEAD>");
		webPage.document.write("<BODY>");
		webPage.document.write(" UPS Shipment Status page is Loading...");
		webPage.document.write("...please Wait");
		webPage.document
				.write("<FORM name='frm1' method='GET' action='http://wwwapps.ups.com/WebTracking/processInputRequest'>");
		webPage.document
				.write("<input type='hidden' name='sort_by' value= 'status'>");
		webPage.document
				.write("<input type='hidden' name='TypeOfInquiryNumber' value= 'T'>");
		webPage.document
				.write("<input type='hidden' name='tracknums_displayed' value= '5'>");
		webPage.document
				.write("<input type='hidden' name='InquiryNumber1' value= '"+ trackingNumber + "'>");
		webPage.document
				.write("<input type='hidden' name='AgreeToTermsAndConditions' value= 'yes'>");
		webPage.document.write("<\/FORM>");
		webPage.document.write("<\/BODY><\/HTML>");
		webPage.document.frm1.submit();
		return ("CancelOperation");
	}
	if (carrier == "Airborne") {
		webPage = window.open("","Child",config = "height=300,width=700,toolbar=1,scrollbars=1,location=1,status=1,resizable=1");
		webPage.document.write("<HTML><HEAD>");
		webPage.document.write("<\/HEAD>");
		webPage.document.write("<BODY>");
		webPage.document.write(" Airborne Shipment Status page is Loading...");
		webPage.document.write("...please Wait");
		webPage.document
				.write("<FORM name='frm1' method='post' action='http://track.dhl-usa.com/TrackByNbr.asp'>");
		webPage.document
				.write("<input type='hidden' name='txtTrackNbrs' value= '" + trackingNumber + "'>");
		webPage.document.write("<\/FORM>");
		webPage.document.write("<\/BODY><\/HTML>");
		webPage.document.frm1.submit();
		return ("CancelOperation");
	}
}