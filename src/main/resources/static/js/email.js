// 이메일 문의 버튼 클릭 시 고객센터 게시판 열기
document
  .getElementById("emailInquiryBtn")
  .addEventListener("click", function () {
    const inquiryBoard = document.getElementById("inquiryBoard");
    inquiryBoard.style.display = "block"; // 게시판을 보이게 설정
  });

// 문의 제출 처리
document.getElementById("inquiryForm").addEventListener("submit", function (e) {
  e.preventDefault();

  // 입력한 제목과 내용 가져오기
  const subject = document.getElementById("subject").value;
  const message = document.getElementById("message").value;

  // 새 문의 항목을 게시판에 추가
  const board = document.getElementById("board");
  const newInquiry = document.createElement("div");
  newInquiry.classList.add("inquiry-item");
  newInquiry.innerHTML = `<h4>${subject}</h4><p>${message}</p>`;
  board.appendChild(newInquiry);

  // 폼 초기화
  document.getElementById("inquiryForm").reset();
});
