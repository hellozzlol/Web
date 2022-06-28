/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {


	let tbl = document.querySelector('#show table');
	let capt = document.createElement('caption')
	capt.innerHTML = '회원리스트';
	tbl.append(capt);

	//리스트출력.
	let ajax = new XMLHttpRequest();
	ajax.open('get', 'member?cmd=list');
	ajax.send();
	ajax.onload = function() {
		let data = JSON.parse(this.responseText)

		let tbody = document.querySelector('#show tbody')
		data.forEach(member => {
			tbody.append(makeTr(member));
		})
	}

	//form 기본 기능 =>ajax처리.(XMLHttpRequest,fetch)
	document.forms.memberFrm.addEventListener("submit", function(e) {
		e.preventDefault();
		let formData = new FormData(e.target);// e.target = form
		for (let ent of formData.entries()) {
			console.log(ent);
		};
		fetch('memberservlet', {
			method: 'post',
			body: formData
		})//get : url,post : 추가정보 지정.
			.then(function(result) {
				return result.json();
			})
			.then(function(result) {
				console.log(result);
			})
			.catch(function(err) {
				console.error(err);
			})
	})
});



let fields = ['membno', 'membname', 'membphone', 'membaddr', 'membbirth', 'membImage'];

//회원정보 => tr생성
function makeTr(member) {
	// console.log(member);
	let tr = document.createElement('tr');
	//tr.addEventListener('click', showDetail );
	tr.setAttribute('id', member.membno);//tr 의 아이디 값 활용
	//tr.addEventListener('click',showDetail)
	fields.forEach(field => {
		let td = document.createElement('td');
		//null,0, undefined,''=>false 이외 true
		td.innerHTML = member[field] ?
			(field == 'membImage' ? '<img width = "60px" src = "images/'
				+ member[field] + '">' : member[field]) : '';
		tr.append(td);
	});

	//삭제버튼.
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', rowDelete, false);
	let td = document.createElement('td');
	td.append(btn);
	tr.append(td);
	//체크박스. input type = 'checkbox'
	let ck = document.createElement('input');
	ck.setAttribute('type', 'checkbox');
	td = document.createElement('td');

	td.append(ck);
	tr.append(td);
	return tr;
}

function rowDelete() {
	let delId = this.parentElement.parentElement.getAttribute('id');
	let formData = new FormData();
	formData.append("cmd","delete");
	formData.append("delId",delId);
	//id = 32&name = hong
	fetch('memberservlet',{
		method : 'post',
		headers:{'Content-type' : 'application/x-www-form-urlencoded'},
		body : `cmd=delete&delId=${delId}`
	})
	.then(function(result){
		return result.json();
	})
	.then(function(result){
		console.log(result)
		//화면에서 지우도록 기능 추가.
		
		
	})
	.catch(function(err){
		console.log(err)
	})
	
	
}

