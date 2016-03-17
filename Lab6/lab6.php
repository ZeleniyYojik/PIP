<?php

	define('ST_T', microtime());//начало 
	@$X=$_GET['XSelector'];
	@$Y=$_GET['YSelector'];//array
	@$R=$_GET['RSelector'];
	if (is_numeric($X) && is_numeric($Y) && is_numeric($R) && $X<5 &&  $X>-3) {
				$date = new DateTime();
		echo "<table align='center'>
		        <tr>
		            <td>X: </td>
		            <td>$X</td>
		        </tr>
		        <tr>
		            <td>Y: </td>
		            <td>$Y</td>
		        </tr>
		        <tr>
		            <td>R: </td>
		            <td>$R</td>
		         <tr>
		            <td>In area: </td>
		            <td>" . (inFigure($X, $Y, $R)? "Yes" : "No") . "</td>
		        </tr>
		    </table>";
		echo '<div style="text-align:center; margin-top:30px">Current date time '.$date->format('Y-m-d H:i:s')."</div>";
		printf("<br/>".'<div style="text-align:center">Script worked %.5f s.'.'</div>', microtime()-ST_T);//
		} else{
			echo "Wrong input!";	
		}
	function inFigure($X, $Y, $R){
		//				Первая четверть										Вторая четверть																Четвертая четверть
		return (($X>=0 && $X<=($R/2) && $Y>=0 && $Y<=$R) || ($X<=0 && $X>=(-$R/2) && $Y>=0 && $Y<=($R/2) && ($Y-$X)<=($R/2)) || ($X>=0 && $X<=($R/2) && $Y<=0 && $Y>=(-$R/2) && (($X*$X+$Y*$Y)<=($R*$R))));
	}
?> 