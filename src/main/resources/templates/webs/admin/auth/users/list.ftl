<#import "/spring.ftl" as spring />
<#import "/webs/admin/layouts/main.ftl" as layout />

<@layout.layout>

<div class="content">

	<h2 class="ui dividing header">
		<i class="icon"></i>
		<div class="content">
			<@spring.messageText "menu.title.user.list" "Member List" />
		</div>
	</h2>

	<div class="ui four column centered grid">
		<div class="column">
			<div class="ui action input">
			  <input type="search" placeholder="<@spring.messageText "label.search" "Search" />" id="inpSearch" />
			  <select class="ui compact selection dropdown" id="selSearch">
			    <option value="team">
                    <@spring.messageText "team" "Team" />
                </option>
			    <option value="position">
                    <@spring.messageText "position" "Position" />
                </option>
			    <option value="name">
			    	<@spring.messageText "user.name" "Name" />
			    </option>
			    <option value="mobilephone">
			    	<@spring.messageText "user.mobilephone" "Mobile phone" />
		    	</option>
		    	<option value="telephone">
                    <@spring.messageText "user.telephone" "Telephone" />
                </option>
                <option value="email">
                    <@spring.messageText "user.email" "Email" />
                </option>
                <option value="userid">
                    <@spring.messageText "user.userid" "ID" />
                </option>
			  </select>
			</div>
		</div>
	</div>

    <table class="ui blue sortable celled table">
        <thead>
	        <tr>
	            <th class="one wide btn_sort sorted" data-sort="id" data-direction="DESC">
	                <@spring.messageText "label.no" "No." />
	            </th>
	            <th class="one wide btn_sort sorted" data-sort="t_name" data-direction="DESC">
                    <@spring.messageText "team" "Team" />
                </th>
	            <th class="one wide btn_sort sorted" data-sort="p_name" data-direction="DESC">
	                <@spring.messageText "position" "Position" />
	            </th>
	            <th class="two wide btn_sort sorted" data-sort="name" data-direction="DESC">
	            	<@spring.messageText "user.name" "Name" />
	            </th>
	            <th class="three wide btn_sort sorted" data-sort="mobilephone" data-direction="DESC">
                    <@spring.messageText "user.mobilephone" "Mobile phone" />
                </th>
                <th class="two wide btn_sort sorted" data-sort="telephone" data-direction="DESC">
                    <@spring.messageText "user.telephone" "Telephone" />
                </th>
                <th class="three wide btn_sort sorted" data-sort="email" data-direction="DESC">
                    <@spring.messageText "user.email" "Email" />
                </th>
                <th class="two wide btn_sort sorted" data-sort="userid" data-direction="DESC">
                    <@spring.messageText "user.userid" "ID" />
                </th>
	            <th class="two wide">
                    <@spring.messageText "user.avatar" "Avatar" />
                </th>
	        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
        <tfoot id="tfoot">
	        <tr>
	            <th colspan="8" id="pagination">

	            </th>
	            <th>
                    <a href="/admin/www/auth/users/add">
                        <div class="ui right floated small primary labeled icon button">
                          <i class="user icon"></i><@spring.messageText "user.registration" "User Register" />
                        </div>
                    </a>
                </th>
	        </tr>
        </tfoot>
    </table>

    <script type="text/template" id="_tbody">
        <% _.each(data.data, function(item, index) { %>
	        <tr data-id="<%= item.id %>">
	            <td>
	            	<% if (data.direction == 'DESC') { %>
                        <%= data.totalElement - index - data.pageIndex * data.pageSize %>
                    <% } else {%>
                        <%= index + data.pageIndex * data.pageSize + 1 %>
                    <% } %>
	            </td>
	            <td>
                    <%= item.team.name %>
                </td>
	            <td>
	            	<%= item.position.name %>
	            </td>
	            <td>
	                <a href="/admin/www/auth/users/<%= item.id %>">
	            	    <%= item.name %>
	            	</a>
	            </td>
	            <td>
	            	<%= item.mobilephone %>
	            </td>
	            <td>
	            	<%= item.telephone %>
	            </td>
	            <td>
	            	<%= item.email %>
	            </td>
	            <td>
	            	<% if (typeof(item.userid) != 'undefined') { %>
	            		<%= item.userid %>
	            	<% } else { %>
	            		---
	            	<% } %>
	            </td>
	            <td>
	            	<div class="ui small image">
	            		<img src="<%= item.avatar %>" alt="avatar" width="100" height="100" />
	            	</div>
	            </td>
	        </tr>
        <% }); %>
    </script>

    <script type="text/javascript">

        render.renderTable(_API_URL);
    	
    </script>
</div>

</@layout.layout>
