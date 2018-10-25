<?php
	// this checks if a user even exists lol
	$zid = $_GET['zid'];
	$zpass = $_GET['zpass'];
	$con=mysqli_connect("127.0.0.1","feewka","fakepassword","feewka");
	$result=mysqli_query($con,"SELECT name FROM users where zid='$zid' and zpass='$zpass'");
	$row = mysqli_fetch_array($result);
	$data = $row[0];
	if ($data){
		echo json_encode($data);
	}
	mysqli_close($con);
?>
