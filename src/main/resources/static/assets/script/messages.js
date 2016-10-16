function Message () {

	var _this       	= this;
	var messages    	= {};
	var lang        	= Cookies.get('org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE') || 'en';
    var message_lang	= localStorage.getItem('message_' + lang);
        
    if (message_lang != undefined) {
    	this.messages = JSON.parse(message_lang);
    } else {
    	$.ajax({
            url     : '/i18n/message_' + lang + '.json',
            method  : 'get',
            async	: false,
            contentType: 'application/json; charset=UTF-8'
        }).then(function(res) {
            _this.messages = res;
            localStorage.setItem('message_' + lang, JSON.stringify(res));
        }, function(err) {
            console.error(err);
        });
    }

}

Message.prototype.get = function(key) {
    return this.messages[key];
}

var message = new Message();