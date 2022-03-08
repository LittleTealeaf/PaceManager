

function navHome() {
}

function navDocs() {
    
    
}

function loadBodyHTML(htmlFile) {
    fetch(htmlFile).then(response => response.text()).then(data => document.getElementById("body").innerHTML = data);
}