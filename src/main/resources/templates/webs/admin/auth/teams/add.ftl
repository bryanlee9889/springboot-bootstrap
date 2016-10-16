<#import "/spring.ftl" as spring />
<#import "/webs/admin/layouts/main.ftl" as layout />

<@layout.layout>

<div class="content">

	<h2 class="ui dividing header">
		<i class="icon"></i>
		<div class="content">
			<@spring.messageText "menu.title.team.list.add" "Team Add" />
		</div>
	</h2>
	
	<div class="ui three column centered grid">
		<div class="column">
			<form class="ui form ajaxform" method="post" accept-charset="application/json; charset=UTF8">
                <div class="fields">
                    <div class="four wide required field">
                        <label>
                            <@spring.messageText "team.name" "Name"/>
                        </label>
                    </div>
                    <div class="twelve wide field">
                        <input name="name" type="text" />
                    </div>
                </div>

                <div class="ui error message"></div>

                <button class="fluid ui blue button" type="submit">
                    <@spring.messageText "button.form.register" "Submit registration" />
                </button>
            </form>
		</div>
	</div>
    
    <script type="text/javascript">

        $(function() {
    
	    	var $form = $('.ajaxform');
	    	$form.attr('action', _API_URL);

            $form
              .form({
                fields: {
                  name: {
                    identifier: 'name',
                    rules: [
                      {
                        type   : 'empty',
                        prompt : message.get('form_error_empty')
                      }
                    ]
                  }
                }
              })
            ;

	        utils.submitJsonForm($form, function(res) {
	            window.location = '/admin/www/auth/teams';
	        });
        
        });
    </script>
	
</div>

</@layout.layout>
