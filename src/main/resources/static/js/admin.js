console.log("admin js script loaded")

document.querySelector("#small_size")
.addEventListener("change" , function(event){

let file=event.target.files[0];
let reader=new FileReader();
reader.onload= function(){
    document.querySelector("#upload_image_preview")
    .setAttribute("src" , reader.result);

};
reader.readAsDataURL(file);
});