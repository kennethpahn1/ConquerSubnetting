<?php
	// gets where exactly in what section that a user is up to for resuming.
	$zid = $_GET['zid'];
	$module_id = $_GET['module_id'];
	$section = $_GET['section'];
	$con=mysqli_connect("127.0.0.1","feewka","ChiYat2k18!!","feewka");
	$result=mysqli_query($con,"SELECT `order` FROM progress where zid='$zid' and module_id='$module_id' and section='$section'");
	$row = mysqli_fetch_array($result);
	$data = $row[0];
	if ($data){
		echo $data;
	} else{
		echo 0;
	}
	mysqli_close($con);
?>
