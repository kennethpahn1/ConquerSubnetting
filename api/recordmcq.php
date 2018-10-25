<?php
	// writes the user's mcq answer into the ddb.
	$con=mysqli_connect("127.0.0.1","feewka","fakepassword","feewka");
	$sql="INSERT INTO mcqquizanswers (zid, module_id, module_question, answer) VALUES ('" . $_GET['zid'] . "', '" . $_GET['module_id'] . "','" . $_GET['module_question'] . "','" . $_GET['answer'] . "')";
	if (mysqli_query($con,$sql)) {
		echo "Success";
	}
?>
