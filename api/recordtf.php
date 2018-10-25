<?php
	// writes the user's t/f answer into a db.
	$con=mysqli_connect("127.0.0.1","feewka","ChiYat2k18!!","feewka");
	$sql="INSERT INTO tfquizanswers (zid, module_id, module_question, answer) VALUES ('" . $_GET["zid"] . "', '" . $_GET["module_id"] . "','" . $_GET["module_question"] . "','" . $_GET["answer"] . "')";
	if (mysqli_query($con,$sql)) {
		echo "Success";
	}
?>
