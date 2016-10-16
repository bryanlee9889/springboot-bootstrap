<#import "/spring.ftl" as spring />
<#import "/webs/admin/layouts/main.ftl" as layout />

<@layout.layout>
    <div class="content">

        <h2 class="ui dividing header">
        	<i class="icon"></i>
			<div class="content">
            	<@spring.messageText "menu.title.user.add" "User Add" />
			</div>
        </h2>

        <div class="ui two column centered grid">
            <div class="column">
                <form class="ui form ajaxform" method="post" accept-charset="application/json; charset=UTF8">

                    <div class="fields" id="box_team">
                        <div class="three wide field">
                            <div class="ui grid">
                                <div class="column required inline field">
                                    <label>
                                        <@spring.messageText "team" "Team"/>
                                    </label>
                                    <button class="ui right floated icon button" id="btn_add_team">
                                        <i class="add circle icon"></i>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="thirteen wide field">
                            <div class="ui grid">
                                <div class="four wide column">
                                    <div class="ui radio checkbox">
                                        <input type="radio" name="representative" checked="checked" class="hidden">
                                        <label>
                                            <@spring.messageText "team.representative" "Representative" />
                                        </label>
                                    </div>
                                </div>
                                <div class="ten wide column">
                                    <select class="ui fluid selection dropdown" name="team">
                                        <option value="">
                                            <@spring.messageText "team.select" "Select Team" />
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="fields" id="box_position">
                        <div class="four wide required field">
                            <label>
                                <@spring.messageText "position" "Position"/>
                            </label>
                        </div>
                        <div class="twelve wide field">
                            <select class="ui fluid selection dropdown" name="position" id="sel_position">
                                <option value="">
                                    <@spring.messageText "position.select" "Select Position" />
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="fields">
                        <div class="four wide required field">
                            <label>
                                <@spring.messageText "user.name" "Name"/>
                            </label>
                        </div>
                        <div class="twelve wide field">
                            <input name="name" type="text" />
                        </div>
                    </div>

                    <div class="fields">
                        <div class="four wide required field">
                            <label>
                                <@spring.messageText "user.mobilephone" "Mobilephone"/>
                            </label>
                        </div>
                        <div class="twelve wide field">
                            <input name="mobilephone" type="text" />
                        </div>
                    </div>

                    <div class="fields">
                        <div class="four wide required field">
                            <label>
                                <@spring.messageText "user.telephone" "Telephone"/>
                            </label>
                        </div>
                        <div class="twelve wide field">
                            <input name="telephone" type="text" />
                        </div>
                    </div>

                    <div class="fields">
                        <div class="four wide required field">
                            <label>
                                <@spring.messageText "user.email" "Email"/>
                            </label>
                        </div>
                        <div class="twelve wide field">
                            <input name="email" type="text" />
                        </div>
                    </div>

                    <div class="fields">
                        <div class="four wide required field">
                            <label>
                                <@spring.messageText "user.avatar" "Avatar"/>
                            </label>
                        </div>
                        <div class="twelve wide field">
                            <div class="ui grid">
                                <div class="eleven wide column">
                                    <img class="ui fluid image" src="" alt="avatar image" id="img_avatar" />
                                </div>
                                <div class="three wide column">
                                    <label for="avatar">
                                        <i class="huge cloud upload icon"></i>
                                    </label>
                                    <input name="avatar" id="avatar" type="file" style="display: none;"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="ui error message"></div>

                    <button class="fluid ui blue button" type="submit">
                        <@spring.messageText "button.form.register" "Submit registration" />
                    </button>
                </form>
            </div>
        </div>

        <script type="text/template" id="_select_team">
            <% _.each(data, function(item, index) { %>
                <option value="<%= item.id %>">
                    <%= item.name %>
                </option>
            <% }) %>
        </script>

        <script type="text/template" id="_select_position">
            <% _.each(data, function(item, index) { %>
                <option value="<%= item.id %>">
                    <%= item.name %>
                </option>
            <% }) %>
        </script>

        <script type="text/template" id="_box_team">
            <div class="fields" data-boxid="<%= Date.now() %>">
                <div class="three wide field">

                </div>

                <div class="thirteen wide field">
                    <div class="ui grid">
                        <div class="four wide column">
                            <div class="ui radio checkbox">
                                <input type="radio" name="representative" class="hidden">
                                <label>
                                    <@spring.messageText "team.representative" "Representative" />
                                </label>
                            </div>
                        </div>
                        <div class="ten wide column">
                            <select class="ui fluid selection dropdown" name="team">
                                <option value="">
                                    <@spring.messageText "team.select" "Select Team" />
                                </option>
                                <% _.each(data, function(item, index) { %>
                                    <option value="<%= item.id %>">
                                        <%= item.name %>
                                    </option>
                                <% }) %>
                            </select>
                        </div>
                        <div class="two wide column">
                            <button class="ui right floated icon button btn_remove_team">
                                <i class="remove icon"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </script>

        <script type="text/javascript">

            var teams       = {};
            var positions   = {};
            var index       = 1;

            function rebind() {
                $('.checkbox').checkbox();
                $('.dropdown').dropdown();
                validateForm($('.ajaxform'));
            }

            function renderSelectTeam(data) {
                var source      = $('#_select_team').html();
                var template    = _.template(source);
                var html        = template(data);
                $('[name=team').append(html);
            }

            function renderSelectPosition(data) {
                var source      = $('#_select_position').html();
                var template    = _.template(source);
                var html        = template(data);
                $('#sel_position').append(html);
            }

            function removeBoxTeam(boxID) {
                if (index == 1) {
                    // display minimum error
                    return;
                }
                $('[data-boxid=' + boxID + ']').remove();
                index--;

                // not check any representative
                if ($('[name=representative]:checked').length == 0) {
                    $('[name=representative]').first().parent().checkbox('check');
                }
            }

            function addBoxTeam() {
                if (index == 5) {
                    // display maximum error
                    return;
                }
                // append team box
                index++;
                var source      = $('#_box_team').html();
                var template    = _.template(source);
                var html        = template({
                    data    : teams.data
                });

                $('#box_position').before(html);
                rebind();
            }

            function validateForm($form) {
                $form
                    .form({
                        fields: {
                            team: {
                                identifier: 'team',
                                rules: [
                                            {
                                                type   : 'empty',
                                                prompt : message.get('form_error_empty')
                                            }
                                ]
                            },
                            position: {
                                identifier: 'position',
                                rules: [
                                            {
                                                type   : 'empty',
                                                prompt : message.get('form_error_empty')
                                            }
                                ]
                            },
                            name: {
                                identifier: 'name',
                                rules: [
                                            {
                                                type   : 'empty',
                                                prompt : message.get('form_error_empty')
                                            }
                                ]
                            },
                            mobilephone: {
                                identifier: 'mobilephone',
                                rules: [
                                            {
                                                type   : 'empty',
                                                prompt : message.get('form_error_empty')
                                            }
                                        ]
                            },
                            telephone: {
                                identifier: 'telephone',
                                rules: [
                                            {
                                                type   : 'empty',
                                                prompt : message.get('form_error_empty')
                                            }
                                ]
                            },
                            email: {
                                identifier: 'email',
                                rules: [
                                            {
                                                type   : 'email',
                                                prompt : message.get('form_error_empty')
                                            }
                                ]
                            },
                            avatar: {
                                identifier: 'avatar',
                                rules: [
                                            {
                                                type   : 'empty',
                                                prompt : message.get('form_error_empty')
                                            }
                                ]
                            }
                        }
                    });
            }

            $(function() {

                // init data
                $.ajax({
                    url     : '/admin/api/auth/users/init',
                    method  : 'get'
                }).then(function(res) {

                    teams       = {data: res.data.teams};
                    positions   = {data: res.data.positions};

                    renderSelectTeam(teams);
                    renderSelectPosition(positions);

                    var $form = $('.ajaxform');
                    validateForm($form);

                    $form.on('click', '#btn_add_team', function(evt) {
                        evt.preventDefault();
                        addBoxTeam();
                    });

                    $form.on('change', 'select[name=team]', function(evt) {
                        var teamID = $(this).val();
                        $(this).closest('.ui.grid').find('input[name=representative]').attr('data-team', teamID);
                    });

                    $form.on('click', '.btn_remove_team', function(evt) {
                        evt.preventDefault();
                        var boxID = $(this).closest('div.fields').attr('data-boxid');
                        removeBoxTeam(boxID);
                    });

                    $('[type=file]').on('change', function(evt) {
                        var src = URL.createObjectURL(evt.target.files[0]);
                        $('#img_avatar').attr('src', src);
                    });

                    $form.on('submit', function(evt) {
                        if ($form.form('is valid')) {
                            evt.preventDefault();

                            var fd = new FormData();
                            fd.append('ids'         , $.map( $('select[name=team]'), function(elem) {
                                                        if (elem.value != '') return elem.value;
                                                      }));
                            fd.append('teamFk'      , $('[name=representative]:checked').attr('data-team'));
                            fd.append('positionFk'  , $('select[name=position]').val());
                            fd.append('name'        , $('input[name=name]').val());
                            fd.append('mobilephone' , $('input[name=mobilephone]').val());
                            fd.append('telephone'   , $('input[name=telephone]').val());
                            fd.append('email'       , $('input[name=email]').val());
                            fd.append('files'       , $('[name=avatar]')[0].files[0]);

                            $.ajax({
                                url: _API_URL,
                                method: 'post',
                                processData: false,
                                contentType: false,
                                data: fd

                            }).then(function(res) {
                                window.location = '/admin/www/auth/users';
                            }, function(err) {
                                $form.addClass('error');
                                $form.find('.ui.error.message').text(message.get('form_error'));
                            });
                        }
                    });

                }, function(err) {
                    console.error(err);
                });

            });
        </script>
    </div>
</@layout.layout>
