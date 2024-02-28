<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER JOIN"></c:set>
<%@ include file="../common/head.jspf"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>

.signup-form {
	width: 500px;
	background-color: #fff;
	margin: 100px auto;
	padding: 20px;
	border-radius: 8px;
}

.signup-form .text{
	font-size: 0.7rem;
	/* text-align: right; */
	margin-left: 15rem;
}

.signup-form div {
	display: inline-block;
	width: 100%;
	font-size: 1rem;
}

.signup-form label {
	width: 20%;
	display: inline-block;
}

.signup-form div .msg {
	display: inline;
	color: #800808;
	font-size: 0.75rem;
}

.signup-form input {
	padding: 8px;
	margin-bottom: 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.signup-form button {
	background-color: #800808;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 0.75rem;
}

.signup-form button:hover {
	background-color: #260301;
}

</style>

<script>
        $(function() {
            $("#birthdate").datepicker({
            	dateFormat: 'yy-mm-dd',
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-100:c+0'
        });
</script>

<script>
	function callByAjax(loginId) {
		
		var form = document.form1;
		
		var action = "../member/doAction";
		var loginId = form.loginId.value;
		
		$.get(action, {
			loginId : loginId,
		}, function(data) {
			$('.msg').text(data);
		}, 'html');
		
	}
</script>

<script>
// 골드 등급일 경우 골드 라디오 버튼에 체크 설정
function setMembershipCheckbox() {
    var membershipLevel = ${member.authLevel}; // 골드 등급이라고 가정
    
    // 해당 등급에 맞는 라디오 버튼 찾기
    var radioButtons = document.getElementsByName('level');
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].value == membershipLevel) {
            radioButtons[i].checked = true;
        }
    }
}

// 페이지 로딩 시 실행 (예시)
window.onload = setMembershipCheckbox;
</script>

<section class="mt-8 text-xl px-4">
	<div class="signup-form">
		<form name="form1" action="../member/doMembership" method="POST">
			<div class="text">*는 필수정보</div>
			<div>
				<label for="username">*아이디:</label> <input type="text" id="loginId" name="loginId" autocomplete="off" value="${member.loginId }" readonly>
			</div>
			<div>
				<label for="name">*이름:</label> <input type="text" id="mname" name="mname" autocomplete="off" value="${member.mname }" required>
			</div>
			<div>
				<label for="cellphoneNum">*전화번호:</label> <input type="tel" id="cellphoneNum" name="cellphoneNum" autocomplete="off" value="${member.cellphoneNum }" required>
			</div>
			<div>
				<label for="email">*이메일:</label> <input type="email" id="email" name="email" autocomplete="off" value="${member.email }" required>
			</div>
			<div>
				<label for="address">*주소:</label> <input type="text" id="address" name="address" autocomplete="off" value="${member.address }" required>
			</div>
			<div>
				<label for="level">*등급:</label>
		        <input type="radio" id="gold" name="level" class="form-check-input" value="1" required>
		        <label for="open" class="form-check-label mr-5 text-base">골드</label>
		        <input type="radio" id="silver" name="level" class="form-check-input" value="2" required>
		        <label for="open" class="form-check-label text-base mr-5">실버</label>
			</div>
			<div class="center-text mt-5">
				<button type="submit">멤버쉽 가입</button>
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</form>

	</div>
</section>



<%@ include file="../common/foot.jspf"%>