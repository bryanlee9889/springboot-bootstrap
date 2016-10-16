function Utils() {

}

Utils.prototype.parseForm = function($form) {
    var res = {};

    // query input
    $form.find('input').each(function(idx, elem) {
        res[elem.getAttribute('name')] = elem.value;
    });

    // query select
    $form.find('select').each(function(idx, elem) {
        res[elem.getAttribute('name')] = elem.value;
    });

    // query textarea
    $form.find('textarea').each(function(idx, elem) {
        res[elem.getAttribute('name')] = elem.value;
    });

    return res;
}

Utils.prototype.submitJsonForm = function($form, cb_res) {

    var _this = this;

    $form.on('submit', function(evt) {
        if ($form.form('is valid')) {

            var _data = _this.parseForm($form);

            evt.preventDefault();
            var $err = $form.find('.ui.error.message');

            $.ajax({
                url         : $form.attr('action'),
                method      : $form.attr('method'),
                contentType : $form.attr('accept-charset'),
                data        : JSON.stringify(_data)
            }).then(function(res) {
                if (cb_res) {
                    cb_res(res);
                }
            }, function(err) {
                $form.addClass('error');
                $err.text(message.get('form_error'));
            });
        }
    })
}

var utils = new Utils();