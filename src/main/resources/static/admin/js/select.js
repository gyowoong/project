const selectBox = document.querySelector(".select-box");
const checkboxList = document.querySelector(".checkbox-list");

// Toggle dropdown visibility
selectBox.addEventListener("click", () => {
  checkboxList.classList.toggle("active");
});

// Close dropdown when clicking outside
document.addEventListener("click", (e) => {
  if (!e.target.closest(".select-box") && !e.target.closest(".checkbox-list")) {
    checkboxList.classList.remove("active");
  }
});

// Update select-box text with selected options
checkboxList.addEventListener("change", () => {
  const selectedOptions = Array.from(
    checkboxList.querySelectorAll("input:checked")
  ).map((checkbox) => checkbox.value);
  selectBox.textContent =
    selectedOptions.length > 0 ? selectedOptions.join(", ") : "Select options";
});
