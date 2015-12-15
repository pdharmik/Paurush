function coordinates(xCo,yCo,flag){
	var xCoordinate="";
	var yCoordinate="";
	var seperator="/";
	if(!(xCo && yCo))
	{
			seperator="";
	}
	if(xCo){xCoordinate=xCo;}
	if(yCo){yCoordinate=yCo;}
		
	$('#addressCoords').html(xCoordinate+seperator+yCoordinate);
	$('#addressCoordinatesXPreDebriefRFV').val(xCoordinate);
	$('#addressCoordinatesYPreDebriefRFV').val(yCoordinate);
}