const page = document.querySelector("[name='page']").value;
const movieList = document.querySelector("[name='movieList']").value;
const genre = document.querySelector("[name='genre']").value;
let url = "";
if (genre == null) {
  url =
    "https://api.themoviedb.org/3/movie/" +
    movieList +
    "?language=ko-KR&region=KR&page=" +
    page;
} else {
  url =
    "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko-KR&sort_by=popularity.desc&with_genres=" +
    genre +
    "&language=ko-KR&page=" +
    page;
}
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

    // 영화 리스트
    results = json.results;

    let str = "";
    results.forEach((result) => {
      str += `<div class="col-lg-3 col-md-4 col-sm-6 product">`;
      str += `<div class="product__item mb-2">`;
      str += `<a href="movieDetail?id=${result.id}&page=${page}"><img src=${
        "https://image.tmdb.org/t/p/w500" + result.poster_path
      } alt="" class="product__item__pic set-bg"></a></div>`;
      str += `<div class="product__item__text">`;
      str += `<h5><a href="movieDetail?id=${result.id}&page=${page}">${result.title}</a></h5>`;
      str += `<ul><li>예매율</li> 31.8%</ul><ul><li>개봉일</li> ${result.release_date}</ul>`;
      str += `</div></div>`;
    });
    document.querySelector(".trending__product-row").innerHTML = str;

    // 페이지
    const totalPage = json.total_pages;
    const size = 20;
    let tempEnd = Math.ceil(page / 10.0) * 10;
    const start = tempEnd - 9;
    const prev = start > 1;
    const end = totalPage > tempEnd ? tempEnd : totalPage;
    const next = totalPage > tempEnd;

    str = "";
    str += `<li class="page-item `;
    str += `${prev ? "" : "disabled"}`;
    str += `"><a class="page-link text-light bg-transparent" href="${movieList}?genre=${genre}&page=`;
    str += `${page - 10}`;
    str += `">Previous</a></li>`;
    for (let i = start; i < end + 1; i++) {
      str += `<li th:class="page-item" aria-current="page">`;
      str += `<a class="page-link text-light ${
        i == page ? "bg-danger border-light active" : "bg-transparent"
      }`;
      str += ` " href="${movieList}?genre=${genre}&page=${i}">${i}</a></li>`;
    }
    str += `<li class="page-item `;
    str += `${next ? "" : "disabled"}`;
    str += `"><a class="page-link text-light bg-transparent" href="${movieList}?genre=${genre}&page=`;
    str += `${parseInt(page) + 10}`;
    str += `">Next</a></li>`;

    document.querySelector(".pagination").innerHTML = str;
  })
  .catch((err) => console.error(err));

if (movieList == "now_playing") {
  document.querySelector(".nowPlaying").className += " bg-danger active";
} else if (movieList == "upcoming") {
  document.querySelector(".upcoming").className += " bg-danger active";
} else {
  document.querySelector(".popular").className += " bg-danger active";
}

const genreUrl = "https://api.themoviedb.org/3/genre/movie/list?language=ko";

fetch(genreUrl, options)
  .then((res) => res.json())
  .then((json) => {
    str = `<li><a class="dropdown-item" href="popular?genre=&page=1">전체</a></li>`;
    const genres = json.genres;
    genres.forEach((g) => {
      str += `<li><a class="dropdown-item" href="popular?genre=${g.id}&page=1">${g.name}</a></li>`;
      if (genre == g.id) {
        document.querySelector("h3").innerHTML = g.name;
      }
    });
    document.querySelector(".dropdown-menu").innerHTML = str;
  })
  .catch((err) => console.error(err));
