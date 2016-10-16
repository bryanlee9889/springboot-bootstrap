<#import "/spring.ftl" as spring />
<#import "/webs/admin/layouts/main.ftl" as layout />

<@layout.layout>

<div class="content">

	<h2 class="ui dividing header">
		<i class="icon"></i>
		<div class="content">
			<@spring.messageText "menu.title.team.list" "Team list" />
		</div>
	</h2>

	<div class="ui four column centered grid">
		<div class="column">
			<div class="ui action input">
			  <input type="search" placeholder="<@spring.messageText "label.search" "Search" />" id="inpSearch" />
			  <select class="ui compact selection dropdown" id="selSearch">
			    <option value="name">
			    	<@spring.messageText "team.name" "Name" />
			    </option>
			    <option value="status">
			    	<@spring.messageText "team.status" "Status" />
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
	            <th class="thirteen wide btn_sort sorted" data-sort="name" data-direction="DESC">
	                <@spring.messageText "team.name" "Name" />
	            </th>
	            <th class="one wide btn_sort sorted" data-sort="status" data-direction="DESC">
	            	<@spring.messageText "team.status" "Status" />
	            </th>
	            <th class="one wide">
	            	<@spring.messageText "button.action" "Action" />
	            </th>
	        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
        <tfoot id="tfoot">
	        <tr>
	            <th colspan="3" id="pagination">
	
	            </th>
	            <th>
	            	<a href="/admin/www/auth/teams/add">
		            	<div class="ui right floated small primary labeled icon button">
				          <i class="user icon"></i><@spring.messageText "team.registration" "Team Register" />
				        </div>
			        </a>
	            </th>
	        </tr>
        </tfoot>
    </table>
	
	<div class="ui basic modal" id="popup_status_error">
	  	<i class="close icon"></i>
	  	<div class="header">
	    	<@spring.messageText "popup.title" "LET" />
	  	</div>
	  	<div class="image content">
	    	<div class="image">
	      		<i class="warning sign icon"></i>
	    	</div>
	    	<div class="description">
	      		<p>
	      			<@spring.messageText "popup.team.changestatus.error.content" "Error" />
	      		</p>
	    	</div>
	  	</div>
	</div>
	
	<div class="ui basic modal" id="popup_edit_error">
	  	<i class="close icon"></i>
	  	<div class="header">
	    	<@spring.messageText "popup.title" "LET" />
	  	</div>
	  	<div class="image content">
	    	<div class="image">
	      		<i class="warning sign icon"></i>
	    	</div>
	    	<div class="description">
	      		<p>
	      			<@spring.messageText "popup.team.edit.error.content" "Error" />
	      		</p>
	    	</div>
	  	</div>
	</div>

    <script type="text/template" id="_tbody">
        <% _.each(data.data, function(item, index) { %>
	        <tr data-id="<%= item.id %>" data-status="<%= item.status %>">
	            <td>
	            	<% if (data.direction == 'DESC') { %>
	            		<%= data.totalElement - index - data.pageIndex * data.pageSize %>
	            	<% } else {%>
	            		<%= index + data.pageIndex * data.pageSize + 1 %>
	            	<% } %>
	            </td>
	            <td>
	            	<a href="/admin/www/auth/teams/<%= item.id %>/members">
	                	<%= item.name %>
	                </a>
	            </td>
	            <td>
	                <% if (item.status == 1) { %>
	                <div class="ui slider checkbox">
	                    <input type="checkbox" name="status" checked="checked" />
	                    <label>
	                    	<@spring.messageText "status.use" "Use" />
	                    </label>
	                </div>
	                <% } else { %>
	                <div class="ui slider checkbox">
	                    <input type="checkbox" name="status" />
	                    <label>
	                    	<@spring.messageText "status.unused" "Unused" />
	                    </label>
	                </div>
	                <% } %>
	            </td>
	            <td>
	                <button class="ui button btn_edit">
	                	<@spring.messageText "button.edit" "Edit" />
	                </button>
	            </td>
	        </tr>
        <% }); %>
    </script>

    <script type="text/javascript">

        render.renderTable(_API_URL);
    	
    	$('#tbody').on('click', '.ui.slider.checkbox', function(evt) {
    	
    		var $this 	= $(this);
    		var $tr 	= $this.closest('tr');
    		var id 		= $tr.attr('data-id');
    		var status 	= $tr.attr('data-status') == 1 ? 2 : 1;
    		
    		// check team has member
    		$.ajax({
    			url		: _API_URL + '/' + id + '/nothasmember',
    			method	: 'get'
    		}).then(function(res) {
    			
    			// change status
    			$.ajax({
    				url: _API_URL + '/' + id,
    				method: 'put',
    				data: JSON.stringify({
    					status: status
    				})
    			}).then(function(res) {
    				$this.checkbox(status == 1 ? 'check' : 'uncheck');
    				$tr.attr('data-status', status);
    				$this.children('label').text(status == 1 ? '<@spring.messageText "status.use" "Use" />' : '<@spring.messageText "status.unused" "Unused" />');
    			}, function(err) {
    				$('#popup_status_error').modal('show');
    			});
    			
    		}, function(err) {
    			$('#popup_status_error').modal('show');
    		});

    		return false;
    	});
    	
    	$('#tbody').on('click', '.btn_edit', function(evt) {
    		
    		var $tr = $(this).closest('tr');
    		var id 	= $tr.attr('data-id');
    		
    		// check team has member
    		$.ajax({
    			url		: _API_URL + '/' + id + '/nothasmember',
    			method	: 'get'
    		}).then(function(res) {
    			
    			// move to detail
    			window.location = '/admin/www/auth/teams/' + id;
    			
    		}, function(err) {
    			$('#popup_edit_error').modal('show');
    		});
    		
    		return false;
    	});
    	
    </script>
</div>

</@layout.layout>
