async function registerMember() {
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  // 비밀번호 확인
  if (password !== confirmPassword) {
    alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
    return;
  }

  const memberData = {
    memberId: document.getElementById("memberId").value,
    password: password,
    name: document.getElementById("name").value,
    phone: document.getElementById("phone").value,
    gender: document.querySelector('input[name="gender"]:checked').value,
    birth: document.getElementById("birth").value,
    email: document.getElementById("email").value,
    address: document.getElementById("address").value,
    role: "MEMBER",
  };

  try {
    const response = await fetch("/api/members/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(memberData),
    });

    if (response.ok) {
      alert("회원 등록이 완료되었습니다.");
    } else {
      alert("회원 등록에 실패했습니다.");
    }
  } catch (error) {
    console.error("Error:", error);
    alert("서버 오류로 인해 회원 등록에 실패했습니다.");
  }
}

function validateForm() {
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  if (password !== confirmPassword) {
    alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
    return false;
  }

  if (password.length < 6) {
    alert("비밀번호는 최소 6자 이상이어야 합니다.");
    return false;
  }

  return true;
}
