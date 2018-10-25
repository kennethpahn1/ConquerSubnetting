<?php
	// used to clear the progress if the user wishes to not resume.
	$zid = $_GET['zid'];
	$module_id = $_GET['module_id'];
	$con=mysqli_connect("127.0.0.1","feewka","ChiYat2k18!!","feewka");
	$result=mysqli_query($con,"DELETE FROM progress where zid='$zid' and module_id='$module_id'");
	$row = mysqli_fetch_array($result);
	$data = $row[0];
	$result1=mysqli_query($con, "DELETE FROM mcqquizanswers WHERE zid='$zid' and module_id='$module_id'");
	$row = mysqli_fetch_array($result1);
	$data = $row[0];
	$result2=mysqli_query($con, "DELETE FROM tfquizanswers WHERE zid='$zid' and module_id='$module_id'");
	mysqli_close($con);
?>
