<#import "/spring.ftl" as spring />

    <div class="ui vertical accordion menu">

        <div class="item">
        	<a class="title">
        		<i class="dropdown icon"></i>
        		<@spring.messageText "menu.title.team" "Team Management" />
        	</a>
        	<div class="content">
        		<div class="ui form">
        			<div class="grouped fields">
        				<div class="field">
        					<a class="item" href="/admin/www/auth/teams">
			                    <@spring.messageText "menu.title.team.list" "Team List" />
			                </a>
        				</div>
        				<div class="field">
        					<a class="item">
			                    <@spring.messageText "menu.title.team.history" "Team History" />
			                </a>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>

        <div class="item">
            <a class="title">
                <i class="dropdown icon"></i>
                <@spring.messageText "menu.title.position" "Position Management" />
            </a>
            <div class="content">
                <div class="ui form">
                    <div class="grouped fields">
                        <div class="field">
                            <a class="item" href="/admin/www/auth/positions">
                                <@spring.messageText "menu.title.position.list" "Position List" />
                            </a>
                        </div>
                        <div class="field">
                            <a class="item">
                                <@spring.messageText "menu.title.position.history" "Position History" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="item">
            <a class="title">
                <i class="dropdown icon"></i>
                <@spring.messageText "menu.title.user" "User Management" />
            </a>
            <div class="content">
                <div class="ui form">
                    <div class="grouped fields">
                        <div class="field">
                            <a class="item" href="/admin/www/auth/users">
                                <@spring.messageText "menu.title.user.list" "User List" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
