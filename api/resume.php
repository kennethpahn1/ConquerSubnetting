<?php
	$zid = $_GET['zid'];
	$module_id = $_GET['module_id'];
	$con=mysqli_connect("127.0.0.1","feewka","ChiYat2k18!!","feewka");
	$result=mysqli_query($con,"SELECT section FROM progress where zid='$zid' and module_id='$module_id'");
	$row = mysqli_fetch_array($result);
	$data = $row[0];
	if ($data == 0 || $data == 1 || $data == 2 || $data ==  3){
		echo $data;
	} else{
		echo -1;
	}
	mysqli_close($con);
?>
