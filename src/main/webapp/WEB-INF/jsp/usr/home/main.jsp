<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN"></c:set>

<%@ include file="../common/head.jspf"%>
<!-- <style>
  body {
    margin: 0;
    font-family: Arial, sans-serif;
  }

  .slider-container {
    overflow: hidden;
    width: 100%;
  }

  .slider {
    display: flex;
    transition: transform 0.7s ease-in-out;
  }

  .card {
    flex: 0 0 280px;
    box-sizing: border-box;
    padding: 20px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    height: 400px;
  	margin-right: -50px; /* 카드가 겹치게 나타나도록 margin을 음수로 조절 */
    position: relative;
    transition: z-index 0.5s ease-in-out; /* z-index에 대한 전환 효과 추가 */
    /* margin: 100px; /* 가로 중앙 정렬 */ */
  }
  
  .card.active {
    z-index: 2; /* 선택된 카드는 맨 위로 올라오도록 z-index를 높게 설정 */
  }

  .card:not(.active) {
    z-index: 1; /* 선택되지 않은 카드는 낮은 z-index 값을 가지도록 설정 */
  }
</style>
<script>
  let currentCard = 0;

  document.addEventListener('DOMContentLoaded', function() {
    showCard(currentCard);
    document.addEventListener('wheel', handleWheel);
    // setInterval(nextCard, 2000); // 2초마다 다음 카드로 전환
  });

  function showCard(index) { // 현재 카드를 인덱스를 기반으로 표시하는 함수
    const slider = document.querySelector('.slider');
    const cards = document.querySelectorAll('.card');

    if (index >= cards.length) {
      currentCard = 0;
    } else if (index < 0) {
      currentCard = cards.length - 1;
    } else {
      currentCard = index;
    }

    const offset = -currentCard * (350 + 10) + 'px'; // 카드 간격을 포함하여 오프셋 조정
    slider.style.transform = 'translateX(' + offset + ')';

  }

  function nextCard() { // 다음 카드를 표시하는 함수
    showCard(currentCard + 1);
  }

  function prevCard() { // 이전 카드를 표시하는 함수
    showCard(currentCard - 1);
  }
  
  function handleWheel(event) {
	    // 마우스 휠 이벤트 핸들러
	    if (event.deltaY > 0) {
	      // 아래로 스크롤 - 다음 카드로 이동
	      nextCard();
	    } else {
	      // 위로 스크롤 - 이전 카드로 이동
	      prevCard();
	    }
	    event.preventDefault(); // 부드럽게 스크롤하기 위해 기본 이벤트 막기
	  }
</script>

<body>
  <div class="slider-container">
    <div class="slider">
      <div class="card" id="card1">카드 1</div>
      <div class="card" id="card2">카드 2</div>
      <div class="card" id="card3">카드 3</div>
      <div class="card" id="card2">카드 4</div>
      <div class="card" id="card3">카드 5</div>
    </div>
  </div>
</body>
 -->
<%@ include file="../common/foot.jspf"%>