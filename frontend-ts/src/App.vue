<template>
	<div id="app">
		<nav class="navbar" role="navigation" aria-label="main navigation">
			<div class="navbar-brand">
				<h1 class="navbar-item has-text-weight-bold">
					MyAnimeList Tools
				</h1>

				<a
					role="button"
					class="navbar-burger burger"
					aria-label="menu"
					aria-expanded="false"
					data-target="navbarBasicExample"
				>
					<span aria-hidden="true"></span>
					<span aria-hidden="true"></span>
					<span aria-hidden="true"></span>
				</a>
			</div>

			<div id="navbarBasicExample" class="navbar-menu">
				<div class="navbar-start">
					<a class="navbar-item">
						<router-link to="/">Home</router-link>
					</a>

					<a class="navbar-item">
						<router-link to="/anime-list">Anime List</router-link>
					</a>
				</div>
			</div>
		</nav>
		<keep-alive include="anime-list">
			<router-view />
		</keep-alive>
	</div>
</template>

<style lang="scss">
</style>
