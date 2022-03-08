

function navHome() {
    loadBodyHTML("./content/home.html");
}

function navDocs() {
    
    document.getElementById("body").innerHTML = "";
}

function loadBodyHTML(htmlFile) {
    fetch(htmlFile).then(response => response.text()).then(data => document.getElementById("body").innerHTML = data);
}