fetch(`/rest/movieDetail/${movieId}`)
  .then((response) => {
    if (!response.ok) throw new Error("에러");

    return response.json();
  })
  .then((data) => {
    console.log(data);
    // 주요 정보 실행
    document.querySelector(".director_row").innerHTML = "";
    document.querySelector(".actor_row").innerHTML = "";
    document.querySelector(".review_row").innerHTML = "";

    let str = `<div class="section-title"><h5>개요</h5></div>`;
    str += `<div class="anime__review__item overview">${data.overview}</div>`;

    document.querySelector(".overview").innerHTML = str;

    // 주요 정보 버튼 클릭 시
    document.querySelector("#btnradio1").addEventListener("click", () => {
      console.log("버튼1 클릭");
      document.querySelector(".director_row").innerHTML = "";
      document.querySelector(".actor_row").innerHTML = "";
      document.querySelector(".review_row").innerHTML = "";

      let str = `<div class="section-title"><h5>개요</h5></div>`;
      str += `<div class="anime__review__item overview">${data.overview}</div>`;

      document.querySelector(".overview").innerHTML = str;
    });

    // 감독/출연 버튼 클릭 시
    document.querySelector("#btnradio2").addEventListener("click", () => {
      console.log("버튼2 클릭");
      document.querySelector(".overview").innerHTML = "";
      document.querySelector(".review_row").innerHTML = "";

      const directorList = new Set();
      const actorList = new Set();

      data.peopleDtos.forEach((peopleDto) => {
        peopleDto.moviePeople.forEach((moviePeopleDto) => {
          // Director 역할이 있는 사람은 directorList에 추가
          if (moviePeopleDto.role && moviePeopleDto.role === "Director") {
            directorList.add(peopleDto);
          }
          // role이 null인 사람은 actorList에 추가
          if (!moviePeopleDto.role) {
            actorList.add(peopleDto);
          }
        });
      });

      // Set을 다시 Array로 변환
      const directorListArray = Array.from(directorList);
      const actorListArray = Array.from(actorList);

      console.log(directorListArray);
      console.log(actorListArray);

      let str = `<div class="section-title"><h5>감독</h5></div>`;
      directorListArray.forEach((director) => {
        str += `<div class="col-lg-3 col-md-4 col-sm-6 mb-3 product">`;
        str += `<div class="product__item mb-2">`;
        str += `<a href="personDetail?id=${director.id}"><img src=${
          "https://image.tmdb.org/t/p/w500" + director.profilePath
        } alt="" class="product__item__pic set-bg"></a></div>`;
        str += `<div class="product__item__text mx-4">`;
        str += `<h5><a href="personDetail?id=${director.id}">${director.name}</a></h5>`;
        str += `<div class="text-white">Director</div>`;
        str += `</div></div></div>`;
      });

      document.querySelector(".director_row").innerHTML = str;
      str = `<div class="row trending__product-row actor_row">`;
      str = `<div class="section-title"><h5>출연</h5></div>`;
      actorListArray.forEach((actor) => {
        str += `<div class="col-lg-3 col-md-4 col-sm-6 mb-3 product">`;
        str += `<div class="product__item mb-2">`;
        str += `<a href="personDetail?id=${actor.id}"><img src=${
          "https://image.tmdb.org/t/p/w500" + actor.profilePath
        } alt="" class="product__item__pic set-bg"></a></div>`;
        str += `<div class="product__item__text mx-4">`;
        str += `<h5><a href="personDetail?id=${actor.id}">${actor.name}</a></h5>`;
        str += `<div class="text-white">`;
        actor.moviePeople.forEach((moviePeople) => {
          str += `${moviePeople.character} 역`;
        });
        str += `</div>`;
        str += `</div></div>`;
      });
      document.querySelector(".actor_row").innerHTML = str;
    });

    // 평점/리뷰 버튼 클릭 시
    document.querySelector("#btnradio3").addEventListener("click", () => {
      console.log("버튼3 클릭");
      document.querySelector(".overview").innerHTML = "";
      document.querySelector(".director_row").innerHTML = "";
      document.querySelector(".actor_row").innerHTML = "";

      str = "";
      str += `<div class="col-lg-8 col-md-8">`;
      str += `<div class="anime__details__review">`;
      str += `<div class="section-title"><h5>관람평</h5></div>`;
      // array.forEach((element) => {
      //   str += `<div class="anime__review__item">`;
      //   str += `<div class="anime__review__item__pic">`;
      //   str += `<img src="img/anime/review-1.jpg" alt="" /></div>`;
      //   str += `<div class="anime__review__item__text">`;
      //   str += `<h6>작성자</h6><p>댓글 내용</p>`;
      //   str += `</div></div>`;
      // });
      str += `<div class="anime__details__form">`;
      str += `<div class="section-title">`;
      str += `<h5>관람평 작성하기</h5></div>`;
      str += `<form action="#">`;
      str += `<textarea placeholder="Your Comment"></textarea>`;
      str += `<button type="submit">`;
      str += `<i class="fa fa-location-arrow"></i> 관람평 등록</button>`;
      str += `</form></div></div></div>`;

      document.querySelector(".review_row").innerHTML = str;
    });
  });
