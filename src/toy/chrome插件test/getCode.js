var baseUrl = "http://localhost:8080/SOCodeCompletion/";
 var panel = document.createElement("div");
 panel.id = "accordion";
 document.body.appendChild(panel);

(function(){
	chrome.runtime.onMessage.addListener(function(request,sender,senderResponse){
		if(request.greeting === "hello"){
			var result = getCode();
			console.log(result);
        	var xhr = constructXhr();
        	var formData = new FormData();
          	console.log(result.length);
        	for (var i =  0; i < result.length; i++) {
        		var item = result[i];
        		console.log(item);
           		//formData.append("code"+i, escape(item)); 
        	}
           	formData.append("code", escape(result[0])); 
        	// xhr.send("result="+result);
        	xhr.send(formData);
		}else if(request.greeting === "select"){
			var result = request.selected;
			console.log(result);
			displayArr(result);
        	var xhr = constructXhr();
        	var formData = new FormData();
           	formData.append("code", escape(result)); 
        	xhr.send(formData);
		}else{
			senderResponse({"result":[]});
		}
	});
})();

function displayArr(array){
	for (var i = 0; i < array.length; i++) {
		console.log(array[i]);
	}
}

function constructXhr(){
			var xhr = new XMLHttpRequest();
        	xhr.open("POST", baseUrl+"/displayCode", true);
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
       		xhr.onreadystatechange = function() {
            	if (xhr.readyState == 4) {

             		initHeader(); // attach personal js & css
             		addPrismHeader();// attach prism js & css

             		var repo = xhr.responseText;
             		var count = 0;
             		var codes = JSON.parse(repo);
             		var total_array = codes.total;
             		for (var i = 0; i < total_array.length; i++) {
             			var item = total_array[i];
             			var cloneCode = item.cloneCode;
             			var section = constructSection(cloneCode, i);
             			var codeArray = item.codeArray;
             			var title = item.title;
             			console.log("title : "+title);
             			var codeSet = new Array();
             			for (var j = 0; j < codeArray.length; j++) {
             				var code_item = codeArray[j];
             				var filename = code_item.filename;
             				console.log("filename : "+filename);
             				codeSet[j] = code_item.code;
             			}
             			showCodeSet(codeSet, i, title, section);
             		}
             	}
        	}
        	return xhr;
}

function getCode(){
	var list = [];

	$('pre').each(function(){
		var pre = $(this);
    pre.children("code").each(function(){
        var code = $(this);
        var pretty = code.parent().attr("class");
        var isPretty = false;
        if(pretty!=null){
          if(pretty.indexOf("pretty") > 0)
           isPretty = true;
        }
        if(isPretty){
         var codeStr = "";
         code.children().each(function(){
           codeStr = codeStr + code.html();
         });
         list.push(codeStr);
        }else{
         list.push(code.html());
        }
    });

    if(pre.children("code").length == 0){
      list.push(pre.attr("data-original-code"));
    }
	});
	return list;
}


function constructSection(content, index){
    var section = document.createElement("section");
    section.class = "language-java";
    section.id = section.name = "displayCode_"+index;
    content = "<pre class=\"language-java\"><code class=\"language-java\">"+content+"</code></pre>";
    section.innerHTML = content;
    return section;
}

/**
 *  attach required css and js to current page
 */
function addPrismHeader(){
	attachJs("/prism.js");
	attachCss("/themes/prism.css");
}

/**
 *  attach required css and js to current page
 */
function initHeader(){
	attachCss("/themes/own.css");
	// attachCss("/bootstrap/css/bootstrap.min.css");
	// attachJs("/jquery-3.1.0.min.js");
	// attachJs("/bootstrap/js/bootstrap.min.js");
}

function attachCss(path){
	var link = document.createElement("link");
    link.rel = "stylesheet";
    link.href = baseUrl+path;
    document.head.appendChild(link);
}

function attachJs(path){ 
	var script = document.createElement("script");
    script.src = baseUrl+path;
    document.body.appendChild(script);
}

function showCodeSet(array, index, title, section){
	var table = "<table>";
	for (var i = 0; i < array.length; i++) {
		var code = array[i];
		table = table+"<td>";
		table += constructSection(code, i).outerHTML;
		table += "</td>";
	}
	table += "</table>";
    // document.body.appendChild(table);
    // $('#accordion').append(section);
    // $('#accordion').append(constructOneResult(index, title, table));
    var div = document.createElement("div");
    console.log(section.innerHTML);
    div.innerHTML = constructOneResult_simple(index, title, table);
    console.log(div.innerHTML);
    panel.appendChild(section);
    panel.appendChild(div);
}

function constructOneResult(index, title, content){
	var html = "<div class=\"panel panel-default\">"
    +"<div class=\"panel-heading\" id=\"heading"+index+"\">"
      +"<h4 class=\"panel-title\">"
        +"<a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+index+"\" aria-controls=\"collapse"+index+"\">"
          +title
        +"</a>"
      +"</h4>"
    +"</div>"
    +"<div id=\"collapse"+index+"\" class=\"panel-collapse collapse\">"
      +"<div class=\"panel-body\">"
        +content
		+"</div>"
    +"</div>"
  +"</div>";
  return html;
  // $('#accordion').append(html);
}

function constructOneResult_simple(index, title, content){
	var html = "<div id=\"heading"+index+"\">"
      +"<h4 >"
        +"<a href=\"#collapse"+index+"\" aria-controls=\"collapse"+index+"\">"
          +title
        +"</a>"
      +"</h4>"
    +"</div>"
    +"<div id=\"collapse"+index+"\">"
        +content
  +"</div>";
  return html;
  // $('#accordion').append(html);
}


// function sampleJson(){
// 	return  "{\n" +
//                "    \"total\": [\n" +
//                "        {\n" +
//                "            \"codeArray\": [\n" +
//                "                {\n" +
//                "                    \"filename\": \"F:\\\\LuceneOutput\\\\outcome_31.java\",\n" +
//                "                    \"code\": \"\\t\\t// Creating HTTP Post\\r\\n\\t\\tHttpPost httpPost = new HttpPost(\\r\\n\\t\\t\\t\\t</code><a class=\\\"language-markup\\\" style=\\\"color:red\\\">\\\"http://10.0.2.2/android\\\"</a><code class=\\\"language-java\\\">);\\r\\n\\r\\n\\t\\t// Building post parameters\\r\\n\\t\\t// key and value pair\\r\\n\\t\\tList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);\\r\\n\\t\\tnameValuePair.add(new BasicNameValuePair(\\\"email\\\", \\\"user@gmail.com\\\"));\\r\\n\\t\\tnameValuePair.add(new BasicNameValuePair(\\\"message\\\",\\r\\n\\t\\t\\t\\t\\\"Hi, trying Android HTTP post!\\\"));\\r\\n\\r\\n\\t\\t// Url Encoding the POST parameters\\r\\n\\t\\ttry {\\r\\n\\t\\t\\thttpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));\\r\\n\\t\\t} catch (UnsupportedEncodingException e) {\\r\\n\\t\\t\\t// writing error to Log\\r\\n\\t\\t\\te.printStackTrace();\\r\\n\\t\\t}\\r\\n\\r\\n\\t\\t// Making HTTP Request\\r\\n\\t\\ttry {\\r\\n\\t\\t\\tHttpResponse response = httpClient.execute(httpPost);\\r\\n\\t\\t\\tHttpEntity httpEntity = response.getEntity();\\r\\n\\t\\t\\tis = httpEntity.getContent();\\r\\n\\t\\t\\t// writing response to log\\r\\n\\t\\t\\tLog.d(\\\"Http Response:\\\", response.toString());\\r\\n\\t\\t} catch (ClientProtocolException e) {\\r\\n\\t\\t\\t// writing exception to log\\r\\n\\t\\t\\te.printStackTrace();\\r\\n\\t\\t} catch (IOException e) {\\r\\n\\t\\t\\t// writing exception to log\\r\\n\\t\\t\\te.printStackTrace();\\r\\n\\r\\n\\t\\t}\\r\\n\\r\\n\\t\\ttry {\\r\\n\\t\\t\\tBufferedReader reader = new BufferedReader(new InputStreamReader(\\r\\n\\t\\t\\t\\t\\tis, \\\"UTF8\\\"), 8);\\r\\n\\t\\t\\tStringBuilder sb = new StringBuilder();\\r\\n\\t\\t\\tString line = null;\\r\\n\\t\\t\\twhile ((line = reader.readLine()) != null) {\\r\\n\\t\\t\\t\\tsb.append(line + \\\"\\\\n\\\");\\r\\n\\t\\t\\t}\\r\\n\\t\\t\\tis.close();\\r\\n\\t\\t\\tjson = sb.toString();\\r\\n\\t\\t\\t// Log.e(\\\"JSON\\\", json);\\r\\n\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"filename\": \"F:\\\\LuceneOutput\\\\outcome_13.java\",\n" +
//                "                    \"code\": \"\\t\\t\\t\\t// Creating HTTP Post\\r\\n\\t\\t\\t\\tHttpPost httpPost = new HttpPost(\\r\\n\\t\\t\\t\\t\\t\\t</code><a class=\\\"language-markup\\\" style=\\\"color:red\\\">\\\"http://10.0.2.2/user/remotelogin\\\"</a><code class=\\\"language-java\\\">);\\r\\n\\r\\n\\t\\t\\t\\t// Building post parameters\\r\\n\\t\\t\\t\\t// key and value pair\\r\\n\\t\\t\\t\\tList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);\\r\\n\\t\\t\\t\\tnameValuePair.add(new BasicNameValuePair(\\\"email\\\", \\\"user@gmail.com\\\"));\\r\\n\\t\\t\\t\\tnameValuePair.add(new BasicNameValuePair(\\\"message\\\",\\r\\n\\t\\t\\t\\t\\t\\t\\\"Hi, trying Android HTTP post!\\\"));\\r\\n\\r\\n\\t\\t\\t\\t// Url Encoding the POST parameters\\r\\n\\t\\t\\t\\ttry {\\r\\n\\t\\t\\t\\t\\thttpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));\\r\\n\\t\\t\\t\\t} catch (UnsupportedEncodingException e) {\\r\\n\\t\\t\\t\\t\\t// writing error to Log\\r\\n\\t\\t\\t\\t\\te.printStackTrace();\\r\\n\\t\\t\\t\\t}\\r\\n\\r\\n\\t\\t\\t\\t// Making HTTP Request\\r\\n\\t\\t\\t\\ttry {\\r\\n\\t\\t\\t\\t\\tHttpResponse response = httpClient.execute(httpPost);\\r\\n\\t\\t\\t\\t\\tHttpEntity httpEntity = response.getEntity();\\r\\n\\t\\t\\t\\t\\tis = httpEntity.getContent();\\r\\n\\t\\t\\t\\t\\t// writing response to log\\r\\n\\t\\t\\t\\t\\tLog.d(\\\"Http Response:\\\", response.toString());\\r\\n\\t\\t\\t\\t} catch (ClientProtocolException e) {\\r\\n\\t\\t\\t\\t\\t// writing exception to log\\r\\n\\t\\t\\t\\t\\te.printStackTrace();\\r\\n\\t\\t\\t\\t} catch (IOException e) {\\r\\n\\t\\t\\t\\t\\t// writing exception to log\\r\\n\\t\\t\\t\\t\\te.printStackTrace();\\r\\n\\r\\n\\t\\t\\t\\t}\\r\\n\\r\\n\\t\\t\\t\\ttry {\\r\\n\\t\\t\\t\\t\\tBufferedReader reader = new BufferedReader(new InputStreamReader(\\r\\n\\t\\t\\t\\t\\t\\t\\tis, \\\"UTF8\\\"), 8);\\r\\n\\t\\t\\t\\t\\tStringBuilder sb = new StringBuilder();\\r\\n\\t\\t\\t\\t\\tString line = null;\\r\\n\\t\\t\\t\\t\\twhile ((line = reader.readLine()) != null) {\\r\\n\\t\\t\\t\\t\\t\\tsb.append(line + \\\"\\\\n\\\");\\r\\n\\t\\t\\t\\t\\t}\\r\\n\\t\\t\\t\\t\\tis.close();\\r\\n\\t\\t\\t\\t\\tjson = sb.toString();\\r\\n\"\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"title\": \"codeClass_1\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
// }