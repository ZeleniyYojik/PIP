<?php

	define('ST_T', microtime());//начало 
	@$X=$_GET['XSelector'];
	@$Y=$_GET['YSelector'];//array
	@$R=$_GET['RSelector'];
	$X= str_replace(",", ".", $X); // replace ',' with '.'
	$Y= str_replace(",", ".", $Y); // replace ',' with '.'
	$R= str_replace(",", ".", $R); // replace ',' with '.'
	if (is_numeric($X) && is_numeric($Y) && is_numeric($R) && $X<=5 &&  $X>=-3 && $R>=0) {
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
			if(is_null($X)){
				echo "X not selected"."<br/>";
			}else if(!is_numeric($X)){
				echo "X must be numeric, but it is: $X"."<br/>";
			}
			if(is_null($Y)){
				echo "Y not selected"."<br/>";
			}else if(!is_numeric($Y)){
				echo "Y must be numeric, but it is: $Y"."<br/>";
			}
			if($X>5 || $X<-3){
				echo "X not in bounds [-3;5]. It is: $X"."<br/>";
			}
			if(is_null($R)){
				echo "R not selected"."<br/>";
			}else if(!is_numeric($R)){
				echo "R must be numeric, but it is: $R"."<br/>";
			}else if($R!=1 || $R!=2 || $R!=3 || $R!=4 || $R!=5){
					echo "R must be {1;2;3;4;5}, but it is: $R";
			}
		}
	function inFigure($X, $Y, $R){
		//				Первая четверть										Вторая четверть																Четвертая четверть
		return (($X>=0 && $X<=($R/2) && $Y>=0 && $Y<=$R) || ($X<=0 && $X>=(-$R/2) && $Y>=0 && $Y<=($R/2) && ($Y-$X)<=($R/2)) || ($X>=0 && $X<=($R/2) && $Y<=0 && $Y>=(-$R/2) && (($X*$X+$Y*$Y)<=($R*$R))));
	}
?> 
