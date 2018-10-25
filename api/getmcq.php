<?php
	// gets the answer for a specific mcq question.
	$zid = $_GET['zid'];
	$module = $_GET['module'];
	$q = $_GET['q'];
	$con=mysqli_connect("127.0.0.1","feewka","fakepassword","feewka");
	$result=mysqli_query($con,"SELECT `answer` FROM mcqquizanswers WHERE module_id='$module' and module_question='$q' and zid='$zid'");
	$row = mysqli_fetch_array($result);
	$data = $row[0];
	if ($data == 0 || $data == 1 || $data == 2 || $data == 3){
		echo $data;
	}
	mysqli_close($con);
?>
