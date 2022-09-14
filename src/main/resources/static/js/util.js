// font setting
function create(element){
	return document.createElement(element);
}

function getByTag(tag){
	return document.getElementsByTagName(tag);
}

function addScript(src){
	var script = create('script'); 
	
	script.src = src;
	script.type = 'text/javascript';
	getByTag('head')[0].appendChild(script);
}

addScript('https://code.jquery.com/jquery-3.4.1.min.js');
addScript('https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js');

function log(string){
	console.log(string);
}

function label(object, text, color){
	$(object).text(text).css('color', color ? color : 'red');
	go = color != null;
}

function isEmpty(string){
	return string == null || string == "";	
}

function type(object){
	return typeof(object);
}

function encode(code){
	return sha256(code);	
} 

(function (d) {
    t = setTimeout(function () {
      h.className = h.className.replace(/\bwf-loading\b/g, '') + ' wf-inactive';
    }, config.scriptTimeout),
    tk = d.createElement('script'),
    f = false,
    s = d.getElementsByTagName('script')[0],
    a;
  h.className += ' wf-loading';
  tk.src = 'https://use.typekit.net/' + config.kitId + '.js';
  tk.async = true;
  tk.onload = tk.onreadystatechange = function () {
    a = this.readyState;
    if (f || (a && a != 'complete' && a != 'loaded')) return;
    f = true;
    clearTimeout(t);
    try {
      Typekit.load(config);
    } catch (e) {}
  };
  s.parentNode.insertBefore(tk, s);
})(document);