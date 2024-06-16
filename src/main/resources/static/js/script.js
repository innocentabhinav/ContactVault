console.log("script loaded !")

let currentTheme=getThemeFromLocalStorage();
console.log(currentTheme);

document.addEventListener("DOMContentLoaded",()=>{
changeTheme();

})

function changeTheme(){
    //setting to webpage
    changePageTheme(currentTheme,"");
    //setting the listener to change theme button
    const changeThemeButton=document.querySelector("#theme_change_button");
    const oldTheme=currentTheme;
    changeThemeButton.addEventListener('click',(event)=>{
    let oldTheme=currentTheme;
     if(currentTheme==="dark"){
     currentTheme="light";
     }else{
     currentTheme="dark";
     }
   changePageTheme(currentTheme,oldTheme);
    });
}

function getThemeFromLocalStorage(){
    let theme=localStorage.getItem("theme");
    return theme ? theme : "light";
}

function setThemeInLocalStorage(theme){
  localStorage.setItem("theme",theme);


}

function changePageTheme(theme, oldTheme){
     //updating theme to local storage
  setThemeInLocalStorage(currentTheme);
     //removing old theme from webpage and adding new theme to it.
     if(oldTheme){
    document.querySelector('html').classList.remove(oldTheme);
     }
     //setting current theme
  document.querySelector('html').classList.add(theme);

  //changing theme change button text
  document
  .querySelector("#theme_change_button")
  .querySelector('span').textContent= theme =='light' ? 'Dark' : 'Light' ;
}
