/**
 *  upload_jqueryjs
	 Ajax => XMLHttpRequest, fetch, jQuery.ajax() == $.ajax()
 */

$(document).ready(function() {
	// 페이지에 리스트 출력.
	//화면에 보여주는 기능
	$.ajax({
		url: 'member',
		method: 'get', // 디폴트 get 방식
		data: 'cmd=list',
		success: showList,
		error: function(err) {
			console.log(err);
		}
	});
	//추가버튼 클릭.
	$('form[name="memberFrm"]').on('submit', memberAddCallback);

});

function memberAddCallback(e) {
	e.preventDefault();
	console.log(e.target)
	let fData = new FormData(e.target);
	for (let d of fData.entries()) {
		console.log(d)
	}



	//ajax => 데이터 등록,화면에도 추가
	$.ajax({
		url: 'memberservlet',
		method: 'post',
		data: fData,
		contentType: false,
		processData: false,
		dataType: 'json',//결과 값을 json 바꾸겠다.
		Success: function(result) { // 성공시
			console.log(result);

			alert('등록되었습니다');
			let tbody = $('#show > table > tbody');
			tbody.append(makeTr(result))
		},
		error: function(err) {
			console.log(err)
        })
        
    
    

			//추가버튼 클릭.
			$('form[name="memberFrm"]').on('submit', memberAddCallback);



			function showList(result) {
				//function(result) {
				console.log(result);
				let tbody = $('#show > table > tbody');
				$(result).each(function(idx, elem) {
					// console.log(elem);
					tbody.append(makeTr(elem));
				});

			}

			function makeTr(elem) {
				console.log(elem)
				let btn = $('<button />').text('삭제'); // 삭제버튼
				btn.on('click', function() {
					// 비동기호출 fetch와 비슷한 방법 : jquery.ajax
					$.ajax({
						url: 'member',
						method: 'post',
						data: 'cmd=remove&delNo=' + elem.membno,
						success: function(result) { // 성공시
							if (result.retCode == 'Success') {
								alert('삭제되었습니다');
								$('#' + elem.membno).remove();
							} else {
								alert('처리중 에러');
							}
						},
						error: function(err) {  // 에러발생시 
							console.log(err);
						}
					})


				}); // 버튼 이벤트 등록.
				let imgSrc = elem.membImage ? '<img width="50px" src="images/' + elem.membImage + '">' : '';
				return $('<tr />').attr('id', elem.membno)
					.append($('<td />').text(elem.membno)
						, $('<td />').text(elem.membname)
						, $('<td />').text(elem.membphone)
						, $('<td />').text(elem.membaddr)
						, $('<td />').text(elem.membbirth)
						, $('<td />').html(imgSrc)
						, $('<td />').append(btn)
					)

			}

}
