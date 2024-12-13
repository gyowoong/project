let prev = null;

const links = document.querySelectorAll(".fw-bold");

links.forEach((link) => {
  link.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev) {
      prev.classList.remove("clicked");
    }

    e.currentTarget.classList.add("clicked");

    prev = e.currentTarget;
  });
});

let prev2 = null;

const statetitle = document.querySelectorAll(".fw-semibold");

statetitle.forEach((link) => {
  link.addEventListener("click", (e) => {
    e.preventDefault();
    if (prev2) {
      prev2.classList.remove("clicked");
    }

    e.currentTarget.classList.add("clicked");

    prev2 = e.currentTarget;
  });
});
