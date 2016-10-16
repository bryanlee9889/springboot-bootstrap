<#macro layout>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="description" content="LET Admin">
            <meta name="author" content="minhdl9889@gmail.com">

            <title>LET</title>

            <!-- Favicon-->
            <link rel="shortcut icon" href="/assets/img/favicon.ico" type="image/x-icon" />

            <!-- Vendor CSS (GLOBAL MANDATORY STYLES)-->
            <link rel="stylesheet" type="text/css" href="/assets/bower_components/semantic/dist/semantic.min.css" />
            <link rel="stylesheet" type="text/css" href="/assets/style/style.css" />

            <script type="text/javascript" src="/assets/bower_components/jquery/dist/jquery.min.js"></script>
            <script type="text/javascript" src="/assets/bower_components/js-cookie/src/js.cookie.js"></script>
            <script type="text/javascript" src="/assets/bower_components/semantic/dist/semantic.min.js"></script>
            <script type="text/javascript" src="/assets/bower_components/underscore/underscore-min.js"></script>

            <script type="text/javascript" src="/assets/script/messages.js"></script>
            <script type="text/javascript" src="/assets/script/utils.js"></script>
            <script type="text/javascript" src="/assets/script/renders.js"></script>
			<script type="text/javascript" src="/assets/script/configs.js"></script>

        </head>
        <body>
        
        	<div class="ui top attached menu">
    			<#include "/webs/admin/fragments/main_menu_top.ftl" />
    		</div>
    		
    		<div class="ui bottom attached segment pushable">
    			
    			<div class="ui left thin vertical sidebar menu">
    				<#include "/webs/admin/fragments/main_menu_left.ftl" />
    			</div>
    			<div class="pusher">
    				
    				<!-- LOADING -->
	                <div class="ui inverted dimmer" id="loading">
						<div class="ui loader"></div>
					</div>
    				
    				<#nested/>
    				
    				
    			</div>
    			
    			<footer class="ui inverted vertical footer segment">
    				<#include "/webs/admin/fragments/main_footer.ftl" />
				</footer>
    			
    		</div>

        </body>
    </html>
</#macro>