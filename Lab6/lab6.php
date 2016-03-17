<!-- <?php
if ($_SERVER['REQUEST_METHOD']=='GET') {
	require_once 'HTML/Table.php';
	define('ST_T', microtime());//начало 
	//@ отключаем вывод ошибок
	@$X=$_GET['XSelector'];
	@$Y=$_GET['YSelector'];//array
	@$R=$_GET['RSelector'];

	if(count($Y)==0){
		echo("Не выбрана координата Y");
		return;
	}
	//Создаем массив данных
	$data = array();
	for ($i=0; $i <count($Y) ; $i++) { 
		$data[$i]=array($X, $Y[$i], $R, "Некорректные данные");
	}

	for ($i=0; $i < count($Y); $i++) { 
		if (is_numeric($X) && is_numeric($Y[$i]) && is_numeric($R)) {
			$X=floatval($X);
			if ($X<=5 && $X>=-3 && $Y[$i]>=-2 && $Y[$i]<=2 && $Y[$i]>=-2 && $R>=1 && $R<=5) {
				if (inFigure($X,$Y[$i],$R)) {
					$data[$i][3]="Точка в области";
				}else{
					$data[$i][3]="Точка вне области";
				}
			}
		}
	}

	//создаем таблицу
	$attrs = array('width' => '600');
	$attrs['align']='center';
	$table = new HTML_Table($attrs);
	$table->setAutoGrow(true);
	$table->setAutoFill('n/a');

	//заполняем таблицу
	for ($nr = 0; $nr < count($data); $nr++) {
	  $table->setHeaderContents($nr+1, 0, (string)$nr);
	  for ($i = 0; $i < 4; $i++) {
	    if ('' != $data[$nr][$i]) {
	      $table->setCellContents($nr+1, $i+1, $data[$nr][$i]);
	    }
	  }
	}
	//Ставим атрибут всех ячеек
	$altRow = array('align' => 'center');
	$table->setAllAttributes($altRow);
	//Ставим заголовки
	$table->setHeaderContents(0, 0, '');
	$table->setHeaderContents(0, 1, 'Координата X');
	$table->setHeaderContents(0, 2, 'Координата Y');
	$table->setHeaderContents(0, 3, 'Радиус R');
	$table->setHeaderContents(0, 4, 'Результат');
	$hrAttrs = array('bgcolor' => 'silver');
	$table->setRowAttributes(0, $hrAttrs, true);
	$table->setColAttributes(0, $hrAttrs);
	//выводим таблицу и время
	echo $table->toHtml();
	echo '<div style="text-align:center; margin-top:30px">'.date("Текущие дата и время: d/m/y H:i:s")."</div>";
	printf("<br/>".'<div style="text-align:center">Время выполнения скрипта %.5f сек.'.'</div>', microtime()-ST_T);//
}
?>
<?php
	function inFigure($X, $Y, $R){
		//				Первая четверть										Вторая четверть																Четвертая четверть
		return (($X>=0 && $X<=($R/2) && $Y>=0 && $Y<=$R) || ($X<=0 && $X>=(-$R/2) && $Y>=0 && $Y<=($R/2) && ($Y-$X)<=($R/2)) || ($X>=0 && $X<=($R/2) && $Y<=0 && $Y>=(-$R/2) && (($X*$X+$Y*$Y)<=($R*$R))));
	}
?> -->