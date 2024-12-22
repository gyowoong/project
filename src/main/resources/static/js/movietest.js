fetch(`/rest/movieDetail/${movieId}`)
.then((response) => {
  if (!response.ok) throw new Error("에러");

  return response.json();
})
.then((data) => {
  console.log(data);
  // document.querySelector("#btnradio1").addEventListener("click",()=>{
  //   console.log("버튼1 클릭");
  
  //     let str = `<div class="section-title"><h5>개요</h5></div>`;
  //     str += `<div class="anime__review__item overview th:text="${movieDto.overview}"></div>`
      
  //     document.querySelector(".movie_info_people_director").innerHTML = str;
  
  //   });


  // document.querySelector("#btnradio2").addEventListener("click",()=>{
  //   console.log("버튼2 클릭");
  
  //     let str = `<div class="section-title"><h5>감독</h5></div>`;
  //     data.forEach(director => {
  //       str += `<div class="col-lg-3 col-md-4 col-sm-6 mb-3 product">`;
  //       str += `<div class="product__item mb-2">`;
  //       str += `<a href="personDetail?id=${director.id}"><img src=${
  //         "https://image.tmdb.org/t/p/w500" + director.profilePath
  //       } alt="" class="product__item__pic set-bg"></a></div>`;
  //       str += `<div class="product__item__text mx-4">`;
  //       str += `<h5><a href="personDetail?id=${director.id}">${director.name}</a></h5>`;
  //       str += `<ul><li>예매율</li> 31.8%</ul><ul><li>개봉일</li> ${director.releaseDate}</ul>`;
  //       str += `</div></div>`;
  //     });
      
  //     document.querySelector(".movie_info_people_director").innerHTML = str;
  
  //   });
});


