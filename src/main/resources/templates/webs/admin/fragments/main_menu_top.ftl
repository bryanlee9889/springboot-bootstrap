<#import "/spring.ftl" as spring/>

	<a class="item logo">
    	<i class="sidebar icon"></i>
    	<@spring.messageText "menu" "Menu" />
  	</a>
	<div class="item">
		<img class="ui centered mini image" src="/assets/img/logo.png"/>
	</div>
	<div class="right menu">
        <a class="ui item" onclick="showLogout();">
            <@spring.messageText "button.logout" "Logout" />
        </a>
    </div>
    
    <div class="ui basic modal" id="popup_logout">
        <i class="close icon"></i>
        <div class="header">
            <@spring.messageText "popup.logout.title" "Logout" />
        </div>
        <div class="image content">
            <div class="image">
                <i class="archive icon"></i>
            </div>
            <div class="description">
                <p>
                    <@spring.messageText "popup.logout.content" "Do you want to logout?" />
                </p>
            </div>
        </div>
        <div class="actions">
            <div class="two fluid ui inverted buttons">
                <div class="ui ok green basic inverted button" onclick="logout();">
                    <i class="checkmark icon"></i>
                    <@spring.messageText "button.yes" "Yes" />

                </div>
                <div class="ui cancel red basic inverted button">
                    <i class="remove icon"></i>
                    <@spring.messageText "button.no" "No" />
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" th:inline="javascript">
        function showLogout() {
            $('#popup_logout').modal('show');
        }

        function logout() {
            // TODO call api logout
            // callback redirect login
            window.location = '/admin/www/anons/login';
        }
    </script>
