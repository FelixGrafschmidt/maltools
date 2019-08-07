<template>
	<section>
		<nav
			class="pagination is-centered is-rounded"
			role="navigation"
			aria-label="pagination"
		>
			<a
				class="pagination-previous"
				:class="{ disabled: this.currentPage === 1 }"
				>Previous</a
			>
			<a
				class="pagination-next"
				:class="{ disabled: this.currentPage * 10 >= data.length }"
				>Next page</a
			>
			<ul class="pagination-list">
				<!-- <li>
					<a class="pagination-link" aria-label="Goto page 1">1</a>
				</li>
				<li>
					<span class="pagination-ellipsis">&hellip;</span>
				</li> -->
				<li v-for="sliceCount in data.length" :key="sliceCount">
					<a
						class="pagination-link"
						:aria-current="sliceCount === currentPage ? 'page' : undefined"
						:class="{ 'is-current': sliceCount === currentPage }"
						:aria-label="'Goto page ' + sliceCount"
					>
						{{ sliceCount }}
					</a>
				</li>
				<!-- <li><span class="pagination-ellipsis">&hellip;</span></li>
				<li>
					<a class="pagination-link" aria-label="Goto page 86">86</a>
				</li> -->
			</ul>
		</nav>
	</section>
</template>

<script>
	import Vue from "vue";

	export default {
		props: {
			data: Array
		},
		data() {
			return {
				currentPage: 1
			};
		},
		methods: {
			updatePage(page) {
				this.currentPage = page;
				this.$emit("update:page", page);
			}
		}
	};
</script>

<style lang="scss" scoped>
</style>
