<#import "/spring.ftl" as spring />
<#import "/webs/admin/layouts/main.ftl" as layout />

<@layout.layout>

<div class="content">

	<h2 class="ui dividing header">
		<i class="icon"></i>
		<div class="content">
			<@spring.messageText "menu.title.team.list.edit" "Team Edit" />
		</div>
	</h2>
	
	<div class="ui three column centered grid">
		<div class="column">
			<form class="ui form ajaxform" method="put" accept-charset="application/json; charset=UTF8">
                
            </form>
		</div>
	</div>
	
	<script type="text/template" id="_form">

		<div class="fields">
        	<div class="four wide required field">
                <label>
                    <@spring.messageText "team.name" "Name"/>
                </label>
        	</div>
        	<div class="twelve wide field">
        		<input name="name" type="text" value="<%= data.name %>" />
        	</div>
        </div>
        

        <div class="ui error message"></div>

        <button class="fluid ui blue button" type="submit">
            <@spring.messageText "button.form.edit" "Submit editing" />
        </button>
	</script>
    
    <script type="text/javascript">

        $(function() {
    
	    	var $form = $('.ajaxform');
	    	$form.attr('action', _API_URL);

	    	function formValidation() {
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
            }

	    	render.renderForm(_API_URL, formValidation);
	
	        utils.submitJsonForm($form, function(res) {
	            window.location = '/admin/www/auth/teams';
	        });
        
        });
    </script>
	
</div>

</@layout.layout>
