<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Model.Utente"%>
<%
	Utente utente = (Utente) request.getAttribute("utente");
	String username = utente.getUsername();
	String nome = utente.getNome();
	String cognome = utente.getCognome();
	String tipologia = utente.getTipologia();
	int exp = utente.getEsperienza();
	int trofei=exp/100;
	String actived = (String) request.getAttribute("active");
%>

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar" style="cursor: pointer;" onclick="/oop17/Home">
			<div class="profile-userpic">
				<img src="/oop17/fonts/user.png" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"><%=username%></div>
				<div class="profile-usertitle-status">
					<span class="indicator label-success"></span>Online
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" style="cursor:default;" disabled placeholder="<%=tipologia%>">
			</div>
		</form>
		<ul class="nav menu">
			<li <%if(actived.equals("Home")){out.print(" class=\"active\"");}%>><a href="/oop17/Home"><em
					class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li <%if(actived.equals("Giochi")){out.print("class=\" active\"");}%>>
				<a href="/oop17/Giochi"><em class="fa fa-play-circle">&nbsp;</em>Giochi</a>
			</li>
			<li <%if(actived.equals("Profilo")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/Profilo"><em class="fa fa-address-card">&nbsp;</em>Profilo</a>
			</li>
			<li <%if(actived.equals("Timeline")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/Timeline"><em class="fa fa-signal">&nbsp;</em>Timeline</a>
			</li>
			<%if(!tipologia.equals("Utente")){%>
			<li <%if(actived.equals("Gestione Utenti")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/GestioneUtenti"><em class="fa fa-users">&nbsp;</em>Gestione Utenti</a>
			</li>
			<%}%>
			<%if(tipologia.equals("Admin")){%>
			<li <%if(actived.equals("Gestione Moderatori")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/gestioneModeratore"><em class="fa fa-users">&nbsp;</em>Gestione Moderatori</a>
			</li>
			<%}%>
			<%if(tipologia.equals("Admin")){%>
			<li <%if(actived.equals("Rimozione Account")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/RimozioneAccount"><em class="fa fa-user-times">&nbsp;</em>Rimozione Account</a>
			</li>
			<%}%>
			<%if(!tipologia.equals("Utente")){%>
			<li <%if(actived.equals("Gestione Recensioni")){out.print(" class=\"active\"");}%>>
				<a href="/oop17/gestioneRecensioni"><em class="fa fa-list">&nbsp;</em>Gestione Recensioni</a>
			</li>
			<%}%>
			<%if(tipologia.equals("Admin")){%>
			<li class="parent <%if(actived.equals("Gestione Giochi")){out.print(" active");}%>"><a data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-navicon">&nbsp;</em> Gestione Giochi <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="fa fa-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="/oop17/InserimentoGioco">
						<span class="fa fa-arrow-right">&nbsp;</span> Inserisci Gioco
					</a></li>
					<li><a class="" href="/oop17/ModificaGioco">
						<span class="fa fa-arrow-right">&nbsp;</span> Rimozione Gioco
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