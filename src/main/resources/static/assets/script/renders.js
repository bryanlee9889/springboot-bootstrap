'use strict';

function Render() {

}

Render.prototype.renderTable        = function(url) {
    var _this = this;
    $.ajax({
        url         : url,
        method      : 'get'
    }).then(function(res) {
        _this.renderTableBody(res);
        _this.renderPaging(res);
        
        console.log($('.ui.grid').outerHeight())
    }, function(err) {
        console.error(err);
    });
}

Render.prototype.renderForm         = function(url, callback) {
    var _this = this;
    $.ajax({
        url         : url,
        method      : 'get'
    }).then(function(res) {
        _this.renderFormBody(res);
        if (callback) {
            callback();
        }
    }, function(err) {
        console.error(err);
    });
}

Render.prototype.renderPaging       = function(data) {
    var source = ''
        + '<div class="ui right floated pagination menu">'
        + 	'<% if (data.first) { %>'
        + 		'<a class="icon item disabled">'
        +			'<i class="left chevron icon"></i>'
        +		'</a>'
        + 	'<% } else { %>'
        +		'<a class="icon item" data-page="<%= data.pageIndex - 1 %>">'
        +			'<i class="left chevron icon"></i>'
        +   	'</a>'
        + 	'<% } %>'

        + 	'<% if (data.pageIndex - 2 >= 0) { %>'
        + 		'<a class="item" data-page="<%= data.pageIndex - 2 %>">'
        +			'<%= data.pageIndex - 1 %>'
        + 		'</a>'
        + 	'<% } %>'
        + 	'<% if (data.pageIndex - 1 >= 0) { %>'
        + 		'<a class="item" data-page="<%= data.pageIndex - 1%>">'
        + 			'<%= data.pageIndex %>'
        + 		'</a>'
        + 	'<% } %>'

        + 	'<a class="item active">'
        + 		'<%= data.pageIndex + 1 %>'
        + 	'</a>'

        + 	'<% if (data.totalPage - (data.pageIndex + 2) >= 0) { %>'
        + 		'<a class="item" data-page="<%= data.pageIndex + 1 %>">'
        + 			'<%= data.pageIndex + 2 %>'
        + 		'</a>'
        + 	'<% } %>'

        + 	'<% if (data.totalPage - (data.pageIndex + 3) >= 0) { %>'
        + 		'<a class="item" data-page="<%= data.pageIndex + 2 %>">'
        +			'<%= data.pageIndex + 3 %>'
        + 		'</a>'
        + 	'<% } %>'

        + 	'<% if (data.last) { %>'
        + 		'<a class="icon item disabled">'
        + 			'<i class="right chevron icon"></i>'
        + 		'</a>'
        + 	'<% } else { %>'
        + 		'<a class="icon item" data-page="<%= data.pageIndex + 1 %>">'
        + 			'<i class="right chevron icon"></i>'
        + 		'</a>'
        + 	'<% } %>'
        + '</div>';
    var template = _.template(source);
    var html     = template(data);
    $('#pagination').html(html);
}

Render.prototype.renderTableBody    = function(data) {
    var source      = $('#_tbody').html();
    var template    = _.template(source);
    var html        = template(data);
    $('#tbody').html(html);
}

Render.prototype.renderFormBody     = function(data) {
    var source      = $('#_form').html();
    var template    = _.template(source);
    var html        = template(data);
    $('.ajaxform').html(html);
}

var render = new Render();