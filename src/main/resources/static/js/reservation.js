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

document.addEventListener("DOMContentLoaded", () => {
  const dateList = document.getElementById("dateList");
  if (!dateList) {
    console.error("dateList 요소를 찾을 수 없습니다!");
    return;
  }

  const today = new Date();

  Array.from({ length: 7 }).forEach((_, i) => {
    const currentDate = new Date();
    currentDate.setDate(today.getDate() + i);

    const day = currentDate.getDate();
    const dayOfWeek = currentDate.toLocaleDateString("ko-KR", {
      weekday: "short",
    });

    const listItem = `
          <li>
                <a href="#" class="date-btn btn btn-light d-flex flex-column align-items-center justify-content-center">
                    <label class="d-flex flex-column align-items-center">
                        <input type="radio" name="radioDate">
                        <strong>${day}</strong>
                        <em>${dayOfWeek}</em>
                    </label>
                </a>
            </li>
      `;
    dateList.insertAdjacentHTML("beforeend", listItem);
  });
});

// let prev3 = null; //날짜 클릭시 색상 변경
// document.querySelectorAll(".date-btn").forEach((button) => {
//   button.addEventListener("click", (e) => {
//     e.preventDefault();
//     if (prev3) {
//       prev3.classList.remove("clicked");
//     }
//     e.currentTarget.classList.add("clicked");
//     prev3 = e.currentTarget;
//   });
// });

// let prev4 = null; //상영시간 클릭시 테두리 변경
// document.querySelectorAll(".timelist .button").forEach((button) => {
//   button.addEventListener("click", (e) => {
//     e.preventDefault();
//     if (prev4) {
//       prev4.classList.remove("clicked");
//     }
//     e.currentTarget.classList.add("clicked");
//     prev4 = e.currentTarget;
//   });
// });

// let prev5 = null; //영화 클릭시 테두리 변경
// document.querySelectorAll(".movie-item").forEach((button) => {
//   button.addEventListener("click", (e) => {
//     e.preventDefault();
//     if (prev5) {
//       prev5.classList.remove("clicked");
//     }
//     e.currentTarget.classList.add("clicked");
//     prev5 = e.currentTarget;
//   });
// });

// 예매 테이블 클릭시 변화
function addClickHandler(selector, clickedClass) {
  document.querySelectorAll(selector).forEach((button) => {
    button.addEventListener("click", (e) => {
      e.preventDefault();
      document
        .querySelectorAll(selector)
        .forEach((el) => el.classList.remove(clickedClass));
      e.currentTarget.classList.add(clickedClass);
    });
  });
}

// 지역 선택 후 극장 목록 요청
document.querySelectorAll(".region-list").forEach((link) => {
  link.addEventListener("click", (e) => {
    e.preventDefault();

    const region = e.currentTarget.getAttribute("data-region");
    if (!region) {
      console.error("지역값이 없습니다");
      return;
    }
    fetch(`/reservation/theaters?region=${region}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        const theaterList = document.getElementById("theater-list");
        theaterList.innerHTML = "";

        data.forEach((theater) => {
          const theaterElement = document.createElement("a");
          theaterElement.className = "nav-link fw-semibold theater-title";
          theaterElement.href = `#theater-${theater.theaterId}`;
          theaterElement.textContent = theater.theaterName;

          theaterList.appendChild(theaterElement);
        });
        addClickHandler(".fw-semibold", "clicked");
      })
      .catch((error) => console.error("극장 목록 오류", error));
  });
});

document.querySelectorAll(".theater-title").forEach((theaterLink) => {
  theaterLink.addEventListener("click", (e) => {
    e.preventDefault();

    const theaterId = theaterLink.getAttribute("data-theater-id");
    if (!theaterId) {
      console.error("Theater ID is missing");
      return;
    }
    fetch(`/screenings/movies?theaterId=${theaterId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((movies) => {
        const movieList = document.getElementById("movie-list");
        movieList.innerHTML = "";

        movies.forEach((movieTitle) => {
          const listItem = document.createElement("li");
          listItem.className = "list-group-item movie-item";
          listItem.textContent = movieTitle;
          listItem.addEventListener("click", () => {
            console.log(`Selected movie: ${movieTitle}`);
          });

          movieList.appendChild(listItem);
        });
      })
      .catch((error) => console.error("Error fetching movies:", error));
  });
});

// 영화 선택 후 상영시간표 요청
document.getElementById("movie-select").addEventListener("change", (e) => {
  const theaterId = document.getElementById("theater-select").value;
  const movieId = e.target.value;
  fetch(`/reservation/screenings?theaterId=${theaterId}&movieId=${movieId}`)
    .then((response) => response.json())
    .then((data) => {
      const screeningsDiv = document.getElementById("screenings");
      screeningsDiv.innerHTML = ""; // 기존 내용 초기화
      data.forEach((screening) => {
        const div = document.createElement("div");
        div.textContent = `${screening.startTime}`;
        screeningsDiv.appendChild(div);
      });

      // 상영 시간 선택 상태 초기화
      addClickHandler("#screenings div", "clicked");
    });
});
