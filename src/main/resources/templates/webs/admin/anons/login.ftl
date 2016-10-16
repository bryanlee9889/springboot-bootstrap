<#import "/spring.ftl" as spring />
<#import "/webs/admin/layouts/empty.ftl" as layout />

<@layout.layout>

    <div class="ui middle aligned center aligned grid" style="height: 100%">
        <div class="row">
            <div class="fourteen wide mobile ten wide tablet six wide computer column">
                <form class="ui form ajaxform" method="post" accept-charset="application/json; charset=UTF8">
                    <div class="field">
                        <img class="ui centered small image" src="/assets/img/logo.png" />
                    </div>
                    <div class="field">
                        <label>
                            <@spring.messageText "label.username" "Username"/>
                        </label>
                        <input name="username" type="text" />
                    </div>
                    <div class="field">
                        <label>
                            <@spring.messageText "label.password" "Password" />
                        </label>
                        <input name="password" type="password" />
                    </div>

                    <div class="ui error message"></div>

                    <button class="fluid ui blue button" type="submit">
                        <@spring.messageText "button.login" "Login" />
                    </button>
                </form>
            </div>
        </div>
    </div>
    
    <#include "/webs/admin/fragments/empty_footer.ftl" />

    <script type="text/javascript">
    	$(function() {
    
	    	var $form = $('.ajaxform');
	    	$form.attr('action', location.pathname.replace('www', 'api'));
	
	        $form
	          .form({
	            fields: {
	              username: {
	                identifier: 'username',
	                rules: [
	                  {
	                    type   : 'empty',
	                    prompt : message.get('form_error_empty')
	                  }
	                ]
	              },
	              password: {
	                identifier: 'password',
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

</@layout.layout>