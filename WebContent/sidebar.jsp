<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="/oop17/fonts/user.png" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"><%=request.getAttribute("username")%></div>
				<div class="profile-usertitle-status">
					<span class="indicator label-success"></span>Online
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" disabled placeholder="<%=request.getAttribute("tipologia")%>">
			</div>
		</form>
		<ul class="nav menu">
			<li <%if(request.getAttribute("active").equals("Home")){out.print(" class=\"active\"");}%>><a href="/oop17/Home"><em
					class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li <%if(request.getAttribute("active").equals("Giochi")){out.print("class=\" active\"");}%>>
				<a href="/oop17/Giochi"><em class="fa fa-play-circle">&nbsp;</em>Giochi</a>
			</li>
			<li <%if(request.getAttribute("active").equals("Profilo")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/Profilo"><em class="fa fa-address-card">&nbsp;</em>Profilo</a>
			</li>
			<li <%if(request.getAttribute("active").equals("Timeline")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/Timeline"><em class="fa fa-signal">&nbsp;</em>Timeline</a>
			</li>
			<%if(!request.getAttribute("tipologia").equals("Utente")){%>
			<li <%if(request.getAttribute("active").equals("Gestione Utenti")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/GestioneUtenti"><em class="fa fa-users">&nbsp;</em>Gestione Utenti</a>
			</li>
			<%}%>
			<%if(!request.getAttribute("tipologia").equals("Utente")){%>
			<li <%if(request.getAttribute("active").equals("Gestione Moderatori")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/GModeratori"><em class="fa fa-users">&nbsp;</em>Gestione Moderatori</a>
			</li>
			<%}%>
			<%if(!request.getAttribute("tipologia").equals("Utente")){%>
			<li <%if(request.getAttribute("active").equals("Gestione Recensioni")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/gestioneRecensioni"><em class="fa fa-list">&nbsp;</em>Gestione Recensioni</a>
			</li>
			<%}%>
			<%if(request.getAttribute("tipologia").equals("Admin")){%>
			<li class="parent <%if(request.getAttribute("active").equals("Gestione Giochi")){out.print(" active");}%>"><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> Gestione Giochi <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="/oop17/InserimentoGioco">
						<span class="fa fa-arrow-right">&nbsp;</span> Inserisci Gioco
					</a></li>
					<li><a class="" href="/oop17/ModificaGioco">
						<span class="fa fa-arrow-right">&nbsp;</span> Modifica Gioco
					</a></li>
				</ul>
			</li>
			<%}%>
			<li>
				<a href="/oop17/Logout"><em class="fa fa-power-off">&nbsp;</em>Logout</a>
			</li>
		</ul>
	</div>
	<!--/.sidebar-->