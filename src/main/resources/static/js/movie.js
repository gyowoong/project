const url =
  "https://api.themoviedb.org/3/movie/now_playing?language=ko-KR&page=1&region=KR";
const options = {
  method: "GET",
  headers: {
    accept: "application/json",
    Authorization:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhN2UwMzVjMzUyODU4ZDRmMTRiMDIxM2Y5NDE1ODI3YyIsIm5iZiI6MTczMzI5NzU5Ny4zMDU5OTk4LCJzdWIiOiI2NzUwMDViZDM1NWRiYzBiMTVkN2E1NWYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.ov8q6kG-1OXY0deIpXpF_2FqmMM9Z8YPEeqoIyZu8dg",
  },
};

fetch(url, options)
  .then((res) => res.json())
  .then((json) => {
    console.log(json);
    json = json.results;
    let str = "";
    json.forEach((result) => {
      str += `<div class="col-lg-3 col-md-6 col-sm-6 product">`;
      str += `<div class="product__item mb-2">`;
      str += `<a href=""><img src=${
        "https://image.tmdb.org/t/p/original" + result.poster_path
      } alt="" class="product__item__pic set-bg"></a></div>`;
      str += `<div class="product__item__text">`;
      str += `<h5><a href="">${result.title}</a></h5>`;
      str += `<ul><li>예매율</li> 31.8%</ul><ul><li>개봉일</li> ${result.release_date}</ul>`;
      str += `</div></div>`;
    });
    document.querySelector(".trending__product-row").innerHTML = str;
  })
  .catch((err) => console.error(err));
