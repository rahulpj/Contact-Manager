console.log("script loaded");
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
  changeTheme(currentTheme);
});

// setTheme to the localStorage

function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// getTheme from localStorage

function getTheme() {
  let temp = localStorage.getItem("theme");
  if (temp == null) return "light";
  else return temp;
}

//changeTheme , set to web page

function changeTheme(currentTheme) {
  // set to the web
  document.querySelector("html").classList.add(currentTheme);

  // set the listener to change theme button
  const changeThemeButton = document.querySelector("#theme_change_button");
  changeThemeButton.addEventListener("click", (event) => {
    console.log("button clicked");
    const oldTheme = currentTheme;
    if (currentTheme === "light") {
      currentTheme = "dark";
    } else {
      currentTheme = "light";
    }

    // updating to local storage
    setTheme(currentTheme);

    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(currentTheme);

    // changing the text of the button
    changeThemeButton.querySelector("span").textContent =
      currentTheme === "light" ? "Dark" : "Light";
  });
}
