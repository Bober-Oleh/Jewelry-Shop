document.querySelector('#selectNumber').addEventListener('change',sendNumber);
function sendNumber(){
	document.querySelector('#numberPages').submit();
}


document.querySelectorAll('.buy').forEach((elem)=> elem.addEventListener('click',sendTarget));
function sendTarget(event){
	event.preventDefault();
	let button=event.target;
	let id=button.parentNode.querySelector('.prodId').value;
	sendFetch(id);
}

async function sendFetch(id){
	let url="http://localhost:8080/JewelryShop/AddTargetServlet?targetId="+id;
	let promise=await fetch(url);
	let text=await promise.text();
	alert(text);
	
}



