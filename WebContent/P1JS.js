/**
 * 
 */

let elemsToHide = document.getElementsByClassName('form-register');
for(let i = 0; i < elemsToHide.length; i++) {
	elemsToHide[i].style.display = 'none';
}

function registerFunc() {
	document.getElementById('form1').setAttribute('method','post');
	let elemsToHide = document.getElementsByClassName('form-register');
	for(let i = 0; i < elemsToHide.length; i++) {
		elemsToHide[i].style.display = 'block';
		elemsToHide[i].required = 'false';
	}
}

function loginFunc() {
	document.getElementById('form1').setAttribute('method','get');
	let elemsToHide = document.getElementsByClassName('form-register');
	for(let i = 0; i < elemsToHide.length; i++) {
		elemsToHide[i].style.display = 'none';
		elemsToHide[i].required = 'true';
	}
}