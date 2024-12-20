let prev = null; //지역 클릭시 배경색 변경
document.querySelectorAll(".fw-bold").forEach((button) => {
  button.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev) {
      prev.classList.remove("clicked");
    }
    e.currentTarget.classList.add("clicked");
    prev = e.currentTarget;
  });
});

let prev2 = null; //극장 클릭시 글자색 변경
document.querySelectorAll(".fw-semibold").forEach((button) => {
  button.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev2) {
      prev2.classList.remove("clicked");
    }
    e.currentTarget.classList.add("clicked");
    prev2 = e.currentTarget;
  });
});

let prev3 = null; //날짜 클릭시 색상 변경
document.querySelectorAll(".date-btn").forEach((button) => {
  button.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev3) {
      prev3.classList.remove("clicked");
    }
    e.currentTarget.classList.add("clicked");
    prev3 = e.currentTarget;
  });
});

let prev4 = null; //상영시간 클릭시 테두리 변경
document.querySelectorAll(".timelist .button").forEach((button) => {
  button.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev4) {
      prev4.classList.remove("clicked");
    }
    e.currentTarget.classList.add("clicked");
    prev4 = e.currentTarget;
  });
});

let prev5 = null; //영화 클릭시 테두리 변경
document.querySelectorAll(".movie-item").forEach((button) => {
  button.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev5) {
      prev5.classList.remove("clicked");
    }
    e.currentTarget.classList.add("clicked");
    prev5 = e.currentTarget;
  });
});
