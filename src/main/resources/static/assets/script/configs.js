$(function() {
	
	/************************************************************************
	*
	* Dropdown
	*
	************************************************************************/
	$('.dropdown').dropdown();

	/************************************************************************
	*
	* Accordion
	*
	************************************************************************/
	$('.accordion').accordion();

	/************************************************************************
    *
    * Checkbox
    *
    ************************************************************************/
	$('.checkbox').checkbox();
	
	/************************************************************************
	*
	* Language
	*
	************************************************************************/
	$('.lang').on('click', function() {
		window.location = '?lang=' + $(this).attr('data-lang');
	});

	/************************************************************************
    *
    * Table search events
    *
    ************************************************************************/
    $('#inpSearch').on('keydown', function(evt) {
        // enter key
        if(evt.keyCode == 13) {
            var searchVal = $(this).val();
            if(searchVal.trim() != '') {
                var searchKey = $('#selSearch').val();
                API_URL = _API_URL + '?' + searchKey + '=' + encodeURIComponent(searchVal);
                render.renderTable(API_URL);
            } else {
                render.renderTable(_API_URL);
            }
        }
    });
    
    /************************************************************************
    *
    * Table pagination events
    *
    ************************************************************************/
    $('#pagination').on('click', 'a.item:not(.active, .disabled)', function(evt) {
        var page = $(this).attr('data-page') || '0';

        if (API_URL.match(/pageIndex=\d*/)) {
            API_URL = API_URL.replace(/pageIndex=\d*/, 'pageIndex=' + page);
        } else {
            API_URL += API_URL.indexOf('?') > 1 ? ('&pageIndex=' + page) : ('?pageIndex=' + page);
        }

        console.log(API_URL);
        render.renderTable(API_URL);
    });

    /************************************************************************
    *
    * Table sorting events
    *
    ************************************************************************/
    $('.btn_sort').on('click', function(evt) {
        var sort 		= $(this).attr('data-sort');
        var direction 	= $(this).attr('data-direction');

        API_URL = API_URL.replace(/(pageIndex=\d*|\?pageIndex=\d*|&pageIndex=\d*)/, '');

        if (API_URL.match(/orders=\w*/)) {
            API_URL = API_URL.replace(/orders=\w*/, 'orders=' + sort);
        } else {
            API_URL += API_URL.indexOf('?') > 1 ? ('&orders=' + sort) : ('?orders=' + sort);
        }

        if (API_URL.match(/direction=\w*/)) {
            API_URL = API_URL.replace(/direction=\w*/, 'direction=' + direction);
        } else {
            API_URL += API_URL.indexOf('?') > 1 ? ('&direction=' + direction) : ('?direction=' + direction);
        }

        console.log(API_URL);
        render.renderTable(API_URL);

        $(this).attr('data-direction', direction == 'DESC' ? 'ASC' : 'DESC');
        $('.btn_sort').removeClass('ascending descending');
        $(this).addClass(direction == 'DESC' ? 'descending' : 'ascending');

    });
    
    /************************************************************************
    *
    * Menu pushable events
    *
    ************************************************************************/
    $('body .ui.sidebar')
    	.sidebar({
    		context: $('body .bottom.segment')
    	})
    	.sidebar('attach events', 'body .logo');
	
});

/************************************************************************
*
* Ajax
*
************************************************************************/
var timer;
$.ajaxSetup({
    beforeSend: function() {
        timer && clearTimeout(timer);
        timer = setTimeout(function() {
            $('#loading').addClass('active');
        }, 1000);
    },
    complete: function() {
        clearTimeout(timer);
        $('#loading').removeClass('active');
    },
    dataType: 'json',
    contentType: 'application/json; charset=UTF-8',
    statusCode: {
        401: function() {
            window.location = '/error/unauthorized';
        },
        404: function() {
            window.location = '/error/notfound';
        }
    }
});

/************************************************************************
*
* API
* API_URL   : URL that changed with user's action
* _API_URL  : constant URL
*
************************************************************************/
API_URL = _API_URL = window.location.pathname.replace('www', 'api');
