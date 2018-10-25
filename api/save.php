<?php
	$con=mysqli_connect("127.0.0.1","feewka","ChiYat2k18!!","feewka");
	$sql="INSERT INTO progress (zid, module_id, section, `order`) VALUES ('" . $_GET['zid'] . "', '" . $_GET['module_id'] . "','" . $_GET['section'] . "','" . $_GET['order'] . "') ON DUPLICATE KEY UPDATE `order`='" . $_GET['order'] . "', section='" . $_GET['section'] . "'";
	if (mysqli_query($con,$sql)) {
		echo "Success";
	}
?>
