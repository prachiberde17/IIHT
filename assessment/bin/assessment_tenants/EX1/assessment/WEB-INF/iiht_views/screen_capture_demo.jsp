<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="scripts/html2canvas.js"></script>
<script>
function take_screenshot()
{
 html2canvas(document.body, {  
  onrendered: function(canvas)  
  {
    var img = canvas.toDataURL()
    $.post("uploadScreenSnapShot", {data: img}, function (file){
	alert('done');
    });
  }
 });
}

function test(){
	 html2canvas(document.querySelector("#wrapper"), {
        logging: true,
        allowTaint: true
    }).then(function(canvas) {
        var dataImage = canvas.toDataURL("image/png");
        $.ajax({
            type: "POST",
            url: "uploadScreenSnapShot",
            data: { 
                data:dataImage
            }
        }).done(function(fileName) {
                alert("done");

        }); 
    });
}
</script>
<body>
<div id="wrapper">
<div id="screenshot_div">
  <button type="button" onclick="test()">Take Screenshot</button><br/>
  rfgtgtgtgt<br/>
  vgvvvfv<br/>
  <table  border="1">
  <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr> <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr> <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
  <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr> <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr> <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
  <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr> <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr> <tr >
	<td>eeee</td>
	<td>tttt</td>
  </tr>
   <tr >
	<td>eeee</td>
	<td>222222</td>
  </tr>
  </table>
  
  ytttttttttttttttttt<br/>
</div>
</div>
</body>
</html>