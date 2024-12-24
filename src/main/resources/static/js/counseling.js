document.addEventListener("DOMContentLoaded", () => {
  const inquiryBtn = document.getElementById("counselingInquiryBtn");

  if (inquiryBtn) {
    inquiryBtn.addEventListener("click", (e) => {
      e.preventDefault(); // 기본 동작 방지 (폼 제출 등)
      window.location.href = "/center/counseling"; // 페이지 이동
    });
  }
});

// 취소 버튼 클릭 시 폼 숨기기
const resetButton = document.querySelector('button[type="reset"]');
resetButton.addEventListener("click", function () {
  document.getElementById("form-container").style.display = "none"; // 폼 숨기기
});

// 수정 버튼 클릭 시 폼을 업데이트 하기 위한 설정
const editButtons = document.querySelectorAll(".edit-btn");
editButtons.forEach((button) => {
  button.addEventListener("click", (e) => {
    e.preventDefault();
    const counselingId = button.getAttribute("data-id");
    document.getElementById("counseling-id").value = counselingId;
    document.getElementById("form-container").style.display = "block"; // 폼 표시
  });
});

// 삭제 버튼 클릭 시 삭제 요청
const deleteButtons = document.querySelectorAll(".delete-btn");
deleteButtons.forEach((button) => {
  button.addEventListener("click", (e) => {
    const counselingId = button.getAttribute("data-id");
    if (confirm("정말로 삭제하시겠습니까?")) {
      // 서버로 삭제 요청 (예시)
      window.location.href = `/center/counseling/delete/${counselingId}`;
    }
  });
});
