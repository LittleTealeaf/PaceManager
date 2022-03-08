function navHome() {
    console.log("clicked");
}

function navDocs() {
    document.getElementById("body").innerHTML = insertPage("./javadoc/index.html")
}

function insertPage(link) {
    return "<iframe src=\"" + link + "\"></iframe>"
}