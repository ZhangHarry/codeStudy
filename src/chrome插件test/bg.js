function handleAll(info, tab){
    chrome.tabs.sendMessage(tab.id, {greeting: "hello"}, function(response) {
        // var list = response.result;
        // // for (var i = list.length - 1; i >= 0; i--) {
        // //     console.log(list[i]);  
        // // }
        // var str = JSON.stringify(response);
        // localStorage.obj = str;
        // console.log(localStorage.obj);
        // var xhr = new XMLHttpRequest();
        // xhr.open("POST", "http://localhost:8080/ShowResult/displayCode", true);
        // xhr.onreadystatechange = function() {
        //     if (xhr.readyState == 4) {
        //      var codes = JSON.stringify(xhr.responseText);
        //         chrome.tabs.sendMessage(tab.id, {greeting: "display", code : codes}, function(response) { });
        //      }
        //  }
        //  var formData = new FormData();
        //  formData.append("code", str);
        // // console.log("str : "+str);
        // xhr.send("formData=code");
    });
}

function handleSelection(info, tab){
    // chrome.tabs.create({url:"result.html",selected:true});
    // alert(info.selectionText);
    console.log("select : "+info.selectionText);
    chrome.tabs.sendMessage(tab.id, {greeting: "select", selected: info.selectionText}, function(response) {
    });
}


chrome.contextMenus.create({
    "title": "Search Code",
    "contexts":["selection"],
    "onclick":handleSelection
});
chrome.contextMenus.create({
    "title": "Search Code",
    "contexts":["page"],
    "onclick":handleAll
});