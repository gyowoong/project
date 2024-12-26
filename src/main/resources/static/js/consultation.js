document.addEventListener("DOMContentLoaded", function () {
  const saveAnswerButtons = document.querySelectorAll(".saveAnswerBtn");

  saveAnswerButtons.forEach((button) => {
    button.addEventListener("click", function () {
      const consultationId = button.getAttribute("data-id");
      const answerContent = document.getElementById(
        "answer-" + consultationId
      ).value;

      if (answerContent.trim() === "") {
        alert("답변을 입력해주세요.");
        return;
      }

      // 서버에 답변을 전송하는 로직
      fetch(`/consultation/${consultationId}/answer`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ answerContent: answerContent }),
      })
        .then((response) => response.json())
        .then((data) => {
          alert("답변이 저장되었습니다!");
          console.log(data);
        })
        .catch((error) => {
          console.error("답변 저장 실패:", error);
        });
    });
  });
});
