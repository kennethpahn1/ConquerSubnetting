<?php
	// registers a user into a db.
	$con=mysqli_connect("127.0.0.1","feewka","fakepassword","feewka");
	$sql="INSERT INTO users (zid, zpass, name) VALUES ('" . $_GET["zid"] . "', '" . $_GET["zpass"] . "','" . $_GET["name"] . "')";
	if (mysqli_query($con,$sql)) {
		echo "Success";
	}
?>
