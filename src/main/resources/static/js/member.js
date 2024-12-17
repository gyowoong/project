function checkDuplicateId() {
  const memberId = document.querySelector("input[name='memberId']").value;
  fetch(`/member/check-id?memberId=${memberId}`)
    .then((response) => response.json())
    .then((data) => {
      const resultElement = document.getElementById("id-check-result");
      if (data.duplicate) {
        resultElement.textContent = "이미 사용 중인 아이디입니다.";
        resultElement.style.color = "red";
      } else {
        resultElement.textContent = "사용 가능한 아이디입니다.";
        resultElement.style.color = "green";
      }
    })
    .catch((error) => console.error("Error:", error));
}

function checkDuplicateEmail() {
  const email = document.querySelector("input[name='email']").value;
  fetch(`/member/check-email?email=${email}`)
    .then((response) => response.json())
    .then((data) => {
      const resultElement = document.getElementById("email-check-result");
      if (data.duplicate) {
        resultElement.textContent = "이미 사용 중인 이메일입니다.";
        resultElement.style.color = "red";
      } else {
        resultElement.textContent = "사용 가능한 이메일입니다.";
        resultElement.style.color = "green";
      }
    })
    .catch((error) => console.error("Error:", error));
}
